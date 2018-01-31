package com.bank.xy.dao.Impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.bank.xy.dao.OrdersDao;
import com.bank.xy.pojo.Orders;
import com.bank.xy.utils.JDBCUtils;

public class OrdersDaoImpl implements OrdersDao {

	private static DataSource dataSource = JDBCUtils.getDataSource();
	@Override
	public void addOrder(Connection conn,Orders order) throws SQLException {

		QueryRunner qr = new QueryRunner();
		String sql = "insert into orders values(?,?,?,?,?,?)";
		List args = new ArrayList();
		args.add(order.getId());
		args.add(order.getUid());
		args.add(order.getTotalprice());
		args.add(order.getAddress());
		args.add(order.getStatus());
		args.add(order.getCreatetime());
		qr.update(conn, sql, args.toArray());
	}
	@Override
	public List<Orders> findAll(int uid) {
		//创建queryrunner对象
		QueryRunner qr= new QueryRunner(dataSource);
		//编写sql语句
		String sql ="select * from orders where uid = ?";
		//执行sql语句
		try {
			return qr.query(sql, new BeanListHandler<Orders>(Orders.class), uid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询订单失败");
		}

	}

}
