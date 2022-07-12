package com.app.service;

import org.springframework.data.domain.Page;

import com.app.model.Patient;

public interface PatientService extends BaseService<Patient>{
	Page<Patient> doFilterSearchPagingPatient(String searchKey, Integer status, int pageSize, int pageNumber);
	
	Patient findByCode(String code);
}

