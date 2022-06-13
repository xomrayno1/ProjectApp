package com.app.service;

import org.springframework.data.domain.Page;

import com.app.model.Category;

public interface CategoryService extends BaseService<Category>{
	Page<Category> doFilterSearchPagingCategory(String searchKey, Integer status, int pageSize, int pageNumber);
}
