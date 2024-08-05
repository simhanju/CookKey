<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
	<head>
<c:choose>
	<c:when test="${param.userid eq sessionScope.loginUser}">
		<title>MyPage</title>
	</c:when>
	<c:otherwise>
		<title>${param.userid}님의 페이지</title>
	</c:otherwise>
</c:choose>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="${cp}/assets/css/profile.css" />
		<link rel="stylesheet" href="${cp}/assets/css/loading.css" />
	</head>
	<body class="is-preload">
	<div id="loading" class="loading"><div id="loading_img" class="img"></div>잠시만 기다려주세요...</div>

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Main -->
					<div id="main">
						<div class="inner">
							<!-- Header -->
								<%@ include file = "/app/header.jsp" %>
                            <!-- Header 끝 -->

                            <!-- 세션 시작 -->
                            <section class="profile_section">
                                <form class="profile_form" action="">
                                    <div class="profile_div">
                                        <div class="profile">
                                            <div id="userfile"><img></div>
                                            <div>
                                                <p id="userid"><strong>userid</strong></p>
                                                <p id="nickname"><strong>닉네임</strong> : nickname</p>
                                                <p id="birth"><strong>생일</strong> : birth</p>
                                            </div>
                                        </div>
                                        <div>
											<c:if test="${param.userid eq sessionScope.loginUser}">
												<input type='button' value='정보수정' onclick='update()'>
											</c:if>
											<c:if test="${param.userid != sessionScope.loginUser}">
												<input type="button" value="채팅하기" onclick="location.href='${cp}/chatstart.ch?loginUser=${sessionScope.loginUser}&receive=${param.userid}'">
											</c:if>
											<input type="button" value="목록으로" onclick="confirmNavigateToList()">                                        
                                        </div>
                                    </div>
                                    <div class="post_div">
                                        <h2>Recent photo</h2>
                                        <div class="posts">
                                            <div>게시물1file 출력</div>
                                            <div>게시물2</div>
                                            <div>게시물3</div>
                                        </div>
                                    </div>
                                </form>
                            </section> 
                            <!-- 세션 끝 -->
						</div>
					</div>

				<!-- Sidebar -->
		<%@ include file = "/app/sidebar.jsp" %>

		<!-- Scripts -->
            <script src="https://kit.fontawesome.com/220c29f5e3.js" crossorigin="anonymous"></script>
			<script src="${cp}/assets/js/jquery.min.js"></script>
			<script src="${cp}/assets/js/browser.min.js"></script>
			<script src="${cp}/assets/js/breakpoints.min.js"></script>
			<script src="${cp}/assets/js/util.js"></script>
			<script src="${cp}/assets/js/main.js"></script>
		</div>
	</body>
<script>
	window.onload = function(){
		var maxNum = null;
		const userid = "${param.userid}"
		getUser(userid, maxNum);
		console.log(userid);
	}
	$('#wrapper').scroll(function(){
		getUser();
	});
	function getUser(userid, maxNum) {
		console.log(userid);
	    $.ajax({
	        url: "${cp}/userpage.us",
	        type: "GET",
	        dataType:'json',
	        data: {
	        	userid: userid,
	            maxNum: maxNum
	        },
	        success: function(response) {
	            const obj = response;
	            console.log(obj);
	            const user = obj.user;
	            const userfile = obj.userfile;
	            const friendamount = obj.friendamount;
	
	            if(user[0]===null | user[0]===undefined | user[0]===""){
	            	$(".profile_section").html("존재하지 않는 유저입니다.");
	            }
	            else{
	            	$("#userid").html("<strong>"+ user[0].userid +"</strong>");
	            	$("#nickname").html("<strong>닉네임</strong> : "+ user[0].nickname);
	            	$("#birth").html("<strong>생일</strong> : "+ user[0].birth);

	            	
	            	if (userfile[0].length === 0) {
	            	    $("#userfile").html("<img src='${cp}/file/no_profil_user_img.png'>");
	            	} else {
	            	    // 사용자 파일이 존재하는 경우 이미지와 버튼을 함께 출력
	            	   const userImage = '<img src="${cp}/file/' + decodeURI(userfile[0][0].systemname) + '" alt="프로필 사진" /><br>';

	            	    $("#userfile").html(userImage);
	            	}
	            	
	            	
	            	let str = "";
		            let i = 0;
		            const boardlist = obj.boardlist;
		            console.log(boardlist);
		            const boardfiles = obj.boardfiles;
		            for (let board of boardlist) {
		                if(boardfiles[i]===null | boardfiles[i]===undefined | boardfiles[i]===""){ //게시글 사진 있으면 출력
		                }
		                else{
		                	 if(boardfiles[i] && boardfiles[i][0]) {
				                str += "<div>";
		   	                  str += '<a href="#" style="cursor:default" class="profileimage"><img src="${cp}/file/' + decodeURI(boardfiles[i][0].systemname) + '" alt="게시글 사진" /></div>';
				                str += "</div>";
		                     }
			                //str += '<a href="#" class="profileimage"><img src="${cp}/file/'+decodeURI(boardfiles[i] && boardfiles[i][0] ? boardfiles[i][0].systemname : '')+'" alt="게시글  사진" /></a>';
		                }
		                i++;
		            }
	            $(".posts").html(str);
	            }
	        },
	        complete: function () {$("#loading").css("display","none");}
	    });
	}
	
	function update() {
		location.href="${cp}/userupdate.us?userid=${param.userid}";
	}
	function confirmNavigateToList() {
	    const confirmed = confirm("목록으로 돌아가시겠습니까?");
	    if (confirmed) {
	        // 확인 버튼을 누른 경우 boardlist 페이지로 이동
	        window.location.href = "${cp}/app/board/list.jsp";
	    }
	    // 취소 버튼을 누른 경우 아무런 동작 없음
	}
	
</script>
</html>