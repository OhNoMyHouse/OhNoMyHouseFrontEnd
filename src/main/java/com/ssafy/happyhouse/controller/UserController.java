package com.ssafy.happyhouse.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    
    @PostMapping("/regist.do")
    private String registUser(User user, Model model) {
    	try {
			userService.registUser(user);
		} catch (Exception e) {
			model.addAttribute("errorMsg", "회원가입 중 문제가 발생하였습니다.");
		}
    	return "redirect:/user/login_form.do";
    }
    
    @PostMapping("/login.do")
    private String login(@RequestParam String userid, @RequestParam String password, HttpSession session, Model model) {
        try {
            String name = userService.login(userid, password);
            if (name != null) {
                session.setAttribute("userId", userid);
                session.setAttribute("userName", name);
                return "redirect:/";
            } else {
                model.addAttribute("errorMsg", "아이디나 비밀번호가 일치하지 않습니다.");
                return "/user/login_form.do";
            }
        } catch (Exception e) {
            model.addAttribute("errorMsg", "로그인 실행 중 문제가 발생하였습니다.");
            return "/user/login_form.do";
        }
    }
    
    @GetMapping("/logout.do")
    private String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
    
    @GetMapping("/update_form.do")
    private String updateForm(HttpSession session, Model model) throws Exception {
    	String userid = (String) session.getAttribute("userId");
    	User user = userService.getUser(userid);
		model.addAttribute("user", user);
		return "/user/update";
    }
    
    @PostMapping("/update.do")
    private String update(User user, Model model) {
    	try {
			userService.update(user);
		} catch (Exception e) {
			model.addAttribute("errorMsg", "회원정보 수정 중 문제가 발생하였습니다.");
		}
    	return "redirect:/";
    }
    
    @GetMapping
    private String search(@RequestParam String userid, @RequestParam String name, Model model) throws Exception {
    	String password = userService.getPw(userid, name);
    	model.addAttribute("password", password);
    	return "/user/search";
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
