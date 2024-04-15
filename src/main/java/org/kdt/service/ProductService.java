package org.kdt.service;

import java.util.List;

import org.kdt.dto.ProductDTO;

public interface ProductService {
	
	public List<ProductDTO> findProductAll(); // 전체테이블조회
	public List<ProductDTO> selectAllProducts(String searchKeyword); // 전체품목검색
	public List<ProductDTO> searchProductByName(String name); // 동일한제품명검색
	public List<ProductDTO> selectProductsByCategory(String category); // 동일한카테고리검색
	public List<ProductDTO> searchProductByPrice(int price); // 동일한가격검색
	public List<ProductDTO> selectProductsByPriceRange(double minPrice, double maxPrice); // 가격설정
	public int save(ProductDTO dto);
	
}
