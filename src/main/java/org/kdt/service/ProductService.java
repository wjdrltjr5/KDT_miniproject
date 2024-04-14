package org.kdt.service;

import java.util.List;

import org.kdt.dto.ProductDTO;

public interface ProductService {
    List<ProductDTO> select();

    int insertProduct(ProductDTO dto) throws DuplicatedProductnoException;


   // int deleteProduct(Integer productNo);
    int deleteProduct(ProductDTO product);

//    int insertDelete(ProductDTO dto, Integer ProductNo) throws DuplicatedProductnoException;

    int updateProduct(ProductDTO dto) throws DuplicatedProductnoException;
}
