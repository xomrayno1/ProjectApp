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
import com.app.model.Department;
import com.app.model.request.CreateDepartmentRequest;
import com.app.model.request.DeleteRequest;
import com.app.model.request.DepartmentPagingSearchSortModel;
import com.app.model.request.UpdateDepartmentRequest;
import com.app.response.APIResponse;
import com.app.response.APIStatus;
import com.app.service.DepartmentService;
import com.app.utils.Constant;
import com.app.utils.ResponseUtil;


@RestController
@RequestMapping(value = Constant.DEPARTMENT_API)
@CrossOrigin(origins = {Constant.CROSS_ORIGIN_LOCAL_8000, Constant.CROSS_ORIGIN_LOCAL_8001, Constant.CROSS_ORIGIN_LOCAL_8080 })
public class DepartmentRestController {

	private DepartmentService departmentService;
	
	private ModelMapper mapper = new ModelMapper();
	
	private static final Logger log = LoggerFactory.getLogger(DepartmentRestController.class);

	@Autowired
	public DepartmentRestController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@GetMapping
	public List<Department> findAll(){
		List<Department> departments =  departmentService.findAll();
		return departments;
	}
	
	@PostMapping(value = Constant.DEPARTMENT_GET_LIST_PAGING_SORT_SEARCH_FILTER)
	public ResponseEntity<APIResponse> getListPagingSortSearchFilter(@RequestBody DepartmentPagingSearchSortModel cpssm){
		Page<Department> departments =  departmentService.doFilterSearchPagingDepartment(cpssm.getSearchKey(), cpssm.getStatus(),
										cpssm.getPageSize(), cpssm.getPageNumber());
		try {
			if(departments == null) {
				throw new ApplicationException(APIStatus.ERR_DEPARTMENT_LIST_IS_EMPTY);
			}
			log.info("get list filter successfully");
			return ResponseUtil.responseSuccess(departments);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("error department list is empty");
			throw new ApplicationException(APIStatus.ERR_DEPARTMENT_LIST_IS_EMPTY);
		}
	}
	
	@GetMapping(value = Constant.DEPARTMENT_GET_DETAIL)
	public ResponseEntity<APIResponse> getDepartmentDetail(@PathVariable("departmentId") long departmentId){
		try {
			Department department = departmentService.findById(departmentId);
			if(department == null) {
				throw new ApplicationException(APIStatus.ERR_DEPARTMENT_ID_NOT_EXIST);
			}
			log.info("get department detail successfully");
			return ResponseUtil.responseSuccess(department);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("error department id not exists");
			throw new ApplicationException(APIStatus.ERR_DEPARTMENT_ID_NOT_EXIST);
		}
	}
	
	@PostMapping(value = Constant.DEPARTMENT_CREATE)
	public ResponseEntity<APIResponse> createDepartment(@Validated @RequestBody CreateDepartmentRequest departmentRequest){
		Department getDepartment = departmentService. findByCode(departmentRequest.getCode());
		if (getDepartment != null) {
			log.error("error department code already exists");
			throw new ApplicationException(APIStatus.ERR_DEPARTMENT_CODE_ALREADY_EXISTS);
		}
		try {
			mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			Department department = mapper.map(departmentRequest, Department.class);
			departmentService.insert(department);
			log.info("create department successfully");
			return ResponseUtil.responseSuccess("Create department successfully");
		} catch (Exception e) {
			// TODO: handle exception
			log.error("error create department");
			throw new ApplicationException(APIStatus.ERR_CREATE_DEPARTMENT);
		}
	}
	
	@PostMapping(value = Constant.DEPARTMENT_DELETE)
	public ResponseEntity<APIResponse> deleteDepartment(@RequestBody DeleteRequest deleteRequest){
		try {
			if(deleteRequest  != null && deleteRequest.getIds() != null) {
				for(Long id : deleteRequest.getIds()) {
					Department department = departmentService.findById(id);
					if(department == null) {
						throw new ApplicationException(APIStatus.ERR_DEPARTMENT_ID_NOT_EXIST);
					}
					departmentService.delete(department);
				}
			}
 
			log.info("delete department successfully");
			return ResponseUtil.responseSuccess("Delete department successfully");
		} catch (Exception e) {
			// TODO: handle exception
			log.error("error delete department id not exist");
			throw new ApplicationException(APIStatus.ERR_DEPARTMENT_ID_NOT_EXIST);
		}
	}
	
	@PutMapping(value = Constant.DEPARTMENT_UPDATE)
	public ResponseEntity<APIResponse> updateDepartment(@Validated @RequestBody UpdateDepartmentRequest departmentRequest){
		Department departmentById = departmentService.findById(departmentRequest.getId());
		Department departmentByCode = departmentService.findByCode(departmentRequest.getCode());
		if(departmentById != null) {
			if(departmentByCode != null) {
				if(!departmentByCode.getCode().equals(departmentById.getCode())) {
					throw new ApplicationException(APIStatus.ERR_DEPARTMENT_CODE_ALREADY_EXISTS);
				}
			}
			try {
				mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
				
				Department department = mapper.map(departmentRequest, Department.class);
				department.setStatus(departmentById.getStatus());
				department.setCreatedDate(departmentById.getCreatedDate());
				departmentService.update(department);
				log.info("update department successfully");
				return ResponseUtil.responseSuccess("update department successfully");
			} catch (Exception e) {
				log.error("error update department");
				throw new ApplicationException(APIStatus.ERR_UPDATE_DEPARTMENT);
			}
		}else {
			log.error("error update department id not exist");
			throw new ApplicationException(APIStatus.ERR_DEPARTMENT_ID_NOT_EXIST);
		}
	}
}
