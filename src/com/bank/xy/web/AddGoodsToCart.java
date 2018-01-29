package com.bank.xy.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.xy.pojo.Cart;
import com.bank.xy.pojo.User;
import com.bank.xy.service.CartService;
import com.bank.xy.service.Impl.CartServiceImpl;

/**
 * Servlet implementation class AddGoodsToCart
 */
public class AddGoodsToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddGoodsToCart() {
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
		String goodsIds=request.getParameter("id");
		String buynums=request.getParameter("buynum");
		int goodsId=Integer.valueOf(goodsIds);
		int buynum=Integer.valueOf(buynums);
		Cart cart = new Cart();
		cart.setBuynum(buynum);
		cart.setGid(goodsId);
		cart.setUid(user.getId());
		
		CartService cs = new CartServiceImpl();
		int flag=cs.addGoodsToCart(cart);
		if(flag==1){//更新成功
			response.sendRedirect(request.getContextPath()+"/buyorcart.jsp");
		}else if(flag==0){
			request.setAttribute("msg", "添加失败");
			request.getRequestDispatcher("goods_detail.jsp");
		}else{
			request.setAttribute("msg", "服务器忙，请稍后再试");
			request.getRequestDispatcher("goods_detail.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
