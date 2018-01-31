package com.bank.xy.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.xy.pojo.Cart;
import com.bank.xy.pojo.OrderItems;
import com.bank.xy.pojo.Orders;
import com.bank.xy.pojo.User;
import com.bank.xy.service.OrdersService;
import com.bank.xy.service.Impl.OrdersServiceImpl;

/**
 * Servlet implementation class SubmitOrderServlet
 */
public class SubmitOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitOrderServlet() {
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
		//开始封装数据
		//1 封装订单
		List<Cart> list=(List<Cart>) session.getAttribute("list");
		String oid=UUID.randomUUID().toString().replace("-", "");
		int uid=user.getId();
		double totalprice=0;
		for (Cart cart : list) {
			totalprice=totalprice+cart.getGoods().getEstoreprice()*cart.getBuynum();
		}
		String detailAddress = request.getParameter("detailAddress");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String code = request.getParameter("code");
        String address=detailAddress+",姓名:"+name+",电话:"+tel+",邮编:"+code;
		int status=1;
		Date createtime=new Date();
		Orders order = new Orders();
		order.setAddress(address);
		order.setCreatetime(createtime);
		order.setId(oid);
		order.setStatus(status);
		order.setTotalprice(totalprice);
		order.setUid(uid);
		//创建订单明细
		List<OrderItems> oList = new ArrayList<OrderItems>();
		for (Cart cart : list) {
			OrderItems ots = new OrderItems();
			ots.setBuynum(cart.getBuynum());
			ots.setGid(cart.getGid());
			ots.setOid(oid);
			oList.add(ots);
		}
		order.setoList(oList);
		OrdersService os = new OrdersServiceImpl();
		os.addOrders(order);
		response.sendRedirect(request.getContextPath()+"/queryOrderServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
