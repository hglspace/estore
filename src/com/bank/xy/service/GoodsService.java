package com.bank.xy.service;

import java.util.List;

import com.bank.xy.pojo.Goods;

public interface GoodsService {

	List<Goods> findAllGoods();

	Goods findById(int id);

	void addGoods(Goods goods);

}
