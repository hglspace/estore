package com.bank.xy.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.bank.xy.pojo.OrderItems;

public interface OrderItemDao {

	void addOrderItem(Connection conn, List<OrderItems> getoList) throws SQLException;

	List<OrderItems> findByOid(String oid);

}
