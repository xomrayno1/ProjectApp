package com.app.service;

import org.springframework.data.domain.Page;

import com.app.model.WareHouse;

public interface WareHouseService extends BaseService<WareHouse>{
	Page<WareHouse> doFilterSearchPagingWareHouse(String searchKey, Integer status, Integer type, int pageSize, int pageNumber);
	
	WareHouse findByCode(String code);
}
