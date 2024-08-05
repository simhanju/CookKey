package com.ck.app.bookmark;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Action;
import com.ck.action.Transfer;
import com.ck.model.dao.BookmarkDAO;
import com.ck.model.dto.BookmarkDTO;
import com.ck.model.dto.StoreDTO;

public class BookMarkListOkAction implements Action{

	@Override
	public Transfer execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String loginUser = (String)req.getSession().getAttribute("loginUser");
		System.out.println(loginUser);
		
		BookmarkDTO bmdto = new BookmarkDTO();
		bmdto.setUserid(loginUser);
		
		List<BookmarkDTO> b_list = null;
		BookmarkDAO bmdao = new BookmarkDAO();
		b_list = bmdao.getBookmark(bmdto);
		System.out.println("보드 리스트 : "+b_list);
		
		Transfer transfer = new Transfer();
		transfer.setRedirect(false);
		transfer.setPath("/app/bookmark/list.jsp");
		return transfer;
	}
}
