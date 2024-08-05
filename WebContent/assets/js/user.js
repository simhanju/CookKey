const result = document.getElementById("result");
//비밀번호 유효성 검사를 위한 배열
let pwTest = [false,false,false,false,false]
const arCategory = [];

function sendit(){
    const joinForm = document.joinForm;

    const userid = joinForm.userid;
    if(userid.value == ""){
    	alert("아이디를 입력하세요!");
    	userid.focus();
    	return;
    }
    if(userid.value.length<5 || userid.value.length>12){
    	alert("아이디는 5자 이상 12자 이하로 입력하세요!");
    	userid.focus();
    	return;
    }
    
    if(result.innerHTML == ""){
    	alert("아이디 중복검사를 진행해주세요!");
    	userid.focus();
    	return;
    }
    if(result.innerHTML == "중복된 아이디가 있습니다!"){
    	alert("중복체크 통과 후 가입이 가능합니다!");
    	userid.focus();
    	return;
    }
    
    //아래쪽의 pwcheck() 함수를 통해 유효성 검사를 통과했다면 pwTest 배열에는 true값만 존재한다.
    //무언가 실패했다면 false가 포함되어 있으므로, 반복문을 통해 해당 배열을 보며 false값이 있는지 검사
    for(let i=0;i<5;i++){
    	if(!pwTest[i]){
    		alert("비밀번호 확인을 다시하세요!");
    		userpw.value="";
    		userpw.focus();
    		return;
    	}
    }
    const username = joinForm.username;
    const exp_name = /^[가-힣]+$/;
    if(username.value == "") {
    	alert("이름을 입력하세요!");
		username.focus();
		return false;
    }
    else {
    	if(!exp_name.test(username.value)){
    		alert("이름에는 한글만 입력하세요!");
    		username.focus();
    		return false;
    	}
    }
   
    const nickname = joinForm.nickname;
    if(nickname.value == ""){
    	alert("닉네임을 입력해 주세요!");
    	nickname.focus();
    	return;
    }
    
    const phone = joinForm.phone;
    const exp_phone = /^[0-9]*$/;
    if(phone.value == "") {
    	alert("핸드폰 번호를 입력해 주세요!");
		phone.focus();
		return;
    }
    else {
    	if(!exp_phone.test(phone.value)){
    		alert("핸드폰 번호에는 숫자만 입력해 주세요!");
    		phone.focus();
    		return;
    	}    	
    }
    
    const email = joinForm.email;
    const exp_email = /^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
    if(!exp_email.test(email.value)){
    	alert("이메일 형식으로 입력해 주세요! ex)aaaa@naver.com");
    	email.focus();
    	return;
    }
    const birth = joinForm.birth;
    const exp_birth = /^[0-9]*$/;
    if(birth.value == ""){
    	alert("생일을 입력해 주세요! ex)19990620");
    	birth.focus();
    	return;
    }
    else {
    	if(!exp_birth.test(birth.value)){
    		alert("생일 입력란엔 숫자만 입력해 주세요! ex)19990620");
    		phone.focus();
    		return;
    	} 
	}
    
    const usergender = joinForm.usergender;
    if(!usergender[0].checked && !usergender[1].checked){
    	alert("성별을 선택하세요!");
    	return;
    }
    const foreigner = joinForm.foreigner;
    if(!foreigner[0].checked && !foreigner[1].checked){
    	alert("국적을 선택하세요!");
    	return;
    }
    
    const zipcode = joinForm.zipcode;
    if(zipcode.value == ""){
        alert("주소찾기를 진행해 주세요!");
        findAddr();
        return;
    }
    const addrdetail = joinForm.addrdetail;
    if(addrdetail.value == ""){
        alert("상세주소를 입력해 주세요!");
        addrdetail.focus();
        return;
    }
    
    //유효성 검사
    flag = false;
	for(i=0; i < joinForm.usercategory.length; i++){
		if(joinForm.usercategory[i].checked){
			flag = true;
		}
	}
	
	if(flag == false){
		alert("카테고리를 선택해 주세요.");
		return false;
	}
    joinForm.submit();
}
function pwcheck(){
    const userpw = document.joinForm.userpw;
    const userpw_re = document.joinForm.userpw_re;
    //아래쪽에 있는 귀여운 span 태그들 가져오기
    const c = document.querySelectorAll(".pw_check span");
    //영어 대문자, 영어 소문자, 숫자, 특수문자를 한 글자씩 포함하는지 확인하는 정규식
    const reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[~?!@-]).{4,}$/;
    
    if(userpw.value == ""){
    	for(let i=0;i<5;i++){
    		pwTest[i] = false;
    		c[i].classList = "";
    	}
    	return;
    }
    if(!reg.test(userpw.value)){
    	c[0].classList = "pcf";
    	pwTest[0] = false;
    }
    else{
    	c[0].classList = "pct";
    	pwTest[0] = true;
    }
    if(userpw.value.length < 8){
    	c[1].classList = "pcf";
    	pwTest[1] = false;
    }
    else{
    	c[1].classList = "pct";
    	pwTest[1] = true;
    }
    //같은 문자가 네번 연속해서 있는지 검사하는 정규식
    if(/(\w)\1\1\1/.test(userpw.value)){
    	c[2].classList = "pcf";
    	pwTest[2] = false;
    }
    else{
    	c[2].classList = "pct";
    	pwTest[2] = true;
    }
    //[]안의 문자들로 문자열이 이루어져 있는지 검사하는 정규식
    if(!/^[a-zA-Z0-9~?!@-]*$/.test(userpw.value)){
    	c[3].classList = "pcf";
    	pwTest[3] = false;
    }
    else{
    	c[3].classList = "pct";
    	pwTest[3] = true;
    }
    if(userpw.value != userpw_re.value){
    	c[4].classList = "pcf";
    	pwTest[4] = false;
    }
    else{
    	c[4].classList = "pct";
    	pwTest[4] = true;
    }
}
function findAddr() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("addretc").value = extraAddr;
            
            } else {
                document.getElementById("addretc").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('zipcode').value = data.zonecode;
            document.getElementById("addr").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("addrdetail").focus();
        }
    }).open();
}
function addcategory(){
	const joinForm = document.joinForm;
	const category_list = document.getElementsByClassName("category_list")[0];
	const category = joinForm.category;
	
	if(category.value == ""){
		alert("카테고리를 입력해 주세요!");
		category.focus();
		return;
	}
	if(arCategory.indexOf(category.value) != -1){
		alert("중복된 카테고리입니다!");
		category.focus();
		category.value="";
		return;
	}
	if(arCategory.length == 5){
		alert("카테고리는 5개 이하로 입력해주세요!");
		return;
	}
	
	//span 태그 노드 생성
	const inputCategory = document.createElement("span");
	//span 태그 노드 클래스 값으로 usercategory
	inputCategory.classList = "usercategory";
	//span 태그 노드 name 값으로 usercategory
	inputCategory.name = "usercategory";
	//span 태그 노드 내부 내용으로 입력한 취미 문자열 설정
	inputCategory.innerHTML = category.value;
	//취미 목록 배열에 입력한 취미 문자열 추가
	arCategory.push(category.value);
	
	//a태그 노드 생성
	const xBox = document.createElement("a");
	//디자인을 위한 클래스 설정
	xBox.classList = "xBox";
	//위에서 만든 span 태그의 자식으로 xBox 추가
	inputCategory.appendChild(xBox);
	//추가되어 있는 span태그 클릭 시 카테고리를 지워주기 위한 이벤트 설정
	inputCategory.addEventListener('click',deleteCategory);
	//아래쪽의 카테고리 목록을 보여줄 div의 자식으로 span태그 추가
	category_list.appendChild(inputCategory);
	
	category.value = "";
	category.focus();
}
function categoryKeyup(){
	// 앤터를 눌렀을때
	if(window.event.keyCode == 13){
		addCategory();
	}
}
function deleteCategory(e){
	let deleteNode = null;
	if(e.target.classList == "xBox"){
		deleteNode = e.target.parentNode;
	}
	else{
		deleteNode = e.target;
	}
	
	let txt = deleteNode.innerText;
	console.log(txt);
	for(let i in arCategory){
		if(arCategory[i] == txt){
			arCategory.splice(i,1);
			break;
		}
	}
	
	deleteNode.remove();
}
function checkId(){
	const xhr = new XMLHttpRequest();
	const userid = document.joinForm.userid;
	if(userid.value == ""){
		alert("아이디를 입력하세요!");
		userid.focus();
		return;
	}
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4){
			if(xhr.status == 200){
				let txt = xhr.responseText.trim();
				if(txt == "O"){
					result.innerHTML = "사용할 수 있는 아이디입니다!";
					alert("사용할 수 있는 아이디입니다!");
					document.joinForm.userpw.focus();
				}
				else{
					result.innerHTML = "중복된 아이디가 있습니다!";
					alert("중복된 아이디가 있습니다!");
					userid.value = "";
					userid.focus();
				}
			}
		}
	}
	
	xhr.open("GET",cp+"/checkidok.us?userid="+userid.value);
	xhr.send();
}

let currentStep = 0; // 현재 단계 인덱스

function updateVisibility() {
    const rows = document.querySelectorAll('#wrap table tbody tr:not(:last-child)');
    const steps = [
        // "아이디"부터 "비밀번호 확인"까지. pw_check는 인덱스 계산에 포함되지 않으므로, 인덱스 조정 필요.
        Array.from(rows).slice(1, 5), // "아이디"부터 "비밀번호 확인"까지
        Array.from(rows).slice(5, 15), // "pw_check" div (비밀번호 확인 포함)부터 "참고항목"까지
        Array.from(rows).slice(15) // "카테고리"
    ];

    // 모든 행 숨기기
    rows.forEach(row => row.style.display = 'none');

    // 현재 단계의 행만 보이기
    steps[currentStep].forEach(row => row.style.display = '');

    // 버튼 상태 업데이트
    updateButtonState();
}

function updateButtonState() {
    // const backButton = document.querySelector('input[value="이전"]');
    // const nextButton = document.querySelector('input[value="다음"]');
    const completeButton = document.querySelector('input[value="가입 완료"]');

    // // "이전" 버튼 활성화/비활성화
    // backButton.disabled = currentStep === 0;

    // "다음" 버튼과 "가입 완료" 버튼 관리
    if (currentStep >= 2) {
        nextButton.style.display = '';
        completeButton.style.display = '';
    } else {
        nextButton.style.display = '';
        completeButton.style.display = 'none';
    }
}

function next() {
    if (currentStep < 2) {
        currentStep++;
        updateVisibility();
    }
}

function back() {
    if (currentStep > 0) {
        currentStep--;
        updateVisibility();
    }
}

document.addEventListener('DOMContentLoaded', function() {
    updateVisibility();
});