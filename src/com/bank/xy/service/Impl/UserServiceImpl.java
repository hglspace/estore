package com.bank.xy.service.Impl;

import com.bank.xy.dao.UserDao;
import com.bank.xy.dao.Impl.UserDaoImpl;
import com.bank.xy.pojo.User;
import com.bank.xy.service.UserService;
import com.bank.xy.utils.MD5Utils;
import com.bank.xy.utils.UUIDUtils;

public class UserServiceImpl implements UserService {

	private static UserDao ud = new UserDaoImpl();
	@Override
	public User findByName(String username) {
		return ud.findByName(username);
	}

	@Override
	public int register(User user) {
		String password=user.getPassword();
		password=MD5Utils.str2MD5(password);
		user.setPassword(password);
		return ud.register(user);
	}

}
