package com.bank.xy.dao.Impl;


import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
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
	@Override
	public Goods findById(int id) {
	   QueryRunner qr = new QueryRunner(dataSource);
	   String sql = "select * from goods where id=?";
	   try {
		    return qr.query(sql, new BeanHandler<Goods>(Goods.class), id);
	    } catch (SQLException e) {
		// TODO Auto-generated catch block
		    e.printStackTrace();
		    throw new RuntimeException("查询商品详情失败");
	    }
	}
	@Override
	public void addGoods(Goods goods) {
		// TODO Auto-generated method stub
		//创建queryrunner对象
		QueryRunner qr = new QueryRunner(dataSource);
		//编写sql语句
		String sql = "insert into goods values(null,?,?,?,?,?,?,?)";
		//执行sql语句
		try {
			qr.update(sql, goods.getName(),goods.getMarketprice(),goods.getEstoreprice(),goods.getCategory(),goods.getNum(),goods.getImgurl(),goods.getDescription());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("插入商品信息出错");
		}

	}

}
