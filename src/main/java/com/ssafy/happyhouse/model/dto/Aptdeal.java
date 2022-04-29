package com.ssafy.happyhouse.model.dto;

public class Aptdeal {

	private String aptName;
	private String dongName;
	private String floor;
	private int dealYear;
	private int dealMonth;
	private int dealDay;
	private String area;
	private String dealAmount;

	public String getAptName() {
		return aptName;
	}

	public void setAptName(String aptName) {
		this.aptName = aptName;
	}

	public String getDongName() {
		return dongName;
	}

	public void setDongName(String dongName) {
		this.dongName = dongName;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public int getDealYear() {
		return dealYear;
	}

	public void setDealYear(int dealYear) {
		this.dealYear = dealYear;
	}

	public int getDealMonth() {
		return dealMonth;
	}

	public void setDealMonth(int dealMonth) {
		this.dealMonth = dealMonth;
	}

	public int getDealDay() {
		return dealDay;
	}

	public void setDealDay(int dealDay) {
		this.dealDay = dealDay;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

	public Aptdeal(String aptName, String dongName, String floor, int dealYear, int dealMonth, int dealDay, String area,
			String dealAmount) {
		super();
		this.aptName = aptName;
		this.dongName = dongName;
		this.floor = floor;
		this.dealYear = dealYear;
		this.dealMonth = dealMonth;
		this.dealDay = dealDay;
		this.area = area;
		this.dealAmount = dealAmount;
	}

	@Override
	public String toString() {
		return "Aptdeal [aptName=" + aptName + ", dongName=" + dongName + ", floor=" + floor + ", dealYear=" + dealYear
				+ ", dealMonth=" + dealMonth + ", dealDay=" + dealDay + ", area=" + area + ", dealAmount=" + dealAmount
				+ "]";
	}

}
