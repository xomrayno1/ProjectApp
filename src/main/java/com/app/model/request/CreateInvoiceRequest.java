package com.app.model.request;

import java.math.BigDecimal;

public class CreateInvoiceRequest {
	private Long patientId;
	
	private Integer objectType;
	private Integer numberBHYT;
	private String fromDate;
	private String toDate;
	private Integer routingType;
	private Integer area;
	private Integer form;
	private String dateStart;
	
	private String trieuChung;
	private Integer benhChinh;
	private String dienGiaiBenhChinh;
	private Integer benhKemTheo;
	private String dienGiaiBenhKemTheo;
	private String tuVanDieuTri;
	private String dienBienDieuTri;
	private Integer ketQuaKham;
 
	private Long userId;
	private String dateEnd;
	
	private BigDecimal totalAmount;

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
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

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
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

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
	
}
