<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
   <head>
      <title>글쓰기</title>
      <meta charset="utf-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
      <link rel="stylesheet" href="${cp}/assets/css/board.css" />
      <link rel="stylesheet" href="${cp}/assets/css/loading.css" />
   </head>
   <body class="is-preload">
   <c:if test="${empty loginUser }">
		<script>
			alert("로그인 후 이용하세요!");
			location.replace("${cp}/");
		</script>
	</c:if>
   <div id="loading" class="loading">
   	<div id="loading_img" class="img"></div>
   	잠시만 기다려주세요...
   </div>
   		<div id="wrapper">
   			<div id="main">
           		<div class="inner">
                	<%@ include file = "/app/header.jsp" %>
	                    <section>
	                        <form action="${cp}/boardwriteok.bo" id="boardForm" name="boardForm" method="post" enctype="multipart/form-data">
	                            <div class="newBoard_div">
	                                <div class="up_div">
	                                    <input type="button" value="작성 취소" >
	                                    <h3>새로운 게시물 만들기</h3>
	                                    <input type="submit" value="작성" >
	                                </div>
	                                <div class="down_div">
	                                    <div class="picfure_div">
	                                        <div class="picfure">
	                                            <div class="r0">
	                                                <div id="picfure_img" class="file0_cont">
	                                                    <input type="file" name="file0" id="file0" style="display:none">
	                                                    <span id="file0name" style="display: none;">선택된 파일 없음</span>
	                                                </div>
	                                                <div class="button_div">
	                                                    <a href="javascript:upload(0)" class="fa-solid fa-pen-to-square"></a>
	                                                    <a href="javascript:cancelFile(0)" class="fa-solid fa-trash"></a>
	                                                </div>
	                                            </div>
	                                        </div>
	                                        <div>
	                                            <input class="plus" type="button" value=" 사진 추가" onclick="javascript:upload(0)">
	                                        </div>
	                                    </div>
	                                    <div class="writing_div">
	                                        <div>
	                                            <img class="writer_profile" src="">
	                                            <span id="userid">${loginUser}</span>
	                                        </div>
	                                        <textarea placeholder="글을 작성해주세요." name="boardcontents"></textarea>
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
            <script>
                // document.addEventListener('DOMContentLoaded', function() {
                //     var categoryButton = document.getElementById('category_button');
                //     var dropdownMenu = document.querySelector('.category_dropdown_menu');
            
                //     categoryButton.addEventListener('click', function() {
                //         if (dropdownMenu.style.display === 'grid') {
                //             dropdownMenu.style.display = 'none';
                //         } else {
                //             dropdownMenu.style.display = 'grid';
                //         }
                //     });
                // });
            </script>
            <script>
                //현재 업로드된 파일의 개수(가장 마지막 행 번호)
                let i = 0;
                function sendit(){
                    const boardForm = document.boardForm;
                    //유효성 검사
                    boardForm.submit();
                }
                function upload(num){
                    $("#file"+num).click();
                }
                //$(선택자).change(함수) : 해당 선택자의 요소에 변화가 일어난다면 넘겨주는 함수 호출
                $("[type=file]").change(function(e){
                    //e : 파일이 업로드된 상황 자체를 담고있는 이벤트 객체
                    //e.target : 파일이 업로드가 된 input[type=file] 객체(태그객체)
                    const fileTag = e.target;
                    console.log(fileTag);
                    //e.target.files : 파일태그에 업로드가 된 파일들의 배열
                    const file = fileTag.files[0];
                    console.log(file);
                    
                    if(file == undefined){
                        //업로드 창을 띄웠다가 취소한 경우(파일이 업로드 되었다가 없어진 경우)
                        cancelFile(fileTag.id.split("e").pop());
                    }
                    else{
                        //파일을 업로드를 한 경우(없었다가 업로드, 있었는데 다른 파일로 업로드)
                        //#file0name 찾아서 내부 텍스트 변경(파일의 이름으로)
                        $("#"+fileTag.id+"name").text(file.name);
                        //업로드 된 파일의 확장자명         
                        let ext = file.name.split(".").pop();
                        if(ext == "jpeg" || ext == "png" || ext == "jpg" || ext == "webp" || ext == "gif"){
                            //".  file0       _cont"
                            $("."+fileTag.id+"_cont .thumbnail").remove();
                            const reader = new FileReader();
                            reader.onload = function(ie){
                                // 이미지 태그 새로 만들어서 넣기
                                const img = document.createElement("img");
                                img.setAttribute("src",ie.target.result);
                                img.setAttribute("class","thumbnail");
                                document.querySelector("."+fileTag.id+"_cont").appendChild(img);

                                // // 수정버튼 만들어서 넣기
                                // const 수정버튼 = document.createElement("a");
                                // 수정버튼.setAttribute("href", "javascript:upload("+(i-1)+")");
                                // 수정버튼.setAttribute("class", "fa-solid fa-pen-to-square");
                                // document.querySelector(".button_div").appendChild(수정버튼);

                                // // 삭제버튼 만들어서 넣기
                                // const 삭제버튼 = document.createElement("a");
                                // 삭제버튼.setAttribute("href", "javascript:cancelFile("+(i-1)+")");
                                // 삭제버튼.setAttribute("class", "fa-solid fa-trash");
                                // document.querySelector(".button_div").appendChild(삭제버튼);
                            }
                            reader.readAsDataURL(file);
                        }
                        else{
                            $("."+fileTag.id+"_cont .thumbnail").remove();
                        }
                        
                        if(fileTag.id == "file"+i){
                            const cloneElement = $(".r"+i).clone(true);
                            i++;
                            cloneElement.attr("class","r"+i);
                            cloneElement.children("div").attr("class","file"+i+"_cont");
                            
                            cloneElement.find("input[type='file']").attr("name","file"+i);
                            cloneElement.find("input[type='file']").attr("id","file"+i);
                            cloneElement.find("input[type='file']").val("");
                            
                            cloneElement.find("span").attr("id","file"+i+"name");
                            cloneElement.find("span").text("선택된 파일 없음");
                            
                            //                          javascript:upload(  1  )
                            cloneElement.find("a")[0].href = "javascript:upload("+i+")";
                            //                          javascript:cancelFile(  1  )
                            cloneElement.find("a")[1].href = "javascript:cancelFile("+i+")";
                            // jQuery객체.appendTo("부모선택자") : 해당 선택자의 자식으로 jQuery 객체 추가
                            cloneElement.appendTo("#boardForm .newBoard_div .down_div .picfure_div .picfure")

                            const 사진추가 = $(".plus");
                            사진추가.attr("onclick","javascript:upload("+i+")") ;
                        }
                    }
                })
                function cancelFile(num){
                    //파일 업로드 했다가 취소로 파일을 삭제하는 경우에는 문자열로 넘어온다.
                    num = Number(num);
                    //가장 마지막 [첨부 삭제] 버튼을 누른 경우
                    if(num == i){ return; }
                    //tr 지우기
                    $(".r"+num).remove();
                    //지워진 다음 행 부터 숫자 바꿔주기
                    for(let j=num+1;j<=i;j++){
                        const el = $("#boardForm .newBoard_div .down_div .picfure_div .picfure .r"+j);
                        el.attr("class","r"+(j-1));
                        
                        el.find("div").attr("class","file"+(j-1)+"_cont");
                        
                        el.find("input[type=file]").attr("name","file"+(j-1));
                        el.find("input[type=file]").attr("id","file"+(j-1));
                        
                        el.find("span").attr("id","file"+(j-1)+"name");
                        
                        el.find("a")[0].href = "javascript:upload("+(j-1)+")";
                        el.find("a")[1].href = "javascript:cancelFile("+(j-1)+")";

                        const 사진추가 = $(".plus");
                        사진추가.attr("onclick","javascript:upload("+(j-1)+")") ;
                    }
                    i--;
                }
            </script>
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
               const userfile = obj.userfile;
               let str = "";
               let i = 0;
               for (let category of categories) {
                  str += "<div>";
                  str += '<input type="checkbox" id="'+ category.categorynum +'"name="categorynum" value="'+ category.categorynum +'">'
                  str += '<label for="'+ category.categorynum +'">'+ category.categoryname +'</label>'
                  str += "</div>";
               }
               $(".category_dropdown_menu").html(str);
               if (userfile[0].length === 0) {
              	    $(".writer_profile").attr("src","${cp}/file/no_profil_user_img.png");
              	} else {
              	    // 사용자 파일이 존재하는 경우 이미지와 버튼을 함께 출력
              	   const userImage = decodeURI(userfile[0][0].systemname);

              	    $(".writer_profile").attr("src","${cp}/file/"+userImage);
              	}
           },
           complete: function () {$("#loading").css("display","none");}
       });
   }

</script>
</html>