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
 
	
	//contact api
	public static final String CONTACT_API = API_PREFIX + "/contact";
	public static final String CONTACT_GET_LIST_PAGING_SORT_SEARCH_FILTER = "/contact_get_list_paging_sort_search_filter";
	public static final String CONTACT_CREATE = "/contact_create";
	public static final String CONTACT_CHANGE_STATUS = "/contact_change_status";
	
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
	
	enum BookingStatus {
		CHO_XU_LY(0),
		DA_HOAN_THANH(1),
		HUY(-1)
		;
		
		private final int value;

		private BookingStatus(int value) {
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
