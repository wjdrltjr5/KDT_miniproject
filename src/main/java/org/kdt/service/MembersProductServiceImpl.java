package org.kdt.service;

import org.apache.ibatis.session.SqlSession;
import org.kdt.Config;
import org.kdt.dao.MemberDAO;
import org.kdt.dao.MembersProductDAO;
import org.kdt.dao.ProductDAO;
import org.kdt.dto.MemberDTO;
import org.kdt.dto.MembersProductDTO;
import org.kdt.dto.ProductDTO;
import org.kdt.exception.NoMoneyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MembersProductServiceImpl implements MembersProductService{
    private final MembersProductDAO membersProductDao;
    private final ProductDAO productDao;
    private final MemberDAO memberDAO;

    private Logger log = LoggerFactory.getLogger(this.getClass());
    public MembersProductServiceImpl(MembersProductDAO membersProductDao, ProductDAO productDao,MemberDAO memberDAO) {
        this.membersProductDao = membersProductDao;
        this.productDao = productDao;
        this.memberDAO = memberDAO;
    }
    @Override
    public List<MembersProductDTO> findByStatusHold(){
        try(SqlSession session = Config.getConnection()){
            return membersProductDao.findByStatusHold(session);
        }
    }

    @Override
    public int requestOrderFailure(String orderNo) {
        try(SqlSession session = Config.getConnection()){
        int result = membersProductDao.requestOrderFailure(session, orderNo);
        MembersProductDTO byOrderNo = membersProductDao.findByOrderNo(session, orderNo);
        ProductDTO byNo = productDao.findByNo(session,byOrderNo.getProduct_no());
        int returnMoney = byOrderNo.getProduct_quantity() * byNo.getProduct_price();
        MemberDTO memberDTO = memberDAO.findByNo(session, byOrderNo.getMember_no());
        memberDTO.setMember_balance(memberDTO.getMember_balance() + returnMoney);
        memberDAO.updateBalance(session,memberDTO);
        session.commit();
        return result;
        }
    }

    @Override
    public int requestOrderPermit(String orderNo){
        try(SqlSession session = Config.getConnection()){
            if(checkQuantity(session,orderNo)){
                int result;
                if(checkPermitProduct(orderNo)){
                    MembersProductDTO byOrderNo = membersProductDao.findByOrderNoAndStatusHold(session,orderNo);
                    MembersProductDTO dto = membersProductDao.findByPermitAndProductNoAndMemberNo(session, byOrderNo);
                    dto.setProduct_quantity(dto.getProduct_quantity() + byOrderNo.getProduct_quantity());
                    result = membersProductDao.updateStock(session, dto);
                    membersProductDao.deleteByOrderNo(session,orderNo);
                    session.commit();
                }else {
                    result = membersProductDao.requestOrderPermit(session, orderNo);
                    session.commit();

                }
                return result;
            }
            return -1;
        }
    }
    @Override
    public MembersProductDTO findByNo(String orderNo) {
        try(SqlSession session = Config.getConnection()){
            return membersProductDao.findByOrderNo(session, orderNo);
        }
    }

    private boolean checkQuantity(SqlSession session,String orderNo){
            MembersProductDTO request = membersProductDao.findByOrderNo(session, orderNo);
            if(request == null) return  false;
            ProductDTO productDto = productDao.findByNo(session,request.getProduct_no());
            if(request.getProduct_quantity() > productDto.getProduct_quantity()){
                return false;
            }else{
                deductStockQuantities(session,productDto,request);
                return true;
            }
    }
    private int deductStockQuantities(SqlSession session,ProductDTO productDTO, MembersProductDTO membersProductDTO){
        return productDao.deductStockQuanties(session, productDTO, membersProductDTO);
    }

    @Override
    public List<MembersProductDTO> selectProductsByCategory(String category) {
        try(SqlSession session = Config.getConnection()){
            return membersProductDao.selectProductsByCategory(session,category);
        }
    }
    @Override
    public List<MembersProductDTO> searchProductByName(String name) {
        try(SqlSession session = Config.getConnection()){
        return membersProductDao.searchProductByName(session,name);
        }
    }

    @Override
    public List<MembersProductDTO> selectAllProducts(String searchKeyword) {
        try(SqlSession session = Config.getConnection()){
            return membersProductDao.selectProductByAll(session,searchKeyword);
        }
    }
    @Override
    public List<MembersProductDTO> findByMemberNo(int memberNo) {
        try(SqlSession session = Config.getConnection()){
            return membersProductDao.findByMemberNo(session, memberNo);
        }
    }

    @Override
    public int requestStock(MembersProductDTO membersProductDTO,MemberDTO memberDTO) throws NoMoneyException{
        try(SqlSession session = Config.getConnection()){
            pay(session,membersProductDTO,memberDTO);
            int result = membersProductDao.requestStock(session, membersProductDTO);
            session.commit();
            return result;
        }
    }

    @Override
    public int deleteStock(MembersProductDTO membersProductDTO) {
        try (SqlSession session = Config.getConnection()){
            int result = membersProductDao.deleteStock(session, membersProductDTO);
            session.commit();
            return result;
        }
    }

    @Override
    public int updateStock(MembersProductDTO dto) {
        try(SqlSession session = Config.getConnection()){
            int result = membersProductDao.updateStock(session,dto);
            session.commit();
            return result;
        }
    }

    @Override
    public List<MembersProductDTO> findByMemberNoAndProductsCategory(MemberDTO memberDTO, String category) {
        try(SqlSession session = Config.getConnection()){
            return membersProductDao.findByMemberNoAndProductsCategory(session,memberDTO,category);
        }
    }

    @Override
    public List<MembersProductDTO> findByMemberNoAndProductsCategoryOrProductName(MemberDTO memberDTO, String searchKeyword) {
        try(SqlSession session = Config.getConnection()){
            return membersProductDao.findByMemberNoAndProductsCategoryOrProductName(session,memberDTO,searchKeyword);
        }
    }

    @Override
    public List<MembersProductDTO> findByMemberNoAndProductsName(MemberDTO memberDTO, String name) {
        try(SqlSession session = Config.getConnection()){
            return membersProductDao.findByMemberNoAndProductsName(session,memberDTO,name);
        }
    }

    private boolean checkPermitProduct(String orderNo){
        try(SqlSession session = Config.getConnection()){
            MembersProductDTO byOrderNo = membersProductDao.findByOrderNo(session,orderNo);
            MembersProductDTO dto = membersProductDao.findByPermitAndProductNoAndMemberNo(session, byOrderNo);
            return dto != null;
        }
    }
    private void pay(SqlSession session,MembersProductDTO membersProductDTO, MemberDTO memberDTO)throws NoMoneyException{
        ProductDTO byNo = productDao.findByNo(session,membersProductDTO.getProduct_no());
        int requireMoney = byNo.getProduct_price() * membersProductDTO.getProduct_quantity();
        if(requireMoney > memberDTO.getMember_balance()){
            throw new NoMoneyException("소지금액이 부족합니다. 보유금액 : "+ memberDTO.getMember_balance() +
                    "필요금액 : " + requireMoney);
        }
        memberDTO.setMember_balance(memberDTO.getMember_balance() - requireMoney);
        memberDAO.updateBalance(session,memberDTO);
    }

}
