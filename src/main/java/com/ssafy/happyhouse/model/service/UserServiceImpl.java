package com.ssafy.happyhouse.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.UserDAO;
import com.ssafy.happyhouse.model.dto.User;

@Service
public class UserServiceImpl implements UserService {

    UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public boolean registUser(User user) {
        return userDAO.registUser(user);
    }

    @Override
	public String login(Map<String, String> map) {
        return userDAO.login(map);
    }

    @Override
	public User getUser(String userid) {
        return userDAO.getUser(userid);
    }

    @Override
	public boolean update(User user) {
        return userDAO.update(user);
    }

    @Override
	public String getPw(String id, String name) {
        return userDAO.getPw(id, name);
    }
}
