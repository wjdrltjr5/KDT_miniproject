package org.kdt.service;

import org.apache.ibatis.session.SqlSession;
import org.kdt.Config;
import org.kdt.dao.MembersProductDAO;
import org.kdt.dao.ProductDAO;
import org.kdt.dto.MemberDTO;
import org.kdt.dto.MembersProductDTO;
import org.kdt.dto.ProductDTO;

import java.util.List;

public class MembersProductServiceImpl implements MembersProductService{
    private final MembersProductDAO membersProductDao;
    private final ProductDAO productDao;
    public MembersProductServiceImpl(MembersProductDAO membersProductDao, ProductDAO productDao) {
        this.membersProductDao = membersProductDao;
        this.productDao = productDao;
    }
    @Override
    public List<MembersProductDTO> findByStatusHold(){
        try(SqlSession session = Config.getConnection()){
            return membersProductDao.findByStatusHold(session);
        }
    }

    @Override
    public int requestOrderFailure(String OrderNo) {
        try(SqlSession session = Config.getConnection()){
        int result = membersProductDao.requestOrderFailure(session,OrderNo);
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
                    MembersProductDTO byOrderNo = membersProductDao.findByOrderNo(session,orderNo);
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
    public int requestStock(MembersProductDTO membersProductDTO) {
        try(SqlSession session = Config.getConnection()){
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
}
