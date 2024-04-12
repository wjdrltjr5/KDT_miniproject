package org.kdt;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.kdt.dto.ProductDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
	
    public static void main(String[] args) {
        SqlSession session = Config.getConnection();
        List<ProductDTO> test = session.selectList("ProductMapper.findProductAll");
        for(ProductDTO d : test){
            System.out.println(d);
        }
        
        
//        ProductDTO dto = new ProductDTO("test", "test", "test", "test", 100, 100);
//        int n = session.insert("config/ProductMapper.saveProduct", dto);
//        session.commit();
//        System.out.println(n+"개가 생성됨.");
    }
    
}