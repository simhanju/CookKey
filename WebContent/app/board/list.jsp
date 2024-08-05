<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <c:set var="cp" value="${pageContext.request.contextPath}"
	scope="session" />
<!DOCTYPE HTML>
<html>
	<head>
		<title>index</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="${cp}/assets/css/main1.css" />
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
                            
                            <!-- 세션 -->
                                <section class="timeline"></section>
						</div>
					</div>

				<!-- Sidebar -->
               <%@ include file = "/app/sidebar.jsp" %>

				<!-- 글쓰기 버튼 -->
                <div class="write_button_div"
                	style="
                	position: fixed;
                	top: 80%; left: 90%;
    
				   display: flex;
				   justify-content: center;
				   align-items: center;
				
				   width: 70px; height: 70px;
				   z-index: 1000;
				
				   border: 1px solid #3d4449;
				   background-color: antiquewhite;
				   border-radius: 50%;
				   cursor: pointer;
                	"
                >
                    <a href="${cp}/app/board/write.jsp" class="fa-solid fa-pen"></a>
                </div>    
                <!-- 글쓰기 버튼 끝 -->
			</div>
	</body>
		<!-- Scripts -->
<script src="https://kit.fontawesome.com/220c29f5e3.js" crossorigin="anonymous"></script>
<script src="${cp}/assets/js/jquery.min.js"></script>
<script src="${cp}/assets/js/browser.min.js"></script>
<script src="${cp}/assets/js/breakpoints.min.js"></script>
<script src="${cp}/assets/js/util.js"></script>
<script src="${cp}/assets/js/main.js"></script>
<script>
	var isLoading = false; // 데이터 로딩 중인지 여부를 나타내는 변수 추가
	var isScrollEventRegistered = false; // 스크롤 이벤트가 등록되었는지 여부를 나타내는 변수 추가
	var maxNum = null;
	var firstlist = false;
	var loginUser = '${loginUser}';
	window.onload = function(){
		getboardlist(maxNum);
		console.log("실행");
	}
	
	$('#wrapper').scroll(function(){
		getboardlist(maxNum);
	});



 // 좋아요 아이콘 눌렀을때(좋아요 추가)
    function like(num){
       $.ajax({
          url:"${cp}/likeAdd.bo",
          type: "GET",
          data: {
               num:num
          },
          success : function(response) {
               const obj = JSON.parse(response);
               const success = obj.success;
               const likeCnt = obj.likeCnt;

               if(success === "O") {
                   // 좋아요 아이콘 js를 like_cancel로 바꾸기
                   $("#like"+num).prop("href", "javascript:like_cancel("+num+")");
                   // 좋아요 아이콘 색을 빨강으로 바꾸기
                   $("#like"+num).addClass("like_cancel");
                   // 좋아요 수
                   console.log(likeCnt);
                   $("#like_count"+num).html("좋아요 수 : "+likeCnt);
               }
               else{
                   alert("like 함수가 동작하지않았습니다.");
                   return;
               }
          }
       })
   }

   // 좋아요 아이콘 다시 눌러 취소했을때 (좋아요 삭제)
   function like_cancel(num) {
       $.ajax({
           url:"${cp}/likeCancel.bo",
           type: "GET",
           data: {
               num:num
           },
           success : function(response) {
               const obj = JSON.parse(response);
               const success = obj.success;
               const likeCnt = obj.likeCnt;

               if(success === "O") {
                   // 좋아요 아이콘 js를 like로 돌려놓기
                   $("#like"+num).prop("href", "javascript:like("+num+")");
                   // 좋아요 아이콘 색을 노랑으로 돌려놓기
                   $("#like"+num).removeClass("like_cancel");
                   // 좋아요 수 제거
                   console.log(likeCnt);
                   $("#like_count"+num).html("좋아요 수 : "+likeCnt);
               }
               else{
                   alert("like_cancel 함수가 동작하지않았습니다.");
                   return;
               }
          }
       })
   }

   // 북마크 아이콘을 눌렸을때 (북마크 추가)
   function bookmark(num) {
       $.ajax({
           url:"${cp}/bookmarkAdd.bo",
           type: "GET",
           data: {
               num:num
           },
           success : function(response) {
               const obj = JSON.parse(response);
               const success = obj.success;

               if(success === "O") {
                   // 북마크 아이콘 js를 bookmark cancel로 돌려놓기
                   $("#bookmark"+num).prop("href", "javascript:bookmark_cancel("+num+")");
                   //  북마크 아이콘 색을 빨강으로 바꾸기
                   $("#bookmark"+num).addClass("like_cancel");
               }
               else{
                   alert("bookmark 함수가 동작하지않았습니다.");
                   return;
               }
           }
       })
   }
   // 북마크 아이콘을 다시 눌러 취소했을때 (북마크 취소)
   function bookmark_cancel(num) {
       $.ajax({
           url:"${cp}/bookmarkCancel.bo",
           type: "GET",
           data: {
               num:num
           },
           success : function(response) {
               const obj = JSON.parse(response);
               const success = obj.success;

               if(success === "O") {
                   // 북마크 아이콘 js를 bookmark로 돌려놓기
                   $("#bookmark"+num).prop("href", "javascript:bookmark("+num+")");
                   // 북마크 아이콘 색을 노랑으로 돌려놓기
                   $("#bookmark"+num).removeClass("like_cancel")
               }
               else{
                   alert("bookmark 함수가 동작하지않았습니다.");
                   return;
               }
          }
       })
   }
   
   function getReplies(boardnum, maxnum) { //임의로 첫 더보기 버튼 파라미터에 maxnum은 0 넣고, 새로 생성되는거에 replies의 마지막 num 넣기
       console.log("맥스넘:"+maxnum);
       $("#loading").css("display","flex");
   var maxnum = maxnum
    console.log("댓글 실행")
             if(maxnum===0){
                 $('#reply'+boardnum).html("");
             }
     $.ajax({
         url:"${cp}/replylist.rp",
         type: "GET",
         data: {
            boardnum:boardnum,
            maxnum:maxnum
         },
         success : function(response) {
            
             const obj = JSON.parse(response);
             const replies = obj.replies;

             
             let str = "";
             
             let maxnum = 0;
             for(let reply of replies) {
               for(let reply1 of reply){
            	str += '<tr id="reply_row' + reply1.replynum + '">';
            	 str += '<td>' + reply1.userid + '</td>';
                 str+='<td>'
                 str+='<span id="reply-display'+reply1.replynum+'">'+reply1.replycontents+'</span>'
                 str+='<input type="text" id="reply-edit' + reply1.replynum + '" value="' + reply1.replycontents + '" style="display:none;">';
             	  str+='</td>'
                 str += '<td>' + reply1.regdate + '</td>';
                if(reply1.userid !== '${loginUser}'){
                str+="";	
                }
                else{
                	str += '<td><button id="reply-modify(' + reply1.replynum + ')" onclick="toggleEdit(' + reply1.replynum + ')">수정</button></td>';
                    str += '<td><button id="reply-modicomp(' + reply1.replynum + ')" onclick="modiReply(' + reply1.replynum + ',' +reply1.boardnum+')" style="display:none;">수정완료</button></td>';
                    str += '<td><button  id="reply-delete(' + reply1.replynum + ')" onclick="deleteReply('+reply1.replynum+ ',' +reply1.boardnum+')">삭제</button></td>';                    	  
                    str += '<td><button  id="modi-cancel(' + reply1.replynum + ')" onclick="toggleEdit('+reply1.replynum+ ')" style="display:none;">수정취소</button></td>';                	
                }
                str += '</tr>';
                maxnum = reply1.replynum;
               }
             }
             console.log(str);

             if(maxnum===0){
            $(".reply_plus_div"+boardnum).attr("style","display:none");
             }
             else{
            	 $(".reply_plus_div"+boardnum).attr("style","display:block");   
               $("#plus"+boardnum).attr("href", "javascript:getReplies("+boardnum+","+maxnum+")")
             }
             if(str === null | str === undefined | str === ""){
                /* str += '<tr>';
                str += '<td>추가 댓글이 없습니다.</td>';
                str += '</tr>'; */
             }
             
             $("#reply"+boardnum).append(str);
        },
        complete: function() {
            $("#loading").css("display","none");
        }
     })
 }

   // 댓글 추가 하기
  function reply_write(boardnum) {
      $("#loading").css("display","flex");
      var replyContent = $('#reply-write' + boardnum).val(); 
      console.log(boardnum)
      if (!replyContent) {
          alert("댓글 내용을 입력해 주세요.");
          return;
      }
  
      console.log("댓글 내용:", replyContent);  // 디버깅을 위한 콘솔 출력
  
      $.ajax({
          url: "${cp}/replywrite.rp",
          type: "GET",
          data: {
              boardnum: boardnum,
              replycontents: replyContent
          },
          success: function(response) {
              const obj = JSON.parse(response);
              if (obj.success[0] === "O") {
                  console.log("댓글이 추가되었습니다.");
                  $('#reply-write' + boardnum).val("");
                  $('#reply'+boardnum).html("");
                  $('.reply_plus_div'+boardnum).attr("style","display:block");
                  getReplies(boardnum, null);
                  $("#loading").css("display","flex");
              } else {
                 console.log("댓글 추가 실패");
              }
          },
          error: function() {
             console.log("서버 통신 오류가 발생했습니다.");
          },
          complete: function() {
              $('#replytable'+boardnum).find('thead').attr("style","display:flex");
              $("#loading").css("display","none");
          }
      });
  }
	
  function getboardlist(maxNum) {
      $("#loading").css("display","flex");
      if (isLoading) {
          return;
      }
      isLoading = true;

      $.ajax({
          url: "${cp}/boardlist.bo",
          type: "GET",
          dataType: 'json', // 서버로부터 JSON 응답을 기대함
          data: {
              maxNum: maxNum
          },
          success: function(response) {
              const obj = response; 
              const timeline = obj.timeline;
              const likes = obj.likes;
              const lreply = obj.lreply;
              const userfiles = obj.userfiles;
              const boardfiles = obj.boardfiles;
              const mylikes = obj.mylikes;
              const mybookmarks = obj.mybookmarks;

              let str = "";
              let i = 0;

              
              for (let board of timeline) {
            	  firstlist = true;
                  str += '<div class="posts" data-boardnum="' + board.boardnum + '">';
                  str += "<article>";
          
                  str += '<div id="writer_div">';
                  str += '<a href=${cp}/app/user/userpage.jsp?userid=' +timeline[i].userid+ ' class="image"><img src="${cp}/file/' + decodeURI(userfiles[i].length != 0 ? userfiles[i][0].systemname : 'no_profil_user_img.png') + '" alt="프로필 사진" /></a>';
                  str += '<h3>이름 : '+ board.userid +'</h3>';
                  str += '<h3>작성시간 : '+ board.regdate +'</h3>';
                  if(timeline[i].userid !== '${loginUser}'){
                      
                  }
	              else{
	                   str += '<div class="board_RD">';
	                   str += '<a href="${cp}/boardupdate.bo?boardnum='+board.boardnum+'">게시글 수정</a>';
	                   str += '<a href="${cp}/boarddelete.bo?boardnum='+board.boardnum+'">게시글 삭제</a>';
	                  str += '</div>';
	              }
                  str += '</div>';
                  if(board.updatedate === null || board.updatedate === "" || board.updatedate === undefined){
                  } 
                  else{
                      str += '<h3>수정 : '+ board.updatedate +'</h3>';
                  }
                  // 게시글 이미지 출력
                  if(boardfiles[i] && boardfiles[i][0]) {
	                  str += '<div href="#" class="image"><img src="${cp}/file/' + decodeURI(boardfiles[i][0].systemname) + '" alt="게시글 사진" /></div>';
                  }
                  
                  // 게시글 내용 출력
                  str += '<p style="overflow: hidden; height: 20px;">' + board.boardcontents + '</p>';
                  // 더보기 링크 추가

                  // 좋아요 및 북마크 기능 추가
                  str += '<ul class="actions">';
                  str += '<li id="like_li">';
                  if (mylikes[0][i] === null || mylikes[0][i] === undefined ||  mylikes[0][i] === "" ){
                      str += '<a href="javascript:like('+board.boardnum+')" id="like'+board.boardnum+'" class="fa-solid fa-thumbs-up"></a>';
                  }
                  else {
                      str += '<a href="javascript:like_cancel('+board.boardnum+')" id="like'+board.boardnum+'" class="fa-solid fa-thumbs-up like_cancel"></a>';
                  }
                  str += '<p id="like_count'+board.boardnum+'">좋아요 수 : ' + likes[0][i] + '</p>';
                  str += '</li>';

				  if(timeline[i].userid !== '${loginUser}') {
                  if(mybookmarks[0][i] === null || mybookmarks[0][i] === undefined ||  mybookmarks[0][i] === "") {
	                      str += '<li><a href="javascript:bookmark('+board.boardnum+')" id="bookmark'+board.boardnum+'" class="fa-solid fa-bookmark"></a></li>';
	                  }
	                  else {
	                      str += '<li><a href="javascript:bookmark_cancel('+board.boardnum+')" id="bookmark'+board.boardnum+'" class="fa-solid fa-bookmark like_cancel"></a></li>';
	                  }
				  }
	               str += '</ul>';

                  // form name="replyForm' 시작
                   str += '<div class="reply_write_div" id="reply_write_div'+board.boardnum+'">';
                   str += '<h3>댓글 작성</h3>';
                   if(timeline[i].userid !== '${loginUser}'){
                	   str+= "";
              		}	
                   else{
                   }
                   str += '<textarea name="" id="reply-write'+board.boardnum+'" cols="30" rows="4" style="resize: none;"></textarea>';
                   str += '<input type="button" value="댓글 추가" onclick="javascript:reply_write('+board.boardnum+')">';
                   str += '</div>';
         
              str += '<div class="table-wrapper">';
                    if (lreply[0][i] === null || lreply[0][i] === "" || lreply[0][i] === undefined) {
                       str += '<table id= "replytable'+board.boardnum+'"}>';
                       str += '<thead style="display:none">';
                       str += '<tr>';
                       str += '<th>작성자</th>';
                       str += '<th>댓글 내용</th>';
                       str += '<th>작성 시간</th>';
                       str += '</tr>';
                       str += '</thead>';
                       str += '<tbody id="reply'+board.boardnum+'">';
 /*                      str += '<tr>';
                      str += '<td>댓글이 없습니다.</td>';
                      str += '</tr>'; */
                      str += '</tbody>';
                      str += '</table>';
                      str += '<div class="reply_plus_div'+board.boardnum+'" style="display:none">';
                      str += '<a href="javascript:getReplies('+board.boardnum+',0)" id="plus'+board.boardnum+'" class="fa-solid fa-circle-plus"></a>';
                      str += '</div>';
          // if문 일때 <div class="table-wrapper"> 끝
                    } 
                    else {
                      str += '<table id= "replytable'+board.boardnum+'">';
                      str += '<thead>';
                      str += '<tr id="reply_row">';
                      str += '<th>작성자</th>';
                      str += '<th>댓글 내용</th>';
                      str += '<th>작성 시간</th>';
                      str += '</tr>';
                      str += '</thead>';
                      str += '<tbody id="reply'+board.boardnum+'">';
                      str += '<tr id="reply_row' + lreply[0][i].replynum + '">';
                      str += '<td>' + lreply[0][i].userid + '</td>';
                      str+='<td>'
                      str+='<span id="reply-display'+lreply[0][i].replynum+'">'+lreply[0][i].replycontents+'</span>'
                      str+='<input type="text" id="reply-edit' + lreply[0][i].replynum + '" value="' + lreply[0][i].replycontents + '" style="display:none;">';
                  	  str+='</td>'
                      str += '<td>' + lreply[0][i].regdate + '</td>';
                      if(lreply[0][i].userid !== '${loginUser}'){
                    	  console.log("로그인 유저: ${loginUser}");
                    	  str += "";
                      }
                      else{
                    	  console.log("로그인 유저: ${loginUser}");
                      str += '<td><button id="reply-modify(' + lreply[0][i].replynum + ')" onclick="toggleEdit(' + lreply[0][i].replynum + ')">수정</button></td>';
                      str += '<td><button id="reply-modicomp(' + lreply[0][i].replynum + ')" onclick="modiReply(' + lreply[0][i].replynum + ',' +board.boardnum+')" style="display:none;">수정완료</button></td>';
                      str += '<td><button  id="reply-delete(' + lreply[0][i].replynum + ')" onclick="deleteReply('+lreply[0][i].replynum+ ',' +board.boardnum+')">삭제</button></td>';                    	  
                      str += '<td><button  id="modi-cancel(' + lreply[0][i].replynum + ')" onclick="toggleEdit('+lreply[0][i].replynum+ ')" style="display:none;">수정취소</button></td>';                    	  
                      
                      }
                      str += '</tr>';
                      str += '</tbody>';
                      str += '</table>';
                      str += '<div id="reply_plus_div" class="reply_plus_div'+board.boardnum+'">';
                      str += '<a href="javascript:getReplies('+board.boardnum+',0)" id="plus'+board.boardnum+'" class="fa-solid fa-circle-plus"></a>';
                      str += '</div>';
         // else문 일때 <div class="table-wrapper"> 끝
                    }
                    str += '</div>';
                    str += "</article>";
        // article 끝
                    str += '</div>';
        // div class="posts 끝       
                     i++;

              }

              isLoading = false;

              if(str === null | str === undefined | str === ""){
            	  if(firstlist){
            	  	
            	  }
            	  else {
              		$(".timeline").html("등록된 게시글이 없습니다.");  
            	  }
              }
              else{
              	
                  $(".timeline").append(str);
              }

          },
          error: function(xhr, status, error) {
              console.error("AJAX 요청 실패:", status, error);
          },
          complete: function() {
              isLoading = false;
              $("#loading").css("display","none");
          }
      });
  }
  function toggleEdit(replynum) {
	  console.log("로그인 유저: ${loginUser}");
	    var contentDisplay = document.getElementById('reply-display' + replynum);
	    var contentEdit = document.getElementById('reply-edit' + replynum);
		var replymodi = document.getElementById('reply-modify(' + replynum + ')');
		var modicomp = document.getElementById('reply-modicomp(' + replynum + ')');
		var replydelete = document.getElementById('reply-delete(' + replynum + ')');
		var modicancel = document.getElementById('modi-cancel(' + replynum + ')');
	    
		if (contentEdit.style.display === 'none') {
	        contentEdit.style.display = 'block';
	        contentDisplay.style.display = 'none';
	        replymodi.style.display = 'none';
	        modicomp.style.display='inline';
	        replydelete.style.display= 'none';
	        modicancel.style.display='inline';
	    } else {
	        contentEdit.style.display = 'none';
	        contentDisplay.style.display = 'block';
	        replymodi.style.display = 'inline';
	        modicomp.style.display='none';
	        replydelete.style.display= 'inline';
	        modicancel.style.display='none';
	    }
	}
  function modiReply(replynum,boardnum) {
      $("#loading").css("display","flex");
      var replyContent = $('#reply-edit' + replynum).val(); 
      console.log(replynum)
      console.log(boardnum)
      console.log(loginUser)
      if (!replyContent) {
          alert("댓글 내용을 입력해 주세요.");
          return;
      }
  
      console.log("댓글 내용:", replyContent);  // 디버깅을 위한 콘솔 출력
  
      $.ajax({
          url: "${cp}/replyupdate.rp",
          type: "GET",
          data: {
        	  boardnum:boardnum,
              replynum: replynum,
              replycontents: replyContent
          },
          success: function(response) {
        	  console.log(response);
              const obj = JSON.parse(response);
              if (obj.success[0] === "O") {
                  console.log("댓글이 추가되었습니다.");
                  $('#reply-edit' + replynum).val("");
                  $('#reply'+boardnum).html("");
                  $('.reply_plus_div'+boardnum).attr("style","display:block");
                  getReplies(boardnum, 0);
                  $("#loading").css("display","flex");
              } else {
                 console.log("댓글 추가 실패");
              }
          },
          error: function() {
             console.log("서버 통신 오류가 발생했습니다.");
          },
          complete: function() {
              $("#loading").css("display","none");
          }
      });
  }
  
  
  function deleteReply(replynum,boardnum){
	  console.log(replynum);
      console.log(boardnum);
      $("#loading").css("display","flex");
	 $.ajax({
		 url:"${cp}/replydelete.rp",
		 type:"GET",
		 data:{
			 replynum:replynum,
			 boardnum:boardnum
		 },
		 success: function(response){
			 console.log(response);
			 const obj = JSON.parse(response);
			 console.log(obj);
             const success = obj.success;
             console.log(success);

             if(success === "O") {
				 console.log("삭제완료");  
				 $('#reply_row' + replynum).remove();
	             $('#reply'+boardnum).html("");
				 
				 $('#reply' + boardnum).html("ㅁㄴㄹㅇ")
				
				 if ($('#reply_row'+replynum).length === 0) {
	                 $('#replytable'+boardnum).find('thead').attr("style","display:none");
	             }
	             getReplies(boardnum,0);
	             $("#loading").css("display","flex");
             }
             else{
                 alert("댓글삭제 실패!!");
                 return;
             }
		 },
		 error: function(xhr, status, error) {
	            console.error("AJAX 요청 실패:", status, error);
	            alert("서버 통신 오류가 발생했습니다.");
	        },
	        complete: function() {
	            $("#loading").css("display","none");
	        }
		 
	 })
  }

  // 스크롤 이벤트 핸들러 등록 함수
  function registerScrollEvent() {
      if (!isScrollEventRegistered) {
          $(window).scroll(function() {
              if ($(window).scrollTop() + $(window).height() == $(document).height()) {
                  // 가장 아래에 있는 게시글의 번호 중 가장 작은 값을 가져옴
                  const minBoardNum = getMinBoardNum();

                  getboardlist(minBoardNum); // 다음 게시글의 시작 번호를 인수로 전달하여 호출
              }
          });
          isScrollEventRegistered = true;
      }
  }

  // 가장 아래에 있는 게시글의 번호 중 가장 작은 값을 가져오는 함수
  function getMinBoardNum() {
      let minBoardNum = Infinity; // 무한대로 초기화하여 비교에 사용

      $(".posts").each(function() {
          const boardNum = $(this).data("boardnum");
          if (boardNum && boardNum < minBoardNum) {
              minBoardNum = boardNum;
          }
      });

      // 무한대로 초기화한 값이 변경되지 않았다면 게시글이 없는 상태이므로 0을 반환
      return isFinite(minBoardNum) ? minBoardNum : 0;
  }

  // 스크롤 이벤트 등록
  if (!isScrollEventRegistered) {
      registerScrollEvent();
  }


</script>
</html>