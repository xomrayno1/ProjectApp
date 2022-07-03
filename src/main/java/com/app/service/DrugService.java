package com.app.service;

import org.springframework.data.domain.Page;

import com.app.model.Drug;

public interface DrugService extends BaseService<Drug>{
	Page<Drug> doFilterSearchPagingDrug(String searchKey, Integer loaiVatTu, Integer status, int pageSize, int pageNumber);
	
	Drug findByCode(String code);
}

