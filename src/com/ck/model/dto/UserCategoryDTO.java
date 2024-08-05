package com.ck.model.dto;

public class UserCategoryDTO {
	private long u_categorynum;
	private String userid;
	private long categorynum;
	
	@Override
	public String toString() {
		return "U_category [u_categorynum=" + u_categorynum + ", userid=" + userid + ", categorynum=" + categorynum
				+ "]";
	}
	
	public long getU_categorynum() {
		return u_categorynum;
	}
	public void setU_categorynum(long u_categorynum) {
		this.u_categorynum = u_categorynum;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public long getCategorynum() {
		return categorynum;
	}
	public void setCategorynum(long categorynum) {
		this.categorynum = categorynum;
	}
}
