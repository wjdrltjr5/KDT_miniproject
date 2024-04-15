package org.kdt.ui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.kdt.dao.ProductDAO;
import org.kdt.dto.MemberDTO;
import org.kdt.dto.ProductDTO;
import org.kdt.service.ProductService;
import org.kdt.service.ProductServiceImpl;

public class ProductMain extends JFrame {

	private JButton btnSelectAll; // 전체 테이블 조회버튼
	private JTable table; // 테이블
	private ProductService productService;

	JComboBox<String> comboBox; // 검색조건box
	private JTextField searchBar; // 검색bar
	JButton searchButton; // 검색button
	private JTextField textField; // 검색된 항목수 표시
	private JButton restartButton; // 초기화
	private JButton btnStockRequest;

	private JButton btnStockModify;
	private JButton btnStockDelete;
	private String message = "전체 테이블의 항목 수: 검색된항목의수가 없습니다.              "
			+ "               ※가격으로 검색시 클릭후 바로 검색을 눌러서 범위를 설정하시오.※";
	private JButton btnStockInsert;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(() -> {
//            try {
//                ProductMain frame = new ProductMain();
//                frame.setVisible(true);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//	} // main end

	// ProductMain
	public ProductMain(MemberDTO memberDTO) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1065, 632);
		getContentPane().setLayout(null);

		btnSelectAll = new JButton("전체테이블");
		btnSelectAll.setBounds(926, 90, 111, 85);
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
		getContentPane().add(searchButton);

		textField = new JTextField();
		textField.setText(message);
		textField.setBounds(181, 553, 733, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		restartButton = new JButton("초기화");
		restartButton.setBounds(926, 185, 111, 23);
		getContentPane().add(restartButton);
		
		btnStockRequest = new JButton("입고 요청");
		btnStockRequest.setBounds(12, 80, 160, 95);
		getContentPane().add(btnStockRequest);
		
		btnStockModify = new JButton("재고 수정");
		btnStockModify.setBounds(12, 185, 160, 95);
		getContentPane().add(btnStockModify);
		
		btnStockDelete = new JButton("재고 삭제");
		btnStockDelete.setBounds(12, 290, 160, 95);
		getContentPane().add(btnStockDelete);
		
		btnStockInsert = new JButton("재고 추가");
		btnStockInsert.setBounds(12, 395, 160, 95);
		getContentPane().add(btnStockInsert);
		btnStockModify.addActionListener(x -> stockModifyBtnAction());
		btnStockDelete.addActionListener(x -> stockDeleteBtnAction());

		comboBox.addItem("전체품목");
		comboBox.addItem("제품이름");
		comboBox.addItem("카테고리");
		comboBox.addItem("가격");

		ProductDAO dao = new ProductDAO();
		productService = new ProductServiceImpl(dao);

		btnSelectAll.addActionListener(e -> selectAllBtnAction());

		searchButton.addActionListener(e -> searchBtnAction());
		
		restartButton.addActionListener(e -> restartBtnAction());




	} // ProductMain END.
	private void stockModifyBtnAction(){
		StockModify stockModify = new StockModify();
		stockModify.setVisible(true);
	}
	private void stockDeleteBtnAction(){
		StockDelete stockDelete = new StockDelete();
		stockDelete.setVisible(true);
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
		List<ProductDTO> searchResults = null;
		if (selectedCategory.equals("전체품목")) {
			searchResults = productService.selectAllProducts(searchKeyword);
		} else if (selectedCategory.equals("카테고리")) {
			searchResults = productService.selectProductsByCategory(searchKeyword);
		} else if (selectedCategory.equals("제품이름")) {
			searchResults = productService.searchProductByName(searchKeyword);
		} else if (selectedCategory.equals("가격")) {
			// 가격 범위를 설정하는 다이얼로그 띄우기
			showPriceRangeDialog();
			return; // 다이얼로그가 닫히면서 추가 검색을 하지 않도록 리턴
		}
		displaySearchResults(searchResults);
	}
	private void loadTableData() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Product No");
		model.addColumn("Category");
		model.addColumn("Name");
		model.addColumn("Date");
		model.addColumn("Price");
		model.addColumn("Quantity");
		model.setRowCount(0);
		List<ProductDTO> products = productService.findByAll();
		for (ProductDTO product : products) {
			model.addRow(
					new Object[] { product.getProduct_no(), product.getProduct_category(), product.getProduct_name(),
							product.getProduct_date(), product.getProduct_price(), product.getProduct_quantity() });
		}
		table.setModel(model);
	} // 전체테이블조회 END.

	private void displaySearchResults(List<ProductDTO> searchResults) {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Product No");
		model.addColumn("Category");
		model.addColumn("Name");
		model.addColumn("Date");
		model.addColumn("Price");
		model.addColumn("Quantity");

		int numOfResults = 0; // 검색된 항목의 수를 저장하기 위한 변수

		if (searchResults != null) {

			for (ProductDTO product : searchResults) {
				model.addRow(new Object[] { product.getProduct_no(), product.getProduct_category(),
						product.getProduct_name(), product.getProduct_date(), product.getProduct_price(),
						product.getProduct_quantity() });

				numOfResults++; // 검색된 항목의 수를 증가시킴
			}
		}

		textField.setText("검색된 항목의 수: " + numOfResults);

		table.setModel(model);
	} // 검색시 결과 END.

	// 가격 범위를 설정
	private void showPriceRangeDialog() {
		JTextField minPriceField = new JTextField(5);
		JTextField maxPriceField = new JTextField(5);

		JPanel panel = new JPanel();
		panel.add(new JLabel("최소 가격:"));
		panel.add(minPriceField);
		panel.add(Box.createHorizontalStrut(15)); // 간격 조절
		panel.add(new JLabel("최대 가격:"));
		panel.add(maxPriceField);

		int result = JOptionPane.showConfirmDialog(null, panel, "가격 범위 입력", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			try {
				// 사용자가 입력한 최소 가격과 최대 가격 가져오기
				double minPrice = Double.parseDouble(minPriceField.getText());
				double maxPrice = Double.parseDouble(maxPriceField.getText());

				// ProductService를 통해 해당 범위의 상품 검색
				List<ProductDTO> searchResults = productService.selectProductsByPriceRange(minPrice, maxPrice);

				// 검색 결과를 테이블에 표시
				displaySearchResults(searchResults);
			} catch (NumberFormatException e) {
				// 사용자가 유효하지 않은 숫자를 입력한 경우 에러 처리
				JOptionPane.showMessageDialog(null, "유효하지 않은 숫자 형식입니다.", "오류", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
