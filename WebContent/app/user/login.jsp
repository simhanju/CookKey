<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.contextPath}" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
<link rel="stylesheet" href="${cp}/assets/css/loginView.css">
</head>
<body class="login">
	<c:if test="${not empty cookie.joinid.value}">
		<script>
			alert("${cookie.joinid.value}님 가입을 환영합니다~");
			document.loginForm.userpw.focus();
		</script>
	</c:if>

    <div id="wrap" class="login">
        <div class="pan">
            <form action="${cp}/userloginok.us" method="post" name="loginForm">
                <a href=""><img src="${cp}/images/Cookkey로고.png" class="logo"></a>
                <table>
                    <tbody>
                        <tr>
                            <th>아이디</th>
                            <td>
                                <input type="text" name="userid" value="${cookie.joinid.value}">
                            </td>
                        </tr>
                        <tr>
                            <th>비밀번호</th>
                            <td>
                                <input type="password" name="userpw">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <div class="btn_area">
                                    <input type="submit" value="로그인">
                                    <a class="btn" href="${cp}/userjoin.us">회원가입</a>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
        <div class="handle"></div>
        <div class="handle2"></div>
        <div class="handle3"></div>
      
    </div>
</body>
<script>
	document.cookie = "joinid=; path=/;"
</script>
</html>