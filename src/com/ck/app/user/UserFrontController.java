package com.ck.app.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Transfer;

public class UserFrontController extends HttpServlet {
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
		case "/userjoin.us":
			try {
				transfer = new UserJoinAction().execute(req, resp);
			} catch (Exception e) {
				System.out.println("/userjoin : "+e);
			}
			break;
		case "/userjoinok.us":
			try {
				System.out.println("진입전");
				transfer = new UserJoinOkAction().execute(req, resp);
			} catch (Exception e) {
				System.out.println("/userjoinok : "+e);
			}
			break;
			
		case "/checkidok.us":
			try {
				new CheckIdOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/checkidok : "+e);
			}
			break;
		case "/userlogin.us":
			transfer = new Transfer();
			transfer.setPath("/app/user/login.jsp");
			transfer.setRedirect(false);
			break;
		case "/userloginok.us":
			try {
				transfer = new UserLoginOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/userloginok : "+e);
			}
			break;
		case "/userlogout.us":
			req.getSession().invalidate();
			transfer = new Transfer();
			transfer.setRedirect(false);
			transfer.setPath("/");
			break;
		case "/userpage.us":
			try {
				new UserPageAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println("/userpage.us : "+e);
			}
			break;
		case "/userupdate.us":
			try {
				transfer = new UserUpdateAction().execute(req, resp);
			} catch (Exception e) {
				System.out.println("userupdateok : "+ e);
			}
			break;
		case "/userupdateok.us":
			try {
				transfer = new UserUpdateOkAction().execute(req, resp);
			} catch (Exception e) {
				System.out.println("userupdateok : "+ e);
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













