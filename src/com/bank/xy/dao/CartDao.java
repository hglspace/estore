package com.bank.xy.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.bank.xy.pojo.Cart;
import com.bank.xy.pojo.OrderItems;

public interface CartDao {

	Cart findByUidAndGid(int uid, int gid);

	int updateCart(Cart cart);

	int addCart(Cart cart);

	List<Cart> findByUid(int id);

	int deleteCart(int id, int gid);

	void clearCart(Connection conn, int uid, List<OrderItems> getoList) throws SQLException;

}
