package org.kdt.ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import org.kdt.dto.MemberDTO;
import org.kdt.service.MemberService;
import org.kdt.service.MemberServiceImpl;
import java.awt.Color;
import java.awt.SystemColor;

public class FindPasswd extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private MemberService memberService;

	public FindPasswd() {
		memberService = new MemberServiceImpl(new MemberDAO());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(163, 74, 115, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(163, 118, 115, 27);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(163, 166, 115, 27);
		contentPane.add(textField_2);
		
		JLabel lblNewLabel = new JLabel("이름");
		lblNewLabel.setBounds(79, 80, 50, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(79, 124, 50, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setBounds(79, 172, 50, 15);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("비밀번호 찾기");
		lblNewLabel_3.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(106, 22, 139, 33);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("찾기");
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setForeground(SystemColor.textHighlightText);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(132, 214, 96, 27);
		contentPane.add(btnNewButton);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		btnNewButton.addActionListener(e -> findPasswdBtnAction());
	}
	private void findPasswdBtnAction() {
		String name = textField.getText();
		String id = textField_1.getText();
		String email = textField_2.getText();
		
		MemberDTO dto = new MemberDTO();
		dto.setMember_name(name);
		dto.setMember_id(id);
		dto.setMember_email(email);
		
		String passwd = memberService.findPasswd(dto);
		
		if (passwd != null) {
			JOptionPane.showMessageDialog(this, "비밀번호는 " + passwd + " 입니다.");
			
			this.setVisible(false);
			
		} else {
			JOptionPane.showMessageDialog(this, "비밀번호를 찾을 수 없습니다.");
		}
	}
}
