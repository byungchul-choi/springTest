<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>  <!-- 전자정부태그라이브러리 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  <!-- jstl펑션 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>본인확인</title>

<script src="/tpost/resource/js/jquery.min.js"></script>
<script src="/tpost/resource/js/jquery-migrate-1.2.1.min.js"></script>
<script src="/tpost/resource/js/comm.js"></script>
	
<script type="text/javascript">


function onload(){

	if($('#url').val() == 'logInAction' ){

		if($('#result').val() == "N" ) {
			alert($('#errorMessage').val());
		}else if ( $('#result').val() == "init" ) {
			alert($('#errorMessage').val());
			parent.login_Init();
		}else if($('#result').val() =="pwFiveFailTimeOver"){
			parent.pwFiveFailTimeOver();
		}else if($('#result').val() =="pwLastChgOver"){
			parent.pwLastChgOver();
		}
		else{
			parent.logInauth();
		}
	}else if($('#url').val() == 'factorAuth_req'  ){
		
		if($('#result').val() == "N" ) {
			alert($('#errorMessage').val());
		}else if($('#result').val() == "Y"){
			parent.factorAuth_req_ret( $('#ctifNo').val() );
		}
		
	}else if( $('#url').val() == 'factorAuth_confirm' ){
	
		if($('#result').val() == "N" ) {
			alert($('#errorMessage').val());
		}else if($('#result').val() == "Y"){
			parent.factorAuth_confirm_ret();
		}
	}
	else if( $('#url').val() == 'passWordChg_init'  
			|| $('#url').val() == 'passWordChg' 
			){
		
		if($('#result').val() == "N" ) {
			alert($('#errorMessage').val());
		}else if($('#result').val() == "Y"){
			parent.passWordChg();
		}
	}
	
}


</script>
</head>
<body onload="onload();">
 <input type="hidden" name="result" id="result" value="${result}" />
  <input type="hidden" name="url" id="url" value="${url}" />
  <input type="hidden" name="errorMessage" id="errorMessage" value="${errorMessage}" />
  <input type="hidden" name="ctifNo" id="ctifNo" value="${ctifNo}" />
  
</body>
</html>