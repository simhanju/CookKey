package com.ck.model.dto;

public class BookmarkDTO {
	private long s_bookmarknum;
	private String userid;
	private long storenum;
	private long boardnum;
	
	
	public long getBoardnum() {
		return boardnum;
	}
	public void setBoardnum(long boardnum) {
		this.boardnum = boardnum;
	}
	public long getS_bookmarknum() {
		return s_bookmarknum;
	}
	public void setS_bookmarknum(long s_bookmarknum) {
		this.s_bookmarknum = s_bookmarknum;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public long getStorenum() {
		return storenum;
	}
	public void setStorenum(long storenum) {
		this.storenum = storenum;
	}
}
