package org.kdt.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

public class Main extends JFrame {

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
					Main frame = new Main();
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
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("\uC785\uACE0\uC694\uCCAD");
		btnNewButton.setBounds(16, 5, 95, 150);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\uC7AC\uACE0\uC218\uC815");
		btnNewButton_1.setBounds(16, 155, 95, 150);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\uC7AC\uACE0\uC0AD\uC81C");
		btnNewButton_2.setBounds(16, 305, 95, 150);
		contentPane.add(btnNewButton_2);
		
		textField = new JTextField();
		textField.setBounds(210, 5, 329, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("\uAC80\uC0C9");
		btnNewButton_3.setBounds(540, 5, 60, 23);
		contentPane.add(btnNewButton_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(120, 5, 84, 23);
		contentPane.add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(130, 38, 411, 385);
		contentPane.add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JButton btnNewButton_4 = new JButton("\uAD00\uB9AC\uC790\uC6A9\uC7AC\uACE0\uCD94\uAC00");
		btnNewButton_4.setBounds(550, 400, 123, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("\uC804\uCCB4 \uD14C\uC774\uBE14");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_5.setBounds(550, 69, 123, 23);
		contentPane.add(btnNewButton_5);
	}
}
