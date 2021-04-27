package com.itcast.dao;

import java.util.Map;

import com.itcast.domain.User;

public interface UserDao {


	/**
	 * 登录
	 * 
	 * @param map
	 * @return
	 */
	public User login(Map<String, String> map);

}
