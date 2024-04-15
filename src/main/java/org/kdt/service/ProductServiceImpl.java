package org.kdt.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.kdt.Config;
import org.kdt.dao.ProductDAO;
import org.kdt.dto.ProductDTO;

public class ProductServiceImpl implements ProductService {
    
    private ProductDAO dao;
    private SqlSession sqlSession;
    
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
		// TODO Auto-generated method stub
		return 0;
	}
	
	// 전체품목검색
	@Override
	public List<ProductDTO> selectAllProducts(String searchKeyword) {
	    SqlSession session = null;
	    List<ProductDTO> list = null;
	    
	    try {
	        session = Config.getConnection();
	        list = dao.selectAllProducts(session, searchKeyword);
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	    
	    return list;
	}
	
	// 동일한 제품명검색
	@Override
	public List<ProductDTO> searchProductByName(String name) {
		// TODO Auto-generated method stub
		SqlSession session = null;
		List<ProductDTO> list = null;
		
	    try {
	        session = Config.getConnection();
	        list = dao.selectProductsByName(session, name);
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	    
	    return list;
	}


	// 동일한 제품카테고리검색
	@Override
	public List<ProductDTO> selectProductsByCategory(String category) {
		// TODO Auto-generated method stub
		SqlSession session = null;
	    List<ProductDTO> list = null;
	    
	    try {
	        session = Config.getConnection();
	        list = dao.selectProductsByCategory(session, category);
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	    
	    return list;
	}
	

	// 동일한 제품가격검색
	@Override
	public List<ProductDTO> searchProductByPrice(int price) {
		// TODO Auto-generated method stub
		SqlSession session = null;
	    List<ProductDTO> list = null;
	    
	    try {
	        session = Config.getConnection();
	        list = dao.searchProductByPrice(session, price);
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	    
	    return list;
	}

	@Override
	public List<ProductDTO> selectProductsByPriceRange(double minPrice, double maxPrice) {
	    SqlSession session = null;
	    List<ProductDTO> list = null;
	    try {
	        session = Config.getConnection();
	        list = dao.selectProductsByPriceRange(session, minPrice, maxPrice);
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	    return list;
	}







    

}
