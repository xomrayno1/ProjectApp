package com.app.model.request;

import java.math.BigDecimal;

public class CreateProductRequest {
	private String code;
	private String nameVi;
	private String nameEn;
	private Long categoryId;
	private BigDecimal price;
	private BigDecimal priceBHYT;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNameVi() {
		return nameVi;
	}
	public void setNameVi(String nameVi) {
		this.nameVi = nameVi;
	}
	public String getNameEn() {
		return nameEn;
	}
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getPriceBHYT() {
		return priceBHYT;
	}
	public void setPriceBHYT(BigDecimal priceBHYT) {
		this.priceBHYT = priceBHYT;
	}
 
	
	
}
