package com.ck.model.dto;

public class BlockDTO {
	private long blocknum;
	private String from_userid;
	private String to_userid;
	@Override
	public String toString() {
		return "BlockDTO [blocknum=" + blocknum + ", from_userid=" + from_userid + ", to_userid=" + to_userid + "]";
	}
	public long getBlocknum() {
		return blocknum;
	}
	public void setBlocknum(long blocknum) {
		this.blocknum = blocknum;
	}
	public String getFrom_userid() {
		return from_userid;
	}
	public void setFrom_userid(String from_userid) {
		this.from_userid = from_userid;
	}
	public String getTo_userid() {
		return to_userid;
	}
	public void setTo_userid(String to_userid) {
		this.to_userid = to_userid;
	}
}
