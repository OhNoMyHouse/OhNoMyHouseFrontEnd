package com.ssafy.happyhouse.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.happyhouse.model.dto.DataInfo;
import com.ssafy.happyhouse.model.service.AddressService;

@RequestMapping("/region")
@Controller
public class RegionController {

	AddressService addressService;

	@Autowired
	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

//	@Override
//	public Object process(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String act = request.getParameter("act");
//		if (act.equals("sido")) {
//			return getSido(request, response);
//		} else if (act.equals("gugun")) {
//			return getGugun(request, response);
//		}else if (act.equals("dong")) {
//			return getDong(request, response);
//		}
//		return null;
//	}

	private Object getDong(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String sido = request.getParameter("sido");
		String gugun = request.getParameter("gugun");
		return new DataInfo("application/json;charset=utf-8", addressService.getDongName(sido, gugun));
	}
	
	private Object getGugun(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String sido = request.getParameter("sido");
		return new DataInfo("application/json;charset=utf-8", addressService.getGugunName(sido));
	}

	private DataInfo getSido(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		return new DataInfo("application/json;charset=utf-8", addressService.getSidoName());
	}

}
