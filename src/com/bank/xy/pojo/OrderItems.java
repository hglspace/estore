package com.bank.xy.pojo;

public class OrderItems {
	private String oid;
	private int gid;
	private int buynum;
	//在这里添加商品的属性，因为一个订单明细中对应了多个商品的信息
	private Goods goods;
	
	private Orders orders;
	
	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public int getBuynum() {
		return buynum;
	}

	public void setBuynum(int buynum) {
		this.buynum = buynum;
	}

	@Override
	public String toString() {
		return "OrderItems [oid=" + oid + ", gid=" + gid + ", buynum=" + buynum
				+ ", goods=" + goods + "]";
	}

}
