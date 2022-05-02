package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.happyhouse.model.dto.Board;
import com.ssafy.happyhouse.model.dto.PageInfo;
import com.ssafy.happyhouse.model.service.BoardService;

//@RequestMapping("/board")
@Controller
public class BoardController {
    BoardService boardService;

    @Autowired
    public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

    
    @GetMapping("/")
    private String makelist(Model model) throws Exception {
    	List<Board> list = boardService.getBoardList();
    	model.addAttribute("boardList", list);
    	return "/index";
    }
    
//    @PostMapping("/makepoll.do")
    @PostMapping("/board/makepoll.do")
    private String makePoll(@RequestParam String title, @RequestParam String content, Model model) throws Exception {
    	Board board = new Board();
    	board.setTitle(title);
    	board.setContent(content);
    	boardService.makePoll(board);
    	return "redirect:/";
    }
    
    PageInfo makePoll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int idx = Integer.parseInt(request.getParameter("idx"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        try {
            boardService.makePoll(new Board(idx, title, content));
            List<Board> list = boardService.getBoardList();
            request.setAttribute("boardList", list);
            return new PageInfo(true, "/index.jsp");
        } catch (Exception e) {
            request.setAttribute("errorMsg", "이미 있는 index입니다.");
            throw e;
        }
    }

    PageInfo updateForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int idx = Integer.parseInt(request.getParameter("idx"));
        try {
            Board board = boardService.getBoard(idx);
            System.out.println(board);
            request.setAttribute("board", board);
            return new PageInfo(true, "/board/update.jsp");
        } catch (SQLException e) {
            request.setAttribute("errorMsg", "부서 상세 조회에 실패하셨습니다.");
            throw e;
        }
    }

    PageInfo update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int idx = Integer.parseInt(request.getParameter("idx"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        try {
            Board board = new Board(idx, title, content);
            boardService.update(board);
            List<Board> list = boardService.getBoardList();
            request.setAttribute("boardList", list);
            return new PageInfo(true, "/index.jsp");
        } catch (Exception e) {
            request.setAttribute("errorMsg", "공지사항 갱신 중 문제가 발생하였습니다.");
            throw e;
        }
    }

    PageInfo delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int idx = Integer.parseInt(request.getParameter("idx"));
        try {
            boardService.delete(idx);
            List<Board> list = boardService.getBoardList();
            request.setAttribute("boardList", list);
            return new PageInfo(true, "/index.jsp");
        } catch (SQLException e) {
            request.setAttribute("errorMsg", "부서 목록 조회에 실패하셨습니다.");
            throw e;
        }
    }
}
