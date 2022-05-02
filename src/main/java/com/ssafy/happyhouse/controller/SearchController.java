package com.ssafy.happyhouse.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.happyhouse.model.dto.DataInfo;
import com.ssafy.happyhouse.model.service.AddressServiceImpl;
import com.ssafy.happyhouse.model.service.AptInfoService;
import com.ssafy.happyhouse.model.service.AptdealService;
import com.ssafy.happyhouse.model.service.SsgiService;

@RequestMapping("/search")
@Controller
public class SearchController {
	AddressServiceImpl addressServiceImpl;
	AptInfoService aptInfoService;
	AptdealService aptdealService;
	SsgiService ssgiService;
	
	@Autowired
	public void setAddressService(AddressServiceImpl addressServiceImpl) {
		this.addressServiceImpl = addressServiceImpl;
	}

	@Autowired
	public void setAptInfoService(AptInfoService aptInfoService) {
		this.aptInfoService = aptInfoService;
	}

	@Autowired
	public void setAptdealService(AptdealService aptdealService) {
		this.aptdealService = aptdealService;
	}

	@Autowired
	public void setSsgiService(SsgiService ssgiService) {
		this.ssgiService = ssgiService;
	}

//	@Override
//	public Object process(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//		String subUrl = request.getServletPath().substring(7);
//
//		if (subUrl.equals("/InfoWithDongCode.do")) {
//			return aptInfoListByDongCode(request, response);
//		} else if (subUrl.equals("/DealWithAptName.do")) {
//			return dealInfoListByAptName(request, response);
//		} else if (subUrl.equals("/DealWithDongCode.do")) {
//			return dealInfoListByDongCode(request, response);
//		}else if (subUrl.equals("/SsgiWithDongCode.do")) {
//			return ssgiListByDongCode(request, response);
//		}
//		return null;
//	}

	private DataInfo ssgiListByDongCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			String sido = request.getParameter("sido");
			String gugun = request.getParameter("gugun");
			String dong = request.getParameter("dong");
			Long dongCode = Long.parseLong(addressServiceImpl.getDongCode(sido,gugun,dong));
			return new DataInfo("application/json;charset=utf-8", ssgiService.getSsigListByDongCode(dongCode));
			
		} catch (Exception e) {
			request.setAttribute("errorMsg", "홈페이지 로드에 실패했습니다");
			throw e;
		}
	}

	private DataInfo dealInfoListByDongCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String sido = request.getParameter("sido");
			String gugun = request.getParameter("gugun");
			String dong = request.getParameter("dong");

			String dongCode = addressServiceImpl.getDongCode(sido,gugun,dong);
			return new DataInfo("application/json;charset=utf-8", aptdealService.getAptDealByDongCode(dongCode));
		
		} catch (Exception e) {
			request.setAttribute("errorMsg", "홈페이지 로드에 실패했습니다");
			throw e;
		}
	}

	private DataInfo dealInfoListByAptName(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String aptName = request.getParameter("aptName");
			return new DataInfo("application/json;charset=utf-8", aptdealService.getAptDealByAptName(aptName));
		} catch (Exception e) {
			request.setAttribute("errorMsg", "홈페이지 로드에 실패했습니다");
			throw e;
		}
	}

	private DataInfo aptInfoListByDongCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String sido = request.getParameter("sido");
			String gugun = request.getParameter("gugun");
			String dong = request.getParameter("dong");
			
			String dongCode = addressServiceImpl.getDongCode(sido,gugun,dong);
			System.out.println(dongCode);
			return new DataInfo("application/json;charset=utf-8", aptInfoService.getAptInfoByDongCode(dongCode));
		} catch (Exception e) {
			request.setAttribute("errorMsg", "홈페이지 로드에 실패했습니다");
			throw e;
		}
	}

}
