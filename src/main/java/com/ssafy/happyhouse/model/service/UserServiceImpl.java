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
	public boolean registUser(User user) throws Exception {
        return userDAO.registUser(user);
    }

    @Override
	public String login(Map<String, String> map) throws Exception {
        return userDAO.login(map);
    }

    @Override
	public User getUser(String userid) throws Exception {
        return userDAO.getUser(userid);
    }

    @Override
	public boolean update(User user) throws Exception {
        return userDAO.update(user);
    }

    @Override
	public String getPw(String id, String name) throws Exception {
        return userDAO.getPw(id, name);
    }
}
