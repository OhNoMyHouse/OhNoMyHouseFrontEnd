package com.ssafy.happyhouse.model.service;

import java.util.Map;

import com.ssafy.happyhouse.model.dto.User;

public interface UserService {

	boolean registUser(User user) throws Exception;

	String login(Map<String, String> map) throws Exception;

	User getUser(String userid) throws Exception;

	boolean update(User user) throws Exception;

	String getPw(String id, String name) throws Exception;

}