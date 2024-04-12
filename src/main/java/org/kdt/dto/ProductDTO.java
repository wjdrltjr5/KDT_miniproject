package org.kdt.dto;

public class ProductDTO {
	String product_no;
	String product_category;
	String product_name;
	String product_date;
	int product_price;
	int product_quantity;
	
	////////////////////////////////////////////////////////////////////////////////////////////
	// 생성자 ////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	
	public ProductDTO() {
		// TODO Auto-generated constructor stub
	}
	public ProductDTO(String product_no, String product_category, String product_name, String product_date,
			int product_price, int product_quantity) {
		this.product_no = product_no;
		this.product_category = product_category;
		this.product_name = product_name;
		this.product_date = product_date;
		this.product_price = product_price;
		this.product_quantity = product_quantity;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////
	// getter & setter /////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	
	public String getProduct_no() {
		return product_no;
	}
	public void setProduct_no(String product_no) {
		this.product_no = product_no;
	}
	public String getProduct_category() {
		return product_category;
	}
	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_date() {
		return product_date;
	}
	public void setProduct_date(String product_date) {
		this.product_date = product_date;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public int getProduct_quantity() {
		return product_quantity;
	}
	public void setProduct_quantity(int product_quantity) {
		this.product_quantity = product_quantity;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////
	// toString	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public String toString() {
		return "ProductDTO [product_no=" + product_no + ", product_category=" + product_category + ", product_name="
				+ product_name + ", product_date=" + product_date + ", product_price=" + product_price
				+ ", product_quantity=" + product_quantity + "]";
	}
	
	
}
