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
			$('.footer').html('');
			
			var inner = '';
			if($('#sndnRfsl').val() == "1"){
				inner = '<button type="button" onclick="sndnRfslUpdate()">수신거부</button>';
			}
			if($('#sndnRfsl').val() == "0"){
				inner = '<button type="button" class="state-submit" onclick="sndnRfslUpdate()">수신거부 해제</button>';
			}
			
			$('.footer').append(inner);		
		});
		
		function closeMe() {
	        var win = window.open("","_self"); /* url = "" or "about:blank"; target="_self" */
	        win.close();
	    }
		
		//기관별 수신거부
		function sndnRfslUpdate(){
			var value = '';
			
			if($('#sndnRfsl').val() == '0') value = '1';
			else value = '0';
			
			var parameters = {
					a			:$('#a').val(),
					seqNo		:$('#seqNo').val(),
					sndnRfsl	:value
			};
			
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "sndnRfslUpdate.ajax",
				data : parameters,
				success : function(data) {
					if(value == 0) alert("공공알림문자 서비스 수신거부되었습니다.");
					if(value == 1) alert("공공알림문자 서비스 수신거부 해제되었습니다.");
					
					closeMe();
				},complete : function(data) {
					
				}
			});
		}
	</script>
</head>

<body>
	<input type="hidden" id="a" name="a" value="${a}">
	<input type="hidden" id="sndnRfsl" name="sndnRfsl" value="${sndnRfsl}">
	<input type="hidden" id="seqNo" name="seqNo" value="${seqNo}">
	<!--Navigation+PageTitle-->
	<div id="mobileheader" class="mobileheader">
		<button type="button" class="nav_back" onclick="closeMe()"></button>
		<h2>공공알림문자 서비스 </h2>
	</div>
	<!--//Navigation+PageTitle-->
	<!--Container-->
	<div id="container" class="container">
		<div class="contents-wrap">
			<h3>${title}</h3>
			<div class="agreement">
				<div class="article">
					<p>* 아래 '수신거부'신청을 하시면 건강보험공단에서 발송하는 안내문 등은 문자메시지로 제공되지 않습니다.<br>
					* 수신거부 신청 후 다시 문자메세지로 수신을 원하시면 초기 안내문 페이지의 URL을 클릭하시어 '수신거부 해제'신청을 해주시기 바랍니다.</p>
				</div>
			</div>
		</div>
		<div class="footer">
			
			
		</div>

	</div>
	<!--//(e)Container-->
</body>

</html>