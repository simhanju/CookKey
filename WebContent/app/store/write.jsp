<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>write</title>
    <link href="${cp}/assets/css/write1.css" rel="stylesheet">
    
</head>
<body class="is-preload">
    <!-- Wrapper -->
    <div id="wrapper">
        <!-- Main -->
        <div id="main">
            <div class="inner">
                <!-- Header -->
								<%@ include file = "/app/header.jsp" %>
                <!-- 타이틀 띄워주는 테이블 -->
                <form id="storeForm" method="post" name="storeForm" action="${cp}/storewriteok.st" enctype="multipart/form-data">
                    <table border="1" id="tb">
                        <tr>
                            <th>카테고리</th>
                            <td>
                                <input type="checkbox" name="categorynum" value=500>조리도구
                                <input type="checkbox" name="categorynum" value=501>음식재료
                                <input type="checkbox" name="categorynum" value=502>양념
                                <input type="checkbox" name="categorynum" value=503>향신료
                                <input type="checkbox" name="categorynum" value=504>조미료
                                <input type="checkbox" name="categorynum" value=5000>기타
                            </td>
                        </tr>
                        <tr>
                            <th>제목</th>
                            <td>
                                <input type="text" name="storetitle" maxlength="50" placeholder="제목을 입력하세요">
                            </td>
                        </tr>
                        <tr>
                            <th>금액</th>
                            <td>
                                <input type="text" name="price" maxlength="50" placeholder="금액을 입력하세요" >
                            </td>
                        </tr>
                        <tr>
                            <th>작성자</th>
                            <td>
                                <input type="text" name="userid" maxlength="50" value="${loginUser}" readonly>
                            </td>
                        </tr>
                        <tr>
                            <th>내용</th>
                            <td>
                                <textarea name="storecontents"></textarea>
                            </td>
                        </tr>
                        <tr id="r" class="r0">
                            <th>파일 첨부1</th>
                            <td class="file0_cont" id="fileBox">
                                <div>
                                    <input type="file" name="file0" id="file0" style="display:none">
                                    <span id="file0name">선택된 파일 없음</span>
                                </div>
                                <div>
                                    <a class="btn" href="javascript:upload(0)">파일 선택</a>
                                    <a class="btn" href="javascript:cancelFile(0)">첨부 삭제</a>
                                </div>
                            </td>
                        </tr>
                    </table>
                </form>
                <table class="btn_area">
                    <tbody>
                        <tr>
                            <td>
                                <a class="btn" href="javascript:sendit();">등록</a>
                                <a class="btn" href="#" onclick="confirmNavigateToList();">목록</a>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- Sidebar -->
		<%@ include file = "/app/sidebar.jsp" %>
    </div>
</body>
<script src="https://kit.fontawesome.com/220c29f5e3.js" crossorigin="anonymous"></script>
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/browser.min.js"></script>
<script src="assets/js/breakpoints.min.js"></script>
<script src="assets/js/util.js"></script>
<script src="assets/js/main.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

<script>

    function confirmNavigateToList() {
        const confirmed = confirm("목록으로 돌아가시겠습니까?");
        if (confirmed) {
            // 확인 버튼을 누른 경우 storelist 페이지로 이동
            window.location.href = "${cp}/storelist.st?page=${param.page}&keyword=${param.keyword}";
        }
        // 취소 버튼을 누른 경우 아무런 동작 없음
    }

    window.setTimeout(function(){
        document.querySelector("#wrap>div:nth-child(1)").style.display="none";
    },1200)

    //현재 업로드된 파일의 개수(가장 마지막 행 번호)
    let i = 0;
    function sendit(){
        const storeForm = document.storeForm;
        //유효성 검사
        flag = false;
		for(i=0; i < storeForm.categorynum.length; i++){
			if(storeForm.categorynum[i].checked){
				flag = true;
			}
		}
		
		if(flag == false){
			alert("카테고리를 선택해 주세요.");
			return false;
		}
        
        storeForm.submit();
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
                //". file0 _cont"
                $("."+fileTag.id+"_cont .thumbnail").remove();
                const reader = new FileReader();
                reader.onload = function(ie){
                    const img = document.createElement("img");
                    img.setAttribute("src",ie.target.result);
                    img.setAttribute("class","thumbnail");
                    document.querySelector("."+fileTag.id+"_cont").appendChild(img);
                }
                reader.readAsDataURL(file);
            }
            else{
                $("."+fileTag.id+"_cont .thumbnail").remove();
            }

            //가장 마지막 [파일 선택] 버튼을 눌렀을 때
            if(fileTag.id == "file"+i){
                const cloneElement = $(".r"+i).clone(true);
                i++;
                cloneElement.attr("class","r"+i);
                cloneElement.children("th").text("파일 첨부"+(i+1));
                cloneElement.children("td").attr("class","file"+i+"_cont");

                cloneElement.find("input[type='file']").attr("name","file"+i);
                cloneElement.find("input[type='file']").attr("id","file"+i);
                cloneElement.find("input[type='file']").val("");

                cloneElement.find("span").attr("id","file"+i+"name");
                cloneElement.find("span").text("선택된 파일 없음");

                // javascript:upload( 1 )
                cloneElement.find("a")[0].href = "javascript:upload("+i+")";
                // javascript:cancelFile( 1 )
                cloneElement.find("a")[1].href = "javascript:cancelFile("+i+")";

                // jQuery객체.appendTo("부모선택자") : 해당 선택자의 자식으로 jQuery 객체 추가
                cloneElement.appendTo("#storeForm tbody")

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
</script>
</html>
