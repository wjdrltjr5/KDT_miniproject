package org.kdt.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	MemberService memberService;
	private JPanel contentPane;
	private JTextField textField_id;
	private JTextField textField_pw;
	private JTextField textField_chpw;
	private JTextField textField_em;
	private JTextField textField_name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertMember frame = new InsertMember();
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
	public InsertMember() {
		
		memberService = new MemberServiceImpl();
		memberService.setMemberDAO(new MemberDAO());
		MemberDTO memberDTO = new MemberDTO();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 506, 529);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblID = new JLabel("ID");
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setBounds(83, 187, 102, 25);
		contentPane.add(lblID);
		
		JLabel lblNewLabel_1 = new JLabel("회원가입");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(137, 86, 204, 25);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblPw = new JLabel("PW");
		lblPw.setHorizontalAlignment(SwingConstants.CENTER);
		lblPw.setBounds(83, 232, 102, 25);
		contentPane.add(lblPw);
		
		JLabel lblChPw = new JLabel("Ch-PW");
		lblChPw.setHorizontalAlignment(SwingConstants.CENTER);
		 lblChPw.setBounds(83, 278, 102, 25);
		contentPane.add( lblChPw);
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(83, 138, 102, 25);
		contentPane.add(lblName);
		
		JLabel lblEM = new JLabel("E/M");
		lblEM.setHorizontalAlignment(SwingConstants.CENTER);
		lblEM.setBounds(83, 325, 102, 25);
		contentPane.add(lblEM);
		
		textField_name = new JTextField();
		textField_name.setBounds(186, 139, 155, 23);
		contentPane.add(textField_name);
		textField_name.setColumns(10);
		
		textField_id = new JTextField();
		textField_id.setColumns(10);
		textField_id.setBounds(186, 188, 155, 23);
		contentPane.add(textField_id);
		
		textField_pw = new JTextField();
		textField_pw.setColumns(10);
		textField_pw.setBounds(186, 233, 155, 23);
		contentPane.add(textField_pw);
		
		textField_chpw = new JTextField();
		textField_chpw.setColumns(10);
		textField_chpw.setBounds(186, 279, 155, 23);
		contentPane.add(textField_chpw);
		
		textField_em = new JTextField();
		textField_em.setColumns(10);
		textField_em.setBounds(186, 327, 155, 23);
		contentPane.add(textField_em);
		
		JButton btnInsertMember = new JButton("회원가입");
		btnInsertMember.setBounds(183, 394, 91, 23);
		contentPane.add(btnInsertMember);
		
		btnInsertMember.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
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
		            memberDTO.setMember_name(name);
		            memberDTO.setMember_id(id);
		            memberDTO.setMember_passwd(pw);
		            memberDTO.setMember_email(em);
		            int result = memberService.insertMember(memberDTO);
		        }
			}
		});
	}
}
