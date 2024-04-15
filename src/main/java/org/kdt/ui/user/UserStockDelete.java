package org.kdt.ui.user;

import org.kdt.dao.MembersProductDAO;
import org.kdt.dao.ProductDAO;
import org.kdt.dto.MemberDTO;
import org.kdt.dto.MembersProductDTO;
import org.kdt.service.MembersProductService;
import org.kdt.service.MembersProductServiceImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

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
        membersProductService = new MembersProductServiceImpl(new MembersProductDAO(), new ProductDAO());
        this.memberDTO = memberDTO;
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

        JButton btnDeleteProduct = new JButton("삭제하기");
        btnDeleteProduct.setBounds(65, 211, 200, 40);
        contentPane.add(btnDeleteProduct);

        btnDeleteProduct.addActionListener(e -> deleteProductBtnAction());
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
