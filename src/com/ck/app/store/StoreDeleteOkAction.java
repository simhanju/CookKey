package com.ck.app.store;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Action;
import com.ck.action.Transfer;
import com.ck.model.dao.StoreDAO;
import com.ck.model.dao.CategoryDAO;
import com.ck.model.dao.FileDAO;
import com.ck.model.dto.CategoryDTO;
import com.ck.model.dto.FileDTO;

public class StoreDeleteOkAction implements Action{
	@Override
	public Transfer execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		long storenum = Long.parseLong(req.getParameter("storenum"));
		String keyword = req.getParameter("keyword");
		String temp = req.getParameter("page");
		
		
		int page = temp==null||temp.equals("") ? 1 : Integer.parseInt(temp);
		
		keyword = URLEncoder.encode(keyword,"UTF-8");
		StoreDAO sdao = new StoreDAO();
		
		Transfer transfer = new Transfer();
		transfer.setRedirect(true);

		String saveFolder = req.getServletContext().getRealPath("file");
		FileDAO fdao = new FileDAO();
		FileDTO fdto = new FileDTO();
		CategoryDAO cdao = new CategoryDAO();
		CategoryDTO cdto = new CategoryDTO();
		cdto.setStorenum(storenum);
		fdto.setStorenum(storenum);
		List<FileDTO> files = fdao.getFiles(fdto);
		List<CategoryDTO> categories = cdao.getCategories(cdto);
		for (int i = 0; i < categories.size(); i++) {
			cdao.deleteCategory(categories.get(i));
		}
		
		
		
		fdao.deleteFileNum(storenum);
		
		if(sdao.deleteStore(storenum)) {
			
			transfer.setPath(req.getContextPath()+"/storelist.st?page="+page+"&keyword="+keyword);
		}
		
		else {
			transfer.setPath(req.getContextPath()+"/storeview.st?page="+page+"&keyword="+keyword+"&storenum="+storenum);
		}
		

		return transfer;
	}
}

















