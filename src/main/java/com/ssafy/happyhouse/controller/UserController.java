package com.ssafy.happyhouse.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.happyhouse.model.dto.PageInfo;
import com.ssafy.happyhouse.model.dto.User;
import com.ssafy.happyhouse.model.service.UserService;

@RequestMapping("/user")
@Controller
public class UserController {
    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
		this.userService = userService;
	}

//	@Override
//    public PageInfo process(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String subUrl = request.getServletPath().substring(5);
//        if (subUrl.equals("/regist_form.do")) {
//            return registForm(request, response);
//        } else if (subUrl.equals("/regist.do")) {
//            return registUser(request, response);
//        } else if (subUrl.equals("/login_form.do")) {
//            return loginForm(request, response);
//        } else if (subUrl.equals("/login.do")) {
//            return login(request, response);
//        } else if (subUrl.equals("/logout.do")) {
//            return logout(request, response);
//        } else if (subUrl.equals("/update_form.do")) {
//            return updateForm(request, response);
//        } else if (subUrl.equals("/update.do")) {
//            return update(request, response);
//        } else if (subUrl.equals("/search_form.do")) {
//            return searchForm(request, response);
//        } else if (subUrl.equals("/search.do")) {
//            return search(request, response);
//        }
//        return null;
//    }

    PageInfo registForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new PageInfo(true, "/user/regist.jsp");
    }

    PageInfo registUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String telephone = request.getParameter("telephone");
        try {
            userService.registUser(new User(userid, password, name, address, telephone));
            return new PageInfo(true, "/user/login_form.do");
        } catch (Exception e) {
            request.setAttribute("errorMsg", "로그인 실행 중 문제가 발생하였습니다.");
            throw e;
        }
    }

    PageInfo loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new PageInfo(true, "/user/login.jsp");
    }

    PageInfo login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        try {
            String name = userService.login(userid, password);
            if (name != null) {
                HttpSession session = request.getSession();
                session.setAttribute("userId", userid);
                session.setAttribute("userName", name);
                return new PageInfo(false, "/index.jsp");
            } else {
                request.setAttribute("errorMsg", "아이디나 비밀번호가 일치하지 않습니다.");
                return new PageInfo(true, "/user/login_form.do");
            }
        } catch (Exception e) {
            request.setAttribute("errorMsg", "로그인 실행 중 문제가 발생하였습니다.");
            throw e;
        }
    }

    PageInfo logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().invalidate();
        return new PageInfo(false, "/index.jsp");
    }

    PageInfo updateForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userid = request.getParameter("userid");
        try {
            User user = userService.getUser(userid);
            request.setAttribute("user", user);
            return new PageInfo(true, "/user/update.jsp");
        } catch (SQLException e) {
            request.setAttribute("errorMsg", "부서 상세 조회에 실패하셨습니다.");
            throw e;
        }
    }

    PageInfo update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String telephone = request.getParameter("telephone");
        try {
            User user = new User(userid, password, name, address, telephone);
            userService.update(user);
            return new PageInfo(false, "/index.jsp");
        } catch (Exception e) {
            request.setAttribute("errorMsg", "로그인 실행 중 문제가 발생하였습니다.");
            throw e;
        }
    }

    PageInfo searchForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new PageInfo(false, "/user/search.jsp");
    }

    PageInfo search(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userid = request.getParameter("userid");
        String name = request.getParameter("name");
        try {
            String password = userService.getPw(userid, name);
            request.setAttribute("password", password);
            return new PageInfo(true, "/user/search.jsp");

        } catch (Exception e) {
            request.setAttribute("errorMsg", "아이디 혹은 비밀번호를 잘못 입력하셨습니다. 중 문제가 발생하였습니다.");
            throw e;
        }
    }
}
