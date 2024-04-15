package org.kdt.service;

import java.util.List;

import org.kdt.dto.ProductDTO;

public interface ProductService {
    List<ProductDTO> findByAll();
    int insertProduct(ProductDTO dto);
    int deleteProduct(ProductDTO product);
    int updateProduct(ProductDTO dto);
	List<ProductDTO> selectAllProducts(String searchKeyword); // 전체품목검색
	List<ProductDTO> searchProductByName(String name); // 동일한제품명검색
	List<ProductDTO> selectProductsByCategory(String category); // 동일한카테고리검색
	List<ProductDTO> searchProductByPrice(int price); // 동일한가격검색
	List<ProductDTO> selectProductsByPriceRange(double minPrice, double maxPrice); // 가격설정

}
