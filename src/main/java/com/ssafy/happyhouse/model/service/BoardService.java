package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.BoardDAO;
import com.ssafy.happyhouse.model.dto.Board;

@Service
public class BoardService {
	BoardDAO boardDAO;

	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	public boolean makePoll(Board board) throws Exception {
		return boardDAO.makePoll(board);
	}

	public List<Board> getBoardList() throws Exception {
		return boardDAO.getBoardList();
	}

	public Board getBoard(int idx) throws Exception {
		return boardDAO.getBoard(idx);
	}

	public boolean update(Board board) throws Exception {
		return boardDAO.update(board);
	}

	public boolean delete(int idx) throws Exception {
		return boardDAO.delete(idx);
	}
}
