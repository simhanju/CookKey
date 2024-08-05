package com.ck.model.dto;

public class BoardDTO {
	private long boardnum;
	private String boardcontents;
	private String regdate;
	private String updatedate;
	private int readcount;
	private String userid;
	@Override
	public String toString() {
		return "BoardDTO [boardnum=" + boardnum + ", boardcontents=" + boardcontents + ", regdate=" + regdate
				+ ", updatedate=" + updatedate + ", readcount=" + readcount + ", userid=" + userid
				+ "]";
	}

	public long getBoardnum() {
		return boardnum;
	}
	public void setBoardnum(long boardnum) {
		this.boardnum = boardnum;
	}
	public String getBoardcontents() {
		return boardcontents;
	}
	public void setBoardcontents(String boardcontents) {
		this.boardcontents = boardcontents;
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
