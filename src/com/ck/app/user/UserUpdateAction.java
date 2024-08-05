package com.ck.app.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Action;
import com.ck.action.Transfer;
import com.ck.model.dao.FileDAO;
import com.ck.model.dao.UserDAO;
import com.ck.model.dto.FileDTO;

public class UserUpdateAction implements Action {
	@Override
	public Transfer execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String userid = req.getParameter("userid");
		UserDAO udao = new UserDAO();
		FileDAO fdao = new FileDAO();
		FileDTO fdto = new FileDTO();
		
		System.out.println("아이디"+userid);
		req.setAttribute("user", udao.getUserById(userid));
		String userimg;
		if(fdao.getUserFileCnt(userid)==0) {
    		userimg = "no_profil_user_img.png";
    	}
    	else {
    		fdto = fdao.getUserFile(userid); 
    		userimg = fdto.getSystemname();
    	}
    	req.setAttribute("userimg", userimg);
		System.out.println(req.getAttribute("user"));
		
		Transfer transfer = new Transfer();
		transfer.setRedirect(false);
		transfer.setPath("/app/user/userupdate.jsp"); 
		return transfer;
	}
}
