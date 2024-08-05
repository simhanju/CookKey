package com.ck.app.board;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.action.Action;
import com.ck.action.Transfer;
import com.ck.model.dao.CategoryDAO;
import com.ck.model.dao.FileDAO;
import com.ck.model.dao.UserDAO;
import com.ck.model.dto.CategoryDTO;
import com.ck.model.dto.FileDTO;
import com.ck.model.dto.UserDTO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class BoardWriteAction implements Action{
	@Override
	public Transfer execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		CategoryDAO cdao = new CategoryDAO();
		CategoryDTO cdto = new CategoryDTO();
		
		String userid = (String)req.getSession().getAttribute("loginUser");
		UserDAO udao = new UserDAO();
		UserDTO user = udao.getUserById(userid);
		System.out.println(userid);
		
		FileDTO file = new FileDTO();
		FileDAO fdao = new FileDAO();
		List<FileDTO> userfile = new ArrayList<FileDTO>();
		file.setUserid(userid);
		userfile = fdao.getFiles(file);
		
		
		List<CategoryDTO> categories = cdao.getCategories(cdto);
		
		System.out.println("===================================");
        
        Gson gson = new Gson();
    	JsonObject json = new JsonObject();
    	JsonArray jsoncategories = new JsonArray();
    	JsonArray jsonuser = new JsonArray();
    	JsonArray jsonuserfile = new JsonArray();
    	
    	for (CategoryDTO category : categories) {
    		jsoncategories.add(gson.toJsonTree(category));
        }
    	
    	
    	
    	json.add("categories", jsoncategories);
    	jsonuser.add(gson.toJsonTree(user));
    	jsonuserfile.add(gson.toJsonTree(userfile));
    	
    	json.add("user", jsonuser);
    	json.add("userfile", jsonuserfile);
    	
    	String str = gson.toJson(json);
    	
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/JSON");
    	PrintWriter out = resp.getWriter();
    	out.print(str);
        
		return null;
	}
}
