package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.Board;

public interface BoardService {

	boolean makePoll(Board board) throws Exception;

	List<Board> getBoardList() throws Exception;

	Board getBoard(int idx) throws Exception;

	boolean update(Board board) throws Exception;

	boolean delete(int idx) throws Exception;

}