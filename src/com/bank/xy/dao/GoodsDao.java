package com.bank.xy.dao;

import java.util.List;

import com.bank.xy.pojo.Goods;

public interface GoodsDao {

	List<Goods> findAllGoods();

	Goods findById(int id);

}
