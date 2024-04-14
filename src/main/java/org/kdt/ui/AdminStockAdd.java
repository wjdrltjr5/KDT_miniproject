package org.kdt.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.kdt.dto.ProductDTO;
import org.kdt.service.ProductService;
import org.kdt.service.ProductServiceImpl;

public class AdminStockAdd extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldProductName;
    private JTextField textFieldProductCategory;
    private JTextField textFieldProductPrice;
    private JTextField textFieldProductQuantity;

    private ProductService productService;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminStockAdd frame = new AdminStockAdd();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AdminStockAdd() {
        productService = new ProductServiceImpl();

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

        JLabel lblProductCategory = new JLabel("제품카테고리");
        lblProductCategory.setHorizontalAlignment(SwingConstants.CENTER);
        lblProductCategory.setBounds(12, 80, 100, 50);
        contentPane.add(lblProductCategory);

        JLabel lblProductPrice = new JLabel("제품가격");
        lblProductPrice.setHorizontalAlignment(SwingConstants.CENTER);
        lblProductPrice.setBounds(12, 140, 100, 50);
        contentPane.add(lblProductPrice);

        JLabel lblProductQuantity = new JLabel("수량");
        lblProductQuantity.setHorizontalAlignment(SwingConstants.CENTER);
        lblProductQuantity.setBounds(12, 200, 100, 50);
        contentPane.add(lblProductQuantity);

        textFieldProductName = new JTextField();
        textFieldProductName.setBounds(126, 20, 200, 50);
        textFieldProductName.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(textFieldProductName);
        textFieldProductName.setColumns(10);

        textFieldProductCategory = new JTextField();
        textFieldProductCategory.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldProductCategory.setColumns(10);
        textFieldProductCategory.setBounds(126, 80, 200, 50);
        contentPane.add(textFieldProductCategory);

        textFieldProductPrice = new JTextField();
        textFieldProductPrice.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldProductPrice.setColumns(10);
        textFieldProductPrice.setBounds(126, 140, 200, 50);
        contentPane.add(textFieldProductPrice);

        textFieldProductQuantity = new JTextField();
        textFieldProductQuantity.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldProductQuantity.setColumns(10);
        textFieldProductQuantity.setBounds(126, 200, 200, 50);
        contentPane.add(textFieldProductQuantity);

        JButton btnAddProduct = new JButton("추가하기");
        btnAddProduct.setBounds(65, 281, 200, 40);
        contentPane.add(btnAddProduct);

        btnAddProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // 입력된 제품 정보 가져오기
                    String productName = textFieldProductName.getText();
                    String productCategory = textFieldProductCategory.getText();
                    int productPrice = Integer.parseInt(textFieldProductPrice.getText());
                    int productQuantity = Integer.parseInt(textFieldProductQuantity.getText());

                    // 현재 날짜를 java.sql.Date 형식으로 가져오기
                    java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());

                    // 제품 객체 생성
                    ProductDTO newProduct = new ProductDTO(0, productCategory, productName, currentDate, productPrice,
                            productQuantity);

                    // 제품 추가
                    int result = productService.insertProduct(newProduct);
                    if (result > 0) {
                        JOptionPane.showMessageDialog(null, "제품이 추가되었습니다.");

                    } else {
                        JOptionPane.showMessageDialog(null, "제품 추가가 실패했습니다.");

                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "잘못된 입력 형식입니다. 숫자를 입력해주세요.");

                } catch (Exception ex) {
                    ex.printStackTrace();

                }
            }
        });
    }
}
