package com.ck.model.dto;

public class UserImgDTO {
	private long u_imgnum;
	private String systemname;
	private String orgname;
	private String userid;
	@Override
	public String toString() {
		return "UserImgDTO [u_imgnum=" + u_imgnum + ", systemname=" + systemname + ", orgname=" + orgname + ", userid="
				+ userid + "]";
	}
	public long getU_imgnum() {
		return u_imgnum;
	}
	public void setU_imgnum(long u_imgnum) {
		this.u_imgnum = u_imgnum;
	}
	public String getSystemname() {
		return systemname;
	}
	public void setSystemname(String systemname) {
		this.systemname = systemname;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
}
