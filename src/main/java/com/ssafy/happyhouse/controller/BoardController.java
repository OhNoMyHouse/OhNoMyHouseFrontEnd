package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.happyhouse.model.dto.Board;
import com.ssafy.happyhouse.model.dto.PageInfo;
import com.ssafy.happyhouse.model.service.BoardService;

@RequestMapping("/board")
@Controller
public class BoardController {
    BoardService boardService;

    @Autowired
    public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

//	@Override
//    public PageInfo process(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String subUrl = request.getServletPath().substring(6);
//        if (subUrl.equals("/makepoll_form.do")) {
//            return makePollForm(request, response);
//        } else if (subUrl.equals("/makepoll.do")) {
//            return makePoll(request, response);
//        } else if (subUrl.equals("/update_form.do")) {
//            return updateForm(request, response);
//        } else if (subUrl.equals("/update.do")) {
//            return update(request, response);
//        } else if (subUrl.equals("/delete.do")) {
//            return delete(request, response);
//        }
//        return null;
//    }

    @GetMapping("/makepoll.do")
    private String makePollForm() {
    	return "redirect:/board/makepoll";
    }
//    PageInfo makePollForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        return new PageInfo(false, "/board/makepoll.jsp");
//    }

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
