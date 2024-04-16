package org.kdt.dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kdt.Config;
import org.kdt.dto.ProductDTO;
import org.kdt.service.ProductService;
import org.kdt.service.ProductServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ProductDAOTest {
	@DisplayName("제품을 추가한다.")
	@Test
	void insertProduct() {
        //given
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProduct_category("Electronics");
        productDTO.setProduct_date(java.sql.Date.valueOf("2024-04-12"));
        productDTO.setProduct_name("Smartphone");
        productDTO.setProduct_price(1000000);
        productDTO.setProduct_quantity(10);

        ProductDAO productDAO = new ProductDAO();
        //when

        int insert;
        try (SqlSession session = Config.getConnection()) {
            insert = productDAO.insert(session, productDTO);
			session.rollback();
        }
        //then
        assertThat(insert).isEqualTo(1);
    }
	@DisplayName("해당정보에 맞는 제품을 삭제한다.")
	@Test
	void deleteProduct(){
	    //given
		ProductDTO productToDelete = new ProductDTO();
		productToDelete.setProduct_no(2);
		productToDelete.setProduct_category("의자");
		productToDelete.setProduct_name("의자");
		ProductService productService = new ProductServiceImpl(new ProductDAO());

		//when
		int i = productService.deleteProduct(productToDelete);
	    //then
		assertThat(i).isEqualTo(1);
	}

	@DisplayName("해당 제품을 변경한다.")
	@Test
	void updateProduct(){
	    //given
		ProductService productService = new ProductServiceImpl(new ProductDAO());
		List<ProductDTO> byAll = productService.findByAll();
		//when
		for(ProductDTO dto : byAll){
			dto.setProduct_price(2000);
			productService.updateProduct(dto);
		}
	    //then
		List<ProductDTO> result = productService.findByAll();
		assertThat(result).hasSize(2).extracting("product_price")
				.contains(2000,2000);
	}
}
