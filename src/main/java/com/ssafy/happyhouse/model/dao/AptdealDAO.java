package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.model.dto.Aptdeal;
import com.ssafy.happyhouse.util.DBUtil;

@Repository
public class AptdealDAO {

	public List<Aptdeal> selectAptdealListByAptName(String aptName) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select houseinfo.aptName, houseinfo.dongName,housedeal.floor, housedeal.dealYear,housedeal.dealMonth,housedeal.dealDay, housedeal.area, housedeal.dealAmount\r\n"
				+ "from houseinfo\r\n" + "inner join housedeal\r\n" + "on housedeal.aptCode = houseinfo.aptCode\r\n"
				+ "where houseinfo.aptName like concat('%',?,'%')";
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, aptName);

			List<Aptdeal> list = new ArrayList<Aptdeal>();

			rs = stmt.executeQuery();
			while (rs.next()) {
				Aptdeal aptdeal = new Aptdeal(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4),
						rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getString(8));
				list.add(aptdeal);
				System.out.println(list);
			}

			return list;

		} finally {
			DBUtil.close(conn, stmt);
			DBUtil.close(rs);
		}
	}

	public List<Aptdeal> selectAptdealListByDongCode(String dongCode) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select houseinfo.aptName, houseinfo.dongName,housedeal.floor, housedeal.dealYear,housedeal.dealMonth,housedeal.dealDay, housedeal.area, housedeal.dealAmount\r\n"
				+ "from houseinfo\r\n" + "inner join housedeal\r\n" + "on housedeal.aptCode = houseinfo.aptCode\r\n"
				+ "where houseinfo.dongCode =?";
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dongCode);

			List<Aptdeal> list = new ArrayList<Aptdeal>();

			rs = stmt.executeQuery();
			while (rs.next()) {
				Aptdeal aptdeal = new Aptdeal(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4),
						rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getString(8));
				list.add(aptdeal);
			}

			return list;

		} finally {
			DBUtil.close(conn, stmt);
			DBUtil.close(rs);
		}
	}
}
