package com.itcast.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import com.itcast.dao.UserDao;
import com.itcast.domain.User;
import com.itcast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("UserService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public User login(Map<String, String> map) {
		return userDao.login(map);
	}

}
