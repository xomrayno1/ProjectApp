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
import com.app.model.Supplier;
import com.app.model.request.SupplierPagingSearchSortModel;
import com.app.model.request.CreateSupplierRequest;
import com.app.model.request.DeleteRequest;
import com.app.model.request.UpdateSupplierRequest;
import com.app.response.APIResponse;
import com.app.response.APIStatus;
import com.app.service.SupplierService;
import com.app.utils.Constant;
import com.app.utils.ResponseUtil;


@RestController
@RequestMapping(value = Constant.SUPPLIER_API)
@CrossOrigin(origins = {Constant.CROSS_ORIGIN_LOCAL_8000, Constant.CROSS_ORIGIN_LOCAL_8001, Constant.CROSS_ORIGIN_LOCAL_8080 })
public class SupplierRestController {

	private SupplierService supplierService;
	
	private ModelMapper mapper = new ModelMapper();
	
	private static final Logger log = LoggerFactory.getLogger(SupplierRestController.class);

	@Autowired
	public SupplierRestController(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	@GetMapping
	public List<Supplier> findAll(){
		List<Supplier> suppliers =  supplierService.findAll();
		return suppliers;
	}
	
	@PostMapping(value = Constant.SUPPLIER_GET_LIST_PAGING_SORT_SEARCH_FILTER)
	public ResponseEntity<APIResponse> getListPagingSortSearchFilter(@RequestBody SupplierPagingSearchSortModel cpssm){
		Page<Supplier> suppliers =  supplierService.doFilterSearchPagingSupplier(cpssm.getSearchKey(), cpssm.getStatus(),
										cpssm.getPageSize(), cpssm.getPageNumber());
		try {
			if(suppliers == null) {
				throw new ApplicationException(APIStatus.ERR_SUPPLIER_LIST_IS_EMPTY);
			}
			log.info("get list filter successfully");
			return ResponseUtil.responseSuccess(suppliers);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("error supplier list is empty");
			throw new ApplicationException(APIStatus.ERR_SUPPLIER_LIST_IS_EMPTY);
		}
	}
	
	@GetMapping(value = Constant.SUPPLIER_GET_DETAIL)
	public ResponseEntity<APIResponse> getSupplierDetail(@PathVariable("supplierId") long supplierId){
		try {
			Supplier supplier = supplierService.findById(supplierId);
			if(supplier == null) {
				throw new ApplicationException(APIStatus.ERR_SUPPLIER_ID_NOT_EXIST);
			}
			log.info("get supplier detail successfully");
			return ResponseUtil.responseSuccess(supplier);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("error supplier id not exists");
			throw new ApplicationException(APIStatus.ERR_SUPPLIER_ID_NOT_EXIST);
		}
	}
	
	@PostMapping(value = Constant.SUPPLIER_CREATE)
	public ResponseEntity<APIResponse> createSupplier(@Validated @RequestBody CreateSupplierRequest supplierRequest){
		Supplier getSupplier = supplierService. findByCode(supplierRequest.getCode());
		if (getSupplier != null) {
			log.error("error supplier code already exists");
			throw new ApplicationException(APIStatus.ERR_SUPPLIER_CODE_ALREADY_EXISTS);
		}
		try {
			mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			Supplier supplier = mapper.map(supplierRequest, Supplier.class);
			supplierService.insert(supplier);
			log.info("create supplier successfully");
			return ResponseUtil.responseSuccess("Create supplier successfully");
		} catch (Exception e) {
			// TODO: handle exception
			log.error("error create supplier");
			throw new ApplicationException(APIStatus.ERR_CREATE_SUPPLIER);
		}
	}
	
	@PostMapping(value = Constant.SUPPLIER_DELETE)
	public ResponseEntity<APIResponse> deleteSupplier(@RequestBody DeleteRequest deleteRequest){
		try {
			if(deleteRequest  != null && deleteRequest.getIds() != null) {
				for(Long id : deleteRequest.getIds()) {
					Supplier supplier = supplierService.findById(id);
					if(supplier == null) {
						throw new ApplicationException(APIStatus.ERR_SUPPLIER_ID_NOT_EXIST);
					}
					supplierService.delete(supplier);
				}
			}
 
			log.info("delete supplier successfully");
			return ResponseUtil.responseSuccess("Delete supplier successfully");
		} catch (Exception e) {
			// TODO: handle exception
			log.error("error delete supplier id not exist");
			throw new ApplicationException(APIStatus.ERR_SUPPLIER_ID_NOT_EXIST);
		}
	}
	
	@PutMapping(value = Constant.SUPPLIER_UPDATE)
	public ResponseEntity<APIResponse> updateSupplier(@Validated @RequestBody UpdateSupplierRequest supplierRequest){
		Supplier supplierById = supplierService.findById(supplierRequest.getId());
		Supplier supplierByCode = supplierService.findByCode(supplierRequest.getCode());
		if(supplierById != null) {
			if(supplierByCode != null) {
				if(!supplierByCode.getCode().equals(supplierById.getCode())) {
					throw new ApplicationException(APIStatus.ERR_SUPPLIER_CODE_ALREADY_EXISTS);
				}
			}
			try {
				mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
				
				Supplier supplier = mapper.map(supplierRequest, Supplier.class);
				supplier.setStatus(supplierById.getStatus());
				supplier.setCreatedDate(supplierById.getCreatedDate());
				supplierService.update(supplier);
				log.info("update supplier successfully");
				return ResponseUtil.responseSuccess("update supplier successfully");
			} catch (Exception e) {
				log.error("error update supplier");
				throw new ApplicationException(APIStatus.ERR_UPDATE_SUPPLIER);
			}
		}else {
			log.error("error update supplier id not exist");
			throw new ApplicationException(APIStatus.ERR_SUPPLIER_ID_NOT_EXIST);
		}
	}
}
