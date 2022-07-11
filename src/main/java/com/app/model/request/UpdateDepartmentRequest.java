package com.app.model.request;

public class UpdateDepartmentRequest {
	private Long id;
	private String code;
	private String name;
	private Integer typeOffice;
	private Integer departmentType;
	private Integer roomType;
	private Integer level;
	private Integer departmentCodeBC;
	 
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getTypeOffice() {
		return typeOffice;
	}
	public void setTypeOffice(Integer typeOffice) {
		this.typeOffice = typeOffice;
	}
	public Integer getDepartmentType() {
		return departmentType;
	}
	public void setDepartmentType(Integer departmentType) {
		this.departmentType = departmentType;
	}
	public Integer getRoomType() {
		return roomType;
	}
	public void setRoomType(Integer roomType) {
		this.roomType = roomType;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getDepartmentCodeBC() {
		return departmentCodeBC;
	}
	public void setDepartmentCodeBC(Integer departmentCodeBC) {
		this.departmentCodeBC = departmentCodeBC;
	}
	
	
}
