package com.ck.app.user;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Action;
import com.ck.action.Transfer;
import com.ck.model.dao.CategoryDAO;
import com.ck.model.dao.UserDAO;
import com.ck.model.dto.CategoryDTO;
import com.ck.model.dto.UserDTO;

public class UserJoinOkAction implements Action{
	@Override
	public Transfer execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		UserDTO user = new UserDTO();
	      user.setUserid(req.getParameter("userid"));
	      user.setUserpw(req.getParameter("userpw"));
	      user.setUsername(req.getParameter("username"));
	      user.setNickname(req.getParameter("nickname"));
	      user.setPhone(req.getParameter("phone"));
	      user.setEmail(req.getParameter("email"));
	      user.setGender(req.getParameter("gender")+"-"+req.getParameter("foreigner"));//W-K);
	      user.setBirth(req.getParameter("birth"));
	      user.setZipcode(req.getParameter("zipcode"));
	      user.setAddr(req.getParameter("addr"));
	      user.setAddrdetail(req.getParameter("addrdetail"));
	      user.setAddretc(req.getParameter("addretc"));
	      
	      UserDAO udao = new UserDAO();
	      
	      if(udao.insertUser(user)) {
	    	 List<CategoryDTO> list = null;
	    	  
	    	 CategoryDAO cdao = new CategoryDAO();
	         CategoryDTO cdto = new CategoryDTO();
	         list = cdao.getCategoryNum(cdto);
	    	  
	         
	         for (int i=1; i<=list.size(); i++) {
	        	// 체크를 한 카테고리 일때
	        	 
	            if(req.getParameter("usercategory"+list.get(i-1).getCategorynum()) != null) {
	               int categorynum = Integer.parseInt(req.getParameter("usercategory"+list.get(i-1).getCategorynum()));
	               
	               CategoryDTO usercategory = new CategoryDTO();
	               usercategory.setUserid(req.getParameter("userid"));
	               usercategory.setCategorynum(categorynum);
	               
	               // usercategory 확인
	               System.out.println(usercategory);
	               
	               cdao.insertCategory(usercategory);
	               
	               System.out.println(categorynum);
	            }
	            else {
	               continue;
	            }
	         }         
	         Cookie cookie = new Cookie("joinid", req.getParameter("userid"));
	         cookie.setPath("/");
	         resp.addCookie(cookie);
	      }
	      else {
	         System.out.println("user테이블에 데이터 입력 실패");
	      }
		
		Transfer transfer = new Transfer();
		transfer.setRedirect(true);
		transfer.setPath(req.getContextPath()+"/");
		return transfer;
	}
}







