package com.bank.xy.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.bank.xy.pojo.User;

public class PrivilegeFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		//获取当前登录的用户
		User loginUser = (User) request.getSession().getAttribute("loginUser");
		if(loginUser==null){
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		//获取当前用户的role
		String role = loginUser.getRole();
		if(StringUtils.equals("管理员", role)){
			arg2.doFilter(request, response);
		}else{
			request.getRequestDispatcher("/false.jsp").forward(request, response);
			return;
		}


	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
