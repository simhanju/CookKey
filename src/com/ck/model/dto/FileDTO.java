package com.ck.model.dto;

public class FileDTO {

	private String systemname;
	private String orgname;
	private long storenum;
	private String userid;
	private long boardnum;
	
	private long u_imgnum;
	private long b_imgnum;
	private long bt_imgnum;
	private long s_imgnum;
	private long board_tempnum;
	
	
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public long getBoardnum() {
		return boardnum;
	}
	public void setBoardnum(long boardnum) {
		this.boardnum = boardnum;
	}
	public long getU_imgnum() {
		return u_imgnum;
	}
	public void setU_imgnum(long u_imgnum) {
		this.u_imgnum = u_imgnum;
	}
	public long getB_imgnum() {
		return b_imgnum;
	}
	public void setB_imgnum(long b_imgnum) {
		this.b_imgnum = b_imgnum;
	}
	public long getBt_imgnum() {
		return bt_imgnum;
	}
	public void setBt_imgnum(long bt_imgnum) {
		this.bt_imgnum = bt_imgnum;
	}
	public long getS_imgnum() {
		return s_imgnum;
	}
	public void setS_imgnum(long s_imgnum) {
		this.s_imgnum = s_imgnum;
	}
	public long getBoard_tempnum() {
		return board_tempnum;
	}
	public void setBoard_tempnum(long board_tempnum) {
		this.board_tempnum = board_tempnum;
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
	public long getStorenum() {
		return storenum;
	}
	public void setStorenum(long storenum) {
		this.storenum = storenum;
	}
}
