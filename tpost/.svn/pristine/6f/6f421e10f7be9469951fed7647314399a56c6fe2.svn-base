<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>  <!-- 전자정부태그라이브러리 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  <!-- jstl펑션 -->

<%-- <jsp:forward page="/tpost/logIn/logIn"/> --%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script src="/tpost/resource/js/jquery.min.js"></script>
<script src="/tpost/resource/js/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript">

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function imgTest(){
	var name= getParameterByName("custId");
//    $('#imgText').val(name);
//   $('#imgTest').val(name);
//   $('#info').text(name);
//   $("label[for='info']".text("정보변경");
//   $('#sndnDate').append(name);
//   document.getElementById("imgText").value ="1111111";
   //alert( $('#imgText').val() ) ;
//    $('#imgText').html('111111111');
//	alert(name);
}

/*<!-- 메인화면 입니다. --> */
function main(){
		  
	  var mainForm = document.getElementById("main");
	  mainForm.action = "/tpost/common/menuMainTop-select";
	  mainForm.method = "post";
	  mainForm.submit();
	  
}


function PopuTest(){
	  
	  var mainForm = document.getElementById("main");
	  mainForm.action = "/tpost/common/menuMainTop-select";
	  mainForm.method = "post";
	  mainForm.submit();
	  
}

/*배치 temp 테이블에 적재*/
function batchTest1(){
	  
	  var mainForm = document.getElementById("main");
	  mainForm.action = "/tpost/batch/batchFileUploadTemp/batchFileUploadTemp";
	  mainForm.method = "post";
	  mainForm.submit();
	  
}
/*공인전자주소 채번*/
function batchTest2(){
	  
	  var mainForm = document.getElementById("main");
//	  mainForm.action = "/tpost/batch/fileTest/batchTestMain2";
	  mainForm.action = "/tpost/batch/batElctAddrInfo/batElctAddrInfo";
	  mainForm.method = "post";
	  mainForm.submit();
	  
}

/*발송내역테이블 적재*/
function batchTest3(){
	  
	  var mainForm = document.getElementById("main");
	  mainForm.action = "/tpost/batch/batSndnInfoLst/batSndnInfoLstUpload";
	  mainForm.method = "post";
	  mainForm.submit();
	  
}

/*최송 발송내역테이블에 적재*/
function batchMMS(){
	  var mainForm = document.getElementById("main");
	  mainForm.action = "/tpost/batch/batMoblSndnMsg/batMoblSndnMsgUpload";
	  mainForm.method = "post";
	  mainForm.submit();
	  
}

/*배치 이미지 파일 적재*/
function batchImg(){
	  var mainForm = document.getElementById("touchImg");
	  mainForm.action = "/tpost/batch/fileTest/image";
	  mainForm.method = "post";
	  mainForm.submit();
	  
}


/************이미지파일 올리기******************/
function fileUploadTest(){
	var formData = new FormData($('#fileForm')[0]); 
		$.ajax({ 
				type: "POST"
			  , enctype: 'multipart/form-data'
			  , url: '/tpost/batch/fileTest/fileUpload'
			  , data: formData
			  , processData: false
			  , contentType: false
			  , cache: false
			  , success: function (result) { 
				  alert("파일저장성공");
			  }
			  ,  error: function(request, status, err) {
		        	alert("에러가 발생했습니다!");
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					
			  }
		});
	  
}
/*************************************************************************/
/*카카오 보내기 */
function kakaoSend(){
	
	 var parameters = $("#kakao").serialize();
		$.ajax({
			type:"post", 
			dataType:"json",
			async:false,
			url:'/tpost/batch/fileTest/kakaoSend',
			data:parameters ,
			success:function(data){
				if(data.result == 'Y'){
					alert(data.errmsg);
					$('#tx_id').val(data.tx_id);
					$('#token').val(data.token);
				}else{
					alert(data.errmsg);
				}
			}
		});
}

/*카카오결과괎 */
function kakaoResult(){
	
	var parameters = $("#kakao").serialize();
	$.ajax({
		type:"post", 
		dataType:"json",
		async:false,
		url:'/tpost/batch/fileTest/kakaoResult',
		data:parameters ,
		success:function(data){
			if(data.result == 'Y'){
				alert(data.errmsg);
				alert(data.result);
				alert(data.signed_data);
			}else{
				alert(data.errmsg);
			}
		}
	});
}


/*************************************************************************/
function fileDownloadTest(){
	/*
	var formData = new FormData($('#fileForm')[0]); 
	var parameters = $("#menu_sel").serialize();
	$.ajax({
		type:"post", 
		dataType:"json",
		async:false,
		url:'/tpost/batch/fileTest/fileDownload.ajax',
		data:parameters ,
		success:function(data){
			alert(data.errmsg);
		}
	});
	*/
	 var mainForm = document.getElementById("main");
	mainForm.action = "/tpost/batch/fileTest/fileDownload";
	mainForm.method = "post";
	mainForm.submit();
	
	  
}

function ascCommUtil(){
	
	 var mainForm = document.getElementById("main");
	 
	mainForm.action = "/tpost/commUtil/ascCommUtil";
	mainForm.method = "post";
	mainForm.submit();
	
	  
}

///////////////////////////////

function batchOganRcrfTest(){
	 var oganRcrfForm = document.getElementById("oganRcrf");
	 oganRcrfForm.action = "/tpost/batch/oganRcrfMgntController/oganRcrfMgnt";
	 oganRcrfForm.method = "post";
	 oganRcrfForm.submit();
}

function batchElctDocTest(){
	 var oganRcrfForm = document.getElementById("elctDoc");
	 oganRcrfForm.action = "/tpost/batch/elctDocSnrcController/elctDocSnrcMgnt";
	 oganRcrfForm.method = "post";
	 oganRcrfForm.submit();
}

/*모바일 발송 결과 정리*/
function moblSndnRsltArg(){
	 var oganRcrfForm = document.getElementById("moblSndnRsltArg");
	 oganRcrfForm.action = "/tpost/batch/moblSndnRsltArg";
	 oganRcrfForm.method = "post";
	 oganRcrfForm.submit();
}

/*통계생성배치*/
function stccInsert(){
	 var oganRcrfForm = document.getElementById("stcc");
	 oganRcrfForm.action = "/tpost/batch/sttcInfoBatInsert";
	 oganRcrfForm.method = "post";
	 oganRcrfForm.submit();
}


/* TB_MOBL_SNDN_MSG 대량건 인서트 */
function sndnMsgInsert(){
	 var oganRcrfForm = document.getElementById("sndnMsg");
	 oganRcrfForm.action = "/tpost/batch/moblSndnBulkIns";
	 oganRcrfForm.method = "post";
	 oganRcrfForm.submit();
}

</script>



<body onload="imgTest();">
<input type="text"  id="imgTest" name="imgTest" value="testtttt" />
<label for="info" id="info">정보</label>
	<form id="main">
	<button type="button" class="btn black" onclick="main();" >메인메뉴 조회</button>
	<button type="button" class="btn black" onclick="PopuTest();" >팝업테스트</button>
	<br>
	1. 파일명[NHIS_NHIS00005_20200615] 실행일:[20200615] 기관코드 [NHIS] 템플릿코드 [NHIS00005]<br>
<!-- 	2. integration_test2<br> -->
<!-- 	3. integration_test3<br> -->
<!-- 	4. 전체 [test-han]<br> -->
	파일명      : <input  type="text" id="fileNm" name="fileNm" /><br>
	요청일      : <input type="text"  id="exeDt " name="exeDt" ><br>
	기관코드   : <input type="text"  id="oganCd " name="oganCd" ><br>
	템플릿코드 : <input type="text"  id="tmpltCd " name="tmpltCd" ><br>
	<br>
	<button type="button" class="btn black" onclick="batchTest1();" >배치1) 파일 업로드 임시테이블 적재</button><br>
	<button type="button" class="btn black" onclick="batchTest2();" >배치2) 공인전자주소 채번후 </button><br>
	<button type="button" class="btn black" onclick="batchTest3();" >배치3) 배치 발송정보목록에 적재 </button><br>
	<button type="button" class="btn black" onclick="batchMMS();" >배치4) 배치mms테이블적재</button><br>
	</form>
	<br><br>
	<form id="kakao" name="kakao">
	카카오 전송테스트입니다. 
		카카오tx_id      : <input  type="text" id="tx_id" name="tx_id" /><br>
		카카오token      : <input type="text"  id="token " name="token" ><br>
		<button type="button" class="btn black" onclick="kakaoSend();" >카카오보내기</button><br>
		<button type="button" class="btn black" onclick="kakaoSend1();" >카카오보내기1</button><br>
		<button type="button" class="btn black" onclick="kakaoResult();" >카카오결과값확인 </button><br>
	</form>
	<br><br>
	<button type="button" class="btn black" onclick="fileUploadTest();" >배치1) 파일 업로드테스트</button>
	
	<form id="fileForm" name="fileForm" method="post" enctype="multipart/form-data"> 
		<input type="file" name="file" id="file" multiple="true"> 
	</form>
	<br>
	<button type="button" class="btn black" onclick="fileDownloadTest();" >파일 다운로드테스트</button>
	<br>
	<button type="button" class="btn black" onclick="ascCommUtil();" >암호화 테스트</button>
	
	<br><br>
	<form id="oganRcrf">
	발송일자  : <input type="text" id="transDt" name="transDt"/><br>
	<button type="button" class="btn black" onclick="batchOganRcrfTest()" >배치) 사전문자관련</button>
	</form>
	
	<br><br>
	<form id="elctDoc">
	발송일자  : <input type="text" id="sndnDate" name="sndnDate"/><br>
	<button type="button" class="btn black" onclick="batchElctDocTest()" >배치) 전자문서송수신</button>
	</form>
	
	<br><br>
	<form id="touchImg">
	Anchor X : <input type="text" id="pointX" name="pointX" /><br>
	Anchor Y : <input type="text"  id="pointY " name="pointY" ><br>
	Box Width : <input type="text"  id="boxWidth " name="boxWidth" ><br>
	Box Height : <input type="text"  id="boxHeight " name="boxHeight" ><br>
	문구 : <input type="text"  id="subject" name="subject" ><br>
	<button type="button" class="btn black" onclick="batchImg();" >배치) img파일만들기</button><br>
	</form>
	<br>
	<br>
	[모바일 발송 결과 정리]
	<form id="moblSndnRsltArg">
	요청일자  : <input type="text" id="transDt" name="transDt"/><br>
	<button type="button" class="btn black" onclick="moblSndnRsltArg()" >배치) [모바일 발송 결과 정리]</button>
	</form>
	<br>
	<br>
	[통계생성 배치]
	<form id="stcc">
	요청일자  : <input type="text" id="transDt" name="transDt"/><br>
	<button type="button" class="btn black" onclick="stccInsert()" >배치) [통계생성 배치]</button>
	</form>
	[모바일발송메시지 대량건  배치]
	<form id="sndnMsg">
	건수  : <input type="text" id="cnt" name="cnt"/> * 524288 (건)<br>
	요청일자  : <input type="text" id="transDt" name="transDt"/>20200701 <br>
	발송시작일자  : <input type="text" id="sndnDtm" name="sndnDtm"/>20200701000000<br>
	발송종료일자  : <input type="text" id="sndnVldDtm" name="sndnVldDtm"/>20200701000000<br>
	<button type="button" class="btn black" onclick="sndnMsgInsert()" >발송메세지벌크</button>
	</form>
</body>
</html>