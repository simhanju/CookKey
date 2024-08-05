<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
<head>
<title>index</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="${cp}/assets/css/board.css" />
<link rel="stylesheet" href="${cp}/assets/css/loading.css" />
	</head>
	<body class="is-preload">
    <!-- Wrapper -->
    <div id="wrapper">
        <!-- Main -->
        <div id="main">
            <div class="inner">
                <!-- Header -->
		<%@ include file = "/app/header.jsp" %>
		<!-- 타이틀 띄워주는 테이블 -->																		<!-- 파일 데이터를 전송하기 위한 형식 -->
					<section>
			          <form action="${cp}/boardupdateok.bo" id="boardForm" name="boardForm" method="post" enctype="multipart/form-data">
			              <input type="hidden" name="boardnum" value="${board.boardnum}">
			              <div class="newBoard_div">
			                  <div class="up_div">
			                      <input type="button" value="작성 취소" >
			                      <h3>게시물 수정</h3>
			                      <input type="submit" value="업데이트" >
			                  </div>
			                  <div class="down_div">
			     
			                      <div class="writing_div">
			                          <div>
			                              <a href="#"><img class="writer_profile" src=""></a>
			                              <span id="userid">${loginUser}</span>
			                          </div>
			                          <textarea name="boardcontents" >${board.boardcontents}</textarea>
			                          <div class="category_dropdown_div">
			                              <strong>카테고리</strong>
			                              <div class="category_dropdown_menu">
			                                  
			                              </div>
			                          </div>
			                      </div>
			                  </div>
			              </div>
			          </form>
			        </section>
                            <!-- 세션 끝 -->
                  </div>
               </div>

            <!-- Sidebar -->
              	<%@ include file = "/app/sidebar.jsp" %>
                <!-- Sidebar 끝 -->

                <!-- top버튼  -->
                <div class="topButton_div">
                    
                </div>
                <!-- top버튼 끝  -->
         </div>

      <!-- Scripts -->
            <script src="https://kit.fontawesome.com/220c29f5e3.js" crossorigin="anonymous"></script>
         <script src="${cp}/assets/js/jquery.min.js"></script>
         <script src="${cp}/assets/js/browser.min.js"></script>
         <script src="${cp}/assets/js/breakpoints.min.js"></script>
         <script src="${cp}/assets/js/util.js"></script>
         <script src="${cp}/assets/js/main.js"></script>
              
   </body>

<script>
   window.onload = function(){

      getUser();
      console.log("실행");
   }

   function getUser() {
	   
       $.ajax({
           url: "${cp}/boardwrite.bo",
           type: "GET",
           success: function(response) {
               const obj = JSON.parse(response);
               const categories = obj.categories;
   
               let str = "";
               let i = 0;
               for (let category of categories) {
                  str += "<div>";
                  str += '<input type="checkbox" id="'+ category.categorynum +'"name="categorynum" value="'+ category.categorynum +'">'
                  str += '<label for="'+ category.categorynum +'">'+ category.categoryname +'</label>'
                  str += "</div>";
               }
               $(".category_dropdown_menu").html(str);
           },
           complete: function () {$("#loading").css("display","none");}
       });
   }

</script>
</html>