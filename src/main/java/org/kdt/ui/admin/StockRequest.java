package org.kdt.ui.admin;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.kdt.dao.MembersProductDAO;
import org.kdt.dao.ProductDAO;
import org.kdt.dto.MembersProductDTO;
import org.kdt.service.MembersProductService;
import org.kdt.service.MembersProductServiceImpl;
import org.kdt.service.ProductService;
import org.kdt.service.ProductServiceImpl;

public class StockRequest extends JFrame {

	private JButton btnSelectAll; // 전체 테이블 조회버튼
	private JTable table; // 테이블
	private ProductService productService;
	private final MembersProductService membersProductService;

	JComboBox<String> comboBox; // 검색조건box
	private JTextField searchBar; // 검색bar
	JButton searchButton; // 검색button
	private JTextField textField; // 검색된 항목수 표시
	private JButton restartButton; // 초기화

	private String message = "전체 테이블의 항목 수: 검색된항목의수가 없습니다.";
	private JButton btnPermit;
	private JButton btnFailure;

	// ProductMain
	public StockRequest() {
		membersProductService = new MembersProductServiceImpl(new MembersProductDAO(), new ProductDAO());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1065, 632);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.decode("#DCDCDC"));

		btnSelectAll = new JButton("전체테이블");
		btnSelectAll.setBounds(926, 90, 111, 85);
		btnSelectAll.setBackground(Color.decode("#483D8B"));
		btnSelectAll.setForeground(Color.white);
		getContentPane().add(btnSelectAll);
		setTitle("입고요청처리");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(181, 72, 733, 471);
		getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		comboBox = new JComboBox<String>();
		comboBox.setBounds(181, 27, 111, 23);
		getContentPane().add(comboBox);

		searchBar = new JTextField();
		searchBar.setBounds(304, 28, 491, 21);
		getContentPane().add(searchBar);
		searchBar.setColumns(10);

		searchButton = new JButton("검색");
		searchButton.setBounds(807, 27, 107, 23);
		searchButton.setBackground(Color.black);
		searchButton.setForeground(Color.white);
		getContentPane().add(searchButton);

		textField = new JTextField();
		textField.setText(message);
		textField.setBounds(181, 553, 733, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		restartButton = new JButton("초기화");
		restartButton.setBounds(926, 470, 111, 23);
		restartButton.setBackground(Color.black);
		restartButton.setForeground(Color.white);
		getContentPane().add(restartButton);
		
		btnPermit = new JButton("요청 허가");
		btnPermit.setBounds(926, 185, 111, 85);
		btnPermit.setBackground(Color.decode("#778899"));
		btnPermit.setForeground(Color.white);
		getContentPane().add(btnPermit);

		btnFailure = new JButton("반려하기");
		btnFailure.setBounds(926, 280, 111, 85);
		btnFailure.setBackground(Color.decode("#DC143C"));
		btnFailure.setForeground(Color.white);
		getContentPane().add(btnFailure);
		
	
		comboBox.addItem("전체품목");
		comboBox.addItem("제품이름");
		comboBox.addItem("카테고리");

		ProductDAO dao = new ProductDAO();
		productService = new ProductServiceImpl(dao);

		btnPermit.addActionListener(e -> permitBtnAction());

		btnFailure.addActionListener(e -> failureBtnAction());

		btnSelectAll.addActionListener(e -> selectAllBtnAction());

		searchButton.addActionListener(e -> searchBtnAction());
		
		restartButton.addActionListener(e -> restartBtnAction());

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });


	} // ProductMain END.
	private void failureBtnAction(){
		Failure failure = new Failure();
		failure.setVisible(true);
	}

	private void permitBtnAction(){
		Permit permit = new Permit();
		permit.setVisible(true);
	}
	private void selectAllBtnAction(){
		loadTableData();
		// 테이블에 표시된 전체 항목 수를 텍스트 필드에 표시
		int totalRowCount = table.getRowCount();
		textField.setText("전체 테이블의 항목 수: " + totalRowCount);
	}

	private void restartBtnAction(){
		// 검색어 입력 필드 초기화
		searchBar.setText("");
		// 검색 조건 선택 상자 초기화
		comboBox.setSelectedIndex(0);

		// 테이블 데이터 초기화
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0); // 모든 행을 삭제하여 테이블을 비웁니다.
		model.setColumnCount(0); // 모든 열을 삭제하여 테이블을 비웁니다.

		// 하단 텍스트 필드 초기화
		textField.setText(message);
	}
	private void searchBtnAction(){
		String selectedCategory = comboBox.getSelectedItem().toString();
		String searchKeyword = searchBar.getText();
		List<MembersProductDTO> searchResults = null;
		if (selectedCategory.equals("전체품목")) {
			searchResults = membersProductService.selectAllProducts(searchKeyword);
		} else if (selectedCategory.equals("카테고리")) {
			searchResults = membersProductService.selectProductsByCategory(searchKeyword);
		} else if (selectedCategory.equals("제품이름")) {
			searchResults = membersProductService.searchProductByName(searchKeyword);
		}
		displaySearchResults(searchResults);
	}
	private void loadTableData() {
		DefaultTableModel model = getDefaultTableModel();
		model.setRowCount(0);
		List<MembersProductDTO> lists = membersProductService.findByStatusHold();

		for(MembersProductDTO dto : lists){
			model.addRow(
					new Object[]{dto.getMember_product_no(),dto.getMember_no(), dto.getMember_name(), dto.getProduct_no(),
							dto.getProduct_name(), dto.getProduct_category(), dto.getProduct_quantity(), dto.getStatus()});
		}

		table.setModel(model);
	} // 전체테이블조회 END.

	private DefaultTableModel getDefaultTableModel() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Order No");
		model.addColumn("Member No");
		model.addColumn("Member Name");
		model.addColumn("Product No");
		model.addColumn("Name");
		model.addColumn("Category");
		model.addColumn("Request Quantity");
		return model;
	}

	private void displaySearchResults(List<MembersProductDTO> searchResults) {
		DefaultTableModel model = getDefaultTableModel();

		int numOfResults = 0; // 검색된 항목의 수를 저장하기 위한 변수

		if (searchResults != null) {

			for (MembersProductDTO dto : searchResults) {
				model.addRow(
						new Object[]{dto.getMember_product_no(),dto.getMember_no(), dto.getMember_name(), dto.getProduct_no(),
								dto.getProduct_name(), dto.getProduct_category(), dto.getProduct_quantity(), dto.getStatus()});

				numOfResults++; // 검색된 항목의 수를 증가시킴
			}
		}

		textField.setText("검색된 항목의 수: " + numOfResults);

		table.setModel(model);
	} // 검색시 결과 END.

}
