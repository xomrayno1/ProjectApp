package com.app.model.request;

public class DrugPagingSearchSortModel {
	private String searchKey;
	private Integer loaiVatTu;
	private Integer status;
	private int pageNumber;
    private int pageSize;
    
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getLoaiVatTu() {
		return loaiVatTu;
	}
	public void setLoaiVatTu(Integer loaiVatTu) {
		this.loaiVatTu = loaiVatTu;
	}
 
}
