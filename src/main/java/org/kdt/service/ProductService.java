package org.kdt.service;

import java.util.List;

import org.kdt.dto.ProductDTO;

public interface ProductService {
    List<ProductDTO> findByAll();
    int insertProduct(ProductDTO dto);
    int deleteProduct(ProductDTO product);
    int updateProduct(ProductDTO dto) ;
}
