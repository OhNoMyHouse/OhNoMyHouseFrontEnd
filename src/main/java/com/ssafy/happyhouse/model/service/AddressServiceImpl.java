package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.dto.AddressDto;
import com.ssafy.happyhouse.model.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressMapper addressMapper;

	@Override
	public List<AddressDto> getSidoName() throws SQLException {
		return addressMapper.selectSido();
	}

	@Override
	public List<AddressDto> getGugunName(String sidoName) throws SQLException {
		return addressMapper.selectGugun(sidoName);
	}

	@Override
	public List<AddressDto> getDongName(String sidoName, String gugunName) throws SQLException {
		return addressMapper.selectdong(sidoName, gugunName);
	}
	
	@Override
	public String getDongCode(String sidoName, String gugunName, String dongName) throws SQLException {
		return addressMapper.selectDongCode(sidoName,gugunName,dongName);
	}
}
