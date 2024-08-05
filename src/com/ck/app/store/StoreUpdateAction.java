package com.ck.app.store;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Action;
import com.ck.action.Transfer;
import com.ck.model.dao.StoreDAO;
import com.ck.model.dto.CategoryDTO;
import com.ck.model.dto.FileDTO;
import com.ck.model.dao.CategoryDAO;
import com.ck.model.dao.FileDAO;

public class StoreUpdateAction implements Action{
	@Override
	public Transfer execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		long storenum = Long.parseLong(req.getParameter("storenum"));
		FileDTO fdto = new FileDTO();
		fdto.setStorenum(storenum);
		StoreDAO sdao = new StoreDAO();
		FileDAO fdao= new FileDAO();
		CategoryDAO cdao = new CategoryDAO();
		CategoryDTO cdto = new CategoryDTO();
		cdto.setStorenum(storenum);
		
		req.setAttribute("store", sdao.getStoreByNum(storenum));
		req.setAttribute("files", fdao.getFiles(fdto));
		req.setAttribute("categories", cdao.getCategories(cdto));
		
		Transfer transfer = new Transfer();
		transfer.setRedirect(false);
		transfer.setPath("/app/store/modify.jsp");
		return transfer;
	}
}







