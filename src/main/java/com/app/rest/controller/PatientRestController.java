package com.app.rest.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.ApplicationException;
import com.app.model.Patient;
import com.app.model.request.CreatePatientRequest;
import com.app.model.request.DeleteRequest;
import com.app.model.request.PatientPagingSearchSortModel;
import com.app.model.request.UpdatePatientRequest;
import com.app.response.APIResponse;
import com.app.response.APIStatus;
import com.app.service.PatientService;
import com.app.utils.Constant;
import com.app.utils.ResponseUtil;


@RestController
@RequestMapping(value = Constant.PATIENT_API)
@CrossOrigin(origins = {Constant.CROSS_ORIGIN_LOCAL_8000, Constant.CROSS_ORIGIN_LOCAL_8001, Constant.CROSS_ORIGIN_LOCAL_8080 })
public class PatientRestController {

	private PatientService patientService;
	
	private ModelMapper mapper = new ModelMapper();
	
	private static final Logger log = LoggerFactory.getLogger(PatientRestController.class);

	@Autowired
	public PatientRestController(PatientService patientService) {
		this.patientService = patientService;
	}

	@GetMapping
	public List<Patient> findAll(){
		List<Patient> patients =  patientService.findAll();
		return patients;
	}
	
	@PostMapping(value = Constant.PATIENT_GET_LIST_PAGING_SORT_SEARCH_FILTER)
	public ResponseEntity<APIResponse> getListPagingSortSearchFilter(@RequestBody PatientPagingSearchSortModel cpssm){
		Page<Patient> patients =  patientService.doFilterSearchPagingPatient(cpssm.getSearchKey(), cpssm.getStatus(),
										cpssm.getPageSize(), cpssm.getPageNumber());
		try {
			if(patients == null) {
				throw new ApplicationException(APIStatus.ERR_PATIENT_LIST_IS_EMPTY);
			}
			log.info("get list filter successfully");
			return ResponseUtil.responseSuccess(patients);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("error patient list is empty");
			throw new ApplicationException(APIStatus.ERR_PATIENT_LIST_IS_EMPTY);
		}
	}
	
	@GetMapping(value = Constant.PATIENT_GET_DETAIL)
	public ResponseEntity<APIResponse> getPatientDetail(@PathVariable("patientId") long patientId){
		try {
			Patient patient = patientService.findById(patientId);
			if(patient == null) {
				throw new ApplicationException(APIStatus.ERR_PATIENT_ID_NOT_EXIST);
			}
			log.info("get patient detail successfully");
			return ResponseUtil.responseSuccess(patient);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("error patient id not exists");
			throw new ApplicationException(APIStatus.ERR_PATIENT_ID_NOT_EXIST);
		}
	}
	
	@PostMapping(value = Constant.PATIENT_CREATE)
	public ResponseEntity<APIResponse> createPatient(@Validated @RequestBody CreatePatientRequest patientRequest){
		Patient getPatient = patientService. findByCode(patientRequest.getCode());
		if (getPatient != null) {
			log.error("error patient code already exists");
			throw new ApplicationException(APIStatus.ERR_PATIENT_CODE_ALREADY_EXISTS);
		}
		try {
			mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			Patient patient = mapper.map(patientRequest, Patient.class);
			patientService.insert(patient);
			log.info("create patient successfully");
			return ResponseUtil.responseSuccess("Create patient successfully");
		} catch (Exception e) {
			// TODO: handle exception
			log.error("error create patient");
			throw new ApplicationException(APIStatus.ERR_CREATE_PATIENT);
		}
	}
	
	@PostMapping(value = Constant.PATIENT_DELETE)
	public ResponseEntity<APIResponse> deletePatient(@RequestBody DeleteRequest deleteRequest){
		try {
			if(deleteRequest  != null && deleteRequest.getIds() != null) {
				for(Long id : deleteRequest.getIds()) {
					Patient patient = patientService.findById(id);
					if(patient == null) {
						throw new ApplicationException(APIStatus.ERR_PATIENT_ID_NOT_EXIST);
					}
					patientService.delete(patient);
				}
			}
 
			log.info("delete patient successfully");
			return ResponseUtil.responseSuccess("Delete patient successfully");
		} catch (Exception e) {
			// TODO: handle exception
			log.error("error delete patient id not exist");
			throw new ApplicationException(APIStatus.ERR_PATIENT_ID_NOT_EXIST);
		}
	}
	
	@PutMapping(value = Constant.PATIENT_UPDATE)
	public ResponseEntity<APIResponse> updatePatient(@Validated @RequestBody UpdatePatientRequest patientRequest){
		Patient patientById = patientService.findById(patientRequest.getId());
		Patient patientByCode = patientService.findByCode(patientRequest.getCode());
		if(patientById != null) {
			if(patientByCode != null) {
				if(!patientByCode.getCode().equals(patientById.getCode())) {
					throw new ApplicationException(APIStatus.ERR_PATIENT_CODE_ALREADY_EXISTS);
				}
			}
			try {
				mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
				
				Patient patient = mapper.map(patientRequest, Patient.class);
				patient.setStatus(patientById.getStatus());
				patient.setCreatedDate(patientById.getCreatedDate());
				patientService.update(patient);
				log.info("update patient successfully");
				return ResponseUtil.responseSuccess("update patient successfully");
			} catch (Exception e) {
				log.error("error update patient");
				throw new ApplicationException(APIStatus.ERR_UPDATE_PATIENT);
			}
		}else {
			log.error("error update patient id not exist");
			throw new ApplicationException(APIStatus.ERR_PATIENT_ID_NOT_EXIST);
		}
	}
}
