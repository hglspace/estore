package com.bank.xy.pojo;

import java.util.Date;
import java.util.List;

public class Orders {
	private String id;
	private int uid;
	private double totalprice;
	private String address;
	private int status;
	private Date createtime;
	//一个订单对应的是多个订单明细吧，所以这里我们要导入订单明细的集合
	private List<OrderItems> oList;
	
	
	public List<OrderItems> getoList() {
		return oList;
	}

	public void setoList(List<OrderItems> oList) {
		this.oList = oList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", uid=" + uid + ", totalprice="
				+ totalprice + ", address=" + address + ", status=" + status
				+ ", createtime=" + createtime + ", oList=" + oList + "]";
	}

}
