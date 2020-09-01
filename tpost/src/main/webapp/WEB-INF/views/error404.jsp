<%--
  Created by IntelliJ IDEA.
  User: myungsubso
  Date: 2019/10/12
  Time: 1:08 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>  <!-- 전자정부태그라이브러리 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  <!-- jstl펑션 -->
<!-- CSS  STYLE -->
<link rel="stylesheet" href="/tpost/resource/css/common.css">
<!-- ---------- -->
<!--     JS     -->
<script src="/tpost/resource/js/jquery.min.js"></script>
<script src="/tpost/resource/js/jquery-migrate-1.2.1.min.js"></script>
<script src="/tpost/resource/js/comm.js"></script>
<!-- ---------- -->
<style>
		.section {height:100%}
		.error {height:100%; background:url(/tpost/resource/images/error.png) no-repeat 50% 30% }
		                                                
		.guidance-message-pnl {text-align:center;position:relative;top:30%;transform: translate(0, 250px); color: #666666;}
		.guidance-message-pnl em {display: block;padding-bottom: 27px;font-size: 38px;line-height: 38px;color: #333333;font-weight: bold}
		.guidance-message-pnl p {font-size: 19px;letter-spacing: 0px;line-height: 23px;font-weight: 500}
		button {margin-top:34px;font-size: 18px;line-height: 34px;color: #666666;font-weight: bold;width: 182px;height: 52px;border-radius: 26px;border: 2px solid #cccccc}
	</style>
	
<html>
<head>
    <title>Title</title>
    <script>
    	function goBack(){
    		window.history.back();
    	}
    	
    </script>
</head>
<body>
<!-- Error 페이지 입니다. <br/> -->
<%-- ${MESSAGE} --%>
<%--  <c:out value ="${MESSAGE}" ></c:out> --%>
<%--  <h4><c:out value="${exception.getMessage() }" > </c:out></h4> --%>
<!--  <ul> -->
<%--  	<c:forEach items="${exception.getStackTrace()}"  var="stack"> --%>
<%--  		<li> <c:out value ="${stack}" ></c:out></li> --%>
<%--  	</c:forEach> --%>
<!--  </ul> -->
<div id="wrap" class="error">
			<div class="section">
				<div class="guidance-message-pnl">
					<em>접속 불가 안내</em>
					<p>서비스 이용에 불편을 드려 죄송합니다.<br><br>
						잘못된 URL로 접속이 불가합니다.<br>
						URL을 확인해주세요.<br>
					</p>
					<button type="button" onclick="goBack();">되돌아가기</button>
				</div>
			</div>
	</div>
</body>
</html>
