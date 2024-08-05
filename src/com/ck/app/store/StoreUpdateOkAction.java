package com.ck.app.store;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Action;
import com.ck.action.Transfer;
import com.ck.model.dao.StoreDAO;
import com.ck.model.dao.CategoryDAO;
import com.ck.model.dao.FileDAO;
import com.ck.model.dto.StoreDTO;
import com.ck.model.dto.CategoryDTO;
import com.ck.model.dto.FileDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class StoreUpdateOkAction implements Action{
	@Override
	public Transfer execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String saveFolder = req.getServletContext().getRealPath("file");
		int size = (int)(1024*1024*1024*1.5);
		
		MultipartRequest multi = new MultipartRequest(req,saveFolder,size,"UTF-8",
				new DefaultFileRenamePolicy());
		//삭제해야 할 파일명들
		String updateCnt = multi.getParameter("updateCnt");
		System.out.println("updateCnt:"+updateCnt);
		String page = multi.getParameter("page");
		String keyword = multi.getParameter("keyword");
		long storenum = Long.parseLong(multi.getParameter("storenum"));
		String storetitle = multi.getParameter("storetitle");
		String storecontents = multi.getParameter("storecontents");
		String userid = multi.getParameter("userid");
		String price = multi.getParameter("price");
		int state = Integer.parseInt(multi.getParameter("state"));
		
		StoreDTO store = new StoreDTO();
		store.setStorenum(storenum);
		store.setStoretitle(storetitle);
		store.setStorecontents(storecontents);
		store.setUserid(userid);
		store.setState(state);
		store.setPrice(price);
		
		
		CategoryDTO cdto = new CategoryDTO();
		CategoryDAO cdao = new CategoryDAO();
		cdto.setStorenum(storenum);
		
		List<CategoryDTO> categories = cdao.getCategories(cdto);
		
		for (int i = 0; i < categories.size(); i++) {
			cdao.deleteCategory(categories.get(i));
		}
		
		String[] categorynum = multi.getParameterValues("categorynum");
			
		if(categorynum != null && categorynum.length!=0) {
			for (int i = 0; i < categorynum.length; i++) {
				cdto.setCategorynum(Integer.parseInt(categorynum[i]));
				cdao.insertCategory(cdto);
			}
		}
		else {
			cdto.setCategorynum(5000);
			cdao.insertCategory(cdto);
		}

		
		
		
		//input[type=file] 의 name들
		Enumeration<?> temp = multi.getFileNames();
		ArrayList<String> fileNames = new ArrayList<String>();
		while(temp.hasMoreElements()) {
			fileNames.add((String)temp.nextElement());
		}
		//fileNames 리스트에는 모든 input[type=file]의 name들이 거꾸로 추가되어 있다.
		int len = fileNames.size();
		Collections.reverse(fileNames);
		fileNames.remove(len-1);
		System.out.println("filenames : "+fileNames);
		
		FileDAO fdao = new FileDAO();
		boolean flag = false;
		boolean fcheck = false;
		int cnt = 0;
		for(String name : fileNames) {
			flag = true;
			String orgname = multi.getOriginalFileName(name);
			String systemname = multi.getFilesystemName(name);
			//orgname이 null이라는 뜻은 실제 파일데이터는 날라오지 않았다는 뜻
			//기존 파일에서 변화 없이 그대로 뒀다는 뜻(새롭게 insert 하지 않아야 한다.)
			if(orgname == null){
				continue;
			}
			FileDTO fdto = new FileDTO();
			fdto.setStorenum(storenum);
			fdto.setOrgname(orgname);
			fdto.setSystemname(systemname);
			
			fcheck=fdao.insertFile(fdto);
			cnt++;
			if(!fcheck) {
				break;
			}
		}
		//파일 업로드 했니?
		if(flag) {
			if(cnt == 0) {
				
			}
			else {
				//DB삽입은 실패했니?
				if(!fcheck) {
					for(String name : fileNames) {
						String systemname = multi.getFilesystemName(name);
						//DB상에 있는 t_file 테이블에 올라갔던 내용들 삭제
						FileDTO fdto = new FileDTO();
						fdto.setSystemname(systemname);
						fdto.setStorenum(storenum);
						fdao.deleteFile(fdto);
						
						//실제 경로에 존재하는 파일을 자바의 객체로 가져옴
						File file = new File(saveFolder,systemname);
						//파일이 존재한다면
						if(file.exists()) {
							//삭제
							file.delete();
						}
					}
					//실패 페이지로 이동
				}
			}
		}
		
		//수정 혹은 삭제된 기존 파일들을 지우기 위한 로직
		String[] deleteFiles = updateCnt.split("\\\\");
		for(String name : deleteFiles) {
			File file = new File(saveFolder,name);
			if(file.exists()) {
				file.delete();
				FileDTO fdto = new FileDTO();
				fdto.setSystemname(name);
				fdto.setStorenum(storenum);
				fdao.deleteFile(fdto);
			}
		}
		
		StoreDAO sdao = new StoreDAO();
		Transfer transfer = new Transfer();
		transfer.setRedirect(true);
		if(sdao.updateStore(store)) {
			
		}
		else {
			
		}
		String queryString = String.format("?storenum=%s&keyword=%s&page=%s",storenum+"",keyword,page);
		transfer.setPath(req.getContextPath()+"/storeview.st"+queryString);
		return transfer;
	}
}












