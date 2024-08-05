<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
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
         </div>
   </body>
      <!-- Scripts -->
<script src="https://kit.fontawesome.com/220c29f5e3.js" crossorigin="anonymous"></script>
<script src="${cp}/assets/js/jquery.min.js"></script>
<script src="${cp}/assets/js/browser.min.js"></script>
<script src="${cp}/assets/js/breakpoints.min.js"></script>
<script src="${cp}/assets/js/util.js"></script>
<script src="${cp}/assets/js/main.js"></script>
<!--  <script>
         function createDropbar(){
             dropMenu.classList.toggle('displaynone');
         }
         menuIcon.addEventListener('click', createDropbar);

</script> -->
<script>
	var isLoading = false; // 데이터 로딩 중인지 여부를 나타내는 변수 추가
	var isScrollEventRegistered = false; // 스크롤 이벤트가 등록되었는지 여부를 나타내는 변수 추가
	var maxNum = null;
	var firstlist = false;
	
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
   var maxnum = maxnum
    console.log("댓글 실행")
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
         
             console.log(obj);
             console.log(replies);
             
             let maxnum = 0;
             for(let reply of replies) {
               for(let reply1 of reply){
                str += '<tr>'
                str += '<td>' + reply1.userid + '</td>';
                str += '<td>' + reply1.replycontents + '</th>';
                str += '<td>' + reply1.regdate + '</td>';
                str += '</tr>';
                maxnum = reply1.replynum;
               }
             }
             console.log(str)
             $("#reply"+boardnum).append(str);
             
             if(maxnum===0){
            $("#plus"+boardnum).attr("style","display:none");                
             }
             else{
               $("#plus"+boardnum).attr("href", "javascript:getReplies("+boardnum+","+maxnum+")")
             }
        }
     })
 }

   // 댓글 추가 하기
  function reply_write(boardnum) {
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
                  getReplies(boardnum, null);
              } else {
                 console.log("댓글 추가 실패");
              }
          },
          error: function() {
             console.log("서버 통신 오류가 발생했습니다.");
          }
      });
  }
	
  function getboardlist(maxNum) {
  	console.log("왜 안돼 갑자기");
      if (isLoading) {
          return;
      }
      isLoading = true;

      $.ajax({
          url: "${cp}/boardsearch.bo",
          type: "GET",
          dataType: 'json', // 서버로부터 JSON 응답을 기대함
          data: {
              maxNum: maxNum
          },
          success: function(response) {
              const obj = response;
              console.log(obj);
              const timeline = obj.timeline;
              console.log(timeline);
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
            	  
            	  console.log(decodeURI(userfiles[i]).length!=0);
                  str += '<div class="posts" data-boardnum="' + board.boardnum + '">';
                  str += "<article>";
          
                  str += '<div id="writer_div">';
                  str += '<a href=${cp}/app/user/userpage.jsp?userid=' +timeline[i].userid+ ' class="image"><img src="${cp}/file/' + decodeURI(userfiles[i].length != 0 ? userfiles[i][0].systemname : 'no_profil_user_img.png') + '" alt="프로필 사진" /></a>';
                  str += '<h3>이름 : '+ board.userid +'</h3>';
                  str += '<h3>작성시간 : '+ board.regdate +'</h3>';
                  str += '</div>';
                  if(board.updatedate === null || board.updatedate === "" || board.updatedate === undefined){
                  } 
                  else{
                      str += '<h3>수정 : '+ board.updatedate +'</h3>';
                  }
                  // 게시글 이미지 출력
                  if(boardfiles[i] && boardfiles[i][0]) {
	                  str += '<a href="#" class="image"><img src="${cp}/file/' + decodeURI(boardfiles[i] && boardfiles[i][0] ? boardfiles[i][0].systemname : '') + '" alt="게시글 사진" /></a>';
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
                   str += '<textarea name="" id="reply-write'+board.boardnum+'" cols="30" rows="4" style="resize: none;"></textarea>';
                   str += '<input type="button" value="댓글 추가" onclick="javascript:reply_write('+board.boardnum+')">';
                   str += '</div>';
         
              str += '<div class="table-wrapper">';
                    if (lreply[0][i] === null || lreply[0][i] === "" || lreply[0][i] === undefined) {
                       str += '<table>';
                      str += '<tbody>';
                      str += '<tr>';
                      str += '<td>댓글이 없습니다.</td>';
                      str += '</tr>';
                      str += '</tbody>';
                      str += '</table>';
          // if문 일때 <div class="table-wrapper"> 끝
                    } 
                    else {
                        console.log("댓글" + lreply[0][i]);
                      str += '<table>';
                      str += '<thead>';
                      str += '<tr>';
                      str += '<th>작성자</th>';
                      str += '<th>댓글 내용</th>';
                      str += '<th>작성 시간</th>';
                      str += '</tr>';
                      str += '</thead>';
                      str += '<tbody id="reply'+board.boardnum+'">';
                      str += '<tr>';
                      str += '<td>' + lreply[0][i].userid + '</td>';
                      str += '<td>' + lreply[0][i].replycontents + '</th>';
                      str += '<td>' + lreply[0][i].regdate + '</td>';
                      str += '</tr>';
                      str += '</tbody>';
                      str += '</table>';
                      str += '<div class="reply_plus_div">';
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
              $("#loading").css("display","none");
              if(str === null | str === undefined | str === ""){
            	  if(firstlist){
            	  	
            	  }
            	  else {
              		$(".timeline").html("검색된 게시글이 없습니다.");  
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
          }
      });
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