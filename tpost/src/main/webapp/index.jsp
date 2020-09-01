<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:forward page="/tpost/logIn/logIn"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script>
<!-- 메인화면 입니다. -->
function main(){
		  
	  var mainForm = document.getElementById("main");
	  mainForm.action = "/tpost/logIn/logIn";
	  mainForm.method = "post";
	  mainForm.submit();
	  
}


function PopuTest(){
	  
	  var mainForm = document.getElementById("main");
	  mainForm.action = "/tpost/common/menuMainTop-select";
	  mainForm.method = "post";
	  mainForm.submit();
	  
}

function batchTest(){
	  
	  var mainForm = document.getElementById("main");
	  mainForm.action = "/tpost/batch/fileTest/batchTestMain";
	  mainForm.method = "post";
	  mainForm.submit();
	  
}

function batchMMS(){
	  var mainForm = document.getElementById("main");
	  mainForm.action = "/tpost/batch/fileTest/batchTestMMS";
	  mainForm.method = "post";
	  mainForm.submit();
	  
}


function goMsgRcveGdc(){
	window.open("/tpost/msgRcve/msgRcveGdc", "PopupWin", "width=500px,height=600px");	
}
function goMsgRcveRfslDsms(){
	window.open("/tpost/msgRcve/msgRcveRfslDsms", "PopupWin", "width=500px,height=600px");	
}

function goMsgRcveData(){
	var msgRcveDataForm = document.getElementById("msgRcveData");
	msgRcveDataForm.action = "/tpost/msgRcve/goMsgRcveData";
	msgRcveDataForm.method = "post";
	msgRcveDataForm.submit();
}

</script>

<body>
	<form id="main">	111
	<button type="button" class="btn black" onclick="main();" >메인메뉴 조회</button>
	<button type="button" class="btn black" onclick="PopuTest();" >팝업테스트</button>
	<button type="button" class="btn black" onclick="batchTest();" >배치테스트</button>
	<button type="button" class="btn black" onclick="batchMMS();" >배치mms테이블적재</button>
	</form>
	
	<button type="button" class="btn black" onclick="goMsgRcveGdc();" >안내문 확인</button>
	<button type="button" class="btn black" onclick="goMsgRcveRfslDsms();" >수신거부/해제</button>
	<br>
	
	<form id="msgRcveData" name="msgRcveData">
		<input id="sndnDate" name="sndnDate" placeholder="발송일자"/>
		<input id="sndnOgan" name="sndnOgan" placeholder="발송기관코드"/>
		<input id="tmpltCd" name="tmpltCd" placeholder="템플릿코드"/>
		<input id="ciNum" name="ciNum" placeholder="연계정보(CI)"/>
		<button type="button" class="btn black" onclick="goMsgRcveData();" >맵핑 테스트</button>
		<br>TEST CASE 1 : 20200319 100 10000005 00000000001
		<br>TEST CASE 2 : 20200319 100 10000005 00000000002
	</form>
</body>
</html>