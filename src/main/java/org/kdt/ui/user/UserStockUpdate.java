package org.kdt.ui.user;

import org.kdt.dao.MembersProductDAO;
import org.kdt.dao.ProductDAO;
import org.kdt.dto.MemberDTO;
import org.kdt.dto.MembersProductDTO;
import org.kdt.service.MembersProductService;
import org.kdt.service.MembersProductServiceImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UserStockUpdate extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldProductNo;
    private JTextField textFieldProductQuantity;

    private final MembersProductService membersProductService;
    private MemberDTO memberDTO;

    /**
     * Launch the application.
     */

    /**
     * Create the frame.
     */
    public UserStockUpdate(MemberDTO memberDTO) {
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

        JLabel lblProductQuantity = new JLabel("수량");
        lblProductQuantity.setHorizontalAlignment(SwingConstants.CENTER);
        lblProductQuantity.setBounds(12, 100, 100, 50);
        contentPane.add(lblProductQuantity);

        textFieldProductQuantity = new JTextField();
        textFieldProductQuantity.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldProductQuantity.setBounds(126, 100, 200, 50);
        contentPane.add(textFieldProductQuantity);
        textFieldProductNo.setColumns(10);

        JButton btnUpdateProduct = new JButton("수정하기");
        btnUpdateProduct.setBounds(65, 211, 200, 40);
        contentPane.add(btnUpdateProduct);

        btnUpdateProduct.addActionListener(e -> deleteUpdateBtnAction());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    private void deleteUpdateBtnAction(){
        try {
            // 입력된 제품 정보 가져오기
            MembersProductDTO dto = new MembersProductDTO();
            dto.setProduct_no(textFieldProductNo.getText());
            dto.setMember_no(String.valueOf(memberDTO.getMember_no()));
            dto.setProduct_quantity(Integer.parseInt(textFieldProductQuantity.getText()));

            int result = membersProductService.updateStock(dto);
            if (result > 0) {
                JOptionPane.showMessageDialog(null, "제품이 수정되었습니다.");
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
