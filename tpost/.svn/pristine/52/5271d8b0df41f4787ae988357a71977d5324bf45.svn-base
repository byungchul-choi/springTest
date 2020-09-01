<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="viewport"
        content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>템플릿 저장/수정</title>

    <!-- CSS  STYLE -->
	<link rel="stylesheet" href="/tpost/resource/css/common.css">
	<!-- ---------- -->
	<style>
        .table-pnl + .table-pnl { margin-top:40px !important}
        td textarea {padding: 20px 10px; box-sizing: border-box; width:100%; height:160px; border:none; resize: none; line-height: 1.6;}
        .markup{height:690px}
        iframe{margin-left:30px;height: 750px;background: #FFFFFF;}
    </style>
	
	<!--     JS     -->
	<script src="/tpost/resource/js/jquery.min.js"></script>
	<script src="/tpost/resource/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="/tpost/resource/js/jquery-ui.min.js"></script>
	<script src="/tpost/resource/js/comm.js"></script>
	<!-- ---------- -->
    <script type="text/javascript">
  		//goTmpltView(템플릿 미리보기)
	    function goTmpltView(){
	    	$('#viewHtml').val($('#editHtml').val());
	    	
	    	var tmpltViewForm = document.getElementById("tmpltView");
	    	tmpltViewForm.action = "goTmpltView";
	    	tmpltViewForm.method = "post";
	    	tmpltViewForm.target = "tmpltViewFrame"
	    	tmpltViewForm.submit();
	    			
	    }
  		
	  	//goTmpltMgnt(목록으로)
	    function goTmpltMgnt(){
	    	var tmpltViewForm = document.getElementById("tmpltView");
	    	tmpltViewForm.action = "tmpltMgnt";
	    	tmpltViewForm.method = "post";
	    	tmpltViewForm.submit();
	    }
	
	    //템플릿 저장
	    function tmpltEditInsert(){
	    	if(reqValCheck('editHtml', '템플릿 내용')) return;
	    		
	    	var parameters = "editOganCd="+ $('#editOganCd').val() 
	    				  + "&editTmpltCd=" + $('#editTmpltCd').val()
	    				  + "&editHtml=" + encodeURIComponent($('#editHtml').val());
	    	
	    	$.ajax({
	    		type : "post",
	    		dataType : "json",
	    		async : false,
	    		url : "tmpltEditInsert.ajax",
	    		data : parameters,
	    		success : function(data) {
	    			alert($('#tmpltCrtYn').val() + "되었습니다.");
	    			location.href = "/tpost/common/tmpltMgnt";
	    		},
	    		complete : function(data) {
	    		}
	    		,
	    		error: function(request, status, error){  
	    		} 
	    	});
	    }
    </script>
</head>

<body>
<form id="tmpltEdit">
<input type="hidden" id="editOganCd" name="editOganCd" value="${editOganCd}"/>
<input type="hidden" id="editTmpltCd" name="editTmpltCd" value="${editTmpltCd}"/>
<input type="hidden" id="tmpltCrtYn" name="tmpltCrtYn" value="${tmpltCrtYn}"/>
    <!-- wrap -->
    <div id="wrap">
        <!-- contents -->
        <div id="contents">
            <!-- section -->
            <div class="section">
                <!-- caption-pnl -->
                <div class="caption-pnl">
                    <h2 id="popuTitle" name="popuTitle">${popuTitle}</h2>
                    <div class="buttonset">
                    	<button type="button" onclick="goTmpltMgnt()">목록으로</button>
                        <button type="button" onclick="goTmpltView()">미리보기</button>
                        <button type="button" class="state-highlight" onclick="tmpltEditInsert()">${tmpltCrtYn}</button>
                    </div>
                </div>
                <!-- //caption-pnl -->

                <!-- //caption-pnl -->
                <div class="colgroup-wrap">
                    <div class="colgroup-1-2">
                        <!-- table-pnl -->
                        <div class="table-pnl">
                            <!-- table -->
                            <table class="vtable">
                                <tbody>
                                <tr>
                                    <th>템플릿내용 입력<span style="color: #fea338;">*</span></th>
                                </tr>
                                <tr>
                                    <td><textarea class="markup" id="editHtml" name="editHtml" placeholder="템플릿 내용 입력">${editHtml}</textarea></td>
                                </tr>
                                </tbody>
                            </table>
                            <!-- //table -->
                        </div>
                        <!-- //table-pnl -->
                    </div>
                    <div class="colgroup-1-2">
                        <iframe id="tmpltViewFrame" name="tmpltViewFrame" allowtransparency="true" src=""></iframe>
                    </div>
                </div>
            </div>
            <!--// section -->
        </div>
        <!-- //contents -->

    </div>
    <!-- //wrap -->
</form>
<form id ="tmpltView">
	<input type="hidden" id="viewHtml" name="viewHtml">
</form>
</body>

</html>