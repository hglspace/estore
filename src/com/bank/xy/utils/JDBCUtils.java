package com.bank.xy.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 将增删改查操作中重复代码提取出来
 */
public class JDBCUtils {
	
	private static ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource("itcast");
	
	
	public static Connection getConnection() throws SQLException{
		return comboPooledDataSource.getConnection();
	}
	public static DataSource getDataSource(){
		return comboPooledDataSource;
	}
	
	public static void release(ResultSet rs, Statement stmt, Connection conn) {

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
		release(stmt, conn);
	}

	public static void release(Statement stmt, Connection conn) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			stmt = null;
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}
	
}
