package com.app.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.app.model.Product;
import com.app.repository.ProductRepository;
import com.app.response.specification.ProductSpecification;
import com.app.service.ProductService;
import com.app.utils.Constant;

@Service
public class ProductServiceImpl implements ProductService{
	
	private ProductRepository proRepo;
	
	@Autowired
	public ProductServiceImpl(ProductRepository proRepo) {
		this.proRepo = proRepo;
	}

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return proRepo.findAll();
	}

	@Override
	public Product update(Product instance) {
		// TODO Auto-generated method stub
		return proRepo.save(instance);
	}

	@Override
	public void delete(Product instance) {
		// TODO Auto-generated method stub
		instance.setStatus(Constant.Status.IN_ACTIVE.getValue());
		proRepo.save(instance);
	}

	@Override
	public Product findById(long id) {
		// TODO Auto-generated method stub
		return proRepo.findById(id).orElse(null);
	}

	@Override
	public Product insert(Product instance) {
		// TODO Auto-generated method stub
		instance.setStatus(Constant.Status.ACTIVE.getValue());
		return proRepo.save(instance);
	}

	@Override
	public Product findByName(String name) {
		// TODO Auto-generated method stub
		return proRepo.findByName(name).orElse(null);
	}

	@Override
	public Page<Product> doFilterSearchPagingProduct(String searchKey, Integer status, int pageSize, int pageNumber) {
		// TODO Auto-generated method stub
		return proRepo.findAll(new ProductSpecification(searchKey, status), PageRequest.of(pageNumber - 1, pageSize));
	}

	@Override
	public Product findByNameEn(String nameEn) {
		// TODO Auto-generated method stub
		return proRepo.findByNameEn(nameEn);
	}

	@Override
	public Product findByNameVi(String nameVi) {
		// TODO Auto-generated method stub
		return proRepo.findByNameVi(nameVi);
	}

	@Override
	public Product findByCode(String code) {
		// TODO Auto-generated method stub
		return proRepo.findByCode(code);
	}

}