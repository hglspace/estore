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

import com.bank.xy.pojo.User;

public class ValidateLogin implements Filter{
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
   /*
    * (non-Javadoc)
    * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
    * 首先校验用户是否登陆,我们在web,xml中配置了/*去拦截所有的资源

      进入拦截器,获取用户保存在session中的信息,如果session中有值,那么就放行,如果没有值,就拦截用户,让用户去登陆

      但是,用户在进入登陆页面的时候,会先进入login.jsp.那login.jsp是不是也会被拦截器拦截

      login.jsp中存在很多的引入的js跟css样式,这些资源也会被拦截掉,导致用户无法去访问这些资源,导致页面上没有任何的样式效果

      所以我们要将所有的以.js跟.css结尾的文件放行,让用户去访问

      但是我们发现,用户还会去访问一些图片,所以我们要讲.jpg.png.gif...这些图片的资源也要放行,让用户去访问

      那就下来用户输入用户名跟密码之后,点击登陆,跳转到loginServlet,这个时候这个访问的请求也会被拦截器拦截,如果我们不将这个请求放行,会导致,用户去查询session
      中是否存在信息,但是用户没有进入登陆的servlet,所以用户无法将信息保存到session中,导致用户还是会跳转到登陆的页面,这将形成一个死循环.

      所以我们除了要放行.jsp.css.js.png等资源之外,还要放行loginServlet这个请求

      所以我们要放行的资源就得出来了.......
    */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//将request对象强转成httpservletrequest
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		
		//获取用户访问的uri
		String path = req.getServletPath();
		if(path.contains(".jsp")||path.contains(".css")||path.contains(".js")||path.contains(".jpg")||path.contains(".gif")||path.contains(".png")||path.contains(".ico")||path.contains(".js")||path.contains("/loginServlet")||path.contains("/registerServlet")){
			chain.doFilter(req, res);
			return;
		}else{
			//校验登录的功能
			User loginUser = (User) req.getSession().getAttribute("loginUser");
			if(loginUser==null){
				res.sendRedirect("/estore/login.jsp");
				return;
			}else{
				chain.doFilter(req, res);
				return;
			}
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
