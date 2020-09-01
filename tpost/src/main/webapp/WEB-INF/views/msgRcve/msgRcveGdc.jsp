<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!doctype html>
<html lang="ko">

<head>
	<meta charset="UTF-8">
	<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="viewport"
		content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width">
	<meta http-equiv="content-Script-type" content="text/javascript">
	<meta http-equiv="content-Style-type" content="text/css">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>공공알림문자 서비스</title>

	<!-- CSS  STYLE -->
	<link rel="stylesheet" href="/tpost/resource/css/mobile.css">
	<!-- ---------- -->
	<!--     JS     -->
	<script src="/tpost/resource/js/jquery.min.js"></script>
	<script src="/tpost/resource/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="/tpost/resource/js/jquery-ui.min.js"></script>
	<script src="/tpost/resource/js/comm.js"></script>
	<!-- ---------- -->
	<script type="text/javascript">
		$('document').ready(function(){	
			commCd_bas('tscoCd', '00008');
		});
		
		function selectMale(){
			$('#sexCls').val('1');
			document.getElementById("male").className="state-highlight";
			document.getElementById("female").className="";
			
		}
		function selectFemale(){
			$('#sexCls').val('2');

			document.getElementById("male").className="";
			document.getElementById("female").className="state-highlight";
		}
		
		function closeMe() {
	        var win = window.open("","_self"); /* url = "" or "about:blank"; target="_self" */
	        win.close();
	    }
		
		//문자인증 시 본인확인
		function onesCheck(){
			var parameters = {
					a			:$('#a').val(),
					name		:$('#name').val(),
					brdt		:$('#brdt').val(),
					sexCls		:$('#sexCls').val()
			};
			
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "onesCheck.ajax",
				data : parameters,
				success : function(data) {
					if(data.errorMessage != ""){
						alert(data.errorMessage);	
					}
					else{
						$('#anocInfo').val(data.anocInfo + "*");
						$('#sndnOgan').val(data.sndnOgan);
						$('#tmpltCd').val(data.tmpltCd);

						var msgRcveDataForm = document.getElementById("msgRcveData");
						msgRcveDataForm.action = "goMsgRcveData";
						msgRcveDataForm.method = "post";
						msgRcveDataForm.submit();
					}
				},
				complete : function(data) {
				}
			});
		}
		
		function callBack(sel_id, codeNum){
			
		}
	</script>
</head>

<body>
	<form id="msgRcveData">
	<input type="hidden" id="a" name="a" value="${a}">
	<input type="hidden" id="b" name="b" value="${b}">
	
	<input type="hidden" id="sexCls" name="sexCls" value="1">
	<textarea id="anocInfo" name="anocInfo" style="display:none;"></textarea>
	<input type="hidden" id="sndnOgan" name="sndnOgan">
	<input type="hidden" id="tmpltCd" name="tmpltCd">
	</form>
	
	<div id="mobileheader" class="mobileheader">
		<button type="button" class="nav_back" onclick="closeMe()"></button>
		<h2>공공알림문자 서비스 </h2>
	</div>
	
	<div id="container" class="container">
		<div class="contents-wrap">
			<h3>본인확인</h3>
			<div class="inputfield">
				<ul>
					<li><label>성명</label><input type="text" size="12" id="name" name="name">
					</li>
					<li><label>생년월일</label><input type="text" placeholder="생년월일8자리(19800101)" id="brdt" name="brdt">
					</li>
				</ul>
				<button type="button" class="state-highlight" onclick="selectMale()" id="male" name="male">남성</button>
				<button type="button" onclick="selectFemale()" id="female" name="female">여성</button>
				<ul class="certi">
					<li><span><select id="tscoCd" name="tscoCd"></select><input type="text" id="phoneNum" name="phoneNum"></span><button type="button">인증번호</button>
					</li>
					<li><span><label>인증번호</label><input type="text" size="7"></span><button type="button">확인</button>
					</li>
				</ul>
			</div>
			<div class="agreement">
				<div class="agree-check"><input type="checkbox" checked="checked"><label></label><a>공공알림문자 서비스 수신 및 공인
						전자주소 생성 동의 (필수)</a></div>

				<div class="article">
					<p>* 고유식별번호(CI)를 활용하여 이동통신사가 확보한 핸드폰번호로 문자 메시지를 발송하는데 동의합니다.<br>
					* '전자문서 및 전자거래 기본법' 제 18조의 4에 따라 유통증명서 생성/발급 목적으로 성명, CI, 공인전자주소를 한국인터넷진흥원(KISA)에 등록합니다.</p>
				</div>
			</div>
		</div>
		<div class="footer">
			<button type="button">취소</button>
			<button type="button" class="state-submit" onclick="onesCheck()">확인</button>
		</div>
	</div>
	<!--//(e)Container-->
	</form>
</body>

</html>