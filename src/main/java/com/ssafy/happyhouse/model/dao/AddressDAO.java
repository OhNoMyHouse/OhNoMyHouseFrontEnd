package com.ssafy.happyhouse.model.dao;

import com.ssafy.happyhouse.model.dto.AddressDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressDAO {

    List<AddressDto> selectSido();

    List<AddressDto> selectGugun(String sidoName);

    List<AddressDto> selectdong(String sidoName, String gugunName);

    String selectDongCode(String sidoName, String gugunName, String dongName);

}
