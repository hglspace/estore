package com.bank.xy.listener;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.bank.xy.pojo.Orders;
import com.bank.xy.service.OrdersService;
import com.bank.xy.service.Impl.OrdersServiceImpl;

public class OrderListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
        Timer timer = new Timer();
		
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				//1.查询订单状态为1的订单
				OrdersService orderService = new OrdersServiceImpl();
				int status = 1;
				List<Orders> Lo = orderService.queryOrderStatus(status);
				//遍历订单集合
				for (Orders o : Lo) {
					//获取订单的创建时间
					long createtime = o.getCreatetime().getTime();
					//获取当前系统时间
					long systemtime = System.currentTimeMillis();
					if((systemtime-createtime) > 10000){
						//调用service去修改订单的状态
						orderService.changeOrderStatus(o.getId());
					}
				}
				
			}
		};
		timer.schedule(task, 0, 2000);
	}

}
