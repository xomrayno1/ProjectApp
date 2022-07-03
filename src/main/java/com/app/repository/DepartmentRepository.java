package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Department;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long>, JpaSpecificationExecutor<Department>{
	@Override
	List<Department> findAll();
	
	Optional<Department> findByCode(String code);
}
