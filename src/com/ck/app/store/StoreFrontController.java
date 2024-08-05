package com.ck.app.store;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Transfer;
import com.mysql.cj.Session;

public class StoreFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		doProcess(req, resp);
	}
	
	private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//길 나누는 코드
		String requestURI = req.getRequestURI();// ???/userjoin.us
		String contextPath = req.getContextPath(); // ???
		String command = requestURI.substring(contextPath.length());
		
		System.out.println(command);

		Transfer transfer = null;
		switch(command) {
		case "/storelist.st":
			try {
				transfer = new StoreListOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/storelist.st : "+e);
			}
			break;
//		case "storelistok.st":
		case "/storewrite.st":
			transfer = new Transfer();
			transfer.setRedirect(false);
			transfer.setPath("/app/store/write.jsp");
			break;
		case "/storewriteok.st":
			try {
				transfer = new StoreWriteOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/storewriteok.st : "+e);
			}
			break;
		case "/storeview.st":
			try {
				transfer = new StoreViewOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/storeview.st : "+e);
			}
			break;
		case "/storedelete.st":
			try {
				transfer = new StoreDeleteOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/storedelete.st : "+e);
			}
			break;
		case "/storeupdate.st":
			try {
				transfer = new StoreUpdateAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/storeupdate.st : "+e);
			}
			break;
		case "/storeupdateok.st":
			try {
				transfer = new StoreUpdateOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/storeupdateok.st : "+e);
			}
			break;
		case "/storeBookmarkList.st":
			try {
				transfer = new StoreBookmarkListOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/storebookmarklist.st : "+e);
			}
			break;
		case "/filedownload.st":
			try {
				new FileDownloadAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/filedownload.st : "+e);
			}
			break;
		}
		
		if(transfer != null) {
			if(transfer.isRedirect()) {
				resp.sendRedirect(transfer.getPath());
			}
			else {
				req.getRequestDispatcher(transfer.getPath()).forward(req, resp);
			}
		}
	}
}













