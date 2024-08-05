package com.ck.model.dto;

public class BoardImgDTO {
	private long b_imgnum;
	private String systemname;
	private String orgname;
	private long boardnum;
	@Override
	public String toString() {
		return "BoardImgDTO [b_imgnum=" + b_imgnum + ", systemname=" + systemname + ", orgname=" + orgname
				+ ", boardnum=" + boardnum + "]";
	}
	public long getB_imgnum() {
		return b_imgnum;
	}
	public void setB_imgnum(long b_imgnum) {
		this.b_imgnum = b_imgnum;
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
	public long getBoardnum() {
		return boardnum;
	}
	public void setBoardnum(long boardnum) {
		this.boardnum = boardnum;
	}
}
