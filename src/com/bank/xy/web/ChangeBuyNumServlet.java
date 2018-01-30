package com.bank.xy.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.xy.pojo.User;
import com.bank.xy.service.CartService;
import com.bank.xy.service.Impl.CartServiceImpl;

/**
 * Servlet implementation class ChangeBuyNumServlet
 */
public class ChangeBuyNumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeBuyNumServlet() {
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
		String gids=request.getParameter("gid");
		String bn=request.getParameter("buynum");
		int gid=Integer.valueOf(gids);
		int buynum=Integer.valueOf(bn);
		CartService cs = new CartServiceImpl();
		int flag=cs.updateCart(user.getId(),gid,buynum);
		if(flag==1){
			response.sendRedirect(request.getContextPath()+"/queryCartServlet");
		}else if(flag==0){
			request.setAttribute("msg", "修改购买数量失败");
			request.getRequestDispatcher("/cart.jsp").forward(request, response);
			return;
		}else{
			request.setAttribute("msg", "服务器忙，请稍后再试");
			request.getRequestDispatcher("/cart.jsp").forward(request, response);
			return;
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
