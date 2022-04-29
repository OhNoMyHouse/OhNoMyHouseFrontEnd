package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.model.dto.Region;
import com.ssafy.happyhouse.util.DBUtil;

@Repository
public class AddressDAO {
	public List<Region> selectSido() throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT DISTINCT sidoName FROM baseaddress";
		try {
			List<Region> list = new ArrayList<Region>();
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new Region(rs.getString(1)));
			}
			System.out.println(list);
			return list;
		} finally {
			DBUtil.close(conn,stmt );
			DBUtil.close(rs);
		}
	}

	public List<Region> selectGugun(String sidoName) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT DISTINCT gugunName FROM baseaddress where sidoName = ?";
		try {
			List<Region> list = new ArrayList<Region>();
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sidoName);
			rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new Region(rs.getString(1)));
			}
			return list;
		} finally {
			DBUtil.close(conn,stmt );
			DBUtil.close(rs);
		}
	}

	public List<Region> selectdong(String sidoName, String gugunName) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT DISTINCT dongName FROM baseaddress where sidoName = ? and gugunName=?";
		try {
			List<Region> list = new ArrayList<Region>();
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sidoName);
			stmt.setString(2, gugunName);
			rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new Region(rs.getString(1)));
			}
			return list;
		}  finally {
			DBUtil.close(conn,stmt);
			DBUtil.close(rs);
		}
	}
	
	public String selectDongCode(String sidoName, String gugunName,String dongName) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT dongCode FROM baseaddress where sidoName = ? and gugunName=? and dongName = ?";
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sidoName);
			stmt.setString(2, gugunName);
			stmt.setString(3, dongName);
			System.out.println(sidoName);
			System.out.println(gugunName);
			System.out.println(dongName);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		}  finally {
			DBUtil.close(conn,stmt);
			DBUtil.close(rs);
		}
		return null;
	}
}
