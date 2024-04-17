package org.kdt.ui.user;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.kdt.dao.MemberDAO;
import org.kdt.dao.MembersProductDAO;
import org.kdt.dao.ProductDAO;
import org.kdt.dto.MemberDTO;
import org.kdt.dto.MembersProductDTO;
import org.kdt.service.MembersProductService;
import org.kdt.service.MembersProductServiceImpl;

public class UserStockDelete extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldProductNo;

    private final MembersProductService membersProductService;
    private MemberDTO memberDTO;

    /**
     * Launch the application.
     */

    /**
     * Create the frame.
     */
    public UserStockDelete(MemberDTO memberDTO) {
        membersProductService = new MembersProductServiceImpl(new MembersProductDAO(), new ProductDAO(),new MemberDAO());
        this.memberDTO = memberDTO;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 350, 200);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(Color.decode("#DCDCDC"));
        setTitle("재고삭제");

        JLabel lblProductNo = new JLabel("제품번호");
        lblProductNo.setHorizontalAlignment(SwingConstants.CENTER);
        lblProductNo.setBounds(12, 31, 77, 50);
        contentPane.add(lblProductNo);

        textFieldProductNo = new JTextField();
        textFieldProductNo.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldProductNo.setBounds(101, 32, 212, 50);
        contentPane.add(textFieldProductNo);
        textFieldProductNo.setColumns(10);

        JButton btnDeleteProduct = new JButton("삭제하기");
        btnDeleteProduct.setBounds(68, 105, 200, 40);
        btnDeleteProduct.setBackground(Color.decode("#DC143C"));
        btnDeleteProduct.setForeground(Color.white);
        contentPane.add(btnDeleteProduct);

        btnDeleteProduct.addActionListener(e -> deleteProductBtnAction());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    private void deleteProductBtnAction(){
        try {
            // 입력된 제품 정보 가져오기
            MembersProductDTO dto = new MembersProductDTO();
            dto.setProduct_no(textFieldProductNo.getText());
            dto.setMember_no(String.valueOf(memberDTO.getMember_no()));

            int result = membersProductService.deleteStock(dto);
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
