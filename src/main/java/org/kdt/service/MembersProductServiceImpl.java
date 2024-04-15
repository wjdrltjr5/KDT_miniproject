package org.kdt.service;

import org.apache.ibatis.session.SqlSession;
import org.kdt.Config;
import org.kdt.dao.MembersProductDAO;
import org.kdt.dao.ProductDAO;
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
    public int requestOrderPermit(String orderNo){
        try(SqlSession session = Config.getConnection()){
            if(checkQuantity(orderNo)){
                membersProductDao.requestOrderPermit(session,orderNo);
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

    private boolean checkQuantity(String orderNo){
        try(SqlSession session = Config.getConnection()){
            MembersProductDTO request = membersProductDao.findByOrderNo(session, orderNo);
            ProductDTO productDto = productDao.findByNo(session,request.getProduct_no());
            if(request.getProduct_quantity() > productDto.getProduct_quantity()){
                return false;
            }else{
                deductStockQuantities(session,productDto,request);
                return true;
            }
        }
    }
    private int deductStockQuantities(SqlSession session,ProductDTO productDTO, MembersProductDTO membersProductDTO){
        return productDao.deductStockQuanties(session, productDTO, membersProductDTO);
    }
}
