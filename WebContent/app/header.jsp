<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<link rel="stylesheet" href="${cp}/assets/css/sidebar.css" />
<script src="https://kit.fontawesome.com/220c29f5e3.js" crossorigin="anonymous"></script>
<header id="header">
	<a href="${cp}" class="logo"><strong>Cook Key</strong> 요리 관련 SNS
		입니다.</a>
	<ul class="icons">
		<li><a href="${cp}/app/chat/chatlist.jsp" id="myIcon" class="fa-regular fa-comments"></a></li>
		<li class="profile-dropdown"><a href="#" id="myIcon"
			class="fa-solid fa-circle-user"></a> <!-- 드롭다운 메뉴 추가 -->
			<ul class="dropdown-content">
				<li><a href="${cp}/app/user/userpage.jsp?userid=${loginUser}"
					class="fa-solid fa-circle-user">프로필</a></li>
				<li><a href="${cp}/userlogout.us"
					class="fa-solid fa-right-from-bracket">로그아웃</a></li>
			</ul></li>
	</ul>
</header> 