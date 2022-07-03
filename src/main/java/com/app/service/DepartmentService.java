package com.app.service;

import org.springframework.data.domain.Page;

import com.app.model.Department;

public interface DepartmentService extends BaseService<Department>{
	Page<Department> doFilterSearchPagingDepartment(String searchKey, Integer status, int pageSize, int pageNumber);
	
	Department findByCode(String code);
}
