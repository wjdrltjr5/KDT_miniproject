package org.kdt.ui.admin;

import java.awt.Color;
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

import org.kdt.dao.MembersProductDAO;
import org.kdt.dao.ProductDAO;
import org.kdt.service.MembersProductService;
import org.kdt.service.MembersProductServiceImpl;

public class Permit extends JFrame {

	private static final long serialVersionUID = 1L;

	private final MembersProductService membersProductService;
	private JPanel contentPane;
	private JTextField orderNo;

	public Permit() {
		setTitle("허가하기");
		membersProductService = new MembersProductServiceImpl(new MembersProductDAO(), new ProductDAO());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("주문번호");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 33, 100, 50);
		contentPane.add(lblNewLabel);

		orderNo = new JTextField();
		orderNo.setBounds(126, 34, 200, 50);
		contentPane.add(orderNo);
		orderNo.setColumns(10);
		
		JButton btnPermit = new JButton("허가하기");
		btnPermit.setBounds(79, 113, 200, 40);
		btnPermit.setBackground(Color.decode("#778899"));
		btnPermit.setForeground(Color.white);
		contentPane.add(btnPermit);

		btnPermit.addActionListener(e -> permitBtnAction());

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
	}

	private void permitBtnAction(){
		int i = membersProductService.requestOrderPermit(orderNo.getText());
		if(i < 0){
			JOptionPane.showMessageDialog(null, "허가가 불가능 합니다.");
			setVisible(false);
		}else {
			JOptionPane.showMessageDialog(null, "허가하였습니다.");
			setVisible(false);
		}
	}
}
