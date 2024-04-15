package org.kdt.ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class StockModify extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldProductName;
    private JTextField textFieldProductCategory;
    private JTextField textFieldProductPrice;
    private JTextField textFieldProductQuantity;

    // MySQL 연결을 위한 정보
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/testdb";
    String userid = "root";
    String passwd = "1234";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StockModify frame = new StockModify();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public StockModify() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 350, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblProductName = new JLabel("제품이름");
        lblProductName.setBounds(12, 20, 100, 50);
        lblProductName.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblProductName);

        textFieldProductName = new JTextField();
        textFieldProductName.setBounds(126, 20, 200, 50);
        contentPane.add(textFieldProductName);
        textFieldProductName.setColumns(10);

        JButton btnSearchProduct = new JButton("검색하기");
        btnSearchProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // 입력된 제품 이름 가져오기
                    String productName = textFieldProductName.getText();

                    // 데이터베이스 연결
                    Connection conn = DriverManager.getConnection(url, userid, passwd);

                    // 제품 검색
                    String searchSql = "SELECT * FROM product WHERE product_name = ?";
                    PreparedStatement searchPstmt = conn.prepareStatement(searchSql);
                    searchPstmt.setString(1, productName);
                    ResultSet rs = searchPstmt.executeQuery();

                    if (rs.next()) {
                        // 검색된 제품 정보 표시
                        String productCategory = rs.getString("product_category");
                        String productPrice = rs.getString("product_price");
                        String productQuantity = rs.getString("product_quantity");

                        // 새 창에 검색된 제품 정보를 넘겨주기
                        ModifyProductInfo modifyProductInfo = new ModifyProductInfo(url, userid, passwd, productName, productCategory, productPrice, productQuantity);
                        modifyProductInfo.setVisible(true);
                    } else {
                        // 검색 결과가 없을 때 메시지 출력
                        JOptionPane.showMessageDialog(contentPane, "해당하는 제품이 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                    }

                    // 리소스 정리
                    rs.close();
                    searchPstmt.close();
                    conn.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnSearchProduct.setBounds(126, 80, 200, 50);
        contentPane.add(btnSearchProduct);
    }
}

class ModifyProductInfo extends JFrame {
    private JPanel contentPane;
    private JTextField textFieldProductName;
    private JTextField textFieldProductCategory;
    private JTextField textFieldProductPrice;
    private JTextField textFieldProductQuantity;
    
    private String url;
    private String userid;
    private String passwd;

    public ModifyProductInfo(String url, String userid, String passwd, String productName, String productCategory, String productPrice, String productQuantity) {
        this.url = url;
        this.userid = userid;
        this.passwd = passwd;
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 350, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblProductName = new JLabel("제품이름");
        lblProductName.setBounds(12, 20, 100, 50);
        lblProductName.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblProductName);

        textFieldProductName = new JTextField(productName);
        textFieldProductName.setBounds(126, 20, 200, 50);
        contentPane.add(textFieldProductName);
        textFieldProductName.setColumns(10);

        JLabel lblProductCategory = new JLabel("제품카테고리");
        lblProductCategory.setBounds(12, 80, 100, 50);
        lblProductCategory.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblProductCategory);

        textFieldProductCategory = new JTextField(productCategory);
        textFieldProductCategory.setColumns(10);
        textFieldProductCategory.setBounds(126, 80, 200, 50);
        contentPane.add(textFieldProductCategory);

        JLabel lblProductPrice = new JLabel("제품가격");
        lblProductPrice.setBounds(12, 140, 100, 50);
        lblProductPrice.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblProductPrice);

        textFieldProductPrice = new JTextField(productPrice);
        textFieldProductPrice.setColumns(10);
        textFieldProductPrice.setBounds(126, 140, 200, 50);
        contentPane.add(textFieldProductPrice);

        JLabel lblProductQuantity = new JLabel("수량");
        lblProductQuantity.setBounds(12, 200, 100, 50);
        lblProductQuantity.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblProductQuantity);

        textFieldProductQuantity = new JTextField(productQuantity);
        textFieldProductQuantity.setColumns(10);
        textFieldProductQuantity.setBounds(126, 200, 200, 50);
        contentPane.add(textFieldProductQuantity);

        JButton btnModifyProduct = new JButton("수정하기");
        btnModifyProduct.addActionListener(e -> {
            try {
                // 수정된 제품 정보 가져오기
                String modifiedProductName = textFieldProductName.getText();
                String modifiedProductCategory = textFieldProductCategory.getText();
                String modifiedProductPrice = textFieldProductPrice.getText();
                String modifiedProductQuantity = textFieldProductQuantity.getText();

                // 데이터베이스 연결
                Connection conn = DriverManager.getConnection(url, userid, passwd);

                // 제품 정보 업데이트
                String updateSql = "UPDATE product SET product_name = ?, product_category = ?, product_price = ?, product_quantity = ? WHERE product_name = ?";
                PreparedStatement updatePstmt = conn.prepareStatement(updateSql);
                updatePstmt.setString(1, modifiedProductName);
                updatePstmt.setString(2, modifiedProductCategory);
                updatePstmt.setString(3, modifiedProductPrice);
                updatePstmt.setString(4, modifiedProductQuantity);
                updatePstmt.setString(5, productName);

                int rowsAffected = updatePstmt.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(contentPane, "제품 정보가 성공적으로 수정되었습니다.", "성공",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(contentPane, "제품 정보 수정에 실패했습니다.", "오류",
                            JOptionPane.ERROR_MESSAGE);
                }

                updatePstmt.close();
                conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        btnModifyProduct.setBounds(65, 281, 200, 40);
        contentPane.add(btnModifyProduct);
    }
}
