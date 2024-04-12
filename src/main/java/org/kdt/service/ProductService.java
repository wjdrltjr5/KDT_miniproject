package org.kdt.service;

import java.util.List;

import org.kdt.dto.ProductDTO;

public interface ProductService {
	
	public List<ProductDTO> findProductAll();
	public int save(ProductDTO dto);
}
