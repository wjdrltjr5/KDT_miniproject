package org.kdt.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.kdt.dto.ProductDTO;

public class ProductDAO {
	
	// find
	public List<ProductDTO> findProductAll(SqlSession session) {
		List<ProductDTO> list = session.selectList("org.kdt.mapper.ProductMapper.findProductAll");
		return list;
	}
	
	// save
	public int saveProduct(SqlSession session, ProductDTO dto) {
		return session.insert("/config.ProductMapper.saveProduct", dto);
	}
}
