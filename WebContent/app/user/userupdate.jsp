<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="cp" value="${pageContext.request.contextPath}"
	scope="session" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${cp}/assets/css/userupdate.css">
<title>개인정보 수정하기</title>
</head>
<body>
	<%@ include file = "/app/header.jsp" %>
	
	<div class="update_div">
		<form action="${cp}/userupdateok.us" method="post" name="updatepwForm" class="updatepwForm" enctype="multipart/form-data">
			<!-- 비밀번호 수정 -->
			<div>
				<label>현재 비밀번호</label>
				<div>
					<input type="password" id="now_pw">
					<input type="button" value="입력" onclick="nowpwcheck('${user.userpw}')">
				</div>
			</div>
			<div>
               <label for="userpw">새 비밀번호</label>
               <input type="password" name="userpw" id="userpw" onkeyup="pwcheck()">
			</div>
            <div>
               <label for="userpw_re">새 비밀번호 확인</label>
               <div>
               		<input type="password" name="userpw_re" id="userpw_re" onkeyup="pwcheck()">
               		<input type="button" value="비밀번호 수정" onclick="pw_sendit()">
	           </div>
            </div>
			<div class="pw_check">
				<span>현재 비밀번호를 확인해주세요</span>
				<span>영어 대문자, 소문자, 숫자, 특수문자(~,?,!,@,-)를 모두 하나 이상 포함해야 해요 😀</span> 
				<span>최소 8자 이상의 비밀번호가 보안에 안전해요 😄</span> 
				<span>같은 문자가 연속해서 사용되지 않았어요 😆</span>
				<span>사용할 수 없는 문자가 포함되지 않았어요 😊</span>
				<span>비밀번호 확인이 완료되었어요! 🤗</span>
			</div>
		</form>
		<form action="${cp}/userupdateok.us" method="post" name="updateInfoForm" class="updateInfoForm" enctype="multipart/form-data">
			<!-- 개인정보 수정 -->
			<div>
				<div>
					<input type="file" name="file" id="file" style="display:none">
					<input type="hidden" id="userimgname" name="userimgname" style="displey:none">
					<a class="btn" href="javascript:upload()"><img id="img" src="${cp}/file/${userimg}"></a>
				</div>
				<a class="btn" href="javascript:deleteFile()">프로필사진 삭제</a>
			</div>
			<div>
				<label>아이디</label>
				<input type="text" name="userid" value="${user.userid}" readonly>
			</div>
			<div>
				<label>이름</label>
				<input type="text" name="username" value="${user.username}">
			</div>
			<div>
				<label>닉네임</label>
				<input type="text" name="nickname" value="${user.nickname}">
			</div>
			<div>
				<label>전화번호</label>
				<input type="text" name="phone" value="${user.phone}">
			</div>
			<div>
				<label>이메일</label>
				<input type="text" name="email" value="${user.email}">
			</div>
			<input type="button" value="개인정보 수정" onclick="sendit()">
		</form>
	</div>
	<!-- <input type="button" value="뒤로가기" onclick="confirmNavigateToList()"> -->
</body>

<script src="https://kit.fontawesome.com/220c29f5e3.js" crossorigin="anonymous"></script>
<script src="${cp}/assets/js/browser.min.js"></script>
<script src="${cp}/assets/js/jquery.min.js"></script>
<script src="${cp}/assets/js/breakpoints.min.js"></script>
<script src="${cp}/assets/js/util.js"></script>
<script src="${cp}/assets/js/main.js"></script>
<script src="${cp}/assets/js/userupdate.js"></script>
<script>
    function confirmNavigateToList() {
        const confirmed = confirm("목록으로 돌아가시겠습니까?");
        if (confirmed) {
            // 확인 버튼을 누른 경우 storelist 페이지로 이동
            window.location.href = "${cp}/app/user/userpage.us?userid=${user.userid}";
        }
        // 취소 버튼을 누른 경우 아무런 동작 없음
    }



    function upload(){
        $("#file").click();
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
            //업로드 된 파일의 확장자명
            let ext = file.name.split(".").pop();
            if(ext == "jpeg" || ext == "png" || ext == "jpg" || ext == "webp" || ext == "gif"){
                //". file0 _cont"
                console.log(file.name);
                $("."+fileTag.id+"_cont .thumbnail").remove();
                const reader = new FileReader();
                reader.onload = function(ie){
                    const img = document.getElementById("img");
                    img.setAttribute("src",ie.target.result);
                    img.setAttribute("class","thumbnail");
                    const name = document.getElementById("userimgname");
                    name.setAttribute("value", file.name);
                }
                reader.readAsDataURL(file);
            }
            else{
                $("."+fileTag.id+"_cont .thumbnail").remove();
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
            console.log(j);
            const el = $("#storeForm tbody .r"+j);
            el.attr("class","r"+(j-1));

            el.find("th").text("파일 첨부"+j);
            el.find("td").attr("class","file"+(j-1)+"_cont");

            el.find("input[type=file]").attr("name","file"+(j-1));
            el.find("input[type=file]").attr("id","file"+(j-1));

            el.find("span").attr("id","file"+(j-1)+"name");

            el.find("a")[0].href = "javascript:upload("+(j-1)+")";
            el.find("a")[1].href = "javascript:cancelFile("+(j-1)+")";
        }
        i--;
    }
    function deleteFile(num){
        const img = document.getElementById("img");
        img.setAttribute("src", "${cp}/file/no_profil_user_img.png")
        const name = document.getElementById("file");
        name.setAttribute("name", "delete")
        name.setAttribute("value", "delete")
    }
</script>
</html>