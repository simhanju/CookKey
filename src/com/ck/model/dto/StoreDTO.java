package com.ck.model.dto;

public class StoreDTO {
	private long storenum;
	private String storetitle;
	private String storecontents;
	private String regdate;
	private String updatedate;
	private int readcount;
	private int state;
	private String userid;
	private String systemname;
	private String price;
	
	public String getSystemname() {
		return systemname;
	}
	public void setSystemname(String systemname) {
		this.systemname = systemname;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public long getStorenum() {
		return storenum;
	}
	public void setStorenum(long storenum) {
		this.storenum = storenum;
	}
	public String getStoretitle() {
		return storetitle;
	}
	public void setStoretitle(String storetitle) {
		this.storetitle = storetitle;
	}
	public String getStorecontents() {
		return storecontents;
	}
	public void setStorecontents(String storecontents) {
		this.storecontents = storecontents;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
}
