package org.kdt.service;

import java.util.List;

import org.kdt.dto.ProductDTO;

public interface ProductService {
	List<ProductDTO> select();

	int insert(ProductDTO dto) throws DuplicatedProductnoException;

	int delete(String productNo);

	int insertDelete(ProductDTO dto, String Productno) throws DuplicatedProductnoException;
}
