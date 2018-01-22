package com.bank.xy.service;

import com.bank.xy.pojo.User;

public interface UserService {

	User findByName(String username);

	int register(User user);

	User findByNamePassword(String username, String password);
}
