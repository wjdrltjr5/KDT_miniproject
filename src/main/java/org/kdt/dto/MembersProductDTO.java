package org.kdt.dto;

public class MembersProductDTO {
	String member_product_no;
	String member_no; // 회원번호
	String member_name;
	String product_no; // 상품번호
	String product_name;
	String product_category;
	String status;

	public int getProduct_price() {
		return product_price;
	}

	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}

	int product_price;
	int product_quantity; // 재고량


	public MembersProductDTO() {
		// TODO Auto-generated constructor stub
	}

	public MembersProductDTO(String member_no, String product_no, String status, int product_quantity) {
		this.member_no = member_no;
		this.product_no = product_no;
		this.status = status;
		this.product_quantity = product_quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMember_no() {
		return member_no;
	}
	public void setMember_no(String member_no) {
		this.member_no = member_no;
	}
	public String getProduct_no() {
		return product_no;
	}
	public void setProduct_no(String product_no) {
		this.product_no = product_no;
	}
	public int getProduct_quantity() {
		return product_quantity;
	}
	public void setProduct_quantity(int product_quantity) {
		this.product_quantity = product_quantity;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProduct_category() {
		return product_category;
	}

	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}

	public String getMember_product_no() {
		return member_product_no;
	}

	public void setMember_product_no(String member_product_no) {
		this.member_product_no = member_product_no;
	}

	@Override
	public String toString() {
		return "MembersProductDTO [member_no=" + member_no + ", product_no=" + product_no + ", product_quantity="
				+ product_quantity + "]";
	}
	
	
	
}
