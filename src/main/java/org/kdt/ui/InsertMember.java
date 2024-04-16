package org.kdt.ui;

import java.awt.Color;

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

public class InsertMember extends JFrame {
	private final MemberService memberService;
	private JPanel contentPane;
	private JTextField textField_id;
	private JTextField textField_pw;
	private JTextField textField_chpw;
	private JTextField textField_em;
	private JTextField textField_name;



	/**
	 * Create the frame.
	 */
	public InsertMember() {
		memberService = new MemberServiceImpl(new MemberDAO());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.decode("#DCDCDC"));
		setTitle("회원가입");

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblID = new JLabel("ID");
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setBounds(12, 106, 72, 30);
		contentPane.add(lblID);
		
		JLabel lblNewLabel_1 = new JLabel("회원가입");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 10, 286, 25);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblPw = new JLabel("PW");
		lblPw.setHorizontalAlignment(SwingConstants.CENTER);
		lblPw.setBounds(12, 158, 72, 30);
		contentPane.add(lblPw);
		
		JLabel lblChPw = new JLabel("Ch-PW");
		lblChPw.setHorizontalAlignment(SwingConstants.CENTER);
		 lblChPw.setBounds(12, 211, 72, 30);
		contentPane.add( lblChPw);
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(12, 54, 72, 30);
		contentPane.add(lblName);
		
		JLabel lblEM = new JLabel("E/M");
		lblEM.setHorizontalAlignment(SwingConstants.CENTER);
		lblEM.setBounds(12, 258, 72, 30);
		contentPane.add(lblEM);
		
		textField_name = new JTextField();
		textField_name.setBounds(96, 54, 178, 25);
		contentPane.add(textField_name);
		textField_name.setColumns(10);
		
		textField_id = new JTextField();
		textField_id.setColumns(10);
		textField_id.setBounds(96, 107, 178, 25);
		contentPane.add(textField_id);
		
		textField_pw = new JTextField();
		textField_pw.setColumns(10);
		textField_pw.setBounds(96, 159, 178, 25);
		contentPane.add(textField_pw);
		
		textField_chpw = new JTextField();
		textField_chpw.setColumns(10);
		textField_chpw.setBounds(96, 212, 178, 25);
		contentPane.add(textField_chpw);
		
		textField_em = new JTextField();
		textField_em.setColumns(10);
		textField_em.setBounds(96, 261, 178, 25);
		contentPane.add(textField_em);
		
		JButton btnInsertMember = new JButton("회원가입");
		btnInsertMember.setBounds(83, 333, 108, 38);
		btnInsertMember.setBackground(Color.black);
		btnInsertMember.setForeground(Color.white);
		contentPane.add(btnInsertMember);
		
		btnInsertMember.addActionListener(x -> signUpBtnAction());
	}
	private void signUpBtnAction(){
		String name = textField_name.getText();
		String id = textField_id.getText();
		String pw = textField_pw.getText();
		String chpw = textField_chpw.getText();
		String em = textField_em.getText();

		if (name.isEmpty() || id.isEmpty() || pw.isEmpty() || chpw.isEmpty() || em.isEmpty()) {
			JOptionPane.showMessageDialog(null, "모든 입력란을 채워주세요.", "경고", JOptionPane.WARNING_MESSAGE);
		} else if (!pw.equals(chpw)) {
			JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다. 다시 확인해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
		} else {
			MemberDTO memberDTO = new MemberDTO();
			memberDTO.setMember_name(name);
			memberDTO.setMember_id(id);
			memberDTO.setMember_passwd(pw);
			memberDTO.setMember_email(em);
			int i = memberService.insertMember(memberDTO);
			if(i == -1){
				JOptionPane.showMessageDialog(null, "아이디 또는 이메일 중복입니다.", "회원가입 실패", JOptionPane.INFORMATION_MESSAGE);
			}else {

			JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.", "회원가입 성공", JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
			new Login().setVisible(true);
			}
		}
	};
}
