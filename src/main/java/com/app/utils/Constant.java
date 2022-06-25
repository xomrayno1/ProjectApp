package com.app.utils;

public interface Constant {
	
	public static final String CROSS_ORIGIN_LOCAL_8000 = "http://localhost:8000";
	public static final String CROSS_ORIGIN_LOCAL_8001 = "http://localhost:8001";
	public static final String CROSS_ORIGIN_LOCAL_8080 = "http://localhost:8080";
 
	
	public static final String API_PREFIX = "/api/v1";
	
	// product API
	public static final String PRODUCT_API = API_PREFIX + "/products";
	public static final String PRODUCT_GET_LIST_PAGING_SORT_SEARCH_FILTER = "/product_get_list_paging_sort_search_filter";
	public static final String PRODUCT_GET_DETAIL = "/product_get_detail/{proId}";
	public static final String PRODUCT_DELETE = "/product_delete";
	public static final String PRODUCT_UPDATE = "/product_update";
	public static final String PRODUCT_CREATE = "/product_create";
	public static final String PRODUCT_UPLOAD = "/product_upload";
	public static final String PRODUCT_GET_LIST_ACTIVE = "/product_get_list_active";
	
	//category API
	public static final String CATEGORY_API = API_PREFIX + "/categories";
	public static final String CATEGORY_GET_LIST_PAGING_SORT_SEARCH_FILTER = "/category_get_list_paging_sort_search_filter";
	public static final String CATEGORY_GET_DETAIL= "/category_get_detail/{cateId}";
	public static final String CATEGORY_DELETE = "/category_delete";
	public static final String CATEGORY_UPDATE = "/category_update";
	public static final String CATEGORY_CREATE = "/category_create";
	public static final String CATEGORY_GET_LIST_ACTIVE = "/category_get_list_active";
	
	//shop API
	public static final String SHOP_API = API_PREFIX + "/shop";
	public static final String SHOP_GET_LIST_PAGING_SORT_SEARCH_FILTER = "/shop_get_list_paging_sort_search_filter";
	public static final String SHOP_GET_DETAIL= "/shop_get_detail/{shopId}";
	public static final String SHOP_DELETE = "/shop_delete";
	public static final String SHOP_UPDATE = "/shop_update";
	public static final String SHOP_CREATE = "/shop_create";
	public static final String SHOP_GET_LIST_ACTIVE = "/shop_get_list_active";
	
	//warehouse API
	public static final String WAREHOUSE_API = API_PREFIX + "/warehouse";
	public static final String WAREHOUSE_GET_LIST_PAGING_SORT_SEARCH_FILTER = "/warehouse_get_list_paging_sort_search_filter";
	public static final String WAREHOUSE_GET_DETAIL= "/warehouse_get_detail/{warehouseId}";
	public static final String WAREHOUSE_DELETE = "/warehouse_delete";
	public static final String WAREHOUSE_UPDATE = "/warehouse_update";
	public static final String WAREHOUSE_CREATE = "/warehouse_create";
	public static final String WAREHOUSE_GET_LIST_ACTIVE = "/warehouse_get_list_active";
 
	//user api
	public static final String USER_API = API_PREFIX + "/user";
	public static final String USER_GET_LIST_PAGING_SORT_SEARCH_FILTER = "/user_get_list_paging_sort_search_filter";
	public static final String USER_GET_DETAIL= "/user_get_detail/{userId}";
	public static final String USER_DELETE = "/user_delete";
	public static final String USER_UPDATE = "/user_update";
	public static final String USER_CREATE = "/user_create";
	
	//auth
	
	public static final String AUTH_API = "/authenticate";
 
	
	public static final String PATH_RESOURCE = "src/main/resources";
	public static final String PATH_UPLOAD = "uploads";
 
	public enum Status {
		IN_ACTIVE(0),
		ACTIVE(1);
		
		private final int value;

		private Status(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

	}
	
	enum ShopType {
		PROVINCE(1),
		DISTRICT(2),
		WARD(3),
		VILLAGE(4)
		;
		
		private final int value;

		private ShopType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
	
	enum WareHouseType {
		KHO_CHAN(1),
		KHO_LE(2),
		KHO_CO_SO(3),
		QUAY_THUOC(4)
		;
		
		private final int value;

		private WareHouseType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
	
	enum ROLE {
		ADMIN(1),
		USER(2),
		;
		
		private final int value;

		private ROLE(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
 
	}
	
	public static final String PATTERN_DATE_DDMMYYYY = "dd/mm/yyyy";
	
	public static final String PATTERN_DATE_TIMESTAMP = "dd/mm/yyyy HH:mm:ss";
	
	public static final String PATTERN_DATE_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	
	
}