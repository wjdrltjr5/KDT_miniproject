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
import org.kdt.dto.MembersProductStatus;
import org.kdt.exception.NoMoneyException;
import org.kdt.service.MembersProductService;
import org.kdt.service.MembersProductServiceImpl;

public class UserStockRequestForm extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldProductNo;
    private JTextField textFieldProductQuantity;

    private final MembersProductService membersProductService;
    private MemberDTO memberDTO;


    public UserStockRequestForm(MemberDTO memberDTO) {
        membersProductService = new MembersProductServiceImpl(new MembersProductDAO(),new ProductDAO(),new MemberDAO());
        this.memberDTO = memberDTO;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 350, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(Color.decode("#DCDCDC"));
        setTitle("입고요청");

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblProductName = new JLabel("제품번호");
        lblProductName.setBounds(12, 20, 79, 50);
        lblProductName.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblProductName);

        JLabel lblProductQuantity = new JLabel("수량");
        lblProductQuantity.setHorizontalAlignment(SwingConstants.CENTER);
        lblProductQuantity.setBounds(12, 93, 79, 50);
        contentPane.add(lblProductQuantity);

        textFieldProductNo = new JTextField();
        textFieldProductNo.setBounds(103, 20, 223, 50);
        textFieldProductNo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(textFieldProductNo);
        textFieldProductNo.setColumns(10);

        textFieldProductQuantity = new JTextField();
        textFieldProductQuantity.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldProductQuantity.setColumns(10);
        textFieldProductQuantity.setBounds(103, 94, 221, 50);
        contentPane.add(textFieldProductQuantity);

        JButton btnInsertProduct = new JButton("입고요청하기");
        btnInsertProduct.setBounds(68, 176, 200, 40);
        btnInsertProduct.setBackground(Color.decode("#778899"));
        btnInsertProduct.setForeground(Color.white);
        contentPane.add(btnInsertProduct);

        btnInsertProduct.addActionListener(e -> insertProductBtnAction());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    private void insertProductBtnAction() {
        try {
            // 입력된 제품 정보 가져오기
            MembersProductDTO dto = new MembersProductDTO();
            dto.setProduct_no(textFieldProductNo.getText());
            dto.setProduct_quantity(Integer.parseInt(textFieldProductQuantity.getText()));
            dto.setMember_no(String.valueOf(memberDTO.getMember_no()));
            dto.setStatus(MembersProductStatus.HOLD.getText());
            // 제품 추가
            int result = membersProductService.requestStock(dto,memberDTO);
            if (result > 0) {
                JOptionPane.showMessageDialog(null, "제품이 요청되었습니다.");
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "제품 요청이 실패했습니다.");
                setVisible(false);
            }
        }catch (NoMoneyException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ChargeMoneyForm chargeMoneyForm = new ChargeMoneyForm(memberDTO);
            chargeMoneyForm.setVisible(true);
        }catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "잘못된 입력 형식입니다. 숫자를 입력해주세요.");

        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
