package com.bank.xy.dao.Impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.bank.xy.dao.UserDao;
import com.bank.xy.pojo.User;
import com.bank.xy.utils.JDBCUtils;

public class UserDaoImpl implements UserDao {

	
	private static DataSource dataSource=JDBCUtils.getDataSource();
	@Override
	public User findByName(String username) {
		QueryRunner qr = new QueryRunner(dataSource);
		String sql = "select * from user where username=?";
		User user=null;
		try {
			user=qr.query(sql, new BeanHandler<User>(User.class), username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("查询用户失败");
		}
		return user;
	}
	@Override
	public int register(User user) {
        QueryRunner qr = new QueryRunner(dataSource);
        String sql = "insert into user values(null,?,?,?,?)";
        List<Object> list = new ArrayList<Object>();
        list.add(user.getNickname());
        list.add(user.getUsername());
        list.add(user.getPassword());
        list.add(user.getRole());
        try {
			return qr.update(sql, list.toArray());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -2;
		}
	}
	@Override
	public User findByNamePassword(String username, String password) {
        QueryRunner qr = new QueryRunner(dataSource);
        String sql = "select * from user where username=? and password=?";
        try {
			return qr.query(sql, new BeanHandler<User>(User.class), username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("查询用户出错");
		}
	}

}
