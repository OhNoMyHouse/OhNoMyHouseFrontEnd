package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.Board;

public interface BoardDAO {

	boolean makePoll(Board board) throws Exception;

	List<Board> getBoardList() throws Exception;

	Board getBoard(int idx) throws SQLException;

	boolean update(Board board) throws SQLException;

	boolean delete(int idx) throws SQLException;

}