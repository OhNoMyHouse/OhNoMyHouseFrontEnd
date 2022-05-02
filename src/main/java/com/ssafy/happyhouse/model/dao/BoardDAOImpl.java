package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.model.dto.Board;
import com.ssafy.happyhouse.util.DBUtil;

@Repository
public class BoardDAOImpl implements BoardDAO {

    /*
    CREATE TABLE `boardinfo` (
    `idx` varchar(20) NOT NULL,
    `title` varchar(20) NOT NULL,
    `content` varchar(500) NOT NULL,
    PRIMARY KEY (`idx`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
    */
    @Override
	public boolean makePoll(Board board) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "INSERT INTO boardinfo (title, content) VALUES(?, ?)";
        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, board.getTitle());
            stmt.setString(2, board.getContent());

            return stmt.executeUpdate() > 0;
        } finally {
            DBUtil.close(conn, stmt);
        }
    }

    @Override
	public List<Board> getBoardList() throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT idx, title, content FROM boardinfo";
        ArrayList<Board> list = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Board
                        (rs.getInt(1), rs.getNString(2), rs.getString(3)));
            }
        } finally {
            DBUtil.close(conn, stmt);
            DBUtil.close(rs);
        }
        return list;
    }

    @Override
	public Board getBoard(int idx) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT idx, title, content FROM boardinfo WHERE idx = ?";
        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idx);

            rs = stmt.executeQuery();
            if (rs.next()) {
                return new Board(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        } finally {
            DBUtil.close(conn, stmt);
            DBUtil.close(rs);
        }
        return null;
    }

    @Override
	public boolean update(Board board) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "UPDATE boardinfo SET title = ?, content = ? WHERE idx=?";
        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, board.getTitle());
            stmt.setString(2, board.getContent());
            stmt.setInt(3, board.getIdx());
            return stmt.executeUpdate() > 0;
        } finally {
            DBUtil.close(conn, stmt);
        }
    }

    @Override
	public boolean delete(int idx) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "delete from boardinfo where idx = ?";
        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idx);
            return stmt.executeUpdate() > 0;
        } finally {
            DBUtil.close(conn, stmt);
        }
    }
}
