package com.bank.xy.service.Impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.bank.xy.dao.CartDao;
import com.bank.xy.dao.GoodsDao;
import com.bank.xy.dao.OrderItemDao;
import com.bank.xy.dao.OrdersDao;
import com.bank.xy.dao.Impl.CartDaoImpl;
import com.bank.xy.dao.Impl.GoodsDaoImpl;
import com.bank.xy.dao.Impl.OrderItemDaoImpl;
import com.bank.xy.dao.Impl.OrdersDaoImpl;
import com.bank.xy.pojo.Goods;
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
	private static GoodsDao gd = new GoodsDaoImpl();
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
		return od.findAll(uid);
	}
	@Override
	public Orders findOrder(String oid, int uid) {
        Orders orders=od.findOrderByOidAndUid(oid,uid);
        List<OrderItems> oList = otd.findByOid(oid);
        for (OrderItems orderItems : oList) {
			int gid = orderItems.getGid();
			Goods goods = gd.findById(gid);
			orderItems.setGoods(goods);
		}
        orders.setoList(oList);
		return orders;
	}
	@Override
	public void deleteOrderById(String oid) {

		Connection conn = null;
		try {
			//获取连接
			conn = JDBCUtils.getConnection();
			//设置手动提交事务
			conn.setAutoCommit(false);
			//业务处理
			//2.删除订单明细表
			otd.deleteOrderItems(conn,oid);//orders和orderitmes有外键关联，所以先删除这个
			//1.删除订单表
			od.deleteOrder(conn,oid);
		} catch (Exception e) {
			//回滚数据
			if(conn!=null){
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}finally{
			//提交事务
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
	public List<Orders> queryOrderStatus(int status) {
       return od.queryOrderStatus(status);
	}
	@Override
	public void changeOrderStatus(String id) {
		// TODO Auto-generated method stub
		od.updateOrderStatus(id);
	}

}
