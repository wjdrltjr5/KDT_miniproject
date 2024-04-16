package org.kdt.ui.admin;

import org.kdt.dao.ProductDAO;
import org.kdt.dto.ProductDTO;
import org.kdt.service.ProductService;
import org.kdt.service.ProductServiceImpl;

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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

import java.util.List;

public class StockModify extends JFrame {

    private static final long serialVersionUID = 1L;
    private final ProductService productService;
    private JPanel contentPane;
    private JTextField textFieldProductName;
    /**
     * Launch the application.
     */

    /**
     * Create the frame.
     */
    public StockModify() {
        productService = new ProductServiceImpl(new ProductDAO());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 360, 230);
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

        JButton btnSearchProduct = new JButton("검색하기");
        btnSearchProduct.addActionListener(e -> searchProductBtnAction());
        btnSearchProduct.setBounds(126, 80, 200, 50);
    
        contentPane.add(btnSearchProduct);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        }
    private void searchProductBtnAction(){
        // 입력된 제품 이름 가져오기
        String productName = textFieldProductName.getText();
        List<ProductDTO> list = productService.searchProductByName(productName);
        if (list.size() == 0) {
            JOptionPane.showMessageDialog(contentPane, "해당하는 제품이 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
            setVisible(false);
        }
        for (ProductDTO dto : list) {
            ModifyProductInfo modifyProductInfo = new ModifyProductInfo(dto);
            modifyProductInfo.setVisible(true);
        }
        setVisible(false);
    }

}

class ModifyProductInfo extends JFrame {
    private final ProductService productService;
    private ProductDTO productDTO;
    private JPanel contentPane;
    private JTextField textFieldProductName;
    private JTextField textFieldProductCategory;
    private JTextField textFieldProductPrice;
    private JTextField textFieldProductQuantity;

    public ModifyProductInfo(ProductDTO productDTO) {
        this.productDTO = productDTO;
        productService = new ProductServiceImpl(new ProductDAO());

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

        textFieldProductName = new JTextField(productDTO.getProduct_name());
        textFieldProductName.setBounds(126, 20, 200, 50);
        contentPane.add(textFieldProductName);
        textFieldProductName.setColumns(10);

        JLabel lblProductCategory = new JLabel("제품카테고리");
        lblProductCategory.setBounds(12, 80, 100, 50);
        lblProductCategory.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblProductCategory);

        textFieldProductCategory = new JTextField(productDTO.getProduct_category());
        textFieldProductCategory.setColumns(10);
        textFieldProductCategory.setBounds(126, 80, 200, 50);
        contentPane.add(textFieldProductCategory);

        JLabel lblProductPrice = new JLabel("제품가격");
        lblProductPrice.setBounds(12, 140, 100, 50);
        lblProductPrice.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblProductPrice);

        textFieldProductPrice = new JTextField(String.valueOf(productDTO.getProduct_price()));
        textFieldProductPrice.setColumns(10);
        textFieldProductPrice.setBounds(126, 140, 200, 50);
        contentPane.add(textFieldProductPrice);

        JLabel lblProductQuantity = new JLabel("수량");
        lblProductQuantity.setBounds(12, 200, 100, 50);
        lblProductQuantity.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblProductQuantity);

        textFieldProductQuantity = new JTextField(String.valueOf(productDTO.getProduct_quantity()));
        textFieldProductQuantity.setColumns(10);
        textFieldProductQuantity.setBounds(126, 200, 200, 50);
        contentPane.add(textFieldProductQuantity);

        JButton btnModifyProduct = new JButton("수정하기");
        btnModifyProduct.addActionListener(e -> modifyProductBtnAction());
        btnModifyProduct.setBounds(65, 281, 200, 40);
        contentPane.add(btnModifyProduct);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    private void modifyProductBtnAction(){
        // 수정된 제품 정보 가져오기
        String modifiedProductName = textFieldProductName.getText();
        String modifiedProductCategory = textFieldProductCategory.getText();
        String modifiedProductPrice = textFieldProductPrice.getText();
        String modifiedProductQuantity = textFieldProductQuantity.getText();

        productDTO.setProduct_name(modifiedProductName);
        productDTO.setProduct_category(modifiedProductCategory);
        productDTO.setProduct_price(Integer.parseInt(modifiedProductPrice));
        productDTO.setProduct_quantity(Integer.parseInt(modifiedProductQuantity));

        int rowsAffected = productService.updateProduct(productDTO);

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(contentPane, "제품 정보가 성공적으로 수정되었습니다.", "성공",
                    JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
        } else {
            JOptionPane.showMessageDialog(contentPane, "제품 정보 수정에 실패했습니다.", "오류",
                    JOptionPane.ERROR_MESSAGE);
            setVisible(false);
        }


    }
}
