package org.kdt.dto;

public class MembersProductDTO {
	
	String member_no; // 회원번호
	String member_name;
	String product_no; // 상품번호

	String product_name;
	String status;
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

	@Override
	public String toString() {
		return "MembersProductDTO [member_no=" + member_no + ", product_no=" + product_no + ", product_quantity="
				+ product_quantity + "]";
	}
	
	
	
}
