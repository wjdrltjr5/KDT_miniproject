package org.kdt.dto;

import java.sql.Date;

public class ProductDTO {
	private String productNo; 
	private String productCategory;
	private String productName; 
	private Date productDate; 
	private int productPrice; 
	private int productQuantity; 

	public ProductDTO() {

	}

	public ProductDTO(String productNo, String productCategory, String productName, Date productDate, int productPrice,
			int productQuantity) {
		this.productNo = productNo;
		this.productCategory = productCategory;
		this.productName = productName;
		this.productDate = productDate;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;
	}

	
	@Override
	public String toString() {
		return "ProductDTO [productNo=" + productNo + ", productCategory=" + productCategory + ", productName="
				+ productName + ", productDate=" + productDate + ", productPrice=" + productPrice + ", productQuantity="
				+ productQuantity + "]";
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

}
