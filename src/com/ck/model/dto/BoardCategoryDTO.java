package com.ck.model.dto;

public class BoardCategoryDTO {
	private long b_categorynum;
	private long boardnum;
	private long categorynum;
	
	@Override
	public String toString() {
		return "B_categoryDTO [b_categorynum=" + b_categorynum + ", boardnum=" + boardnum + ", categorynum="
				+ categorynum + "]";
	}
	
	public long getB_categorynum() {
		return b_categorynum;
	}
	public void setB_categorynum(long b_categorynum) {
		this.b_categorynum = b_categorynum;
	}
	public long getBoardnum() {
		return boardnum;
	}
	public void setBoardnum(long boardnum) {
		this.boardnum = boardnum;
	}
	public long getCategorynum() {
		return categorynum;
	}
	public void setCategorynum(long categorynum) {
		this.categorynum = categorynum;
	}
}
