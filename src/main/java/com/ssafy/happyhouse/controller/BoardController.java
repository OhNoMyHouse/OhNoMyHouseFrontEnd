package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.happyhouse.model.dto.Board;
import com.ssafy.happyhouse.model.service.BoardService;

@Controller
public class BoardController {
    BoardService boardService;

    @Autowired
    public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

    @GetMapping("/")
    private String boardList(Model model) {
    	List<Board> list = boardService.getBoardList();
    	model.addAttribute("boardList", list);
    	return "/index";
    }

    @PostMapping("/board/makepoll.do")
    private String makePoll(Board board) {
    	boardService.makePoll(board);
    	return "redirect:/";
    }
    
    @GetMapping("/board/update_form.do")
    private String updateForm(@RequestParam int idx, Model model) {
    	Board board = boardService.getBoard(idx);
    	model.addAttribute("board", board);
    	return "/board/update";
    }
    
    @PostMapping("/board/update.do")
    private String update(Board board) {
    	boardService.update(board);
    	return "redirect:/";
    }
    
    @PostMapping("/board/delete.do")
    private String delete(@RequestParam int idx) {
    	boardService.delete(idx);
    	return "redirect:/";
    }
}
