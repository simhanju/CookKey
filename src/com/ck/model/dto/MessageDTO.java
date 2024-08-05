package com.ck.model.dto;

public class MessageDTO {
	long chatnum;
	long chatID;
	String chatcontent;
	String writerID;
	String regdate;
	
	public String getWriterID() {
		return writerID;
	}
	public void setWriterID(String writerID) {
		this.writerID = writerID;
	}
	public long getChatnum() {
		return chatnum;
	}
	public void setChatnum(long chatnum) {
		this.chatnum = chatnum;
	}
	public long getChatID() {
		return chatID;
	}
	public void setChatID(long chatID) {
		this.chatID = chatID;
	}
	public String getChatcontent() {
		return chatcontent;
	}
	public void setChatcontent(String chatcontent) {
		this.chatcontent = chatcontent;
	}

	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	
}
