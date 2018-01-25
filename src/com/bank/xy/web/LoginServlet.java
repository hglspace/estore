package com.bank.xy.web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.xy.pojo.User;
import com.bank.xy.service.UserService;
import com.bank.xy.service.Impl.UserServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserService us = new UserServiceImpl();
        User user = us.findByNamePassword(username,password);
        if(user==null){
        	request.setAttribute("msg", "用户名或密码错误！");
        	request.getRequestDispatcher("/login.jsp").forward(request, response);
        	return;
        }
        String remember = request.getParameter("remember");
        if(remember!=null){
			//保存用户信息到cookie中
			Cookie cookie =new Cookie("username", URLEncoder.encode(username, "utf-8"));
			//设置cookie的生存时间
			cookie.setMaxAge(60*60*24);
			//设置cookie的保存路径
			cookie.setPath("/");
			//保存cookie
			response.addCookie(cookie);
		}else{
			Cookie cookie =new Cookie("username", null);
			//设置cookie的生存时间
			cookie.setMaxAge(0);
			//设置cookie的保存路径
			cookie.setPath("/");
			//保存cookie
			response.addCookie(cookie);
		}

        request.getSession().setAttribute("loginUser", user);
        response.sendRedirect(request.getContextPath()+"/index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
