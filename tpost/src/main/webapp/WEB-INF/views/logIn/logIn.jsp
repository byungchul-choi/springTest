<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- CSS  STYLE -->
	<link rel="stylesheet" href="/tpost/resource/css/common.css">
	<!-- ---------- -->
	<link rel="stylesheet" href="/tpost/resource/css/login.css">
	
	<!--     JS     -->
	<script src="/tpost/resource/js/jquery.min.js"></script>
	<script src="/tpost/resource/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="/tpost/resource/js/comm.js"></script>
	
	
<title>로그인</title>

<script type="text/javascript">


function onload(){
	
	var isInIframe = (window.location != window.parent.location);
	if(isInIframe == true){
		top.location.href = "/tpost/common/menuMainTop-select"
	}else{
	}
	
	/*초기화 팝업 설정*/
	fn_init();
}

/*제이쿼리 초기화*/
jQuery(document).ready(function () {
	// 팝업닫기
	jQuery(".close, .state-submit").click(function () {
		jQuery(".layerBox").hide();
	});
	commCd_bas('loginpwinit1 #mvTscoClcd', '00008');
});

/****코드조회 공통함수 ***/
function callBack(sel_id, codeNum ){
	if(sel_id == '#loginpwinit1 #mvTscoClcd'){
		commCd_bas('loginauth1 #mvTscoClcd', '00008');
	}
}

/* capsLock 확인*/
 function capsLock(e){
	
	  var keyCode = 0;
	  var shiftKey=false;
	  keyCode=e.keyCode;
	  shiftKey=e.shiftKey;
	  if (((keyCode >= 65 && keyCode <= 90)&& !shiftKey) || ((keyCode >= 97 && keyCode <= 122) && shiftKey))
	  {
	    $('#capsLock').html("CapsLock 확인 바랍니다");
	    //.html(min + ':' + sec );
	    
	  }else{
	  	$('#capsLock').html("");
	  }
	  
	  if(window.event.keyCode == 13){
			fn_ActionLogIn();
		}
	  
	}


/*초기화 변수및 팝업설정*/
function fn_init(){
	
}

/*로그인함수 */
function fn_ActionLogIn(){
	
	var menuForm = document.getElementById("loginForm");
    menuForm.method = "post";
    menuForm.action="/tpost/logIn/logInAction" ;
 	menuForm.target = "page"  ;
 	menuForm.submit();
	
}


/*아이비 비밀번호 성공*/
function  logInauth (){
	var parameters = "custId=" + $('#custId').val() ; 
	jQuery("#" + "loginauth").show();
	loginauth_init();
	jQuery("#loginauth #custId" ).val($("#loginForm #custId").val());
}

function  pwLastChgOver (){
	alert("비밀번호를 변경한지 3개월이 지났습니다. 비밀번호를 초기화하여주시기 바랍니다.");
	jQuery("#loginpwinit #custId" ).val($("#loginForm #custId").val());
	loginpwinit_init();
	jQuery("#" + "loginpwinit").show();
}

function  pwFiveFailTimeOver (){
	alert("비밀번호를 5회 이상 틀렸습니다. 비밀번호를 초기화 하여주시기 바랍니다.");
	loginpwinit_init();
	jQuery("#" + "loginpwinit").show();
}
function  login_Init(){
	var parameters = "custId=" + $('#custId').val() ; 
	jQuery("#" + "loginpw").show();
	
	jQuery("#" + "loginpw1 #passWord").val("");
	jQuery("#" + "loginpw1 #newPassWord").val("");
	jQuery("#" + "loginpw1 #CfmPassWord").val("");
	
	jQuery("#loginpw1 #custId" ).val($("#loginForm #custId").val());
}




/*loginauth_init*/
function loginauth_init(){
	clearInterval(myVar); 
	$('#loginpwinit #timer').html("0" + ':' + "00" );
    $('#loginauth1 #timer').html("0" + ':' + "00" );
     
	jQuery("#loginauth #tel" ).val("");
	jQuery("#loginauth #celpNum1" ).val("");
	jQuery("#loginauth #celpNum" ).val("");
	jQuery("#loginauth #ctifNo" ).val("");
	
}


/*비밀번호 초기화 팝업 _init*/
function loginpwinit_init(){
	clearInterval(myVar); 
	$('#loginpwinit #timer').html("0" + ':' + "00" );
    $('#loginauth1 #timer').html("0" + ':' + "00" );
    
	jQuery("#loginpwinit #custId" ).val("");
	jQuery("#loginpwinit #celpNum1" ).val("");
	jQuery("#loginpwinit #celpNum" ).val("");
	jQuery("#loginpwinit #ctifNo" ).val("");
	jQuery("#loginpwinit #newPassWord" ).val("");
	jQuery("#loginpwinit #CfmPassWord" ).val("");
	
	
}

function fn_ActionLogOut(){
	var parameters = $("#frm").serialize();
	$.ajax({
		type:"post", 
		dataType:"json",
		async:false,
		url:'/logIn/actionLogout.ajax',
		data:parameters ,
		success:function(data){
			if(data.result == "N" ) {
				alert(data.errorMessage);
			}else{
				alert("로그인아웃 성공");
			}
		}
	});
}

function hrefLink(){
	location.href = "/tpost/common/menuMainTop-select";
} 



/*비밀 번호 초기화 팝업*/
function pwInit_popUp(){
	
	loginpwinit_init(); /*비밀번호 초기화 팝업 초기화*/
	jQuery("#" + "loginpwinit").show();
//	window.open("/tpost/logIn/pwInitPopu", "PopupWin", "width=500px,height=600px");
}


/*엔터키시 조회*/
function enterSerch(){
	if(window.event.keyCode == 13){
		fn_ActionLogIn();
	}
}


/**************************************************************************************************************/
/*핸드폰인증*/ 
/*인증번호 요청에 대한 화면 타임머 스크립트*/
var num = 0; // 몇분을 설정할지의 대한 변수 선언
var myVar;


function time1(){
    myVar = setInterval(alertFunc, 1000); 
}

function time()
{
	num = 60 * 3 ;
	clearInterval(myVar); 
	time1();
}


function alertFunc() {
    var min = num / 60; 
    min = Math.floor(min);
    var sec = num - (60 * min);
    var $input = $('#timer').html(min + ':' + sec );
        $input = $('#loginpwinit #timer').html(min + ':' + sec );
        $input = $('#loginauth1 #timer').html(min + ':' + sec );
    
    if(num <= 0){
        clearInterval(myVar); // num 이 0초가 되었을대 clearInterval로 타이머 종료
        num = 0;
    }else{
    	num--;
    }
    
}


/*****************************************************************/
/*인증번호 요청*/
 function factorAuth_req(frm){
	//factorAuth_req('loginauth1');
	var menuForm = document.getElementById(frm); 
	var frm = '#'+frm;
	 var celpNum1 =   $(frm+' #mvTscoKind').val() + $(frm+' #celpNum1').val(); 
		
		if( frm ==  '#loginpwinit1' && reqValCheck('loginpwinit1 #custId' , 'ID') ){
			return;
		}
		
	 var celpNum1 =   $(frm+' #mvTscoKind').val() + $(frm+' #celpNum1').val(); 
	 $(frm +' #celpNum').val(celpNum1);
	 
	 
	    menuForm.method = "post";
	    menuForm.action="/tpost/logIn/factorAuth_req" ;
	 	menuForm.target = "page" ;
	 	menuForm.submit();
	    
}

function factorAuth_req_ret(ctifNo){
    $('#loginauth1 #ctifNo').val(ctifNo);
	$('#loginpwinit1 #ctifNo').val(ctifNo);
	time();
}


function factorAuth_confirm(){
	if(num <= 0 ){
		alert("인증요청 시간이 지났습니다. 다시 요청해주시기 바랍니다.");
		return;	
	}
	
	var menuForm = document.getElementById('loginauth1'); 
	    menuForm.method = "post";
	    menuForm.action="/tpost/logIn/factorAuth_confirm" ;
	 	menuForm.target = "page" ;
	 	menuForm.submit();
	
}

function factorAuth_confirm_ret(){
	
	jQuery(".layerBox").hide();
	location.href = "/tpost/common/menuMainIndex-select";
}


/*인증취소*/
function factorAuth_cancel(){
	
	jQuery(".layerBox").hide();
}

/***************************************************************************************************************/
/***패스워드 비밀번호 초기화 스크립트******************/
 
/*패스워드 변경하기*/
 
 function passWordChg(){
	jQuery(".layerBox").hide();
}


function fn_Change (frm){
	var menuForm = document.getElementById(frm); 
	
	if(frm == 'loginpwinit1'){
		if(num <= 0 ){
			alert("인증요청 시간이 지났습니다. 다시 요청해주시기 바랍니다.");
			return;	
		}
		url =  'passWordChg_init';
		
	}else{
		url =  'passWordChg';
		
	}
	    menuForm.method = "post";
	    menuForm.action = url ;
	 	menuForm.target = "page"; 
	 	menuForm.submit();
}


/*비밀번호 정합성 체크 */
function fn_pw_check(frm) {

 	var pw_passed = true;  // 추후 벨리데이션 처리시에 해당 인자값 확인을 위해
	var id =   $(frm+' #custId').val()  ;				// 아이디
	var pw =   $(frm+' #newPassWord').val() ;			//비밀번호	
	var pw2 =   $(frm+' #CfmPassWord').val() ;			// 확인 비밀번호
	
	
	pw_passed = true;

	var pattern1 = /[0-9]/;
	var pattern2 = /[a-zA-Z]/;
	var pattern3 = /[~!@\#$%<>^&*]/;     // 원하는 특수문자 추가 제거
	var pw_msg = "";

	if(id.length == 0) {
		alert("아이디를 입력해주세요");
		return false;
	} else {                //필요에따라 아이디 벨리데이션
	}

	if(pw.length == 0) {
		alert("비밀번호를 입력해주세요");
		return false;
	} else {
		if( pw  !=  pw2) {
			alert("비밀번호가 일치하지 않습니다.");
			return false;
		}
   }


	if(!pattern1.test(pw)||!pattern2.test(pw)||!pattern3.test(pw)||pw.length<9||pw.length>50){
		alert("영문+숫자+특수기호 9자리 이상으로 구성하여야 합니다.");
		return false;

	}          

	if(pw.indexOf(id) > -1) {
		alert("비밀번호는 ID를 포함할 수 없습니다.");
		return false;
	}

	var SamePass_0 = 0; //동일문자 카운트
	var SamePass_1 = 0; //연속성(+) 카운드
	var SamePass_2 = 0; //연속성(-) 카운드

	for(var i=0; i < pw.length; i++) {
		var chr_pass_0;
		var chr_pass_1;
		var chr_pass_2;

		if(i >= 2) {
			chr_pass_0 = pw.charCodeAt(i-2);
			chr_pass_1 = pw.charCodeAt(i-1);
			chr_pass_2 = pw.charCodeAt(i);

			if((chr_pass_0 == chr_pass_1) && (chr_pass_1 == chr_pass_2)) {
				SamePass_0++;
			}else {
				SamePass_0 = 0;
			}

			//연속성(+) 카운드
			if(chr_pass_0 - chr_pass_1 == 1 && chr_pass_1 - chr_pass_2 == 1) {
				SamePass_1++;
            }
			else {
				SamePass_1 = 0;
			}

			//연속성(-) 카운드
			if(chr_pass_0 - chr_pass_1 == -1 && chr_pass_1 - chr_pass_2 == -1) {
				SamePass_2++;
			}else {
				SamePass_2 = 0;
            }  
		}     

		if(SamePass_0 > 0) {
			alert("동일문자를 3자 이상 연속 입력할 수 없습니다.");
			pw_passed=false;
		}

 		if(SamePass_1 > 0 || SamePass_2 > 0 ) {
            alert("영문, 숫자는 3자 이상 연속 입력할 수 없습니다.");
            pw_passed=false;
 		} 

 		if(!pw_passed) {             
 			return false;
 			break;
 		}
	}
	
	return true;
 }
/********************************************************************************************************************/

</script>
</head>

<form id="menu" name="menu"> </form>
<iframe id="page" name="page" src=""></iframe>

<body onload="onload();">


    <div class="loginWrap">
        <form class="loginForm" name="loginForm" id="loginForm">
        	<fieldset>
        		<legend>로그인</legend>
                <p class="inputBox">
                    <label>USERID</label>
                    <input type="text" name="custId" id="custId" placeholder="USER ID">
                </p>
                <p class="inputBox">
                    <label>패스워드</label>
                    <input type="password" name="passWord" id="passWord" placeholder="PASSWORD" onkeypress="capsLock(event);" onKeyDown="enterSerch();" >
                </p>
				<button type="button" class="login" onclick="fn_ActionLogIn();" >로그인</button>
				
				<ul>
					<li>
<!-- 					    <input type="checkbox" id="saveId" name="saveId"> -->
					    <label for="saveId"></label><label for="saveId">
<!-- 					     아이디 저장 -->
					     </label>
					     <span id="capsLock" name="capsLock" ></span>
					</li>
					<li><button type="button" class="initPw" onclick="pwInit_popUp();">비밀번호 초기화</button></li>
				</ul>
				
            </fieldset>
        </form>
		<ul class="guide">
			<li>접속 장애시 연락처 : 02-1234-5678</li>
			<li>최초 사용자 비밀번호는 ID와 동일하며 로그인후 즉시 비밀번호 변경을 해야 합니다.</li>
			<li>비밀번호 분실 시 비밀번호 초기화를 요청할 수 있으며 초기화가 완료되면 비밀번호는 ID로 변경됩니다. 초기화 후에는 반드시 비밀번호 변경을 하셔야 합니다.</li>
		</ul>
	</div>
    
    <!-- layerBox pwinit 비밀번호 초기화 -->
	<div id="loginpwinit" class="layerBox pwchange" style="display:none;">
	<form name="loginpwinit1" id="loginpwinit1">
		<div class="layer">
			<div class="topBox">
				<h3>비밀번호 초기화</h3>
				<a class="close" href="#">&#10005;</a>
			</div>
			<div class="contBox">
				<div class="section">
					<!-- table-pnl -->
					<div class="table-pnl">
						<!-- table -->
						<table>
							<colgroup>
								<col width="170px">
								<col width="*">
							</colgroup>
							<tr>
								<th>로그인 ID</th>
								<td><input type="text" name="custId" id="custId" ></td>
							</tr>
<!-- 							<tr> -->
<!-- 								<th>현재 비밀번호</th> -->
<!-- 								<td><input type="password"></td> -->
<!-- 							</tr> -->
							<tr>
								<th>통신사</th>
								<td>
									<select id="mvTscoClcd" name="mvTscoClcd" >
                                	</select>
								</td>
							</tr>
							<tr>
								<th>휴대전화번호</th>
								<td>
									<select id="mvTscoKind" name="mvTscoKind" >
										<option value="010">010</option>
                                		<option value="011">011</option>
                                		<option value="016">016</option>
                                		<option value="018">018</option>
                                		<option value="019">019</option>
									</select>
									<input type="tel" id="celpNum1" name="celpNum1" value="" 
									onkeypress="onlyNumber(); _maxLengthCheck(this, 10);"  
                                           onkeyup="this.value=this.value.replace(/[^0-9]/g,'');"
									/>
                                	<input type="hidden" id="celpNum" name="celpNum" value="" />
								</td>
							</tr>
							<tr>
								<th>인증번호</th>
								<td><input type="number" id="ctifNo" name="ctifNo" >
								    <button type="button" onclick="factorAuth_req('loginpwinit1');" >인증요청</button>
								    <span id="timer" name="timer" >00:00</span>
								</td>
							</tr>
							<tr>
								<th>신규 비밀번호</th>
								<td><input type="password" name="newPassWord" id="newPassWord" ></td>
							</tr>
							<tr>
								<th>신규 비밀번호 확인</th>
								<td><input type="password" name="passWordCfm" id="CfmPassWord" ></td>
							</tr>
						</table>
						<!-- //table -->
					</div>
					<!-- //table-pnl -->
				</div>
				<!-- footer -->
				<div class="footer">
					<div class="buttonset">
						<button type="button" onclick="fn_Change('loginpwinit1');" >확인</button>
						<button type="button" onclick="factorAuth_cancel();">취소</button>
					</div>
				</div>
				<!-- //footer -->
			</div>
			<!-- //contBox -->
		</div>
		<div class="fade"></div>
		</form>
	</div>
	<!-- //layerBox pwinit -->

<!-- 최초 로그인시 비밀번호 변경  -->
<!-- layerBox pwchange -->
	<div id="loginpw" class="layerBox pwchange" style="display:none;">
		<form name="loginpw1" id="loginpw1">
		<div class="layer">
			<div class="topBox">
				<h3>비밀번호 변경</h3>
				<a class="close" href="#">&#10005;</a>
			</div>
			<div class="contBox">
				<div class="section">
					<!-- table-pnl -->
					<div class="table-pnl">
						<!-- table -->
						<table>
							<colgroup>
								<col width="140px">
								<col width="*">
							</colgroup>
							<tr>
								<th>로그인 ID</th>
								<td><input type="text" name="custId" id="custId" ></td>
							</tr>
							<tr>
								<th>현재 비밀번호</th>
								<td><input type="password" name="passWord" id="passWord" ></td>
							</tr>
							<tr>
								<th>신규 비밀번호</th>
								<td><input type="password" name="newPassWord" id="newPassWord" ></td>
							</tr>
							<tr>
								<th>신규 비밀번호 확인</th>
								<td><input type="password" name="passWordCfm" id="CfmPassWord"></td>
							</tr>
							<tr>
								<th class="text-r"><em>안내</em></th>
								<td>
									<ul>
										<li>비밀번호는 영문, 숫자 혼합하여 9자 이상입니다.</li>
										<li>직전에 사용한 비밀번호는 다시 사용할 수 없습니다.</li>
									</ul>
								</td>
							</tr>
						</table>
						<!-- //table -->
					</div>
					<!-- //table-pnl -->
				</div>
				<!-- footer -->
				<div class="footer">
					<div class="buttonset">
						<button type="button" onclick="fn_Change('loginpw1'); ">확인</button>
						<button type="button" onclick="factorAuth_cancel();" >취소</button>
					</div>
				</div>
				<!-- //footer -->
			</div>
			<!-- //contBox -->
		</div>
		<div class="fade"></div>
		</form>
	</div>
	<!-- //layerBox pwchange -->


<!-- 핸드폰 인증 로그인 auth -->
	<div  id="loginauth" class="layerBox pwchange" style="display:none;" >
	 <form name="loginauth1" id="loginauth1">
		<div class="layer">
			<div class="topBox">
				<h3>2-Factor Auth</h3>
				<a class="close" href="#">&#10005;</a>
			</div>
			<div class="contBox">
				<div class="section">
					<!-- table-pnl -->
					<div class="table-pnl">
						<!-- table -->
						<table>
							<colgroup>
								<col width="170px">
								<col width="*">
							</colgroup>
							<tr>
								<th>로그인 ID</th>
								<td><input type="text" name="custId" id="custId" readonly></td>
							</tr>
							<tr>
								<th>통신사</th>
								<td>
									<select id="mvTscoClcd" name="mvTscoClcd" >
                                	</select>
								</td>
							</tr>
							<tr>
								<th>휴대전화번호</th>
								<td>
									<select id="mvTscoKind" name="mvTscoKind" >
										<option value="010">010</option>
										<option value="011">011</option>
										<option value="016">016</option>
										<option value="018">018</option>
										<option value="019">019</option>
									</select>
									<input type="tel" id="celpNum1" name="celpNum1" value=""  
									       onkeypress="onlyNumber(); _maxLengthCheck(this, 10);"  
                                           onkeyup="this.value=this.value.replace(/[^0-9]/g,'');"
									>
									
									  
									
									<input type="hidden" id="celpNum" name="celpNum" value="" />
								</td>
							</tr>
							<tr>
								<th>인증번호</th>
								<td><input type="number" id="ctifNo" name="ctifNo" >
								    <button type="button" onclick="factorAuth_req('loginauth1');">인증요청</button>
								    <span id="timer" name="timer">00:00</span>
								 </td>
							</tr>
						</table>
						<!-- //table -->
					</div>
					<!-- //table-pnl -->
				</div>
				<!-- footer -->
				<div class="footer">
					<div class="buttonset">
						<button type="button"  onclick="factorAuth_confirm();" >확인</button>
						<button type="button" onclick="factorAuth_cancel();" >취소</button>
					</div>
				</div>
				<!-- //footer -->
			</div>
			<!-- //contBox -->
		</div>
		<div class="fade"></div>
		</form>
	</div>
	<!-- 핸드폰 인증 로그인 auth -->

</body>
</html>
