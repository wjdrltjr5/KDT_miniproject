package org.kdt.ui.user;

import java.awt.Color;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.kdt.dao.MemberDAO;
import org.kdt.dao.MembersProductDAO;
import org.kdt.dao.ProductDAO;
import org.kdt.dto.MemberDTO;
import org.kdt.dto.MembersProductDTO;
import org.kdt.service.MembersProductService;
import org.kdt.service.MembersProductServiceImpl;
import org.kdt.service.ProductService;
import org.kdt.ui.Login;

public class UserMain extends JFrame {

	private JButton btnSelectAll; // 전체 테이블 조회버튼
	private JTable table; // 테이블
	private ProductService productService;
	private MembersProductService membersProductService;
	private MemberDTO memberDTO;
	JComboBox<String> comboBox; // 검색조건box
	private JTextField searchBar; // 검색bar
	JButton searchButton; // 검색button
	private JTextField textField; // 검색된 항목수 표시
	private JButton restartButton; // 초기화
	private JButton btnStockRequest;
	private JButton logOutButton;

	private JButton btnStockModify;
	private JButton btnStockDelete;
	private String message = "전체 테이블의 항목 수: 검색된항목의수가 없습니다.              "
			+ "               ※가격으로 검색시 클릭후 바로 검색을 눌러서 범위를 설정하시오.※";

	// ProductMain
	public UserMain(MemberDTO memberDTO) {
		this.memberDTO = memberDTO;
		membersProductService = new MembersProductServiceImpl(new MembersProductDAO(), new ProductDAO(),new MemberDAO());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1065, 632);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.decode("#DCDCDC"));
		setTitle("재고관리시스템(개인)");

		btnSelectAll = new JButton("전체테이블");
		btnSelectAll.setBounds(926, 90, 111, 85);
		btnSelectAll.setBackground(Color.decode("#483D8B"));
		btnSelectAll.setForeground(Color.white);
		getContentPane().add(btnSelectAll);

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
		restartButton.setBounds(926, 185, 111, 23);
		restartButton.setBackground(Color.black);
		restartButton.setForeground(Color.white);
		getContentPane().add(restartButton);
		
		logOutButton = new JButton("로그아웃");
	    logOutButton.setBounds(926, 553, 111, 23);
	    logOutButton.setBackground(Color.decode("#FF4500"));
	    logOutButton.setForeground(Color.white);
	    getContentPane().add(logOutButton);
		
		btnStockRequest = new JButton("입고 요청 하기");
		btnStockRequest.setBounds(12, 80, 160, 95);
		btnStockRequest.setBackground(Color.decode("#778899"));
		btnStockRequest.setForeground(Color.white);
		getContentPane().add(btnStockRequest);
		
		btnStockModify = new JButton("재고 수정");
		btnStockModify.setBounds(12, 185, 160, 95);
		btnStockModify.setBackground(Color.decode("#008B8B"));
		btnStockModify.setForeground(Color.white);
		getContentPane().add(btnStockModify);
		
		btnStockDelete = new JButton("재고 삭제");
		btnStockDelete.setBounds(12, 290, 160, 95);
		btnStockDelete.setBackground(Color.decode("#DC143C"));
		btnStockDelete.setForeground(Color.white);
		getContentPane().add(btnStockDelete);

		comboBox.addItem("전체품목");
		comboBox.addItem("제품이름");
		comboBox.addItem("카테고리");
		btnStockRequest.addActionListener(e ->stockRequestBtnAction());

		btnStockDelete.addActionListener(e -> stockDeleteBtnAction());

		btnStockModify.addActionListener(e -> stockUpdateBtnAction());

		btnSelectAll.addActionListener(e -> selectAllBtnAction());

		searchButton.addActionListener(e -> searchBtnAction());
		
		restartButton.addActionListener(e -> restartBtnAction());
		
		logOutButton.addActionListener(e -> logOutBtnAction());
		
		




	} // ProductMain END.

	private void logOutBtnAction() {
		Login loginPage = new Login();
	    loginPage.setVisible(true);
	    dispose();
	}

	private void stockUpdateBtnAction() {
		UserStockUpdate userStockUpdate = new UserStockUpdate(memberDTO);
		userStockUpdate.setVisible(true);
	}

	private void stockDeleteBtnAction(){
		UserStockDelete userStockDelete = new UserStockDelete(memberDTO);
		userStockDelete.setVisible(true);
	}

	private void stockRequestBtnAction(){
		UserStockRequset userStockRequset = new UserStockRequset(memberDTO);
		userStockRequset.setVisible(true);
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
			searchResults = membersProductService.findByMemberNoAndProductsCategoryOrProductName(memberDTO,searchKeyword);
		} else if (selectedCategory.equals("카테고리")) {
			searchResults = membersProductService.findByMemberNoAndProductsCategory(memberDTO,searchKeyword);
		} else if (selectedCategory.equals("제품이름")) {
			searchResults = membersProductService.findByMemberNoAndProductsName(memberDTO,searchKeyword);
		}
		displaySearchResults(searchResults);
	}
	private void loadTableData() {
		DefaultTableModel model = getDefaultTableModel();
		model.setRowCount(0);
		List<MembersProductDTO> lists = membersProductService.findByMemberNo(memberDTO.getMember_no());

		for (MembersProductDTO dto : lists) {
			model.addRow(
					new Object[]{dto.getProduct_no(), dto.getProduct_name(), dto.getProduct_category(),dto.getProduct_price(), dto.getProduct_quantity()});
		}
		table.setModel(model);
	} // 전체테이블조회 END.

	private DefaultTableModel getDefaultTableModel() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Product No");
		model.addColumn("Name");
		model.addColumn("Category");
		model.addColumn("Price");
		model.addColumn("Quantity");
		return model;
	}

	private void displaySearchResults(List<MembersProductDTO> searchResults) {
		DefaultTableModel model = getDefaultTableModel();

		int numOfResults = 0; // 검색된 항목의 수를 저장하기 위한 변수

		if (searchResults != null) {

			for (MembersProductDTO dto : searchResults) {
				model.addRow(
						new Object[]{dto.getProduct_no(), dto.getProduct_name(), dto.getProduct_category(),dto.getProduct_price(), dto.getProduct_quantity()});

				numOfResults++; // 검색된 항목의 수를 증가시킴
			}
		}

		textField.setText("검색된 항목의 수: " + numOfResults);

		table.setModel(model);
	} // 검색시 결과 END.


}
