package com.springbook.biz.user.impl;

import com.springbook.biz.user.UserVO;

public class UserServiceImpl implements UserService {
	
	
	private UserDAO userDAO;
	
	public void setUserDAO(UserDAO user){
		this.userDAO = user;
	}
	
	
	@Override
	public UserVO getUser(UserVO vo) {
		return userDAO.getUser(vo);
	}

}
