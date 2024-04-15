package org.kdt.ui;

import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.kdt.dao.MemberDAO;
import org.kdt.dto.MemberDTO;
import org.kdt.dto.MemberRole;
import org.kdt.service.MemberService;
import org.kdt.service.MemberServiceImpl;
import org.kdt.ui.admin.ProductMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Login extends JFrame {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final MemberService memberService;
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
		EventQueue.invokeLater(() -> {
            try {
                Login frame = new Login();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		memberService = new MemberServiceImpl(new MemberDAO());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setBounds(33, 57, 57, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("패스워드");
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
		
		btnLogin = new JButton("로그인");
		btnLogin.setBounds(33, 165, 91, 23);
		contentPane.add(btnLogin);

		btnLogin.addActionListener(x -> loginBtnAction());

		btnSignUp = new JButton("회원가입");
		btnSignUp.setBounds(136, 165, 91, 23);
		contentPane.add(btnSignUp);

		btnSignUp.addActionListener(x -> insertMemberBtnAction());

	}

	private void loginBtnAction(){

		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMember_id(tfId.getText());
		memberDTO.setMember_passwd(tfPwd.getText());

		if(memberService.login(memberDTO)){
			log.info("로그인 성공");
			JOptionPane.showMessageDialog(null,"로그인이 성공하였습니다.");
			memberDTO = memberService.findbyId(memberDTO.getMember_id());
			if(memberDTO.getMember_role().equals(MemberRole.ADMIN.getRole())){
				log.info("memberDTO {}", memberDTO);
				ProductMain productMain = new ProductMain(memberDTO);
				productMain.setVisible(true);
			}
			setVisible(false);
		}else{
			JOptionPane.showMessageDialog(null,"아이디 또는 비밀번호가 일치하지 않습니다.");
			log.info("로그인 실패");
		}
	};
	private void insertMemberBtnAction(){
		InsertMember insertMember = new InsertMember();
		this.setVisible(false);
		insertMember.setVisible(true);
	};
}
