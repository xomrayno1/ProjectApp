package com.app.model.request;

import java.math.BigDecimal;
import java.util.List;

public class CreateInvoiceRequest {
	private Long patientId;
	private Long userId;
	private Long productId;
	
	private Integer objectType;
	private Integer numberBHYT;
	private String fromDateString;
	private String toDateString;
	private Integer routingType;
	private Integer area;
	private Integer form;
	private String dateStartString;
	
	private String trieuChung;
	private Integer benhChinh;
	private String dienGiaiBenhChinh;
	private Integer benhKemTheo;
	private String dienGiaiBenhKemTheo;
	private String tuVanDieuTri;
	private String dienBienDieuTri;
	private Integer ketQuaKham;
 
 
	private String dateEndString;
	
	private BigDecimal totalAmount;

	private List<CreateInvoiceDetailRequest> details;
	
	
	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getObjectType() {
		return objectType;
	}

	public void setObjectType(Integer objectType) {
		this.objectType = objectType;
	}

	public Integer getNumberBHYT() {
		return numberBHYT;
	}

	public void setNumberBHYT(Integer numberBHYT) {
		this.numberBHYT = numberBHYT;
	}

	public String getFromDateString() {
		return fromDateString;
	}

	public void setFromDateString(String fromDateString) {
		this.fromDateString = fromDateString;
	}

	public String getToDateString() {
		return toDateString;
	}

	public void setToDateString(String toDateString) {
		this.toDateString = toDateString;
	}

	public Integer getRoutingType() {
		return routingType;
	}

	public void setRoutingType(Integer routingType) {
		this.routingType = routingType;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public Integer getForm() {
		return form;
	}

	public void setForm(Integer form) {
		this.form = form;
	}

	public String getDateStartString() {
		return dateStartString;
	}

	public void setDateStartString(String dateStartString) {
		this.dateStartString = dateStartString;
	}

	public String getTrieuChung() {
		return trieuChung;
	}

	public void setTrieuChung(String trieuChung) {
		this.trieuChung = trieuChung;
	}

	public Integer getBenhChinh() {
		return benhChinh;
	}

	public void setBenhChinh(Integer benhChinh) {
		this.benhChinh = benhChinh;
	}

	public String getDienGiaiBenhChinh() {
		return dienGiaiBenhChinh;
	}

	public void setDienGiaiBenhChinh(String dienGiaiBenhChinh) {
		this.dienGiaiBenhChinh = dienGiaiBenhChinh;
	}

	public Integer getBenhKemTheo() {
		return benhKemTheo;
	}

	public void setBenhKemTheo(Integer benhKemTheo) {
		this.benhKemTheo = benhKemTheo;
	}

	public String getDienGiaiBenhKemTheo() {
		return dienGiaiBenhKemTheo;
	}

	public void setDienGiaiBenhKemTheo(String dienGiaiBenhKemTheo) {
		this.dienGiaiBenhKemTheo = dienGiaiBenhKemTheo;
	}

	public String getTuVanDieuTri() {
		return tuVanDieuTri;
	}

	public void setTuVanDieuTri(String tuVanDieuTri) {
		this.tuVanDieuTri = tuVanDieuTri;
	}

	public String getDienBienDieuTri() {
		return dienBienDieuTri;
	}

	public void setDienBienDieuTri(String dienBienDieuTri) {
		this.dienBienDieuTri = dienBienDieuTri;
	}

	public Integer getKetQuaKham() {
		return ketQuaKham;
	}

	public void setKetQuaKham(Integer ketQuaKham) {
		this.ketQuaKham = ketQuaKham;
	}

	public String getDateEndString() {
		return dateEndString;
	}

	public void setDateEndString(String dateEndString) {
		this.dateEndString = dateEndString;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<CreateInvoiceDetailRequest> getDetails() {
		return details;
	}

	public void setDetails(List<CreateInvoiceDetailRequest> details) {
		this.details = details;
	}

	 
	
	
	
}
