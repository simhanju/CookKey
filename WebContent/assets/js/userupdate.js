const result = document.getElementById("result");
//비밀번호 유효성 검사를 위한 배열
let pwTest = [false,false,false,false,false,false]
const arCategory = [];

function pw_sendit(){
    const updatepwForm = document.updatepwForm;

    //아래쪽의 pwcheck() 함수를 통해 유효성 검사를 통과했다면 pwTest 배열에는 true값만 존재한다.
    //무언가 실패했다면 false가 포함되어 있으므로, 반복문을 통해 해당 배열을 보며 false값이 있는지 검사
    for(let i=0;i<6;i++){
    	if(!pwTest[i]){
    		alert("비밀번호 확인을 다시하세요!");
    		userpw.value="";
    		userpw.focus();
    		return;
    	}
    }

    updatepwForm.submit();
}

function sendit(){
    const updateInfoForm = document.updateInfoForm;

    const username = updateInfoForm.username;
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
   
    const nickname = updateInfoForm.nickname;
    if(nickname.value == ""){
    	alert("닉네임을 입력해 주세요!");
    	nickname.focus();
    	return;
    }

    updateInfoForm.submit();
}

function nowpwcheck(userpw) {
	const now_pw = document.updatepwForm.now_pw.value;
	console.log(now_pw);
	if (userpw==now_pw) {
		pwTest[0]=true;
		alert("비밀번호 확인 완료");
	}
	else {
		pwTest[0]=false;
		alert("현재 비밀번호를 확인해 주세요!");
	}
}
function pwcheck(){
    const userpw = document.updatepwForm.userpw;
    const userpw_re = document.updatepwForm.userpw_re;
    //아래쪽에 있는 귀여운 span 태그들 가져오기
    const c = document.querySelectorAll(".pw_check span");
    //영어 대문자, 영어 소문자, 숫자, 특수문자를 한 글자씩 포함하는지 확인하는 정규식
    const reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[~?!@-]).{4,}$/;
    
    if(userpw.value == ""){
    	for(let i=1;i<6;i++){
    		pwTest[i] = false;
    		c[i].classList = "";
    	}
    	return;
    }
    if(pwTest[0]!=true) {
    	c[0].classList = "pcf"
    }
    else {
    	c[0].classList = "pct"
    }
    if(!reg.test(userpw.value)){
    	c[1].classList = "pcf";
    	pwTest[1] = false;
    }
    else{
    	c[1].classList = "pct";
    	pwTest[1] = true;
    }
    if(userpw.value.length < 8){
    	c[2].classList = "pcf";
    	pwTest[2] = false;
    }
    else{
    	c[2].classList = "pct";
    	pwTest[2] = true;
    }
    //같은 문자가 네번 연속해서 있는지 검사하는 정규식
    if(/(\w)\1\1\1/.test(userpw.value)){
    	c[3].classList = "pcf";
    	pwTest[3] = false;
    }
    else{
    	c[3].classList = "pct";
    	pwTest[3] = true;
    }
    //[]안의 문자들로 문자열이 이루어져 있는지 검사하는 정규식
    if(!/^[a-zA-Z0-9~?!@-]*$/.test(userpw.value)){
    	c[4].classList = "pcf";
    	pwTest[4] = false;
    }
    else{
    	c[4].classList = "pct";
    	pwTest[4] = true;
    }
    if(userpw.value != userpw_re.value){
    	c[5].classList = "pcf";
    	pwTest[5] = false;
    }
    else{
    	c[5].classList = "pct";
    	pwTest[5] = true;
    }
}

