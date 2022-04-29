package com.ssafy.happyhouse.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.UserDAO;
import com.ssafy.happyhouse.model.dto.User;

@Service
public class UserService {

    UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public boolean registUser(User user) throws Exception {
        return userDAO.registUser(user);
    }

    public String login(String id, String pass) throws Exception {
        return userDAO.login(id, pass);
    }

    public User getUser(String userid) throws Exception {
        return userDAO.getUser(userid);
    }

    public boolean update(User user) throws Exception {
        return userDAO.update(user);
    }

    public String getPw(String id, String name) throws Exception {
        return userDAO.getPw(id, name);
    }
}
