package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.app.model.Drug;
import com.app.repository.DrugRepository;
import com.app.response.specification.DrugSpecification;
import com.app.service.DrugService;
import com.app.utils.Constant;

@Service
public class DrugServiceImpl implements DrugService{

	private DrugRepository drugRepository;
	
	@Autowired
	public DrugServiceImpl(DrugRepository drugRepository) {
		this.drugRepository = drugRepository;
	}

	@Override
	public List<Drug> findAll() {
		// TODO Auto-generated method stub
		return drugRepository.findAll();
	}

	@Override
	public Drug update(Drug instance) {
		// TODO Auto-generated method stub
		return drugRepository.save(instance);
	}

	@Override
	public void delete(Drug instance) {
		// TODO Auto-generated method stub
		instance.setStatus(Constant.Status.IN_ACTIVE.getValue());
		drugRepository.save(instance);
	}

	@Override
	public Drug findById(long id) {
		// TODO Auto-generated method stub
		return drugRepository.findById(id).orElse(null);
	}

	@Override
	public Drug insert(Drug instance) {
		// TODO Auto-generated method stub
		instance.setStatus(Constant.Status.ACTIVE.getValue());
		return drugRepository.save(instance);
	}

	@Override
	public Drug findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Drug> doFilterSearchPagingDrug(String searchKey, Integer loaiVatTu, Integer status, int pageSize, int pageNumber) {
		// TODO Auto-generated method stub
		return drugRepository.findAll(new DrugSpecification(searchKey, loaiVatTu, status),
				PageRequest.of(pageNumber - 1, pageSize));
	}

	@Override
	public Drug findByCode(String code) {
		// TODO Auto-generated method stub
		return drugRepository.findByCode(code).orElse(null);
	}

	 
}
