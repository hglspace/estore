package com.bank.xy.dao.Impl;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.bank.xy.dao.CartDao;
import com.bank.xy.pojo.Cart;
import com.bank.xy.utils.JDBCUtils;

public class CartDaoImpl implements CartDao {

	private static DataSource dataSource = JDBCUtils.getDataSource();
	@Override
	public Cart findByUidAndGid(int uid, int gid) {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(dataSource);
		String sql = "select * from cart where uid=? and gid=?";
		try {
			Cart cart= qr.query(sql, new BeanHandler<Cart>(Cart.class), uid,gid);
			return cart;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("查询购物车失败");
		}
	}

	@Override
	public int updateCart(Cart cart) {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(dataSource);
		String sql = "update cart set buynum=? where uid=? and gid=?";
		try {
			return qr.update(sql, cart.getBuynum(),cart.getUid(),cart.getGid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int addCart(Cart cart) {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(dataSource);
		String sql = "insert into cart values(?,?,?)";
		try {
			return qr.update(sql, cart.getUid(),cart.getGid(),cart.getBuynum());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public List<Cart> findByUid(int id) {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(dataSource);
		String sql = "select * from cart where uid=?";
		try {
			return qr.query(sql, new BeanListHandler<Cart>(Cart.class), id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("根据用户id查询购物车失败");
		}
	}

	@Override
	public int deleteCart(int id, int gid) {
		// TODO Auto-generated method stub
		QueryRunner qr = new QueryRunner(dataSource);
		String sql = "delete from cart where uid=? and gid=?";
		try {
			return qr.update(sql, id,gid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("删除购物车数据异常");
		}
	}


}
