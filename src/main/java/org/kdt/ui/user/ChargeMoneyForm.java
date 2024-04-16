package org.kdt.ui.user;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ChargeMoneyForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChargeMoneyForm frame = new ChargeMoneyForm();
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
	public ChargeMoneyForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(12, 31, 77, 50);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(101, 32, 212, 50);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(68, 105, 200, 40);
		contentPane.add(btnNewButton);
	}
}
