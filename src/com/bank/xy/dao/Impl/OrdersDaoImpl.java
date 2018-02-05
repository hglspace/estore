package com.bank.xy.dao.Impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
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
	@Override
	public Orders findOrderByOidAndUid(String oid, int uid) {
		QueryRunner qr= new QueryRunner(dataSource);
		String sql = "select * from orders where id=? and uid=?";
		try {
			return qr.query(sql, new BeanHandler<Orders>(Orders.class), oid,uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("查询订单出错");
		}
	}
	@Override
	public void deleteOrder(Connection conn, String oid) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner();
		String sql = "delete from orders where id=?";
		qr.update(conn, sql, oid);
	}
	@Override
	public List<Orders> queryOrderStatus(int status) {
		// TODO Auto-generated method stub
		QueryRunner qr= new QueryRunner(dataSource);
		String sql = "select * from orders where status=?";
		try {
			return qr.query(sql, new BeanListHandler<Orders>(Orders.class), status);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("根据状态查询订单出错");
		}
	}
	@Override
	public void updateOrderStatus(String id) {
		// TODO Auto-generated method stub
		QueryRunner qr= new QueryRunner(dataSource);
		String sql = "update orders set status=3 where id=?";
		try {
			qr.update(sql, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("更新订单状态失败");
		}
	}

}
