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
import com.app.model.Shop;
import com.app.model.request.CreateShopRequest;
import com.app.model.request.DeleteRequest;
import com.app.model.request.ShopPagingSearchSortModel;
import com.app.model.request.UpdateShopRequest;
import com.app.response.APIResponse;
import com.app.response.APIStatus;
import com.app.service.ShopService;
import com.app.utils.Constant;
import com.app.utils.ResponseUtil;


@RestController
@RequestMapping(value = Constant.SHOP_API)
@CrossOrigin(origins = {Constant.CROSS_ORIGIN_LOCAL_8000, Constant.CROSS_ORIGIN_LOCAL_8001, Constant.CROSS_ORIGIN_LOCAL_8080})
public class ShopRestController {

	private ShopService shopService;
	
	private ModelMapper mapper = new ModelMapper();
	
	private static final Logger log = LoggerFactory.getLogger(ShopRestController.class);

	@Autowired
	public ShopRestController(ShopService shopService) {
		this.shopService = shopService;
	}

	@GetMapping
	public List<Shop> findAll(){
		List<Shop> shops =  shopService.findAll();
		return shops;
	}
	
	@PostMapping(value = Constant.SHOP_GET_LIST_PAGING_SORT_SEARCH_FILTER)
	public ResponseEntity<APIResponse> getListPagingSortSearchFilter(@RequestBody ShopPagingSearchSortModel cpssm){
		Page<Shop> shops =  shopService.doFilterSearchPagingShop(cpssm.getSearchKey(), cpssm.getStatus(), cpssm.getShopType(),
										cpssm.getPageSize(), cpssm.getPageNumber());
		try {
			if(shops == null) {
				throw new ApplicationException(APIStatus.ERR_SHOP_LIST_IS_EMPTY);
			}
			log.info("get list filter successfully");
			return ResponseUtil.responseSuccess(shops);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("error shop list is empty");
			throw new ApplicationException(APIStatus.ERR_SHOP_LIST_IS_EMPTY);
		}
	}
	
	@GetMapping(value = Constant.SHOP_GET_DETAIL)
	public ResponseEntity<APIResponse> getShopDetail(@PathVariable("shopId") long shopId){
		try {
			Shop shop = shopService.findById(shopId);
			if(shop == null) {
				throw new ApplicationException(APIStatus.ERR_SHOP_ID_NOT_EXIST);
			}
			log.info("get shop detail successfully");
			return ResponseUtil.responseSuccess(shop);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("error shop id not exists");
			throw new ApplicationException(APIStatus.ERR_SHOP_ID_NOT_EXIST);
		}
	}
	
	@PostMapping(value = Constant.SHOP_CREATE)
	public ResponseEntity<APIResponse> createShop(@Validated @RequestBody CreateShopRequest shopRequest){
		Shop shopByCode = shopService.findByCode(shopRequest.getCode());
		if (shopByCode != null) {
			log.error("error shop code already exists");
			throw new ApplicationException(APIStatus.ERR_SHOP_CODE_ALREADY_EXISTS);
		}
		try {
			mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			Shop shop = mapper.map(shopRequest, Shop.class);
			shopService.insert(shop);
			log.info("create shop successfully");
			return ResponseUtil.responseSuccess("Create shop successfully");
		} catch (Exception e) {
			// TODO: handle exception
			log.error("error create shop");
			throw new ApplicationException(APIStatus.ERR_CREATE_SHOP);
		}
	}
	
	@PostMapping(value = Constant.SHOP_DELETE)
	public ResponseEntity<APIResponse> deleteShop(@RequestBody DeleteRequest deleteRequest){
		try {
			if(deleteRequest  != null && deleteRequest.getIds() != null) {
				for(Long id : deleteRequest.getIds()) {
					Shop shop = shopService.findById(id);
					if(shop == null) {
						throw new ApplicationException(APIStatus.ERR_SHOP_ID_NOT_EXIST);
					}
					shopService.delete(shop);
				}
			}
 
			log.info("delete shop successfully");
			return ResponseUtil.responseSuccess("Delete shop successfully");
		} catch (Exception e) {
			// TODO: handle exception
			log.error("error delete shop id not exist");
			throw new ApplicationException(APIStatus.ERR_SHOP_ID_NOT_EXIST);
		}
	}
	
	@PutMapping(value = Constant.SHOP_UPDATE)
	public ResponseEntity<APIResponse> updateShop(@Validated @RequestBody UpdateShopRequest shopRequest){
		Shop shopById = shopService.findById(shopRequest.getId());
		Shop shopByCode = shopService.findByCode(shopRequest.getCode());
		if(shopById != null) {
			if(shopByCode != null) {
				if(!shopByCode.getName().equals(shopRequest.getCode())) {
					throw new ApplicationException(APIStatus.ERR_SHOP_CODE_ALREADY_EXISTS);
				}
			}
			try {
				mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
				
				Shop shop = mapper.map(shopRequest, Shop.class);
				shop.setStatus(shopById.getStatus());
				shop.setCreatedDate(shopById.getCreatedDate());
				shopService.update(shop);
				log.info("update shop successfully");
				return ResponseUtil.responseSuccess("update shop successfully");
			} catch (Exception e) {
				log.error("error update shop");
				throw new ApplicationException(APIStatus.ERR_UPDATE_SHOP);
			}
		}else {
			log.error("error update shop id not exist");
			throw new ApplicationException(APIStatus.ERR_SHOP_ID_NOT_EXIST);
		}
	}
}
