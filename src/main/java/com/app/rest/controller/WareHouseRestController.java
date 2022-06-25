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
import com.app.model.WareHouse;
import com.app.model.request.CreateWareHouseRequest;
import com.app.model.request.DeleteRequest;
import com.app.model.request.WareHousePagingSearchSortModel;
import com.app.model.request.UpdateWareHouseRequest;
import com.app.response.APIResponse;
import com.app.response.APIStatus;
import com.app.service.WareHouseService;
import com.app.utils.Constant;
import com.app.utils.ResponseUtil;


@RestController
@RequestMapping(value = Constant.WAREHOUSE_API)
@CrossOrigin(origins = {Constant.CROSS_ORIGIN_LOCAL_8000, Constant.CROSS_ORIGIN_LOCAL_8001, Constant.CROSS_ORIGIN_LOCAL_8080})
public class WareHouseRestController {

	private WareHouseService wareHouseService;
	
	private ModelMapper mapper = new ModelMapper();
	
	private static final Logger log = LoggerFactory.getLogger(WareHouseRestController.class);

	@Autowired
	public WareHouseRestController(WareHouseService wareHouseService) {
		this.wareHouseService = wareHouseService;
	}

	@GetMapping
	public List<WareHouse> findAll(){
		List<WareHouse> wareHouses =  wareHouseService.findAll();
		return wareHouses;
	}
	
	@PostMapping(value = Constant.WAREHOUSE_GET_LIST_PAGING_SORT_SEARCH_FILTER)
	public ResponseEntity<APIResponse> getListPagingSortSearchFilter(@RequestBody WareHousePagingSearchSortModel cpssm){
		Page<WareHouse> wareHouses =  wareHouseService.doFilterSearchPagingWareHouse(cpssm.getSearchKey(), cpssm.getStatus(), cpssm.getType(),
										cpssm.getPageSize(), cpssm.getPageNumber());
		try {
			if(wareHouses == null) {
				throw new ApplicationException(APIStatus.ERR_WAREHOUSE_LIST_IS_EMPTY);
			}
			log.info("get list filter successfully");
			return ResponseUtil.responseSuccess(wareHouses);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("error wareHouse list is empty");
			throw new ApplicationException(APIStatus.ERR_WAREHOUSE_LIST_IS_EMPTY);
		}
	}
	
	@GetMapping(value = Constant.WAREHOUSE_GET_DETAIL)
	public ResponseEntity<APIResponse> getWareHouseDetail(@PathVariable("wareHouseId") long wareHouseId){
		try {
			WareHouse wareHouse = wareHouseService.findById(wareHouseId);
			if(wareHouse == null) {
				throw new ApplicationException(APIStatus.ERR_WAREHOUSE_ID_NOT_EXIST);
			}
			log.info("get wareHouse detail successfully");
			return ResponseUtil.responseSuccess(wareHouse);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("error wareHouse id not exists");
			throw new ApplicationException(APIStatus.ERR_WAREHOUSE_ID_NOT_EXIST);
		}
	}
	
	@PostMapping(value = Constant.WAREHOUSE_CREATE)
	public ResponseEntity<APIResponse> createWareHouse(@Validated @RequestBody CreateWareHouseRequest wareHouseRequest){
		WareHouse wareHouseByCode = wareHouseService.findByCode(wareHouseRequest.getCode());
		if (wareHouseByCode != null) {
			log.error("error wareHouse code already exists");
			throw new ApplicationException(APIStatus.ERR_WAREHOUSE_CODE_ALREADY_EXISTS);
		}
		try {
			mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			WareHouse wareHouse = mapper.map(wareHouseRequest, WareHouse.class);
			wareHouseService.insert(wareHouse);
			log.info("create wareHouse successfully");
			return ResponseUtil.responseSuccess("Create wareHouse successfully");
		} catch (Exception e) {
			// TODO: handle exception
			log.error("error create wareHouse");
			throw new ApplicationException(APIStatus.ERR_CREATE_WAREHOUSE);
		}
	}
	
	@PostMapping(value = Constant.WAREHOUSE_DELETE)
	public ResponseEntity<APIResponse> deleteWareHouse(@RequestBody DeleteRequest deleteRequest){
		try {
			if(deleteRequest  != null && deleteRequest.getIds() != null) {
				for(Long id : deleteRequest.getIds()) {
					WareHouse wareHouse = wareHouseService.findById(id);
					if(wareHouse == null) {
						throw new ApplicationException(APIStatus.ERR_WAREHOUSE_ID_NOT_EXIST);
					}
					wareHouseService.delete(wareHouse);
				}
			}
 
			log.info("delete wareHouse successfully");
			return ResponseUtil.responseSuccess("Delete wareHouse successfully");
		} catch (Exception e) {
			// TODO: handle exception
			log.error("error delete wareHouse id not exist");
			throw new ApplicationException(APIStatus.ERR_WAREHOUSE_ID_NOT_EXIST);
		}
	}
	
	@PutMapping(value = Constant.WAREHOUSE_UPDATE)
	public ResponseEntity<APIResponse> updateWareHouse(@Validated @RequestBody UpdateWareHouseRequest wareHouseRequest){
		WareHouse wareHouseById = wareHouseService.findById(wareHouseRequest.getId());
		WareHouse wareHouseByCode = wareHouseService.findByCode(wareHouseRequest.getCode());
		if(wareHouseById != null) {
			if(wareHouseByCode != null) {
				if(!wareHouseByCode.getName().equals(wareHouseRequest.getCode())) {
					throw new ApplicationException(APIStatus.ERR_WAREHOUSE_CODE_ALREADY_EXISTS);
				}
			}
			try {
				mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
				
				WareHouse wareHouse = mapper.map(wareHouseRequest, WareHouse.class);
				wareHouse.setStatus(wareHouseById.getStatus());
				wareHouse.setCreatedDate(wareHouseById.getCreatedDate());
				wareHouseService.update(wareHouse);
				log.info("update wareHouse successfully");
				return ResponseUtil.responseSuccess("update wareHouse successfully");
			} catch (Exception e) {
				log.error("error update wareHouse");
				throw new ApplicationException(APIStatus.ERR_UPDATE_WAREHOUSE);
			}
		}else {
			log.error("error update wareHouse id not exist");
			throw new ApplicationException(APIStatus.ERR_WAREHOUSE_ID_NOT_EXIST);
		}
	}
}
