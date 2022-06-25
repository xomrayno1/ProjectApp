package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.app.model.WareHouse;
import com.app.repository.WareHouseRepository;
import com.app.response.specification.WareHouseSpecification;
import com.app.service.WareHouseService;
import com.app.utils.Constant;

@Service
public class WareHouseServiceImpl implements WareHouseService{
	 
	private WareHouseRepository instanceRepo;
	
	@Autowired
	public WareHouseServiceImpl(WareHouseRepository cateRepo) {
		this.instanceRepo = cateRepo;
	}

	@Override
	public List<WareHouse> findAll() {
		// TODO Auto-generated method stub
		return instanceRepo.findAll();
	}

	@Override
	public WareHouse update(WareHouse instance) {
		// TODO Auto-generated method stub
		return instanceRepo.save(instance);
	}

	@Override
	public void delete(WareHouse instance) {
		// TODO Auto-generated method stub
		instance.setStatus(Constant.Status.IN_ACTIVE.getValue());
		instanceRepo.save(instance);
	}

	@Override
	public WareHouse findById(long id) {
		// TODO Auto-generated method stub
		return instanceRepo.findById(id).orElse(null);
	}

	@Override
	public WareHouse insert(WareHouse instance) {
		// TODO Auto-generated method stub
		instance.setStatus(Constant.Status.ACTIVE.getValue());
		return instanceRepo.save(instance);
	}

	@Override
	public WareHouse findByName(String name) {
		// TODO Auto-generated method stub
		return instanceRepo.findByName(name).orElse(null);
	}

	@Override
	public Page<WareHouse> doFilterSearchPagingWareHouse(String searchKey, Integer status, Integer type, int pageSize, int pageNumber) {
		// TODO Auto-generated method stub
		return instanceRepo.findAll(new WareHouseSpecification(searchKey, status, type),
				PageRequest.of(pageNumber - 1, pageSize));
	}

	@Override
	public WareHouse findByCode(String code) {
		// TODO Auto-generated method stub
		return instanceRepo.findByCode(code).orElse(null);
	}

}
