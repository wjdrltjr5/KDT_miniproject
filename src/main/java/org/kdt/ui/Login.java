package org.kdt.ui;

import org.kdt.dao.MemberDAO;
import org.kdt.dto.MemberDTO;
import org.kdt.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	private MemberService memberService;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfPwd;
	private JButton btnLogin;
	private JButton btnSignUp;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {

		memberService = new MemberService(new MemberDAO());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC544\uC774\uB514");
		lblNewLabel.setBounds(33, 57, 57, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uD328\uC2A4\uC6CC\uB4DC");
		lblNewLabel_1.setBounds(33, 111, 57, 31);
		contentPane.add(lblNewLabel_1);
		
		tfId = new JTextField();
		tfId.setBounds(102, 62, 96, 21);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		tfPwd = new JTextField();
		tfPwd.setBounds(102, 116, 96, 21);
		contentPane.add(tfPwd);
		tfPwd.setColumns(10);
		
		btnLogin = new JButton("\uB85C\uADF8\uC778");
		btnLogin.setBounds(33, 165, 91, 23);
		contentPane.add(btnLogin);

		btnLogin.addActionListener(login);

		btnSignUp = new JButton("\uD68C\uC6D0\uAC00\uC785");
		btnSignUp.setBounds(136, 165, 91, 23);
		contentPane.add(btnSignUp);

	}

	private ActionListener login = x -> {

		String id = tfId.getText();
		String pwd = tfPwd.getText();

		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMember_id(id);
		memberDTO.setMember_passwd(pwd);

		if(memberService.login(memberDTO)){
			log.info("로그인 성공");
			JOptionPane.showMessageDialog(null,"로그인이 성공하였습니다.");
		}else{
			JOptionPane.showMessageDialog(null,"아이디 또는 비밀번호가 일치하지 않습니다.");
			log.info("로그인 실패");
		}
	};
}
