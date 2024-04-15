package org.kdt.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.kdt.dto.ProductDTO;

public class ProductDAO {

	// find
	public List<ProductDTO> findProductAll(SqlSession session) {
		List<ProductDTO> list = session.selectList("ProductMapper.findProductAll");
		return list;
	}

	// select-product-all
	public List<ProductDTO> selectAllProducts(SqlSession session, String searchKeyword) {

		Map<String, String> params = new HashMap<>();
		params.put("searchKeyword", searchKeyword);
		return session.selectList("ProductMapper.selectProductByAll", params);

	}

	// select-product-Category
	public List<ProductDTO> selectProductsByCategory(SqlSession session, String category) {
		List<ProductDTO> list = session.selectList("ProductMapper.selectProductsByCategory", category);
		return list;
	}

	// select-product-name
	public List<ProductDTO> selectProductsByName(SqlSession session, String name) {
		List<ProductDTO> list = session.selectList("ProductMapper.searchProductByName", name);
		return list;
	}

	// select-product-price
	public List<ProductDTO> searchProductByPrice(SqlSession session, int price) {
		List<ProductDTO> list = session.selectList("ProductMapper.searchProductByPrice", price);
		return list;
	}

	// select-product-price
	public List<ProductDTO> selectProductsByPriceRange(SqlSession session, double minPrice, double maxPrice) {
		Map<String, Double> params = new HashMap<>();
		params.put("minPrice", minPrice);
		params.put("maxPrice", maxPrice);
		List<ProductDTO> list = session.selectList("selectProductsByPriceRange", params);
		return list;
	}

}
