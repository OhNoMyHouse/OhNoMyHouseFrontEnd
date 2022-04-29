package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.AptInfoDAO;
import com.ssafy.happyhouse.model.dto.AptInfo;

@Service
public class AptInfoService {
	AptInfoDAO aptInfoDAO;

	@Autowired
	public void setAptInfoDAO(AptInfoDAO aptInfoDAO) {
		this.aptInfoDAO = aptInfoDAO;
	}

	public List<AptInfo> getAptInfoByDongCode(String dongCode) throws SQLException {
		return aptInfoDAO.selectAptByDongCode(dongCode);
	}

}
