package com.ck.model.dto;

public class ReplyDTO {
	private long replynum;
	private String replycontents;
	private String regdate;
	private String updatedate;
	private String userid;
	private long boardnum;

	@Override
	public String toString() {
		return "ReplyDTO [replynum=" + replynum + ", replycontents=" + replycontents + ", regdate=" + regdate
				+ ", updatedate=" + updatedate + ", userid=" + userid + ", boardnum=" + boardnum + "]";
	}
	
	public long getReplynum() {
		return replynum;
	}
	public void setReplynum(long replynum) {
		this.replynum = replynum;
	}
	public String getReplycontents() {
		return replycontents;
	}
	public void setReplycontents(String replycontents) {
		this.replycontents = replycontents;
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
