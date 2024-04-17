package org.kdt.ui.user;

import org.kdt.dao.MemberDAO;
import org.kdt.dto.MemberDTO;
import org.kdt.service.MemberService;
import org.kdt.service.MemberServiceImpl;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ChargeMoneyForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	private MemberDTO memberDTO;

	private final MemberService memberService;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ChargeMoneyForm(MemberDTO memberDTO) {
		this.memberDTO = memberDTO;
		memberService = new MemberServiceImpl(new MemberDAO());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("충전할 금액");
		lblNewLabel.setBounds(12, 31, 77, 50);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(101, 32, 212, 50);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnChargeMoney = new JButton("충전하기");
		btnChargeMoney.setBounds(68, 105, 200, 40);
		contentPane.add(btnChargeMoney);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

		btnChargeMoney.addActionListener(e -> chargeMoneyBtnAction());
	}

	private void chargeMoneyBtnAction(){
		int member_balance = memberDTO.getMember_balance();
		int i = memberService.chargeMoney(memberDTO, Integer.parseInt(textField.getText()));
		if(i > 0){
			JOptionPane.showMessageDialog(null, "충전에 성공하였습니다.");
			setVisible(false);
		}else{
			memberDTO.setMember_balance(member_balance);
			JOptionPane.showMessageDialog(null, "충전에 실패하였습니다.");
			setVisible(false);
		}
	}
}
