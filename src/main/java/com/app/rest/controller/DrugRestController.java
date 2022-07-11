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
import com.app.model.Drug;
import com.app.model.request.DrugPagingSearchSortModel;
import com.app.model.request.CreateDrugRequest;
import com.app.model.request.DeleteRequest;
import com.app.model.request.UpdateDrugRequest;
import com.app.response.APIResponse;
import com.app.response.APIStatus;
import com.app.service.DrugService;
import com.app.utils.Constant;
import com.app.utils.ResponseUtil;


@RestController
@RequestMapping(value = Constant.DRUG_API)
@CrossOrigin(origins = {Constant.CROSS_ORIGIN_LOCAL_8000, Constant.CROSS_ORIGIN_LOCAL_8001, Constant.CROSS_ORIGIN_LOCAL_8080 })
public class DrugRestController {

	private DrugService drugService;
	
	private ModelMapper mapper = new ModelMapper();
	
	private static final Logger log = LoggerFactory.getLogger(DrugRestController.class);

	@Autowired
	public DrugRestController(DrugService drugService) {
		this.drugService = drugService;
	}

	@GetMapping
	public List<Drug> findAll(){
		List<Drug> drugs =  drugService.findAll();
		return drugs;
	}
	
	@PostMapping(value = Constant.DRUG_GET_LIST_PAGING_SORT_SEARCH_FILTER)
	public ResponseEntity<APIResponse> getListPagingSortSearchFilter(@RequestBody DrugPagingSearchSortModel cpssm){
		Page<Drug> drugs =  drugService.doFilterSearchPagingDrug(cpssm.getSearchKey(), cpssm.getLoaiVatTu(), cpssm.getStatus(),
										cpssm.getPageSize(), cpssm.getPageNumber());
		try {
			if(drugs == null) {
				throw new ApplicationException(APIStatus.ERR_DRUG_LIST_IS_EMPTY);
			}
			log.info("get list filter successfully");
			return ResponseUtil.responseSuccess(drugs);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("error drug list is empty");
			throw new ApplicationException(APIStatus.ERR_DRUG_LIST_IS_EMPTY);
		}
	}
	
	@GetMapping(value = Constant.DRUG_GET_DETAIL)
	public ResponseEntity<APIResponse> getDrugDetail(@PathVariable("drugId") long cateId){
		try {
			Drug drug = drugService.findById(cateId);
			if(drug == null) {
				throw new ApplicationException(APIStatus.ERR_DRUG_ID_NOT_EXIST);
			}
			log.info("get drug detail successfully");
			return ResponseUtil.responseSuccess(drug);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("error drug id not exists");
			throw new ApplicationException(APIStatus.ERR_DRUG_ID_NOT_EXIST);
		}
	}
	
	@PostMapping(value = Constant.DRUG_CREATE)
	public ResponseEntity<APIResponse> createDrug(@Validated @RequestBody CreateDrugRequest drugRequest){
		Drug getDrug = drugService. findByCode(drugRequest.getCode());
		if (getDrug != null) {
			log.error("error drug code already exists");
			throw new ApplicationException(APIStatus.ERR_DRUG_CODE_ALREADY_EXISTS);
		}
		try {
			mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			Drug drug = mapper.map(drugRequest, Drug.class);
			drugService.insert(drug);
			log.info("create drug successfully");
			return ResponseUtil.responseSuccess("Create drug successfully");
		} catch (Exception e) {
			// TODO: handle exception
			log.error("error create drug");
			throw new ApplicationException(APIStatus.ERR_CREATE_DRUG);
		}
	}
	
	@PostMapping(value = Constant.DRUG_DELETE)
	public ResponseEntity<APIResponse> deleteDrug(@RequestBody DeleteRequest deleteRequest){
		try {
			if(deleteRequest  != null && deleteRequest.getIds() != null) {
				for(Long id : deleteRequest.getIds()) {
					Drug drug = drugService.findById(id);
					if(drug == null) {
						throw new ApplicationException(APIStatus.ERR_DRUG_ID_NOT_EXIST);
					}
					drugService.delete(drug);
				}
			}
 
			log.info("delete drug successfully");
			return ResponseUtil.responseSuccess("Delete drug successfully");
		} catch (Exception e) {
			// TODO: handle exception
			log.error("error delete drug id not exist");
			throw new ApplicationException(APIStatus.ERR_DRUG_ID_NOT_EXIST);
		}
	}
	
	@PutMapping(value = Constant.DRUG_UPDATE)
	public ResponseEntity<APIResponse> updateDrug(@Validated @RequestBody UpdateDrugRequest drugRequest){
		Drug drugById = drugService.findById(drugRequest.getId());
		Drug drugByCode = drugService.findByCode(drugRequest.getCode());
		if(drugById != null) {
			if(drugByCode != null) {
				if(!drugByCode.getCode().equals(drugById.getCode())) {
					throw new ApplicationException(APIStatus.ERR_DRUG_CODE_ALREADY_EXISTS);
				}
			}
			try {
				mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
				
				Drug drug = mapper.map(drugRequest, Drug.class);
				drug.setStatus(drugById.getStatus());
				drug.setCreatedDate(drugById.getCreatedDate());
				drugService.update(drug);
				log.info("update drug successfully");
				return ResponseUtil.responseSuccess("update drug successfully");
			} catch (Exception e) {
				log.error("error update drug");
				throw new ApplicationException(APIStatus.ERR_UPDATE_DRUG);
			}
		}else {
			log.error("error update drug id not exist");
			throw new ApplicationException(APIStatus.ERR_DRUG_ID_NOT_EXIST);
		}
	}
}
