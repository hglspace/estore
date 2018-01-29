package com.bank.xy.service;

import java.util.List;

import com.bank.xy.pojo.Cart;

public interface CartService {

	int addGoodsToCart(Cart cart);

	List<Cart> findUserCart(int id);

}
