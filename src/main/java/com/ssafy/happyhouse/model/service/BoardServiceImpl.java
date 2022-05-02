package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.BoardDAO;
import com.ssafy.happyhouse.model.dto.Board;

@Service
public class BoardServiceImpl implements BoardService {
	BoardDAO boardDAO;

	@Autowired
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	@Override
	public boolean makePoll(Board board) throws Exception {
		return boardDAO.makePoll(board);
	}

	@Override
	public List<Board> getBoardList() throws Exception {
		return boardDAO.getBoardList();
	}

	@Override
	public Board getBoard(int idx) throws Exception {
		return boardDAO.getBoard(idx);
	}

	@Override
	public boolean update(Board board) throws Exception {
		return boardDAO.update(board);
	}

	@Override
	public boolean delete(int idx) throws Exception {
		return boardDAO.delete(idx);
	}
}
