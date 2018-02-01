package com.bank.xy.dao.Impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.bank.xy.dao.OrderItemDao;
import com.bank.xy.pojo.OrderItems;
import com.bank.xy.utils.JDBCUtils;

public class OrderItemDaoImpl implements OrderItemDao {

	private static DataSource dataSource = JDBCUtils.getDataSource();
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

	@Override
	public List<OrderItems> findByOid(String oid) {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(dataSource);
		String sql = "select * from orderitems where oid=?";
		try {
			return qr.query(sql, new BeanListHandler<OrderItems>(OrderItems.class), oid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("查询订单详情出错");
		}
	}

}
