package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Drug;

@Repository
public interface DrugRepository extends CrudRepository<Drug, Long>, JpaSpecificationExecutor<Drug>{
	@Override
	List<Drug> findAll();
	
	Optional<Drug> findByCode(String code);
}
