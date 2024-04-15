package org.kdt.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.kdt.dto.MembersProductDTO;
import org.kdt.dto.ProductDTO;

public class ProductDAO {

	public int delete(SqlSession session, ProductDTO dto){
		return session.delete("ProductMapper.deleteProduct", dto);
	}

	public int update(SqlSession session, ProductDTO dto){
		 return session.update("ProductMapper.updateProduct",dto);
	}

	public int insert(SqlSession session, ProductDTO productDTO){
		return session.insert("ProductMapper.insertProduct", productDTO);
	}

	public List<ProductDTO> findByAll(SqlSession session){
		return session.selectList("ProductMapper.findByAll");
	}

	// select-product-all
	public List<ProductDTO> selectAllProducts(SqlSession session, String searchKeyword) {
		Map<String, String> params = new HashMap<>();
		params.put("searchKeyword", searchKeyword);
		return session.selectList("ProductMapper.selectProductByAll", params);

	}

	// select-product-Category
	public List<ProductDTO> selectProductsByCategory(SqlSession session, String category) {
		return session.selectList("ProductMapper.selectProductsByCategory", category);
	}

	// select-product-name
	public List<ProductDTO> selectProductsByName(SqlSession session, String name) {
		return session.selectList("ProductMapper.searchProductByName", name);
	}

	// select-product-price
	public List<ProductDTO> searchProductByPrice(SqlSession session, int price) {
		return session.selectList("ProductMapper.searchProductByPrice", price);
	}

	// select-product-price
	public List<ProductDTO> selectProductsByPriceRange(SqlSession session, double minPrice, double maxPrice) {
		Map<String, Double> params = new HashMap<>();
		params.put("minPrice", minPrice);
		params.put("maxPrice", maxPrice);
		return session.selectList("selectProductsByPriceRange", params);
	}
	
	public ProductDTO findByNo(SqlSession session, String productNo){
		return session.selectOne("ProductMapper.findByNo",productNo);
	}

	public int deductStockQuanties(SqlSession session, ProductDTO productDTO, MembersProductDTO membersProductDTO) {
		Map<String,Integer> map = new HashMap<>();
		map.put("order_quantity", membersProductDTO.getProduct_quantity());
		map.put("product_no",productDTO.getProduct_no());
		return session.update("ProductMapper.deductStockQuantities", map);
	}
}
