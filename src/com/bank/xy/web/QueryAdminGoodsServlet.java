package com.bank.xy.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.xy.pojo.Goods;
import com.bank.xy.service.GoodsService;
import com.bank.xy.service.Impl.GoodsServiceImpl;

/**
 * Servlet implementation class QueryAdminGoodsServlet
 */
public class QueryAdminGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryAdminGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//调用service查询数据
		GoodsService goodsService = new GoodsServiceImpl();
		List<Goods> gList = goodsService.findAllGoods();
		//将查询结果放到request中
		request.setAttribute("gList", gList);
		//将查询结果转发到查询页面
		request.getRequestDispatcher("/goods_admin.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
