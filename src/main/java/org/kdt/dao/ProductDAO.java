package org.kdt.dao;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.kdt.dto.ProductDTO;

public class ProductDAO {

	public int delete(SqlSession session, ProductDTO dto){
		return session.delete("ProductMapper.deleteProduct", dto);
	}

	public int update(SqlSession session, ProductDTO dto){
		 return session.update("ProductMapper.updateProduct",dto);
	}

	public int insert(SqlSession session, ProductDTO productDTO){
		return session.insert("ProductMapper.insertProduct", productDTO);
	}

	public List<ProductDTO> findByAll(SqlSession session){
		return session.selectList("ProductMapper.findByAll");
	}

}
