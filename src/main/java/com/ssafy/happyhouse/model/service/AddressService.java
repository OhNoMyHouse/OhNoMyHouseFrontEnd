package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.dto.AddressDto;

import java.sql.SQLException;
import java.util.List;

public interface AddressService {

    List<AddressDto> getSidoName() throws SQLException;

    List<AddressDto> getGugunName(String sidoName) throws SQLException;

    List<AddressDto> getDongName(String sidoName, String gugunName) throws SQLException;

    String getDongCode(String sidoName, String gugunName, String dongName) throws SQLException;

}
