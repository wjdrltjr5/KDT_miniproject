package org.kdt.ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.awt.GridLayout;

public class StockModify2 extends JFrame {

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
                    StockModify2 frame = new StockModify2();
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
    public StockModify2() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 360, 200);
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
        textFieldProductName.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(textFieldProductName);
        textFieldProductName.setColumns(10);

        // 검색된 제품 정보를 표시할 텍스트 필드들
        JLabel lblProductCategory = new JLabel("제품카테고리");
        lblProductCategory.setBounds(12, 160, 100, 50);
        lblProductCategory.setHorizontalAlignment(SwingConstants.CENTER); // 중앙 정렬 설정
        lblProductCategory.setVisible(false); // 처음에는 보이지 않도록 설정
        contentPane.add(lblProductCategory);

        textFieldProductCategory = new JTextField();
        textFieldProductCategory.setBounds(126, 160, 200, 50);
        textFieldProductCategory.setHorizontalAlignment(JTextField.CENTER); // 중앙 정렬 설정
        textFieldProductCategory.setVisible(false); // 처음에는 보이지 않도록 설정
        // 테두리 추가
        textFieldProductCategory.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPane.add(textFieldProductCategory);
        textFieldProductCategory.setColumns(10);

        JLabel lblProductPrice = new JLabel("제품가격");
        lblProductPrice.setBounds(12, 220, 100, 50);
        lblProductPrice.setHorizontalAlignment(SwingConstants.CENTER); // 중앙 정렬 설정
        lblProductPrice.setVisible(false); // 처음에는 보이지 않도록 설정
        contentPane.add(lblProductPrice);

        textFieldProductPrice = new JTextField();
        textFieldProductPrice.setBounds(126, 220, 200, 50);
        textFieldProductPrice.setHorizontalAlignment(JTextField.CENTER); // 중앙 정렬 설정
        textFieldProductPrice.setVisible(false); // 처음에는 보이지 않도록 설정
        // 테두리 추가
        textFieldProductPrice.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPane.add(textFieldProductPrice);
        textFieldProductPrice.setColumns(10);

        JLabel lblProductQuantity = new JLabel("수량");
        lblProductQuantity.setBounds(12, 280, 100, 50);
        lblProductQuantity.setHorizontalAlignment(SwingConstants.CENTER); // 중앙 정렬 설정
        lblProductQuantity.setVisible(false); // 처음에는 보이지 않도록 설정
        contentPane.add(lblProductQuantity);

        textFieldProductQuantity = new JTextField();
        textFieldProductQuantity.setBounds(126, 280, 200, 50);
        textFieldProductQuantity.setHorizontalAlignment(JTextField.CENTER); // 중앙 정렬 설정
        textFieldProductQuantity.setVisible(false); // 처음에는 보이지 않도록 설정
        // 테두리 추가
        textFieldProductQuantity.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPane.add(textFieldProductQuantity);
        textFieldProductQuantity.setColumns(10);

        // "검색하기" 버튼에 대한 ActionListener 추가
        JButton btnSearchProduct = new JButton("검색하기");
        btnSearchProduct.setBounds(65, 100, 200, 40);
        contentPane.add(btnSearchProduct);

        // 검색 버튼 클릭 이벤트 핸들러
        btnSearchProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // 입력된 제품 이름 가져오기
                    String productName = textFieldProductName.getText().trim();

                    // 데이터베이스 연결
                    Connection conn = DriverManager.getConnection(url, userid, passwd);

                    // 제품 검색
                    String searchSql = "SELECT * FROM product WHERE product_name LIKE ?";
                    PreparedStatement searchPstmt = conn.prepareStatement(searchSql);
                    searchPstmt.setString(1, "%" + productName + "%");
                    ResultSet rs = searchPstmt.executeQuery();

                    if (rs.next()) {
                        // 검색된 제품 정보를 새 창에 표시
                        showSearchResults(rs);
                    } else {
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
    }

    // 검색 결과를 표시하는 메서드
    private void showSearchResults(ResultSet rs) {
        // 새로운 JFrame 생성
        JFrame resultFrame = new JFrame("검색 결과");
        resultFrame.setSize(500, 400);
        resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 창 닫기 버튼 동작 설정

        // 결과를 표시할 패널 생성
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayout(0, 1));
        // 테두리 추가
        resultPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        try {
            // 첫 번째 검색 결과만 사용
            if (rs.next()) {
                String productName = rs.getString("product_name");
                String productCategory = rs.getString("product_category");
                double productPrice = rs.getDouble("product_price");
                int productQuantity = rs.getInt("product_quantity");

                // 기존에 추가되어 있던 컴포넌트들을 모두 제거
                resultPanel.removeAll();

                // 검색된 제품 정보를 패널에 추가
                JLabel nameLabel = new JLabel("제품 이름: " + productName);
                JLabel categoryLabel = new JLabel("제품 카테고리: " + productCategory);
                JLabel priceLabel = new JLabel("제품 가격: " + productPrice);
                JLabel quantityLabel = new JLabel("제품 수량: " + productQuantity);

                resultPanel.add(nameLabel);
                resultPanel.add(categoryLabel);
                resultPanel.add(priceLabel);
                resultPanel.add(quantityLabel);
                
                // 텍스트 필드에 검색 결과 설정
                textFieldProductCategory.setText(productCategory);
                textFieldProductPrice.setText(String.valueOf(productPrice));
                textFieldProductQuantity.setText(String.valueOf(productQuantity));
                
                // 텍스트 필드 표시 설정
                textFieldProductCategory.setVisible(true);
                textFieldProductPrice.setVisible(true);
                textFieldProductQuantity.setVisible(true);
                
            } else {
                JOptionPane.showMessageDialog(contentPane, "해당하는 제품이 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // 패널을 프레임에 추가하고 화면에 표시
        resultFrame.add(resultPanel);
        resultFrame.setVisible(true);
    }
}
