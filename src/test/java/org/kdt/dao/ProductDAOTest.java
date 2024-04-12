package org.kdt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.kdt.dto.ProductDTO;
import org.kdt.service.DuplicatedProductnoException;

public class ProductDAOTest {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/miniproject1";
        String userid = "root";
        String passwd = "1234";

        try (Connection con = DriverManager.getConnection(url, userid, passwd)) {
            ProductDAO dao = new ProductDAO();

            // 제품 추가 테스트
            System.out.println("제품 추가 테스트:");
            ProductDTO newProduct = new ProductDTO("P002", "Electronics", "Tablet", java.sql.Date.valueOf("2024-04-12"), 800000, 15);
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

            // 제품 목록 조회 테스트
            System.out.println("\n제품 목록 조회 테스트:");
            List<ProductDTO> productList = dao.select(con);
            for (ProductDTO product : productList) {
                System.out.println(product);
            }

            // 제품 삭제 테스트
            System.out.println("\n제품 삭제 테스트:");
            int deleteResult = dao.delete(con, "P002");
            if (deleteResult > 0) {
                System.out.println("제품 삭제 성공!");
                
            } else {
                System.out.println("제품 삭제 실패!");
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
    }
    
}
