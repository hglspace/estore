package com.bank.xy.service.Impl;

import java.util.List;

import com.bank.xy.dao.GoodsDao;
import com.bank.xy.dao.Impl.GoodsDaoImpl;
import com.bank.xy.pojo.Goods;
import com.bank.xy.service.GoodsService;

public class GoodsServiceImpl implements GoodsService {

	private GoodsDao gs = new GoodsDaoImpl();
	@Override
	public List<Goods> findAllGoods() {
		// TODO Auto-generated method stub
		return gs.findAllGoods();
	}

}
