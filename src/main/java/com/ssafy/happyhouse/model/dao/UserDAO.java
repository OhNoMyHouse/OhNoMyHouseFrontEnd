package com.ssafy.happyhouse.model.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.User;

@Mapper
public interface UserDAO {

	boolean registUser(User user) throws Exception;

	String login(Map<String, String> map);

	User getUser(String userid) throws Exception;

	boolean update(User user) throws Exception;

	String getPw(String id, String name);


}