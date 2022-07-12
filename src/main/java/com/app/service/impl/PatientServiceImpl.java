package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.app.model.Drug;
import com.app.model.Patient;
import com.app.repository.PatientRepository;
import com.app.response.specification.DrugSpecification;
import com.app.response.specification.PatientSpecification;
import com.app.service.PatientService;
import com.app.utils.Constant;

@Service
public class PatientServiceImpl implements PatientService{

	private PatientRepository patientRepository;
	
	@Autowired
	public PatientServiceImpl(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	@Override
	public List<Patient> findAll() {
		// TODO Auto-generated method stub
		return patientRepository.findAll();
	}

	@Override
	public Patient update(Patient instance) {
		// TODO Auto-generated method stub
		return patientRepository.save(instance);
	}

	@Override
	public void delete(Patient instance) {
		// TODO Auto-generated method stub
		instance.setStatus(Constant.Status.IN_ACTIVE.getValue());
		patientRepository.save(instance);
	}

	@Override
	public Patient findById(long id) {
		// TODO Auto-generated method stub
		return patientRepository.findById(id).orElse(null);
	}

	@Override
	public Patient insert(Patient instance) {
		// TODO Auto-generated method stub
		instance.setStatus(Constant.Status.ACTIVE.getValue());
		return patientRepository.save(instance);
	}

	@Override
	public Patient findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Patient> doFilterSearchPagingPatient(String searchKey, Integer status, int pageSize, int pageNumber) {
		// TODO Auto-generated method stub
		return patientRepository.findAll(new PatientSpecification(searchKey, status),
				PageRequest.of(pageNumber - 1, pageSize));
	}

	@Override
	public Patient findByCode(String code) {
		// TODO Auto-generated method stub
		return patientRepository.findByCode(code).orElse(null);
	}

	 
}
