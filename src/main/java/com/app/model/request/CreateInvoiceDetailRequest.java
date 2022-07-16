package com.app.model.request;

import java.math.BigDecimal;

public class CreateInvoiceDetailRequest {
	private Long drugId;
	
	private BigDecimal price;
	private Integer quantity;
	private BigDecimal amout;
 
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
 
	public BigDecimal getAmout() {
		return amout;
	}
	public void setAmout(BigDecimal amout) {
		this.amout = amout;
	}
	public Long getDrugId() {
		return drugId;
	}
	public void setDrugId(Long drugId) {
		this.drugId = drugId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
	
	
}
