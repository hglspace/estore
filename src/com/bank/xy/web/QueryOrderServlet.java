package com.bank.xy.web;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class QueryOrderServlet
 */
public class QueryOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryOrderServlet() {
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
		if(user==null){//没有登陆
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		int uid = user.getId();
		OrdersService os = new OrdersServiceImpl();
		List<Orders> orList=os.findAll(uid);
		request.setAttribute("orList", orList);
		request.getRequestDispatcher("orders.jsp").forward(request, response);
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
