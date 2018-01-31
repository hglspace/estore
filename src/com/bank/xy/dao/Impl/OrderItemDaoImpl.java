package com.bank.xy.dao.Impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.bank.xy.dao.OrderItemDao;
import com.bank.xy.pojo.OrderItems;

public class OrderItemDaoImpl implements OrderItemDao {

	@Override
	public void addOrderItem(Connection conn,List<OrderItems> getoList) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner();
		for (OrderItems orderItems : getoList) {
			String sql = "insert into orderitems values(?,?,?)";
			//执行sql语句
			qr.update(conn,sql,orderItems.getOid(),orderItems.getGid(),orderItems.getBuynum());
		}
	}

}
