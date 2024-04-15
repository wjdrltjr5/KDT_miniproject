package org.kdt.service;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.kdt.Config;
import org.kdt.dao.ProductDAO;
import org.kdt.dto.ProductDTO;

public class ProductServiceImpl implements ProductService {
    private final ProductDAO productDAO;
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }
    public List<ProductDTO> findByAll(){
        try(SqlSession session = Config.getConnection()){
            return productDAO.findByAll(session);
        }
    }
    @Override
    public int insertProduct(ProductDTO dto){
        int n = 0;
        try(SqlSession session = Config.getConnection()){
            n =  productDAO.insert(session, dto);
            session.commit();
            return n;
        }
    }
    @Override
    public int deleteProduct(ProductDTO dto){
        int n = 0;
        try(SqlSession session = Config.getConnection()){
            n = productDAO.delete(session, dto);
            session.commit();
            return n;
        }
    }

    @Override
    public int updateProduct(ProductDTO dto){
        int n = 0;
        try(SqlSession session = Config.getConnection()){
            n = productDAO.update(session,dto);
            session.commit();
            return n;
        }
    }
}
