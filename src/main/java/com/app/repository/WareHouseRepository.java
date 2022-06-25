package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.model.WareHouse;

@Repository
public interface WareHouseRepository extends CrudRepository<WareHouse, Long>, JpaSpecificationExecutor<WareHouse>{
	@Override
	List<WareHouse> findAll();
	
	Optional<WareHouse> findByName(String name);
	
	Optional<WareHouse> findByCode(String code);
}
