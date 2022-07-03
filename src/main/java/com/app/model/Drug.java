package com.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Drug  extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String code;
	private String name;
	private Integer loaiThuoc; //loai
	private Integer loaiVatTu; // loai vat tu
	private Integer donViTinh; //don vi tinh
	private Double quyCach; //quy cach
	private String soDangKy;  // so dang ky
	private String congDung; //cong dung
	private Integer duongDung; // duong dung
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLoaiThuoc() {
		return loaiThuoc;
	}
	public void setLoaiThuoc(Integer loaiThuoc) {
		this.loaiThuoc = loaiThuoc;
	}
	public Integer getLoaiVatTu() {
		return loaiVatTu;
	}
	public void setLoaiVatTu(Integer loaiVatTu) {
		this.loaiVatTu = loaiVatTu;
	}
	public Integer getDonViTinh() {
		return donViTinh;
	}
	public void setDonViTinh(Integer donViTinh) {
		this.donViTinh = donViTinh;
	}
	public Double getQuyCach() {
		return quyCach;
	}
	public void setQuyCach(Double quyCach) {
		this.quyCach = quyCach;
	}
	public String getSoDangKy() {
		return soDangKy;
	}
	public void setSoDangKy(String soDangKy) {
		this.soDangKy = soDangKy;
	}
	public String getCongDung() {
		return congDung;
	}
	public void setCongDung(String congDung) {
		this.congDung = congDung;
	}
	public Integer getDuongDung() {
		return duongDung;
	}
	public void setDuongDung(Integer duongDung) {
		this.duongDung = duongDung;
	}
	
	
	
}
