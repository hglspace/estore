package com.bank.xy.service.Impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.bank.xy.dao.CartDao;
import com.bank.xy.dao.OrderItemDao;
import com.bank.xy.dao.OrdersDao;
import com.bank.xy.dao.Impl.CartDaoImpl;
import com.bank.xy.dao.Impl.OrderItemDaoImpl;
import com.bank.xy.dao.Impl.OrdersDaoImpl;
import com.bank.xy.pojo.OrderItems;
import com.bank.xy.pojo.Orders;
import com.bank.xy.service.CartService;
import com.bank.xy.service.OrderItemService;
import com.bank.xy.service.OrdersService;
import com.bank.xy.utils.JDBCUtils;

public class OrdersServiceImpl implements OrdersService {

	private static OrdersDao od = new OrdersDaoImpl();
	private static OrderItemDao otd = new OrderItemDaoImpl();
	private static CartDao cd = new CartDaoImpl();
	@Override
	public void addOrders(Orders order) {

		Connection conn = null;
		try {
			conn=JDBCUtils.getConnection();
			conn.setAutoCommit(false);
			od.addOrder(conn,order);
			otd.addOrderItem(conn,order.getoList());
			int uid = order.getUid();
			cd.clearCart(conn,uid,order.getoList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(conn!=null){
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}finally{
			if(conn!=null){
				try {
					conn.commit();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	@Override
	public List<Orders> findAll(int uid) {
		// TODO Auto-generated method stub
		return od.findAll(uid);
	}

}
