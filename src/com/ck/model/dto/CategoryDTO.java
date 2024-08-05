package com.ck.model.dto;

public class CategoryDTO {
   private long s_categorynum;
   private long storenum;
   
   private long b_categorynum;
   private long boardnum;
   
   private long u_categorynum;
   private String userid;
   
   private int categorynum;
   private String categoryname;
   
   @Override
   public String toString() {
      return "CategoryDTO [s_categorynum=" + s_categorynum + ", storenum=" + storenum + ", b_categorynum="
            + b_categorynum + ", boardnum=" + boardnum + ", u_categorynum=" + u_categorynum + ", userid=" + userid
            + ", categorynum=" + categorynum + ", categoryname=" + categoryname + "]";
   }
   public long getS_categorynum() {
      return s_categorynum;
   }
   public void setS_categorynum(long s_categorynum) {
      this.s_categorynum = s_categorynum;
   }
   public long getStorenum() {
      return storenum;
   }
   public void setStorenum(long storenum) {
      this.storenum = storenum;
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
   public long getU_categorynum() {
      return u_categorynum;
   }
   public void setU_categorynum(long u_categorynum) {
      this.u_categorynum = u_categorynum;
   }
   public String getUserid() {
      return userid;
   }
   public void setUserid(String userid) {
      this.userid = userid;
   }
   public int getCategorynum() {
      return categorynum;
   }
   public void setCategorynum(int categorynum) {
      this.categorynum = categorynum;
   }
   public String getCategoryname() {
      return categoryname;
   }
   public void setCategoryname(String categoryname) {
      this.categoryname = categoryname;
   }
}
