package com.app.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Invoice extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name="patient_id")
	private Patient patient;
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	@ManyToOne
	@JoinColumn(name="user_id")
	private Users users;
	
	private Integer objectType;
	private Integer numberBHYT;
	private Date fromDate;
	private Date toDate;
	private Integer routingType;
	private Integer area;
	private Integer form;
	private Date dateStart;
	
	private String trieuChung;
	private Integer benhChinh;
	private String dienGiaiBenhChinh;
	private Integer benhKemTheo;
	private String dienGiaiBenhKemTheo;
	private String tuVanDieuTri;
	private String dienBienDieuTri;
	private Integer ketQuaKham;

	private Date dateEnd;
	
	private BigDecimal totalAmount;
	
	 @OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY,
	            cascade = CascadeType.ALL)
	    private Set<InvoiceDetail> invoiceDetails;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
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
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
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
	public Date getDateStart() {
		return dateStart;
	}
	public void setDateStart(Date dateStart) {
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
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public Date getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Set<InvoiceDetail> getInvoiceDetails() {
		return invoiceDetails;
	}
	public void setInvoiceDetails(Set<InvoiceDetail> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
	}
	
	

}
