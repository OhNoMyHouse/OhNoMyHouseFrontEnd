package com.ssafy.happyhouse.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	// DB driver를 위한 필드들
	private static final String driverName = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://127.0.0.1:3306/happyhouse?serverTimezone=UTC";
	private static final String user = "ssafy";
	private static final String pass = "ssafy";

	private static DBUtil instance = new DBUtil();

	// DB Driver(연결)
	private DBUtil() {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 연결 된 DB 상태를 return
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, pass);
	}

	// 예외상황 시 매소드 오버로딩
	public static void close(Connection conn, Statement stmt) {
		close(conn);
		close(stmt);
	}

	// 예외상황 시 매소드 오버로딩
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 예외상황 시 매소드 오버로딩
	public static void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 예외상황 시 매소드 오버로딩
	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}