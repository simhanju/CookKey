<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="cp" value="${pageContext.request.contextPath}"
	scope="session" />
<!DOCTYPE HTML>
<html>
<head>
<title>인덱스</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="${cp}/assets/css/storelist.css" />
</head>
<body class="is-preload">
	<c:if test="${empty loginUser }">
		<script>
			alert("로그인 후 이용하세요!");
			location.replace("${cp}/");
		</script>
	</c:if>
	<c:if test="${cookie.w.value != null and cookie.w.value == 'f'}">
		<script>
			alert("게시글 작성 실패!");
		</script>	
	</c:if>
	<div id="wrapper">
		<div></div>
		<div id="main">
			<div class="inner">
			<%@ include file = "/app/header.jsp" %>
			<section style="display: flex; flex-direction: column; justify-content: center; align-items: center;">
				<p>글 개수 : ${totalCnt}</p>
				<div class="posts" style="margin: 0; gap: 20px;">
					<c:choose>
						<c:when test="${list != null and list.size() > 0}">
							<c:forEach var="i" begin="0" end="${list.size() - 1}">
								<c:set var="store" value="${list[i]}" />
								<div class="storeBox">
									<a href="${cp}/storeview.st?storenum=${store.storenum}&page=${page}&keyword=${keyword}">
										<span class="sell">
			                                <c:if test="${store.state==0 }">판매중</c:if>
			                                <c:if test="${store.state==1 }">예약중</c:if>
			                                <c:if test="${store.state==2 }">판매완료</c:if>
                            			</span>
										<p id="regdate">${store.regdate}</p>
						            	<div class="imgBox" style='background-image:url("${cp}/file/${store.systemname}")'>
										</div>
															                            
										<div class="title">
											<p>${store.storetitle}</p>
										</div>
									</a>
							        <p>${store.userid}</p>
									<c:if test="${loginUser!=store.userid}">
										<div id="bookmark" onclick="f('${store.storenum}')">
											<c:if test="${bklist != null and bklist.size() > 0}">
												<c:set var="ck" value="1" />
												<c:forEach var="i" begin="0" end="${bklist.size()-1}">
													<c:if test="${bklist[i].storenum==store.storenum}">
														<p style="display: none;">${ck=0}</p>
													</c:if>
												</c:forEach>
											</c:if> 
											<c:if test="${ck==1}">
												<img id="${store.storenum}bookmarkimg" name="${ck}" src="${cp}/images/빈하트.png">
											</c:if> 
											<c:if test="${ck==0}">
												<img id="${store.storenum}bookmarkimg" name="${ck}"	src="${cp}/images/안빈하트.png">
											</c:if>
											<c:if test="${bklist == null or bklist.size() <= 0}">
												<img id="${store.storenum}bookmarkimg" name="1"	src="${cp}/images/빈하트.png">
											</c:if>
										</div>
							         </c:if>
							         <p>${store.price} 원</p>											
													
													<%-- <td><c:if test="${hot_store[i] == 'O'}"><sup class="hot">Hot</sup></c:if></td> --%>
				                 </div>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<p>등록된 게시글이 없습니다.</p>
						</c:otherwise>
					</c:choose>
					<table class="pagination">
						<tbody>
							<tr>
								<td><c:if test="${startPage != 1}">
										<a class="btn"
											href="${cp}/storelist.st?page=${startPage-1}&keyword=${keyword}">&lt;</a>
									</c:if> <c:forEach begin="${startPage}" end="${endPage}" var="i">
										<c:choose>
											<c:when test="${page == i}">
												<span class="nowPage">${i}</span>
											</c:when>
											<c:otherwise>
												<a class="btn"
													href="${cp}/storelist.st?page=${i}&keyword=${keyword}">${i}</a>
											</c:otherwise>
										</c:choose>
									</c:forEach> <c:if test="${endPage != totalPage}">
										<a class="btn"
											href="${cp}/storelist.st?page=${endPage+1}&keyword=${keyword}">&gt;</a>
									</c:if></td>
								</tr>
							</tbody>
						</table>

						<div class="search_area">
							<input type="search" id="keyword" name="keyword"
							value="${keyword}"> <input type="button" value="검색"
							onclick="search();">
						</div>
						<div id="write_button">
							<a class="button"  href="${cp}/storewrite.st?page=${page}&keyword=${keyword}">글쓰기</a>
						</div>
					</div>
				</section>
			</div>
		</div>
	<%@ include file = "/app/sidebar.jsp" %>
	</div>

	<!-- Scripts -->
	<script src="https://kit.fontawesome.com/220c29f5e3.js" crossorigin="anonymous"></script>
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/browser.min.js"></script>
	<script src="assets/js/breakpoints.min.js"></script>
	<script src="assets/js/util.js"></script>
	<script src="assets/js/main.js"></script>


	<script>
	
		function f(storenum){
		    const xhr = new XMLHttpRequest();
		    const txt = storenum+"bookmarkimg";
		    const temp = document.getElementById(txt).name;
		    
		    if(temp==0) {
				document.getElementById(txt).src = "${cp}/images/빈하트.png";
				document.getElementById(txt).id = storenum+"bookmarkimg";
				document.getElementById(txt).name = 1;
		    }
		    else {
		    	document.getElementById(txt).src = "${cp}/images/안빈하트.png";
				document.getElementById(txt).id = storenum+"bookmarkimg";
				document.getElementById(txt).name = 0;
		    }
		    console.log("asd");
		    console.log('${sessionScope.loginUser}');
	
				
		    xhr.open("GET","app/store/list_db.jsp?loginUser=${loginUser}&storenum="+storenum);
		    xhr.send();
		    
		    
		}
	
	
	
		window.setTimeout(function(){
			document.querySelector("#wrapper>div:nth-child(1)").style.display="none";
		},1300)
		function search(){
			const keyword = document.getElementById("keyword");
			//유효성 검사
			location.replace("${cp}/storelist.st?keyword="+keyword.value);
		}
		
		
	</script>
</body>
</html>