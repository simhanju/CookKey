<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>상세정보</title>
    <link href="${cp}/assets/css/get.css" rel="stylesheet">
</head>
<body class="is-preload">
    <div id="wrapper">
        <div id="main">
            <div class="inner">
				<%@ include file = "/app/header.jsp" %>
                
                <section>
                    <div class="posts">
                        <article>
                            <p>작성자 <input type="text" name="userid" value="${store.userid}" readonly></p>
                            <p>제목<input type="text" name="storetitle" value="${store.storetitle}" readonly></p>
                            <span class="sell">
                                <c:if test="${store.state==0 }">판매중</c:if>
                                <c:if test="${store.state==1 }">예약중</c:if>
                                <c:if test="${store.state==2 }">판매완료</c:if>
                            </span>
                            <c:choose>
                                <c:when test="${files != null and files.size()>0 }">
                                    <c:forEach var="i" begin="0" end="${files.size()-1}">
                                        <c:set var="file" value="${files[i]}"/>
                                        <tr>
                                            <td>
                                                <a href="${cp}/filedownload.st?systemname=${file.systemname}&orgname=${file.orgname}"></a>
                                            </td>
                                        </tr>
                                        <!-- 파일이 이미지 파일인 경우에만 썸네일로 표시 -->
                                        <c:forTokens items="${file.orgname }" delims="." var="token" varStatus="tokenStat">
                                            <c:if test="${tokenStat.last}">
                                                <c:if test="${token eq 'jpeg' or token eq 'jpg' or token eq 'png' or token eq 'gif' or token eq 'webp' }">
                                                    <!-- 썸네일 제작 -->
                                                    <tr>
                                                        <th></th>
                                                        <td class="thumbnail-container">
                                                            <p class="read_count">조회수 : ${store.readcount}</p>
                                                            <img class="thumbnail" src="${cp}/file/${file.systemname}">
                                                        </td>
                                                    </tr>
                                                </c:if>
                                            </c:if>
                                        </c:forTokens>
                                    </c:forEach>
                                </c:when>
                            </c:choose>
                            <p>내용</p>
                            <p>
                                <textarea name="storecontents" class="custom-textarea" readonly>${store.storecontents}</textarea>
                            </p>
                            <ul>
                                <li style="list-style-type: none;">
                                    <ul class="category-list">
                                        <c:forEach var="i" begin="0" end="${categories.size()-1}">
                                            <c:choose>
                                                <c:when test="${categories[i].categorynum==500 }">
                                                    <li class="category-item">#조리도구</li>
                                                </c:when>
                                                <c:when test="${categories[i].categorynum==501 }">
                                                    <li class="category-item">#음식재료</li>
                                                </c:when>
                                                <c:when test="${categories[i].categorynum==502 }">
                                                    <li class="category-item">#양념</li>
                                                </c:when>
                                                <c:when test="${categories[i].categorynum==503 }">
                                                    <li class="category-item">#향신료</li>
                                                </c:when>
                                                <c:when test="${categories[i].categorynum==504 }">
                                                    <li class="category-item">#조미료</li>
                                                </c:when>
                                                <c:when test="${categories[i].categorynum==5000 }">
                                                    <li class="category-item">#기타</li>
                                                </c:when>
                                                <c:otherwise>
                                                    <li class="category-item">Unknown</li>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </ul>
                                </li>
                            </ul>
                            <ul class="actions">
                                <li>
                                    <p>금액</p>
                                    <input type="text" name="price" value="${store.price}" readonly>
                                </li>
                            </ul>
                            <div class="btn_area">
   								 <c:if test="${store.userid == loginUser}">
        							<a class="btn" href="${cp}/storeupdate.st?storenum=${store.storenum}&page=${param.page}&keyword=${param.keyword}">수정</a>
       								<a class="btn" href="${cp}/storedelete.st?storenum=${store.storenum}&page=${param.page}&keyword=${param.keyword}">삭제</a>
   								 </c:if>
   								 <c:if test="${store.userid != loginUser}">
        							<a class="btn" href="${cp}/chatstart.ch?loginUser=${loginUser}&receive=${store.userid}">채팅하기</a>
   								 </c:if>
    								<a class="btn" href="${cp}/storelist.st?page=${param.page}&keyword=${param.keyword}">목록</a>
							</div>
                        </article>
                    </div>
                </section>
            </div>
        </div>
        <!-- Sidebar -->
		<%@ include file = "/app/sidebar.jsp" %>
    </div>
</body>
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/browser.min.js"></script>
<script src="assets/js/breakpoints.min.js"></script>
<script src="assets/js/util.js"></script>
<script src="assets/js/main.js"></script>
<script>
    window.setTimeout(function(){
        document.querySelector("#wrap>div:nth-child(1)").style.display="none";
    },1200)
</script>
</html>
