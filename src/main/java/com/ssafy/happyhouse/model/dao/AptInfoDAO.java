package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.model.dto.AptInfo;
import com.ssafy.happyhouse.util.DBUtil;

@Repository
public class AptInfoDAO {

	public List<AptInfo> selectAptByDongCode(String dongCode) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT aptCode,aptName,dongName,buildYear,lat,lng FROM houseinfo where dongCode=?";
		try {
			List<AptInfo> list = new ArrayList<AptInfo>();
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dongCode);
			rs = stmt.executeQuery();
			while (rs.next()) {
				AptInfo aptInfo = new AptInfo(rs.getInt(1), rs.getString(2), dongCode, rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6));
				list.add(aptInfo);
				System.out.println(aptInfo);
			}
			return list;
		} finally {
			DBUtil.close(conn, stmt);
			DBUtil.close(rs);
		}
	}

}
