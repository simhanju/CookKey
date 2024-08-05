package com.ck.model.dto;

public class LikeDTO {
	private long likenum;
	private String userid;
	private long boardnum;

	@Override
	public String toString() {
		return "LikeDTO [likenum=" + likenum + ", userid=" + userid + ", boardnum=" + boardnum + "]";
	}
	
	public long getLikenum() {
		return likenum;
	}
	public void setLikenum(long likenum) {
		this.likenum = likenum;
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
