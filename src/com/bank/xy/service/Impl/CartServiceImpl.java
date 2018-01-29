package com.bank.xy.service.Impl;

import java.util.ArrayList;
import java.util.List;

import com.bank.xy.dao.CartDao;
import com.bank.xy.dao.Impl.CartDaoImpl;
import com.bank.xy.pojo.Cart;
import com.bank.xy.pojo.Goods;
import com.bank.xy.service.CartService;
import com.bank.xy.service.GoodsService;

public class CartServiceImpl implements CartService {

	private static CartDao cd = new CartDaoImpl();
	private static GoodsService gs = new GoodsServiceImpl();
	@Override
	public int addGoodsToCart(Cart cart) {
		// TODO Auto-generated method stub
		//先查询购物车中这个用户有没有这个商品
		Cart dbCart=cd.findByUidAndGid(cart.getUid(),cart.getGid());
		if(dbCart!=null){//购物车中已有这种商品了
			int buynum=dbCart.getBuynum();
			cart.setBuynum(buynum+cart.getBuynum());
			return cd.updateCart(cart);
		}else{//购物车中没有
			return cd.addCart(cart);
		}
	}
	@Override
	public List<Cart> findUserCart(int id) {
		// TODO Auto-generated method stub
		//先查询购物车
		List<Cart> list = new ArrayList<Cart>();
		list = cd.findByUid(id);
		for (Cart cart : list) {
			Goods goods = gs.findById(cart.getGid());
			cart.setGoods(goods);
		}
		return list;
	}

}
