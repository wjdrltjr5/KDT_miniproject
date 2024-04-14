package org.kdt.dto;

import java.sql.Date;

public class ProductDTO {
	private Integer product_no; // int 대신 Integer로 변경
	private String product_category;
	private String product_name;
	private Date product_date;
	private int product_price;
	private int product_quantity;

	public ProductDTO() {

	}

	public ProductDTO(Integer product_no, String product_category, String product_name, Date product_date, int product_price,
					  int product_quantity) {
		this.product_no = product_no;
		this.product_category = product_category;
		this.product_name = product_name;
		this.product_date = product_date;
		this.product_price = product_price;
		this.product_quantity = product_quantity;
	}
	public ProductDTO(String product_category, String product_name, Date product_date, int product_price,
					  int product_quantity) {
		this.product_category = product_category;
		this.product_name = product_name;
		this.product_date = product_date;
		this.product_price = product_price;
		this.product_quantity = product_quantity;
	}

	@Override
	public String toString() {
		return "ProductDTO [productNo=" + product_no + ", productCategory=" + product_category + ", productName="
				+ product_name + ", productDate=" + product_date + ", productPrice=" + product_price + ", productQuantity="
				+ product_quantity + "]";
	}

	public Integer getProduct_no() { // int 대신 Integer로 변경
		return product_no;
	}

	public void setProduct_no(Integer product_no) { // int 대신 Integer로 변경
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

	public Date getProduct_date() {
		return product_date;
	}

	public void setProduct_date(Date product_date) {
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

}
