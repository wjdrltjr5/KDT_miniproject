package org.kdt.ui.admin;

import org.kdt.dao.MembersProductDAO;
import org.kdt.dao.ProductDAO;
import org.kdt.service.MembersProductService;
import org.kdt.service.MembersProductServiceImpl;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Failure extends JFrame {

	private static final long serialVersionUID = 1L;

	private final MembersProductService membersProductService;
	private JPanel contentPane;
	private JTextField orderNo;

	public Failure() {
		membersProductService = new MembersProductServiceImpl(new MembersProductDAO(), new ProductDAO());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("주문번호");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 20, 100, 50);
		contentPane.add(lblNewLabel);

		orderNo = new JTextField();
		orderNo.setBounds(126, 20, 200, 50);
		contentPane.add(orderNo);
		orderNo.setColumns(10);
		
		JButton btnFailure = new JButton("반려하기");
		btnFailure.setBounds(126, 106, 200, 40);
		contentPane.add(btnFailure);

		btnFailure.addActionListener(e -> btnFailureBtnAction());

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
	}

	private void btnFailureBtnAction(){
		int i = membersProductService.requestOrderFailure(orderNo.getText());
		if(i < 0){
			JOptionPane.showMessageDialog(null, "해당 주문이 존재하지 않습니다.");
			setVisible(false);
		}else {
			JOptionPane.showMessageDialog(null, "반려하였습니다.");
			setVisible(false);
		}
	}
}
