package com.bank.xy.dao.Impl;


import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.bank.xy.dao.GoodsDao;
import com.bank.xy.pojo.Goods;
import com.bank.xy.utils.JDBCUtils;

public class GoodsDaoImpl implements GoodsDao {

	private static DataSource dataSource = JDBCUtils.getDataSource();
	@Override
	public List<Goods> findAllGoods() {
		QueryRunner qr = new QueryRunner(dataSource);
		String sql = "select * from goods";
		try {
			return qr.query(sql, new BeanListHandler<Goods>(Goods.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("查询商品失败");
		}
	}

}
