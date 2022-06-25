package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.app.model.Shop;
import com.app.repository.ShopRepository;
import com.app.response.specification.ShopSpecification;
import com.app.service.ShopService;
import com.app.utils.Constant;

@Service
public class ShopServiceImpl implements ShopService{
	 
	private ShopRepository instanceRepo;
	
	@Autowired
	public ShopServiceImpl(ShopRepository cateRepo) {
		this.instanceRepo = cateRepo;
	}

	@Override
	public List<Shop> findAll() {
		// TODO Auto-generated method stub
		return instanceRepo.findAll();
	}

	@Override
	public Shop update(Shop instance) {
		// TODO Auto-generated method stub
		return instanceRepo.save(instance);
	}

	@Override
	public void delete(Shop instance) {
		// TODO Auto-generated method stub
		instance.setStatus(Constant.Status.IN_ACTIVE.getValue());
		instanceRepo.save(instance);
	}

	@Override
	public Shop findById(long id) {
		// TODO Auto-generated method stub
		return instanceRepo.findById(id).orElse(null);
	}

	@Override
	public Shop insert(Shop instance) {
		// TODO Auto-generated method stub
		instance.setStatus(Constant.Status.ACTIVE.getValue());
		return instanceRepo.save(instance);
	}

	@Override
	public Shop findByName(String name) {
		// TODO Auto-generated method stub
		return instanceRepo.findByName(name).orElse(null);
	}

	@Override
	public Page<Shop> doFilterSearchPagingShop(String searchKey, Integer status, Integer shopType, int pageSize, int pageNumber) {
		// TODO Auto-generated method stub
		return instanceRepo.findAll(new ShopSpecification(searchKey, status, shopType),
				PageRequest.of(pageNumber - 1, pageSize));
	}

	@Override
	public Shop findByCode(String code) {
		// TODO Auto-generated method stub
		return instanceRepo.findByCode(code).orElse(null);
	}

}
