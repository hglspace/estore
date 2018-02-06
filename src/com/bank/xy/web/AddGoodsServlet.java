package com.bank.xy.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.bank.xy.pojo.Goods;
import com.bank.xy.service.GoodsService;
import com.bank.xy.service.Impl.GoodsServiceImpl;
import com.bank.xy.utils.DirUtils;

/**
 * Servlet implementation class AddGoodsServlet
 */
public class AddGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//创建DiskFileItemFactory工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//创建ServletFileUpload
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 解决中文文件名乱码问题
		upload.setHeaderEncoding("UTF-8");
		
		//这个地方下次要改！！！！
		// 创建商品对象
		Goods goods = new Goods();
		try {
			/**
			 * 在这里我们为什么不适用beanutils.populate()
			 * 因为我们在这里使用的是文件上传的表单格式，所以requst获取不到表单
			 * 中的值，所以我们用common-fileupload这个工具类中的parserequest（）
			 * 来获取request中的数据。
			 */
			// 解析出所有的表单项, 包括文件
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem item : items) {
				// 普通表单项
				if (item.isFormField()) {
					//filedName代表的是input标签中name中的值
					String fieldName = item.getFieldName();
					//value是input标签中用户输入的值
					String value = item.getString("UTF-8");
					/*
					 * BeanUtils.setProperty(bean, name, value)
					 * 为对象的某个属性赋值
					 * bean：要赋值的对象
					 * name：对象中的属性
					 * value：要赋的值
					 */
					BeanUtils.setProperty(goods/*这里要改！！！*/, fieldName, value);
				}
				// 一定是文件
				else {
					// 获取文件名称,用来获取文件的后缀名
					String fileName = item.getName();
					// 文件随机命名,先获取UUID
					String uuid = UUID.randomUUID().toString().replace("-", "");
					// 获取文件的后缀名a.b.jpg
					int lastIndex = fileName.lastIndexOf(".");
					// 最终生成的文件名为asdfsadgsadgsadgsadg.jpg
					fileName = uuid + fileName.substring(lastIndex);
					
					// 设置文件在服务器中的保存位置
					String savaPath = "/upload";//这里要改！！！根据测试数据决定
					// 目录打散 /8/7
					String dirs = DirUtils.getDir(fileName);
					
					// 文件在服务器中的保存路径
					String imgurl = savaPath + dirs + "/" + fileName;
					// 把图片路径保存到goods对象中
					BeanUtils.setProperty(goods/*这里要改！！*/, "imgurl", imgurl);
					
					// 获取服务器的真实位置 D:\apache-tomcat-7.0.68\webapps\estore/upload/11/9
					String serverPath = this.getServletContext().getRealPath(savaPath + dirs);
					
					// 判断文件夹是否存在
					File file = new File(serverPath);
					// 如果不存在，则创建文件夹
					if (!file.exists()) {
						file.mkdirs();
					}
					// 最终要上传的文件的名字：D:\apache-tomcat-7.0.68\webapps\estore/upload/11/9/asdfsadgsadgsadgsadg.jpg
					file = new File(serverPath, fileName);
					// 将文件写到服务器
					item.write(file);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//调用service去保存数据
		GoodsService goodsService = new GoodsServiceImpl();
		goodsService.addGoods(goods);
		//跳转页面
		request.getRequestDispatcher("/queryAdminGoodsServlet").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
