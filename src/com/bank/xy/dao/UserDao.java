package com.bank.xy.dao;

import com.bank.xy.pojo.User;

public interface UserDao {

	User findByName(String username);

	int register(User user);
}
