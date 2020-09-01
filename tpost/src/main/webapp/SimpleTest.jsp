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


/*SK 소켓통신 적재*/
function sampleTest(){
	  var mainForm = document.getElementById("main");
	  mainForm.action = "/tpost/batch/SampleTest/sktHttp";
	  mainForm.method = "post";
	  mainForm.submit();
	  
}


/*************************************************************************/

</script>



<body onload="imgTest();">
<input type="text"  id="imgTest" name="imgTest" value="testtttt" />
<label for="info" id="info">정보</label>
	<form id="main">
		<button type="button" class="btn black" onclick="sampleTest();" >샘플테스트</button><br>
	</form>
	
</body>
</html>