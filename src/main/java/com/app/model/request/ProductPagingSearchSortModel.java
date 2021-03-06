package com.app.model.request;

public class ProductPagingSearchSortModel {
	private String searchKey;
	private Integer status;
    private int pageNumber;
    private int pageSize;
    private int sortCase;
    
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
	public int getSortCase() {
		return sortCase;
	}
	public void setSortCase(int sortCase) {
		this.sortCase = sortCase;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	 
}
