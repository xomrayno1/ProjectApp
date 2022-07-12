package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Patient;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long>, JpaSpecificationExecutor<Patient>{
	@Override
	List<Patient> findAll();
	
	Optional<Patient> findByCode(String code);
}
