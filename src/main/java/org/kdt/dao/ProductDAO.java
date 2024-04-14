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

	 public int delete(Connection con, ProductDTO dto) {
	        int n = 0;
	        String sql = "DELETE FROM product WHERE product_no = ? AND product_name = ? AND product_category = ?";
	        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	            pstmt.setInt(1, dto.getProductNo());
	            pstmt.setString(2, dto.getProductName());
	            pstmt.setString(3, dto.getProductCategory());
	          
	            n = pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return n;
	    }

	public int update(Connection con, ProductDTO dto) {
		int n = 0;
		String sql = "UPDATE product SET product_category=?, product_name=?, product_date=?, product_price=?, product_quantity=? WHERE product_no=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, dto.getProductCategory());
			pstmt.setString(2, dto.getProductName());
			pstmt.setDate(3, dto.getProductDate());
			pstmt.setInt(4, dto.getProductPrice());
			pstmt.setInt(5, dto.getProductQuantity());
			pstmt.setInt(6, dto.getProductNo());
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

	public int insert(Connection con, ProductDTO dto) throws DuplicatedProductnoException {
		int n = 0;
		if (isProductNoExists(con, dto.getProductNo())) {
			throw new DuplicatedProductnoException(dto.getProductNo() + " 값이 중복");
		}
		String sql = "INSERT INTO product (product_no, product_category, product_name, product_date, product_price, product_quantity) VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, dto.getProductNo());
			pstmt.setString(2, dto.getProductCategory());
			pstmt.setString(3, dto.getProductName());
			pstmt.setDate(4, dto.getProductDate());
			pstmt.setInt(5, dto.getProductPrice());
			pstmt.setInt(6, dto.getProductQuantity());
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

	public List<ProductDTO> select(Connection con) {
		List<ProductDTO> list = new ArrayList<>();
		String sql = "SELECT product_no, product_category, product_name, product_date, product_price, product_quantity FROM product";
		try (PreparedStatement pstmt = con.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				int productNo = rs.getInt("product_no");
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
		}
		return list;
	}

	private boolean isProductNoExists(Connection con, Integer productNo) {
		boolean exists = false;
		String sql = "SELECT COUNT(*) AS count FROM product WHERE product_no = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, productNo);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					int count = rs.getInt("count");
					exists = count > 0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exists;
	}
}
