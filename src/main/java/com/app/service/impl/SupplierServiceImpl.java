package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.app.model.Supplier;
import com.app.repository.SupplierRepository;
import com.app.response.specification.SupplierSpecification;
import com.app.service.SupplierService;
import com.app.utils.Constant;

@Service
public class SupplierServiceImpl implements SupplierService{

	private SupplierRepository supplierRepository;
	
	@Autowired
	public SupplierServiceImpl(SupplierRepository supplierRepository) {
		this.supplierRepository = supplierRepository;
	}

	@Override
	public List<Supplier> findAll() {
		// TODO Auto-generated method stub
		return supplierRepository.findAll();
	}

	@Override
	public Supplier update(Supplier instance) {
		// TODO Auto-generated method stub
		return supplierRepository.save(instance);
	}

	@Override
	public void delete(Supplier instance) {
		// TODO Auto-generated method stub
		instance.setStatus(Constant.Status.IN_ACTIVE.getValue());
		supplierRepository.save(instance);
	}

	@Override
	public Supplier findById(long id) {
		// TODO Auto-generated method stub
		return supplierRepository.findById(id).orElse(null);
	}

	@Override
	public Supplier insert(Supplier instance) {
		// TODO Auto-generated method stub
		instance.setStatus(Constant.Status.ACTIVE.getValue());
		return supplierRepository.save(instance);
	}

	@Override
	public Supplier findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Supplier> doFilterSearchPagingSupplier(String searchKey, Integer status, int pageSize, int pageNumber) {
		// TODO Auto-generated method stub
		return supplierRepository.findAll(new SupplierSpecification(searchKey, status),
				PageRequest.of(pageNumber - 1, pageSize));
	}

	@Override
	public Supplier findByCode(String code) {
		// TODO Auto-generated method stub
		return supplierRepository.findByCode(code).orElse(null);
	}

}
