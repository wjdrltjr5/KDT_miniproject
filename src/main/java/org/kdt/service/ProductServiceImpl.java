package org.kdt.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.kdt.dao.ProductDAO;
import org.kdt.dto.ProductDTO;

public class ProductServiceImpl implements ProductService {

    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/miniproject1";
    String userid = "root";
    String passwd = "1234";

    Connection con = null; // Connection 변수 선언

    public ProductServiceImpl() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ProductDTO> select() {
        List<ProductDTO> list = null;
        try {
            con = DriverManager.getConnection(url, userid, passwd); // con 초기화
            ProductDAO dao = new ProductDAO();
            list = dao.select(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int insertProduct(ProductDTO dto) throws DuplicatedProductnoException {
        int n = 0;
        try {
            con = DriverManager.getConnection(url, userid, passwd); // con 초기화
            ProductDAO dao = new ProductDAO();
            n = dao.insert(con, dto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public int deleteProduct(ProductDTO dto) {
        int n = 0;
        try {
            con = DriverManager.getConnection(url, userid, passwd); // con 초기화
            ProductDAO dao = new ProductDAO();
            n = dao.delete(con, dto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    
    @Override
	public int updateProduct(ProductDTO dto) throws DuplicatedProductnoException {
		int n = 0;
		try {
			con = DriverManager.getConnection(url, userid, passwd); // con 초기화
			ProductDAO dao = new ProductDAO();
			n = dao.update(con, dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

    @Override
    public int insertDelete(ProductDTO dto, Integer productNo) throws DuplicatedProductnoException {
        int n = 0;
        try {
            con = DriverManager.getConnection(url, userid, passwd); // con 초기화
            ProductDAO dao = new ProductDAO();
            con.setAutoCommit(false);
            dao.delete(con, dto);
            n = dao.insert(con, dto);
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return n;
    }
}
