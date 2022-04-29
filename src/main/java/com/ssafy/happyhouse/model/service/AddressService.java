package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.AddressDAO;
import com.ssafy.happyhouse.model.dto.Region;

@Service
public class AddressService {
	AddressDAO addressDAO;

	@Autowired
	public void setAddressDAO(AddressDAO addressDAO) {
		this.addressDAO = addressDAO;
	}

	public List<Region> getSidoName() throws SQLException {
		return addressDAO.selectSido();
	}

	public List<Region> getGugunName(String sidoName) throws SQLException {
		return addressDAO.selectGugun(sidoName);
	}

	public List<Region> getDongName(String sidoName, String gugunName) throws SQLException {
		return addressDAO.selectdong(sidoName, gugunName);
	}
	
	public String getDongCode(String sidoName, String gugunName,String dongName) throws SQLException {
		return addressDAO.selectDongCode(sidoName,gugunName,dongName);
	}
}
