package com.ssafy.happyhouse.model.dto;

public class AptInfo {
	int aptCode;
	String aptName;
	String dongCode;
	String dongName;
	String buildYear;
	String latitude;
	String longtitude;

	@Override
	public String toString() {
		return "AptInfo [aptCode=" + aptCode + ", aptName=" + aptName + ", dongCode=" + dongCode + ", dongName="
				+ dongName + ", buildYear=" + buildYear + ", latitude=" + latitude + ", longtitude=" + longtitude + "]";
	}

	public AptInfo(int aptCode, String aptName, String dongCode, String dongName, String buildYear, String latitude,
			String longtitude) {
		super();
		this.aptCode = aptCode;
		this.aptName = aptName;
		this.dongCode = dongCode;
		this.dongName = dongName;
		this.buildYear = buildYear;
		this.latitude = latitude;
		this.longtitude = longtitude;
	}

	public int getAptCode() {
		return aptCode;
	}

	public void setAptCode(int aptCode) {
		this.aptCode = aptCode;
	}

	public String getAptName() {
		return aptName;
	}

	public void setAptName(String aptName) {
		this.aptName = aptName;
	}

	public String getDongCode() {
		return dongCode;
	}

	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}

	public String getDongName() {
		return dongName;
	}

	public void setDongName(String dongName) {
		this.dongName = dongName;
	}

	public String getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}

}
