package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Shop;

@Repository
public interface ShopRepository extends CrudRepository<Shop, Long>, JpaSpecificationExecutor<Shop>{
	@Override
	List<Shop> findAll();
	
	Optional<Shop> findByName(String name);
	
	Optional<Shop> findByCode(String code);
}
