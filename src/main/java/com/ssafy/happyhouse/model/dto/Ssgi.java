package com.ssafy.happyhouse.model.dto;

public class Ssgi {
    private String name;
    private String storeType;
    private Long dongCode;
    private String address;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStoreType() {
		return storeType;
	}
	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}
	public Long getDongCode() {
		return dongCode;
	}
	public void setDongCode(Long dongCode) {
		this.dongCode = dongCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Ssgi(String name, String storeType, Long dongCode, String address) {
		super();
		this.name = name;
		this.storeType = storeType;
		this.dongCode = dongCode;
		this.address = address;
	}
    

    
}
