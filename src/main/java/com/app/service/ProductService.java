package com.app.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.app.model.Category;
import com.app.model.Product;

public interface ProductService extends BaseService<Product>{
	Page<Product> doFilterSearchPagingProduct(String searchKey, Integer status, int pageSize, int pageNumber);
	
	Product findByNameEn(String nameEn);
	
	Product findByNameVi(String nameVi);
	
	Product findByCode(String code);
	
	List<Product> findByCategory(Category category);
	
}
