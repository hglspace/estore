package com.bank.xy.service;

import java.util.List;

import com.bank.xy.pojo.Orders;

public interface OrdersService {

	void addOrders(Orders order);

	List<Orders> findAll(int uid);

	Orders findOrder(String oid, int uid);

	void deleteOrderById(String oid);

	List<Orders> queryOrderStatus(int status);

	void changeOrderStatus(String id);

}
