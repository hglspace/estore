package com.bank.xy.pojo;

public class Cart {
	private int uid;
	private int gid;
	private int buynum;
    
	
	//直接加入商品的属性
	private Goods goods;
	
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
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

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	@Override
	public String toString() {
		return "Cart [uid=" + uid + ", gid=" + gid + ", buynum=" + buynum
				+ ", goods=" + goods + "]";
	}

}
