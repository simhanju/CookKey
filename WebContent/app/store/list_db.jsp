<%@page import="com.ck.model.dao.BookmarkDAO"%>
<%@page import="com.ck.model.dto.BookmarkDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.ck.model.dto.StoreDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.google.gson.JsonArray"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String loginUser = request.getParameter("loginUser");
	String storenum = request.getParameter("storenum");
	
	BookmarkDTO bkdto = new BookmarkDTO();
	BookmarkDAO bkdao = new BookmarkDAO();
	bkdto.setUserid(loginUser);
	bkdto.setStorenum(Long.parseLong(storenum));
	bkdao.insertBookmark(bkdto);
	/*
		{
			"datas":[
			         {"userid":"apple","userpw","abcd1234","username":"김사과"},
			         {"userid":"apple","userpw","abcd1234","username":"김사과"},
			         ...
			]
		}

	
	*/
	//gson : GSON 라이브러리를 사용하기 위한 객체
	Gson gson = new Gson();
	
	//json : JSON 형태의 문자열을 만들기 위한 실제 데이터가 될 객체
	JsonObject json = new JsonObject();
	//arr : json에 추가 될 "datas" 키에 해당하는 value가 될 배열
	JsonArray arr = new JsonArray();
	
	StoreDTO store = new StoreDTO();
	if (store.getState()==0) {
		store.setState(1);	
	}
	

	//arr 배열에 담길 Java 기반의 객체
	
	ArrayList<StoreDTO> list = new ArrayList<>();
	list.add(store);

	

	//arr에 추가하기 위해 Java 기반의 객체를 json 객체로 변환하여 추가
	//arr.add(gson.toJsonTree(apple));
	
	//1. JsonArray에 직접 UserDTO 변환하여 추가하는 방법
	/*
	for(UserDTO user : list){
		arr.add(gson.toJsonTree(user));
	}
	//데이터가 담긴 arr 배열을 json 객체에 추가
	json.add("datas", arr);	
	*/
	
	//List 타입들은 gson.toJsonTree()를 이용하면 바로 JsonArray로 변환
	//2. List<UserDTO> 타입을 JSON으로 변환하여 추가하는 방법
	json.add("datas",gson.toJsonTree(list));
	
	String str = gson.toJson(json);
	//System.out.println(str);
	out.print(str);
%>





