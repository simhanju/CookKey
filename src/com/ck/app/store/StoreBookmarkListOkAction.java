package com.ck.app.store;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Action;
import com.ck.action.Transfer;
import com.ck.model.dao.BookmarkDAO;
import com.ck.model.dao.FileDAO;
import com.ck.model.dao.StoreDAO;
import com.ck.model.dto.BookmarkDTO;
import com.ck.model.dto.FileDTO;
import com.ck.model.dto.StoreDTO;

public class StoreBookmarkListOkAction implements Action{
	@Override
	public Transfer execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		
		
		String temp = req.getParameter("page");
		int page = temp == null || temp.equals("") ? 1 : Integer.parseInt(temp);
		String loginUser = (String)req.getSession().getAttribute("loginUser");
		
		StoreDAO sdao = new StoreDAO();
		BookmarkDAO bkdao = new BookmarkDAO();
		BookmarkDTO bkdto = new BookmarkDTO();
		//전체 게시글의 개수
		long totalCnt = 0;
		
		totalCnt = bkdao.getStoreBookmarkCnt(loginUser);
		//한 페이지에서 보여줄 게시글의 개수
		int pageSize = 6;
		
		//페이징 처리 시 아래에 나올 페이지 번호의 개수
		int pageCnt = 10;
		
		//아래쪽 페이징 처리 부분에 보여질 첫 번째 페이지 번호
		int startPage = (page-1)/pageCnt*pageCnt+1;
		
		//아래쪽 페이징 처리 부분에 보여질 마지막 페이지 번호
		int endPage = startPage + (pageCnt-1);
		
		//전체 개시글의 개수를 기반으로 한 띄워질 수 있는 가장 마지막 페이지(실제로 존재할 수 있는 가장 마지막 페이지) 번호
		int totalPage = (int)(totalCnt-1)/pageSize + 1;

		//가장 마지막 페이지 번호(totalPage)보다 단순한 연산으로 구해진 endPage가 더 큰 경우가 있다.(허구의 페이지 번호가 존재할 수 있다)
		//그 때는 endPage를 가장 마지막 페이지 번호(totalPage)로 바꿔주어야 한다.
		
		endPage = endPage > totalPage ? totalPage : endPage;
		int startRow = (page-1)*pageSize;
		
		List<StoreDTO> list = null;
		bkdto.setUserid(loginUser);
		bkdto.setStorenum(-1);
		List<BookmarkDTO> bklist = bkdao.getBookmark(bkdto);
		Collections.reverse(bklist);
		
		FileDAO fdao = new FileDAO();
		int size = bklist.size()-startRow>=6 ? 6: bklist.size()%6;
		list = sdao.getList(startRow, size);
		
		for (int i = 0; i < size; i++) {
			list.set(i, sdao.getStoreByNum(bklist.get(startRow+i).getStorenum()));					
		}
		
		
		
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setRegdate(list.get(i).getRegdate().substring(0,10));
			try {
				list.get(i).setSystemname(fdao.getFile(list.get(i).getStorenum()).getSystemname());				
			} catch (Exception e) {
			}
		}
		
		req.setAttribute("list", list);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("totalCnt", totalCnt);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("page", page);
		req.setAttribute("bklist", bklist);
		
		Transfer transfer = new Transfer();
		transfer.setRedirect(false);
		transfer.setPath("/app/store/storeBookmarkList.jsp");
		return transfer;
	}
}








