package org.kdt;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.kdt.dao.ProductDAO;
import org.kdt.dto.ProductDTO;
import org.kdt.service.ProductService;
import org.kdt.service.ProductServiceImpl;

public class ProductMain extends JFrame {

    private JButton btnSelectAll;
    private JTable table;
    private ProductService productService;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ProductMain frame = new ProductMain();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

 // ProductMain 클래스의 일부분
    public ProductMain() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1065, 632);
        getContentPane().setLayout(null);

        btnSelectAll = new JButton("전체테이블");
        btnSelectAll.setBounds(29, 170, 111, 85);
        getContentPane().add(btnSelectAll);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(181, 38, 733, 505);
        getContentPane().add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        // ProductServiceImpl 객체 생성
        ProductService productService = new ProductServiceImpl();

        // ProductDAO 객체 생성
        ProductDAO productDAO = new ProductDAO();
        // ProductServiceImpl 객체에 ProductDAO 주입
        ((ProductServiceImpl) productService).setProductDAO(productDAO);

        btnSelectAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // 서비스를 통해 데이터 가져오기
                    List<ProductDTO> productList = productService.findProductAll();
                    // 테이블에 표시
                    displayProductList(productList);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "데이터를 가져오는 중 오류가 발생했습니다: " + ex.getMessage(), "오류",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }


    private void displayProductList(List<ProductDTO> productList) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // 기존 행 제거

        for (ProductDTO product : productList) {
            model.addRow(new Object[] { product.getProduct_no(), product.getProduct_category(),
                    product.getProduct_name(), product.getProduct_date(), product.getProduct_price(),
                    product.getProduct_quantity() });
        }
    }
}
