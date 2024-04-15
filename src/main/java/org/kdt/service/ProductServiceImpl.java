package org.kdt.service;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.kdt.Config;
import org.kdt.dao.ProductDAO;
import org.kdt.dto.ProductDTO;

import javax.swing.*;

public class ProductServiceImpl implements ProductService {
    private final ProductDAO productDAO;

    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<ProductDTO> findByAll() {
        try (SqlSession session = Config.getConnection()) {
            return productDAO.findByAll(session);
        }
    }

    @Override
    public int insertProduct(ProductDTO dto) {
        int n = 0;
        try (SqlSession session = Config.getConnection()) {
            n = productDAO.insert(session, dto);
            session.commit();
            return n;
        }
    }

    @Override
    public int deleteProduct(ProductDTO dto) {
        int n = 0;
        try (SqlSession session = Config.getConnection()) {
            n = productDAO.delete(session, dto);
            session.commit();
            return n;
        }
    }

    @Override
    public int updateProduct(ProductDTO dto) {
        int n = 0;
        try (SqlSession session = Config.getConnection()) {
            n = productDAO.update(session, dto);
            session.commit();
            return n;
        }
    }

    // 전체품목검색
    @Override
    public List<ProductDTO> selectAllProducts(String searchKeyword) {
        try (SqlSession session = Config.getConnection()) {
            return productDAO.selectAllProducts(session, searchKeyword);
        }
    }

    // 동일한 제품명검색
    @Override
    public List<ProductDTO> searchProductByName(String name) {
        try (SqlSession session = Config.getConnection()) {
            return productDAO.selectProductsByName(session, name);
        }
    }

    // 동일한 제품카테고리검색
    @Override
    public List<ProductDTO> selectProductsByCategory(String category) {
        try (SqlSession session = Config.getConnection()) {
            return productDAO.selectProductsByCategory(session, category);
        }
    }

    // 동일한 제품가격검색
    @Override
    public List<ProductDTO> searchProductByPrice(int price) {
        try (SqlSession session = Config.getConnection()) {
            return productDAO.searchProductByPrice(session, price);
        }
    }

    @Override
    public List<ProductDTO> selectProductsByPriceRange(double minPrice, double maxPrice) {
        try (SqlSession session = Config.getConnection()) {
            return productDAO.selectProductsByPriceRange(session, minPrice, maxPrice);
        }
    }


}
