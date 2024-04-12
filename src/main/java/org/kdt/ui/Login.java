package org.kdt.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfPwd;

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
		
		JButton btnLogin = new JButton("\uB85C\uADF8\uC778");
		btnLogin.setBounds(33, 165, 91, 23);
		contentPane.add(btnLogin);
		
		JButton btnSignUp = new JButton("\uD68C\uC6D0\uAC00\uC785");
		btnSignUp.setBounds(136, 165, 91, 23);
		contentPane.add(btnSignUp);
	}
}
