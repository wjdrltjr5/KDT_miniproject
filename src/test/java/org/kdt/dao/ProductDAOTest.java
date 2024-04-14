package org.kdt.dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kdt.Config;
import org.kdt.dto.ProductDTO;
import org.kdt.service.DuplicatedProductnoException;
import org.kdt.service.ProductService;
import org.kdt.service.ProductServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ProductDAOTest {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/miniproject1";
		String userid = "root";
		String passwd = "1234";

		try (Connection con = DriverManager.getConnection(url, userid, passwd)) {
			ProductDAO dao = new ProductDAO();

			testInsertProduct(con, dao);
			testSelectProduct(con, dao);
			testUpdateProduct(con, dao);
			testDeleteProduct(con, dao);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
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
	private static void testInsertProduct(Connection con, ProductDAO dao) {
		System.out.println("제품 추가 테스트:");
		ProductDTO newProduct = new ProductDTO(1, "Electronics", "Smartphone",java.sql.Date.valueOf("2024-04-12"),
				1000000, 10);
		try {
			int insertResult = dao.insert(con, newProduct);
			if (insertResult > 0) {
				System.out.println("제품 추가 성공!");
			} else {
				System.out.println("제품 추가 실패!");
			}
		} catch (DuplicatedProductnoException e) {
			System.out.println("제품 번호가 중복됩니다.");
		}
	}

	private static void testSelectProduct(Connection con, ProductDAO dao) {
		System.out.println("\n제품 목록 조회 테스트:");
		List<ProductDTO> productList = dao.select(con);
		for (ProductDTO product : productList) {
			System.out.println(product);
		}
	}

	private static void testUpdateProduct(Connection con, ProductDAO dao) {
		System.out.println("\n제품 정보 변경 테스트:");
		ProductDTO updatedProduct = new ProductDTO(1, "Electronics", "Tablet", java.sql.Date.valueOf("2024-04-12"),
				800000, 15);
		int updateResult = dao.update(con, updatedProduct);
		if (updateResult > 0) {
			System.out.println("제품 정보 변경 성공!");
		} else {
			System.out.println("제품 정보 변경 실패!");
		}
	}

	private static void testDeleteProduct(Connection con, ProductDAO dao) {
	    System.out.println("\n제품 삭제 테스트:");
	    // 삭제할 제품의 정보를 설정하여 ProductDTO 객체 생성
	    ProductDTO productToDelete = new ProductDTO();
	    productToDelete.setProduct_no(1);
	    int deleteResult = dao.delete(con, productToDelete);
	    if (deleteResult > 0) {
	        System.out.println("제품 삭제 성공!");
	    } else {
	        System.out.println("제품 삭제 실패!");
	    }
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
}
