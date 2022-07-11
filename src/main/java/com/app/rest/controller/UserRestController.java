package com.app.rest.controller;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.aspectj.weaver.patterns.IfPointcut.IfFalsePointcut;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.app.model.Roles;
import com.app.model.Users;
import com.app.model.request.CreateUserRequest;
import com.app.model.request.DeleteRequest;
import com.app.model.request.UpdateUserRequest;
import com.app.model.request.UserPagingSearchSortModel;
import com.app.response.APIResponse;
import com.app.response.APIStatus;
import com.app.service.DepartmentService;
import com.app.service.RoleService;
import com.app.service.UserService;
import com.app.utils.Constant;
import com.app.utils.ResponseUtil;


@RestController
@RequestMapping(value = Constant.USER_API)
@CrossOrigin(origins = {Constant.CROSS_ORIGIN_LOCAL_8000, Constant.CROSS_ORIGIN_LOCAL_8001, Constant.CROSS_ORIGIN_LOCAL_8080 })
public class UserRestController {

	private UserService userService;
	private RoleService roleService;
	private DepartmentService departmentService;
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	private ModelMapper mapper = new ModelMapper();
	
	private static final Logger log = LoggerFactory.getLogger(UserRestController.class);
	private SimpleDateFormat sdf = new SimpleDateFormat(Constant.PATTERN_DATE_DDMMYYYY);

	@Autowired
	public UserRestController(UserService userService, RoleService roleService, DepartmentService departmentService) {
		this.userService = userService;
		this.roleService = roleService;
		this.departmentService = departmentService;
	}
 
	
	@PostMapping(value = Constant.USER_GET_LIST_PAGING_SORT_SEARCH_FILTER)
	public ResponseEntity<APIResponse> getListPagingSortSearchFilter(@RequestBody UserPagingSearchSortModel cpssm){
		Page<Users> users =  userService.doFilterSearchPagingUsers(cpssm.getSearchKey(), cpssm.getStatus(),
										cpssm.getPageSize(), cpssm.getPageNumber());
		try {
 
			log.info("get list filter successfully");
			return ResponseUtil.responseSuccess(users);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("error users list is empty");
			throw new ApplicationException(APIStatus.ERR_PROCESS);
		}
	}
	
	@GetMapping(value = Constant.USER_GET_DETAIL)
	public ResponseEntity<APIResponse> getUserDetail(@PathVariable("userId") long userId){
		try {
			Users user = userService.findById(userId);
			if(user == null) {
				throw new ApplicationException(APIStatus.ERR_USER_ID_NOT_EXIST);
			}
			log.info("get user detail successfully");
			return ResponseUtil.responseSuccess(user);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("error user id not exists");
			throw new ApplicationException(APIStatus.ERR_USER_ID_NOT_EXIST);
		}
	}
	
	@PostMapping(value = Constant.USER_CREATE)
	public ResponseEntity<APIResponse> createUser(@Validated @RequestBody CreateUserRequest createUserRequest){
		Users user = userService.findByUsername(createUserRequest.getUsername());
		if (user != null) {
			log.error("error username already exists");
			throw new ApplicationException(APIStatus.ERR_USER_NAME_ALREADY_EXISTS);
		}
		try {
	
			Roles role = roleService.findById(createUserRequest.getRole());
			Set<Roles> setRoles = new HashSet<Roles>();
			setRoles.add(role);
			user = new Users();
			user.setRoles(setRoles);
			user.setEmail(createUserRequest.getEmail());
			user.setUsername(createUserRequest.getUsername());
			user.setAddress(createUserRequest.getAddress());
			user.setStatus(Constant.Status.ACTIVE.getValue());
			user.setGender(createUserRequest.getGender());
			user.setPassword(bcryptEncoder.encode(createUserRequest.getPassword()));
			user.setBirthDay(sdf.parse(createUserRequest.getBirthDay()));
			user.setPhone(createUserRequest.getPhone());
			user.setProvince(createUserRequest.getProvince());
			user.setDistrict(createUserRequest.getDistrict());
			user.setWard(createUserRequest.getWard());
			user.setType(createUserRequest.getType());
			user.setEducation(createUserRequest.getEducation());
			user.setLevel(createUserRequest.getLevel());
			user.setName(createUserRequest.getName());
			user.setCode(createUserRequest.getCode());
			Department department = departmentService.findById(createUserRequest.getDepartmentId());
			if(department == null) {
				throw new ApplicationException(APIStatus.ERR_USER_LEVEL_NOT_EXIST);
			}
			user.setDepartment(department);
			userService.save(user);
			log.info("create user successfully");
			return ResponseUtil.responseSuccess("Create user successfully");
		} catch (Exception e) {
			// TODO: handle exception
			log.error("error create user");
			e.printStackTrace();
			throw new ApplicationException(APIStatus.ERR_CREATE_USER);
		}
	}
	
	@PostMapping(value = Constant.USER_DELETE)
	public ResponseEntity<APIResponse> deleteUser(@RequestBody DeleteRequest deleteRequest){
		try {
			if(deleteRequest  != null && deleteRequest.getIds() != null) {
				for(Long id : deleteRequest.getIds()) {
					Users users = userService.findById(id);
					if(users == null) {
						throw new ApplicationException(APIStatus.ERR_USER_ID_NOT_EXIST);
					}
					userService.delete(users);
				}
			}
			
			log.info("delete user successfully");
			return ResponseUtil.responseSuccess("Delete user successfully");
		} catch (Exception e) {
			// TODO: handle exception
			log.error("error delete user id not exist");
			throw new ApplicationException(APIStatus.ERR_USER_ID_NOT_EXIST);
		}
	}
	
	@PutMapping(value = Constant.USER_UPDATE)
	public ResponseEntity<APIResponse> updateUser(@Validated @RequestBody UpdateUserRequest updateUserRequest){
		Users userById = userService.findById(updateUserRequest.getId());
		Users userByName = userService.findByUsername(updateUserRequest.getUsername());
		if(userById != null) {
			if(userByName != null) {
				if(!userByName.getUsername().equals(userByName.getUsername())) {
					throw new ApplicationException(APIStatus.ERR_USER_NAME_ALREADY_EXISTS);
				}
			}
			try {
				Roles role = roleService.findById(updateUserRequest.getRole());
				Set<Roles> setRoles = new HashSet<Roles>();
				setRoles.add(role);
				 
				userById.setRoles(setRoles);
				userById.setEmail(updateUserRequest.getEmail());
				userById.setUsername(updateUserRequest.getUsername());
				userById.setAddress(updateUserRequest.getAddress());
				userById.setStatus(Constant.Status.ACTIVE.getValue());
				userById.setGender(updateUserRequest.getGender());
				userById.setBirthDay(sdf.parse(updateUserRequest.getBirthDay()));
				if(!userById.getPassword().equals(updateUserRequest.getPassword())) {
					userById.setPassword(bcryptEncoder.encode(updateUserRequest.getPassword()));
				}
				userById.setPhone(updateUserRequest.getPhone());
				userById.setProvince(updateUserRequest.getProvince());
				userById.setDistrict(updateUserRequest.getDistrict());
				userById.setWard(updateUserRequest.getWard());
				userById.setType(updateUserRequest.getType());
				userById.setEducation(updateUserRequest.getEducation());
				userById.setLevel(updateUserRequest.getLevel());
				Department department = departmentService.findById(updateUserRequest.getDepartmentId());
				if(department == null) {
					throw new ApplicationException(APIStatus.ERR_USER_LEVEL_NOT_EXIST);
				}
				userById.setDepartment(department);
				userById.setName(updateUserRequest.getName());
				userById.setCode(updateUserRequest.getCode());
				userService.save(userById);
				log.info("update user successfully");
				return ResponseUtil.responseSuccess("update user successfully");
			} catch (Exception e) {
				log.error("error update user");
				throw new ApplicationException(APIStatus.ERR_UPDATE_USER);
			}
		}else {
			log.error("error update user id not exist");
			throw new ApplicationException(APIStatus.ERR_USER_ID_NOT_EXIST);
		}
	}
	
	@GetMapping(value = Constant.USER_GET_BY_LEVEL)
	public ResponseEntity<APIResponse> getByLevel(@PathVariable("level") Integer level){
		try {
			List<Users> users = userService.findByLevel(level);
			if(users == null) {
				throw new ApplicationException(APIStatus.ERR_USER_LEVEL_NOT_EXIST);
			}
			log.info("get user detail successfully");
			return ResponseUtil.responseSuccess(users);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("error user id not exists");
			throw new ApplicationException(APIStatus.ERR_USER_ID_NOT_EXIST);
		}
	}
	
	@GetMapping(value = Constant.USER_GET_BY_DEPARTMENT)
	public ResponseEntity<APIResponse> getByDepartment(@PathVariable("departmentId") Long departmentId){
		try {
			Department department = departmentService.findById(departmentId);
			if(department != null) {
				List<Users> users = userService.findByDepartment(department);
				if(users == null) {
					throw new ApplicationException(APIStatus.ERR_USER_DEPARTMENT_NOT_EXIST);
				}
				log.info("get user detail successfully");
				return ResponseUtil.responseSuccess(users);
			}else {
				throw new ApplicationException(APIStatus.ERR_USER_DEPARTMENT_NOT_EXIST);
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("error user id not exists");
			throw new ApplicationException(APIStatus.ERR_USER_ID_NOT_EXIST);
		}
	}
	
}
