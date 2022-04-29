package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.model.dto.User;
import com.ssafy.happyhouse.util.DBUtil;

@Repository
public class UserDAO {
    /*
    CREATE TABLE `userinfo` (
      `userid` varchar(20) NOT NULL,
      `password` varchar(20) NOT NULL,
      `name` varchar(20) NOT NULL,
      `address` varchar(20) DEFAULT NULL,
      `telephone` varchar(20) DEFAULT NULL,
      PRIMARY KEY (`userid`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
     */
    public boolean registUser(User user) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "INSERT INTO userinfo VALUES(?, ?, ?, ?, ?)";
        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, user.getUserid());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getAddress());
            stmt.setString(5, user.getTelephone());

            return stmt.executeUpdate() > 0;
        } finally {
            DBUtil.close(conn, stmt);
        }
    }

    public String login(String id, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT name FROM userinfo WHERE userid = ? and password = ?";
        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.setString(2, password);

            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, stmt);
            DBUtil.close(rs);
        }
        return null;
    }

    public User getUser(String userid) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT userid, password, name, address, telephone FROM userinfo WHERE userid = ?";
        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userid);

            rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            }
        } finally {
            DBUtil.close(conn, stmt);
            DBUtil.close(rs);
        }
        return null;
    }

    public boolean update(User user) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "UPDATE userinfo SET password = ?, name = ?, address = ?, telephone = ? WHERE userid = ?";
        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(5, user.getUserid());
            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getAddress());
            stmt.setString(4, user.getTelephone());

            return stmt.executeUpdate() > 0;
        } finally {
            DBUtil.close(conn, stmt);
        }
    }

    public String getPw(String id, String name) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT password FROM userinfo WHERE userid = ? and name = ?";
        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.setString(2, name);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, stmt);
            DBUtil.close(rs);
        }
        return null;
    }
}
