package com.ck.model.dto;

public class BoardBookmarkDTO {
	private long b_bookmark;
	private String userid;
	private long boardnum;
	
	@Override
	public String toString() {
		return "B_bookmarkDTO [b_bookmark=" + b_bookmark + ", userid=" + userid + ", boardnum=" + boardnum + "]";
	}

	public long getB_bookmark() {
		return b_bookmark;
	}
	public void setB_bookmark(long b_bookmark) {
		this.b_bookmark = b_bookmark;
	}
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
}
