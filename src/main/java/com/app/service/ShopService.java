package com.app.service;

import org.springframework.data.domain.Page;

import com.app.model.Shop;

public interface ShopService extends BaseService<Shop>{
	Page<Shop> doFilterSearchPagingShop(String searchKey, Integer status, Integer shopType, int pageSize, int pageNumber);
	
	Shop findByCode(String code);
}
