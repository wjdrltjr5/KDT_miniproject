package org.kdt.service;

import java.sql.Date;

import org.kdt.dto.ProductDTO;

public class ProductServiceTest {
    public static void main(String[] args) {
        ProductService productService = new ProductServiceImpl();

        // 테스트용 ProductDTO 객체 생성
        ProductDTO productDTO = new ProductDTO("P001", "Electronics", "Smartphone", Date.valueOf("2024-04-12"), 1000000, 10);

        // 제품 추가
        try {
            int result = productService.insert(productDTO);
            if (result > 0) {
                System.out.println("제품이 추가되었습니다.");
            } else {
                System.out.println("제품 추가가 실패했습니다.");
            }
        } catch (DuplicatedProductnoException e) {
            System.out.println("제품 번호가 중복되었습니다.");
        }

        // 제품 목록 조회
        System.out.println("제품 목록 :");
        productService.select().forEach(System.out::println);

        // 제품 삭제
        String productNoToDelete = "P001"; // 삭제할 제품 번호
        int deleteResult = productService.delete(productNoToDelete);
        if (deleteResult > 0) {
            System.out.println("제품이 삭제되었습니다.");
        } else {
            System.out.println("제품 삭제에 실패했습니다.");
        }
        
        // 제품 목록 조회
        System.out.println("제품 목록 :");
        productService.select().forEach(System.out::println);

    }
}
