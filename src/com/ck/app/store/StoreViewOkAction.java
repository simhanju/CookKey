package com.ck.app.store;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Action;
import com.ck.action.Transfer;
import com.ck.model.dao.StoreDAO;
import com.ck.model.dao.CategoryDAO;
import com.ck.model.dao.FileDAO;
import com.ck.model.dto.CategoryDTO;
import com.ck.model.dto.FileDTO;
import com.ck.model.dto.StoreDTO;

public class StoreViewOkAction implements Action{
	@Override
	public Transfer execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		long storenum = Long.parseLong(req.getParameter("storenum"));
		String loginUser = (String)req.getSession().getAttribute("loginUser");
		
		
		FileDTO fdto = new FileDTO();
		fdto.setStorenum(storenum);
		StoreDAO sdao = new StoreDAO();
		
		StoreDTO store = sdao.getStoreByNum(storenum);
		if(!store.getUserid().equals(loginUser)) {
			sdao.updateReadCount(storenum);
			store.setReadcount(store.getReadcount()+1);
		}
		FileDAO fdao = new FileDAO();
		CategoryDAO cdao = new CategoryDAO();
		CategoryDTO cdto = new CategoryDTO();
		cdto.setStorenum(storenum);
		
		req.setAttribute("store", store);
		req.setAttribute("files", fdao.getFiles(fdto));
		req.setAttribute("categories", cdao.getCategories(cdto));
		
		
		Transfer transfer = new Transfer();
		transfer.setRedirect(false);
		transfer.setPath("/app/store/get.jsp");
		return transfer;
	}
}





