package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.AptdealDAO;
import com.ssafy.happyhouse.model.dto.Aptdeal;

@Service
public class AptdealService {
	AptdealDAO aptdealDAO;

	@Autowired
	public void setAptdealDAO(AptdealDAO aptdealDAO) {
		this.aptdealDAO = aptdealDAO;
	}

	public List<Aptdeal> getAptDealByAptName(String aptName) throws SQLException {
		return aptdealDAO.selectAptdealListByAptName(aptName);
	}

	public List<Aptdeal> getAptDealByDongCode(String dongCode) throws SQLException {
		return aptdealDAO.selectAptdealListByDongCode(dongCode);
	}
}
