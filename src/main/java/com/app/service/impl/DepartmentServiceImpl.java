package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.app.model.Department;
import com.app.repository.DepartmentRepository;
import com.app.response.specification.DepartmentSpecification;
import com.app.service.DepartmentService;
import com.app.utils.Constant;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	private DepartmentRepository departmentRepository;
	
	@Autowired
	public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return departmentRepository.findAll();
	}

	@Override
	public Department update(Department instance) {
		// TODO Auto-generated method stub
		return departmentRepository.save(instance);
	}

	@Override
	public void delete(Department instance) {
		// TODO Auto-generated method stub
		instance.setStatus(Constant.Status.IN_ACTIVE.getValue());
		departmentRepository.save(instance);
	}

	@Override
	public Department findById(long id) {
		// TODO Auto-generated method stub
		return departmentRepository.findById(id).orElse(null);
	}

	@Override
	public Department insert(Department instance) {
		// TODO Auto-generated method stub
		instance.setStatus(Constant.Status.ACTIVE.getValue());
		return departmentRepository.save(instance);
	}

	@Override
	public Department findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Department> doFilterSearchPagingDepartment(String searchKey, Integer status, int pageSize, int pageNumber) {
		// TODO Auto-generated method stub
		return departmentRepository.findAll(new DepartmentSpecification(searchKey, status),
				PageRequest.of(pageNumber - 1, pageSize));
	}

	@Override
	public Department findByCode(String code) {
		// TODO Auto-generated method stub
		return departmentRepository.findByCode(code).orElse(null);
	}

	 
}
