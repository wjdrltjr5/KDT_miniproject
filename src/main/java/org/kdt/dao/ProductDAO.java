package org.kdt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.kdt.dto.ProductDTO;
import org.kdt.service.DuplicatedProductnoException;

public class ProductDAO {

	public int delete(Connection con, String productNo) {

		int n = 0;
		PreparedStatement pstmt = null;
		try {
			String sql = "delete from product where product_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, productNo);
			n = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n;
	}

	public int insert(Connection con, ProductDTO dto) throws DuplicatedProductnoException {
	    int n = 0;
	    PreparedStatement pstmt = null;
	    try {
	        // 제품 번호가 이미 존재하는지 확인.
	        if (isProductNoExists(con, dto.getProductNo())) {
	            throw new DuplicatedProductnoException(dto.getProductNo() + " 값이 중복");
	        }

	        String sql = "insert into product (product_no, product_category, product_name, product_date, product_price, product_quantity) "
	                    + " values (?,?,?,?,?,?)";

	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, dto.getProductNo());
	        pstmt.setString(2, dto.getProductCategory());
	        pstmt.setString(3, dto.getProductName());
	        pstmt.setDate(4, dto.getProductDate());
	        pstmt.setInt(5, dto.getProductPrice());
	        pstmt.setInt(6, dto.getProductQuantity());

	        n = pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (pstmt != null)
	                pstmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return n;
	}

	public List<ProductDTO> select(Connection con) {
		List<ProductDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select product_no, product_category, product_name, product_date, product_price, product_quantity from product";

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String productNo = rs.getString("product_no");
				String productCategory = rs.getString("product_category");
				String productName = rs.getString("product_name");
				java.sql.Date productDate = rs.getDate("product_date");
				int productPrice = rs.getInt("product_price");
				int productQuantity = rs.getInt("product_quantity");

				ProductDTO dto = new ProductDTO(productNo, productCategory, productName, productDate, productPrice,
						productQuantity);
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// 주어진 제품 번호가 이미 존재하는지 확인하는 메서드
    public boolean isProductNoExists(Connection con, String productNo) {
        boolean exists = false;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT COUNT(*) AS count FROM product WHERE product_no = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, productNo);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                exists = count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return exists;
    }
}