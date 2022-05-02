package com.ssafy.happyhouse.model.mapper;

import com.ssafy.happyhouse.model.dto.AddressDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface AddressMapper {

    List<AddressDto> selectSido() throws SQLException;

    List<AddressDto> selectGugun(String sidoName) throws SQLException;

    List<AddressDto> selectdong(String sidoName, String gugunName) throws SQLException;

    String selectDongCode(String sidoName, String gugunName, String dongName) throws SQLException;
}
