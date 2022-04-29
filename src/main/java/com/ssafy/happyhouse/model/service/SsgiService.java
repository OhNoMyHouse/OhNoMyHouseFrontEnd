package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.SsgiDAO;
import com.ssafy.happyhouse.model.dto.Ssgi;

@Service
public class SsgiService {
	SsgiDAO ssgiDAO;
	
	@Autowired
	public void setSsgiDAO(SsgiDAO ssgiDAO) {
		this.ssgiDAO = ssgiDAO;
	}

	public List<Ssgi> getSsigListByDongCode(Long dongCode) throws SQLException {
		return ssgiDAO.selectSsgi(dongCode);
	}
}
