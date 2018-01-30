package com.bank.xy.dao;

import java.util.List;

import com.bank.xy.pojo.Cart;

public interface CartDao {

	Cart findByUidAndGid(int uid, int gid);

	int updateCart(Cart cart);

	int addCart(Cart cart);

	List<Cart> findByUid(int id);

	int deleteCart(int id, int gid);

}
