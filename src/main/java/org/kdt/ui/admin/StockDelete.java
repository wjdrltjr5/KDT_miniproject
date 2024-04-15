package org.kdt.ui.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.kdt.dao.ProductDAO;
import org.kdt.dto.ProductDTO;
import org.kdt.service.ProductService;
import org.kdt.service.ProductServiceImpl;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StockDelete extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldProductNo;
    private JTextField textFieldProductName;
    private JTextField textFieldProductCategory;

    private ProductService productService;

    /**
     * Launch the application.
     */

    /**
     * Create the frame.
     */
    public StockDelete() {
        productService = new ProductServiceImpl(new ProductDAO());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 350, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblProductNo = new JLabel("제품번호");
        lblProductNo.setHorizontalAlignment(SwingConstants.CENTER);
        lblProductNo.setBounds(12, 20, 100, 50);
        contentPane.add(lblProductNo);

        textFieldProductNo = new JTextField();
        textFieldProductNo.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldProductNo.setBounds(126, 20, 200, 50);
        contentPane.add(textFieldProductNo);
        textFieldProductNo.setColumns(10);

        JLabel lblProductName = new JLabel("제품이름");
        lblProductName.setHorizontalAlignment(SwingConstants.CENTER);
        lblProductName.setBounds(12, 80, 100, 50);
        contentPane.add(lblProductName);

        textFieldProductName = new JTextField();
        textFieldProductName.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldProductName.setBounds(126, 80, 200, 50);
        contentPane.add(textFieldProductName);
        textFieldProductName.setColumns(10);

        JLabel lblProductCategory = new JLabel("제품카테고리");
        lblProductCategory.setHorizontalAlignment(SwingConstants.CENTER);
        lblProductCategory.setBounds(12, 140, 100, 50);
        contentPane.add(lblProductCategory);

        textFieldProductCategory = new JTextField();
        textFieldProductCategory.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldProductCategory.setBounds(126, 140, 200, 50);
        contentPane.add(textFieldProductCategory);
        textFieldProductCategory.setColumns(10);

        JButton btnDeleteProduct = new JButton("삭제하기");
        btnDeleteProduct.setBounds(65, 211, 200, 40);
        contentPane.add(btnDeleteProduct);

        btnDeleteProduct.addActionListener(e -> deleteProductBtnAction());
    }

    private void deleteProductBtnAction(){
        try {
            // 입력된 제품 정보 가져오기
            int productNo = Integer.parseInt(textFieldProductNo.getText());
            String productName = textFieldProductCategory.getText(); // 카테고리에 이름 입력
            String productCategory = textFieldProductName.getText(); // 이름에 카테고리 입력

            // 제품 객체 생성
            ProductDTO productToDelete = new ProductDTO();
            productToDelete.setProduct_name(productName);
            productToDelete.setProduct_category(productCategory);
            productToDelete.setProduct_no(productNo);
            // 제품 삭제
            int result = productService.deleteProduct(productToDelete);
            if (result > 0) {
                JOptionPane.showMessageDialog(null, "제품이 삭제되었습니다.");
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "해당 제품이 존재하지 않습니다.");
                setVisible(false);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "잘못된 입력 형식입니다. 숫자를 입력해주세요.");
        }
    }
}
