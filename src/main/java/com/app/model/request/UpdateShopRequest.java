package com.app.model.request;

public class UpdateShopRequest {
	private long id;
	private String name;   
	private String fullName;  
	private String shortName;
	private String unsignedName;  
	private String code;  
	private Integer type;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getUnsignedName() {
		return unsignedName;
	}
	public void setUnsignedName(String unsignedName) {
		this.unsignedName = unsignedName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
