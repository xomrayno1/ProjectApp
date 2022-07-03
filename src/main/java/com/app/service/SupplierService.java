package com.app.service;

import org.springframework.data.domain.Page;

import com.app.model.Supplier;

public interface SupplierService extends BaseService<Supplier>{
	Page<Supplier> doFilterSearchPagingSupplier(String searchKey, Integer status, int pageSize, int pageNumber);
	
	Supplier findByCode(String code);
}
