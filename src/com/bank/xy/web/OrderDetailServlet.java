package com.bank.xy.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.xy.pojo.Orders;
import com.bank.xy.pojo.User;
import com.bank.xy.service.OrdersService;
import com.bank.xy.service.Impl.OrdersServiceImpl;

/**
 * Servlet implementation class OrderDetailServlet
 */
public class OrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		int uid = user.getId();
		String oid = request.getParameter("oid");
		OrdersService os = new OrdersServiceImpl();
		Orders orders=os.findOrder(oid,uid);
		//保存数据
		request.setAttribute("orders", orders);
		//将数据转发到订单详情页面
		request.getRequestDispatcher("/orders_detail.jsp").forward(request, response);
		return;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
