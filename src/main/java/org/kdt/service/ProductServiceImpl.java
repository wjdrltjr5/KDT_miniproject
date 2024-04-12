package org.kdt.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.kdt.Config;
import org.kdt.dao.ProductDAO;
import org.kdt.dto.ProductDTO;

public class ProductServiceImpl implements ProductService {
    
    private ProductDAO dao;
    
    // ProductDAO 객체를 주입하는 setter 메서드
    public void setProductDAO(ProductDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<ProductDTO> findProductAll() {
        SqlSession session = null;
        List<ProductDTO> list = null;
        
        try {
            session = Config.getConnection();
            list = dao.findProductAll(session);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        
        return list;
    }

    @Override
    public int save(ProductDTO dto) {
        // save 메서드의 구현은 생략됨
        return 0;
    }
}
