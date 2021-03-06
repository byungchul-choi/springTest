<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>  <!-- 전자정부태그라이브러리 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  <!-- jstl펑션 -->
	
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- CSS  STYLE -->
	<link rel="stylesheet" href="/tpost/resource/css/common.css">
	<!-- ---------- -->
	
	<!--     JS     -->
	<script src="/tpost/resource/js/jquery.min.js"></script>
	<script src="/tpost/resource/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="/tpost/resource/js/comm.js"></script>
	<!-- ---------- -->
	
<script>
// 메뉴데이터에 맞게 메뉴리스트 구성 및 이벤트 등록

$(document).ready(function() { 
	
	jQuery(parent.document).find("#loginpw #custId").val('${custId}');
});

var loadMenu = function(aMenuInfo) {
	
    // 메뉴 목록 제거
    jQuery(".gnb li").remove();

    // 메뉴 목록 생성
    var parameters = "";
    
	if(aMenuInfo == "mnu-docu" ){
		parameters = "elctDoc";  /*전자문서*/
	}else if(aMenuInfo == "mnu-certi")  /*유통증명관리*/
	{
		parameters = "acmdCerf";
	}else if(aMenuInfo == "mnu-system"){ /*시스템관리*/
		parameters = "common";
	}else if(aMenuInfo == "mnu-test"){ /*시스템관리*/
		
		parameters = "testMenu";
	}else if(aMenuInfo == "mnu-sttc"){ /*통계*/
		
		parameters = "sttc";
	}else if( aMenuInfo == "mnu-suppCenter"){
		parameters = "suppCenter";
	}
    	
	parameters= "uprMenuId="+parameters;
	
    	$.ajax({
    		type:"post", 
    		dataType:"json",
    		async:false,
    		url:'menuGnb.ajax',
    		data:parameters ,
    		success:function(data){
    			if(data.result == "N" ) {
    				alert(data.errorMessage);
    			}else{
    				
    				if(data.menuGbn.length != 0){
    					var menuGbnLength = data.menuGbn.length - 1;
    					for(var i = menuGbnLength; i > -1; i--){
		    				jQuery(".gnb ul").append("<li><a href='#' id=" + data.menuGbn[i].pathNm + ">"+ data.menuGbn[i].menuNm+"</a></li>");
 
    					}
    				}
    				$("#custNm").text(data.custNm + " 님");
    				jQuery(parent.document).find("#loginpw #custId").val(data.custId);
    			}
    		} 
    	});
	

    // 첫번째 메뉴 활성화
	
    
    // 메뉴 클릭 시 이벤트 처리
    jQuery(".gnb li a").click(function(){
    	parent.linkGbn(jQuery(this).attr("id"));
    	
    	var index = jQuery(".gnb li a").index(this);
    	 
   		 jQuery(".gnb li a").removeClass("state-current");
   		 jQuery(".gnb li:eq("+index+") a").addClass("state-current");
    	
    });

    // 첫번째 메뉴 선택
    jQuery(".gnb li:first-child a").click();
}

jQuery(document).ready(function () {
	// 팝업 띄우기 (임시)
	jQuery("#popupView").change(function () {
		jQuery("#" + this.value).show();
	});

	// 팝업닫기
	jQuery(".close, .state-submit").click(function () {
		jQuery(".layerBox").hide();
	});
});

function pwchange(){
	 jQuery(parent.document).find("#loginpw").show();
}

function logOut(){
	var parameters = $("#frm").serialize();
	$.ajax({
		type:"post", 
		dataType:"json",
		async:false,
		url:'/tpost/logIn/actionLogout.ajax',
		data:parameters ,
		success:function(data){
			if(data.result == "N" ) {
				alert(data.errorMessage);
			}else{
				alert("로그아웃 성공");
				parent.fn_logOut();
				
			}
		} 
	});
}

</script>

<body>
<form  id="menu" name="menu"></form>
    <!-- wrap -->
    <div id="wrap">
        <!-- header -->
        <div id="header">
            <ul class="userInfo">
                <li class="info_pw"><a href="#"  onclick="pwchange();" >비밀번호 변경</a></li>
                <li class="info_logout"><a href="#" onclick="logOut();">로그아웃</a></li>
                <li class="info_user"><span id="custNm">${custNm }님</span></li>
            </ul>
            <div class="gnb">
                <ul class="nav">
                    
                </ul>
            </div>
        </div>
        <!-- //header -->
        
   

    </div>

</body>        


</html>

