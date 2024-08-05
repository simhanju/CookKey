<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.contextPath}" scope="session"/>
<!DOCTYPE HTML>
<html>
	<head>
		<style>
@font-face {
    font-family: 'BMJUA';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/BMJUA.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
@font-face {
	font-family: 'BMHANNAPro';
	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_seven@1.0/BMHANNAPro.woff') format('woff');
	font-weight: normal;
	font-style: normal;
}
@font-face {
    font-family: 'SejonghospitalBold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2312-1@1.1/SejonghospitalBold.woff2') format('woff2');
    font-weight: 700;
    font-style: normal;
}
/* 오뮤 다예쁨체 */
@font-face {
    font-family: 'omyu_pretty';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2304-01@1.0/omyu_pretty.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}
</style>
		<title>index</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="${cp}/assets/css/index1.css" />
	</head>
	<body class="is-preload">
		<c:if test="${!empty loginUser }">
			<script>
				location.replace("${cp}/app/board/list.jsp");
			</script>
		</c:if>
		<div id="wrapper">
			<div id="main">
				<div class="inner">
					<header id="header">
						<a href="#" class="logo"><strong>Cook Key</strong> 요리 관련 SNS 입니다.</a>
						<ul class="icons">
                        	<li><a href="${cp}/userlogin.us" class="button">login</a></li>
                        	<li><a href="${cp}/userjoin.us" class="button">join</a></li>
						</ul>
					</header>
					
                    <section>
                    	<div class="entire_div">
                    		<div class="main_word">
                    			<h2>Cook Key</h2>
                    			<h3 style="font-family: 'SejonghospitalBold';">
                    				요리와 레시피를 향한<br>
                    				모든 연결의 시작, Cook Key
                    			</h3>
                   				<div>
                    				<a class="fa-brands fa-signal-messenger"></a>
                    				<a class="fa-brands fa-facebook"></a>
                    				<a class="fa-sharp fa-solid fa-link"></a>
                    				<a class="fa-brands fa-twitter"><a>
                    			</div>
                    		</div>
                    		<div class="main_img">
                    			<img src="${cp}/images/cookImg_1.jpg">
                    			<img src="${cp}/images/cookImg_2.jpg">
                    			<img src="${cp}/images/cookImg_3.jpg">
                    		</div>
                    		<div class="cook_people">
                    			<img alt="" src="${cp}/images/Cookkey로고.png">
                    			<h3 style="font-family: 'BMJUA';">
                    				요리와 사람,<br>
                    				그 이상을 연결하는 Cook Key<br>
                    				전 세계 요리사이트를 꿈꾸는<br>
                    				대표 커뮤니티 사이트<br>
                    				언제 어디서나 간편하게 즐겨보세요.
                    			</h3>
                    		</div>
						    <div class="kind_slider">
						        <a  class="fa-solid fa-circle-chevron-left" onclick="moveSlide(-1)"></a>
						        <ul class="slider" id="imageSlider">
						            <li>
						            	<h2 style="font-family: 'SejonghospitalBold';">게시물 보기</h2>
                   						<p>온라인으로 연결된 곳이라면 어디서든 볼수있습니다. 친구 혹은 좋아요를 통해 다양한 요리 정보를 확인하세요</p>
                   						<div>
                   							<img src="${cp}/images/게시글 목록.png">
                   						</div>
						            </li>
						            <li>
						            	<h2 style="font-family: 'SejonghospitalBold';">게시물 작성</h2>
                   						<p>자신의 요리 혹은 레시비를 글로 작성할 수 있습니다. 자신만의 요리와 레시피를 다른사람과 공유해보세요!</p>
                   						<div>
                   							<img alt="" src="${cp}/images/게시글 작성.png">
                   						</div>
						            </li>
						            <li>
						            	<h2 style="font-family: 'SejonghospitalBold';">장터 게시물</h2>
                   						<p>단순히 요리 정보를 공유하는것이 아닌, 장터를 통해 직접 거래를 할 수 있습니다. 합리적인 가격과 퀄리티를 보장해드립니다!</p>
                   						<div>
                   							<img alt="" src="${cp}/images/장터 목록.png">
                   						</div>
						            </li>
						            <li>
						            	<h2 style="font-family: 'SejonghospitalBold';">장터 게시물 작성</h2>
                   						<p>자신만의 요리 및 레시피를 다른분들에게 직접 판매할 수 있습니다. 수입이 꽤 짭짭할수도 있어요...</p>
                   						<div>
                   							<img alt="" src="${cp}/images/장터 게시물 글.png">
                   						</div>
						            </li>
						            <li>
						            	<h2 style="font-family: 'SejonghospitalBold';">프로필 설정</h2>
                   						<p>자신의 프로필을 변경 할 수 있습니다. 자신만의 개성으로 멋진 프로필을 완성시켜보세요!</p>
                   						<div>
                   							<img alt="" src="${cp}/images/pf.png">
                   						</div>
						            </li>
						        </ul>
						        <a  class="fa-solid fa-circle-chevron-right" onclick="moveSlide(1)"></a>
						    </div>
						</div>
                    </section>
				</div>
			</div>
		</div>

        <script src="https://kit.fontawesome.com/220c29f5e3.js" crossorigin="anonymous"></script>
  		<script>
		    var slideIndex = 0;
		    showSlides(slideIndex);
		
		    function moveSlide(n) {
		        slideIndex += n;
		        showSlides(slideIndex);
		    }
		
		    function showSlides(n) {
		        var i;
		        var slides = document.getElementsByClassName("slider")[0].getElementsByTagName("li");
		        if (n >= slides.length) {
		            slideIndex = 0;
		        }
		        if (n < 0) {
		            slideIndex = slides.length - 1;
		        }
		        for (i = 0; i < slides.length; i++) {
		            slides[i].style.display = "none";
		        }
		        slides[slideIndex].style.display = "flex";
		    }
		</script>
	</body>
</html>