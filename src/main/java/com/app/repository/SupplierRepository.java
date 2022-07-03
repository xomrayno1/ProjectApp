package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Supplier;
@Repository
public interface SupplierRepository  extends CrudRepository<Supplier, Long>, JpaSpecificationExecutor<Supplier>{

	@Override
	List<Supplier> findAll();
	
	Optional<Supplier> findByCode(String code);
	
}
