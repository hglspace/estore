package com.bank.xy.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.bank.xy.pojo.Orders;

public interface OrdersDao {

	void addOrder(Connection conn, Orders order) throws SQLException;

	List<Orders> findAll(int uid);

}
