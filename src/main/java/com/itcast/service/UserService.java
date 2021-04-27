package com.itcast.service;

import java.util.Map;

import com.itcast.domain.User;

public interface UserService {


	/**
	 * 登录
	 * 
	 * @param map
	 * @return
	 */
	public User login(Map<String, String> map);

}