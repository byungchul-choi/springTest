<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>  <!-- 전자정부태그라이브러리 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  <!-- jstl펑션 -->
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="viewport"
        content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>공인전자주소관리</title>

    <!-- CSS  STYLE -->
	<link rel="stylesheet" href="/tpost/resource/css/common.css">
	<style>
	.layerBox .layer {width:1200px;}
	</style>
	<!-- ---------- -->
	
	<!--     JS     -->
	<script src="/tpost/resource/js/jquery.min.js"></script>
	<script src="/tpost/resource/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="/tpost/resource/js/jquery-ui.min.js"></script>
	<script src="/tpost/resource/js/comm.js"></script>
	<!-- ---------- -->
    <script type="text/javascript">
    	var idx = 1;
    
	    $('document').ready(function(){	
	    	commCd_bas('inputBsrpCls', '00006');
	    	commCd_bas('inputSttsClcd', '00013');
	    	
	    	
	    	if($("#inputSttsStDt").val() == "" && $("#inputSttsClosDt").val() == "") {
	    		getYYYYMMFirstDay('inputSttsStDt');
				getYYYYMMLastDay('inputSttsClosDt');
	    	}
    		
    		datepicker();
	    
	    });
	    
		function enterSerch(){
	    	if(window.event.keyCode == 13){
	    		ofapElctAddrListSelect();
	    	}
	    }
	    //초기화
	    function init(){	
	    	$("#ofapElctAddrList").html('');
	    	$('#ofapElctAddrList').append();
	    }
	
	    //상단 조회 버튼 클릭 시
	    function ofapElctAddrListSelect(){
	    	$('#pageIndex').val(1); /*첫조회는 페이지가 1로 조회*/
	    	
	    	var ofapElctAddrListForm = document.getElementById("ofapElctAddrMgnt");
	    	ofapElctAddrListForm.action = "ofapElctAddrListSelect";
	    	ofapElctAddrListForm.method = "post";
	    	ofapElctAddrListForm.submit();
	    }
	    
	    //이력조회
 		function ofapElctAddrHistSelect(ofapElctAddr){
 			jQuery("#ofapElctAddrHist").show();
	    	
 			$('#ofapElctAddrHist .topBox').html('');
 			$('#ofapElctAddrHist #ofapElctAddrHistList').html('');
 			
 			var inner = '';
 			inner += '<h3>[' + ofapElctAddr + '] 공인전자주소 이력</h3>';
 			inner += '<a class="close" onclick="closePopu()">&#10005;</a>';
 			
 			$('#ofapElctAddrHist .topBox').append(inner);
 			
	    	var parameters = {
 	    			inputOfapElctAddr : ofapElctAddr,
	    	}	
	    	
	    	$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "ofapElctAddrHistSelect.ajax",
				data : parameters,
				success : function(data) {
					if(data.histList.length != 0){
						inner = '';
						for(var i = 0; i < data.histList.length; i++){
							var histList = data.histList[i];
							
							inner += '<tr>';
							inner += '<td>'+ histList.histSeq +'</td>';
							inner += '<td>'+ histList.sttsDt +'</td>';
							inner += '<td>'+ histList.sttsClcdNm +'</td>';
							inner += '</tr>';
						}
						
			 			$('#ofapElctAddrHist #ofapElctAddrHistList').append(inner);
					}
				},
				complete : function(data) {
				}
			});
	    	
	    }
	    
	  	//공인전자주소 탈퇴 업데이트
	    function ofapElctAddrSece(){
	  		var ofapElctAddr = "";
	  		var ciNum = "";
	    	
	    	var max = $("#ofapElctAddrList tr").length;
	    	
	    	for(var i = 0; i < max; i++){
				var chk = $("input[name=chk_info]:eq("+i+")").is(":checked");
				if(chk == true){
					if($("input[name=sttsClcd]:eq("+i+")").val() != "300"){
		    			alert("선택된 " + (i+1) + "번째 값은 등록된 값이 아니므로 탈퇴할 수 없습니다.");	
		    			return;
					}
					if($("input[name=sttsClcd]:eq("+i+")").val() == "300"){
						ofapElctAddr += $("input[name=ofapElctAddr]:eq("+i+")").val() + ",";
						ciNum += $("input[name=ciNum]:eq("+i+")").val() + ",";
					}
	    		}
	    	}
	    	
	    	if(ofapElctAddr == ""){
	    		alert("선택된 항목이 없습니다.");
	    		return;
	    	}
	    	
	    	var parameters = {
	    			inputOfapElctAddr : ofapElctAddr,
	    			inputCiNum : ciNum,
	    	}
	    	
	    	console.log("parameters : ", parameters);

	    	$.ajax({
	    		type : "post",
	    		dataType : "json",
	    		async : false,
	    		url : "ofapElctAddrSece.ajax",
	    		data : parameters,
	    		success : function(data) {
	    			alert("탈퇴되었습니다.");
	    		},
	    		complete : function(data) {
	    			ofapElctAddrListSelect();
	    		}
	    	});
	    }
	  	
	    //공인전자주소 재전송
	    function ofapElctAddrReSend(){
	    	var parameters = "inputPk=";
	    	var max = $("#ofapElctAddrList tr").length;
	    	
	    	for(var i = 0; i < max; i++){
				var chk = $("input[name=chk_info]:eq("+i+")").is(":checked");
				if(chk == true){
					if($("input[name=sttsClcd]:eq("+i+")").val() == "300"
						|| $("input[name=sttsClcd]:eq("+i+")").val() == "200"
							|| $("input[name=sttsClcd]:eq("+i+")").val() == "100"){
		    			alert("재전송은 등록실패되거나 탈퇴된 건에 대해서만 처리 가능합니다. \n선택된 " + (i+1) + "번째 값은 등록된 값이거나 발급된 값이므로 재전송 할 수 없습니다.");	
		    			return;
					}
					if($("input[name=sttsClcd]:eq("+i+")").val() == "400" 
					|| $("input[name=sttsClcd]:eq("+i+")").val() == "500"){
						parameters += $("input[name=ofapElctAddr]:eq("+i+")").val() + ",";
					}
	    		}
	    	}
	    	
	    	if(parameters == "inputPk="){
	    		alert("선택된 항목이 없습니다.");
	    		return;
	    	}
	    	
	    	alert("KISA 연동 후 구현예정");
	    }
	    
	   
	    
	    //ofapElctAddrRegn(공인전자주소등록 - 개인) 팝업 오픈
	    function goFapElctAddrRegn(){
	    	$("#ofapElctAddrRegn #ofapElctAddrRegnList").html("");
	    	$("input:checkbox[id=popCheck]").prop("checked", false);
	    	
	    	jQuery("#ofapElctAddrRegn").show(); 
	    	idx = 1;
	    	
	    	for(var i = 0; i < 5; i++) addRow();
	    	
			$("input[name=popuName]:eq(0)").focus();
	    }
	    
	  //ofapElctAddrRegn(공인전자주소등록 - 법인) 팝업 오픈
	    function goFapElctAddrRegn_co(){
	    	$("#ofapElctAddrRegn_co #ofapElctAddrRegnList_co").html("");
	    	$("input:checkbox[id=popCheck_co]").prop("checked", false);
	    	
	    	jQuery("#ofapElctAddrRegn_co").show(); 
	    	idx = 1;
	    	
	    	for(var i = 0; i < 5; i++) addRow_co();
	    	
			$("input[name=popuName_co]:eq(0)").focus();
	    }
	    
		//행추가(개인)
		function addRow(){
			var ofapElctAddrRegnListTr = '';
			
			ofapElctAddrRegnListTr += '<tr>';
			ofapElctAddrRegnListTr += '<th scope="row"><input id="popCheck_' + idx + '" name="pop_chk_info" type="checkbox"><label for="popCheck_' + idx + '"></label></th>';
			ofapElctAddrRegnListTr += '<td><select id="popuRnrsCls" name="popuRnrsCls"><option value="100">내국인</option><option value="200">외국인</option></select></td>';
			ofapElctAddrRegnListTr += '<td><input type="text" id="popuName" name="popuName"></td>';
			ofapElctAddrRegnListTr += '<td><input type="text" id="popuFronBrdt" name="popuFronBrdt" placeholder="EX)19800101" maxlength="8" onkeypress="onlyNumber(); _maxLengthCheck(this, 7);" onKeyUp="this.value=this.value.replace(/[^0-9]/g,\'\');"></td>';
			ofapElctAddrRegnListTr += '<td><select id="popuSexClcd" name="popuSexClcd"><option value="M">남성</option><option value="F">여성</option></select></td>';
			ofapElctAddrRegnListTr += '<td><input type="text" id="popuCiNum" name="popuCiNum" placeholder="연계정보(CI) 88Byte를 입력해주세요." maxlength="88" onKeyUp="noHangle(event)"></td>';
			ofapElctAddrRegnListTr += '</tr>';
			
			$('#ofapElctAddrRegnList').append(ofapElctAddrRegnListTr);
			$("#popCheck_"+idx).attr("checked", true);
			$("input[name=popuName]:eq("+(idx-1)+")").focus();	
			idx++;
		}
		
		
		//행추가(법인)
		function addRow_co(){
			var ofapElctAddrRegnListTr_co = '';
			
			ofapElctAddrRegnListTr_co += '<tr>';
			ofapElctAddrRegnListTr_co += '<th scope="row"><input id="popCheck_co_' + idx + '" name="pop_chk_info_co" type="checkbox"><label for="popCheck_co_' + idx + '"></label></th>';
			ofapElctAddrRegnListTr_co += '<td><input type="text" id="popuName_co" name="popuName_co"></td>';
			ofapElctAddrRegnListTr_co += '<td><input type="text" id="popuEn_co" name="popuEn_co" maxlength="3" onKeyUp="this.value=this.value.replace(/[^a-zA-Z]/g,\'\');"></td>';
			ofapElctAddrRegnListTr_co += '<td><input type="text" id="popuCiNum_co" name="popuCiNum_co" placeholder="사업자번호 10Byte를 입력해주세요." maxlength="12" onkeypress="onlyNumber(); _maxLengthCheck(this, 9);" onKeyUp="this.value=this.value.replace(/[^0-9]/g,\'\');"></td>';
			ofapElctAddrRegnListTr_co += '</tr>';
			
			$('#ofapElctAddrRegnList_co').append(ofapElctAddrRegnListTr_co);
			$("#popCheck_co_"+idx).attr("checked", true);
			$("input[name=popuName_co]:eq("+(idx-1)+")").focus();	
			idx++;
		}
		
		//공인전자주소 추가(개인)
		function ofapElctAddrListInsert(){
			if(reqValOnCheckTable('ofapElctAddrRegnList', 'pop_chk_info', 0, 'popuName', '고객명')) return;
			if(reqValOnCheckTable('ofapElctAddrRegnList', 'pop_chk_info', 0, 'popuFronBrdt', '생년월일')) return;
			if(reqValOnCheckTable('ofapElctAddrRegnList', 'pop_chk_info', 0, 'popuCiNum', '연계정보(CI)')) return;
			
			var length = $('#ofapElctAddrRegnList tr').length;
			for(var i = 0; i < length; i ++){
				 var chk_info = $("input[name=pop_chk_info]:eq("+i+")");

				 var colBrdt = $("input[name=popuFronBrdt]:eq("+i+")");
				 var colCi = $("input[name=popuCiNum]:eq("+i+")");
				 
				 if(chk_info.is(":checked")){
					 if(colBrdt.val().length != 8){
						 alert((i + 1) + "번째 생년월일 값이 8자리가 아닙니다. 다시 입력해주세요.");
						 colBrdt.focus();
						 return;
					 }
					 
					 if(colCi.val().length != 88){
						 alert((i + 1) + "번째 연계정보(CI) 값이 88Byte가 아닙니다. 다시 입력해주세요.");
						 colCi.focus();
						 return;
					 }
				 }
			 }
			
			var checkPoint = 0;
			
			var popuChk = "";
			
			//inputChk에 체크중인지 아닌지 파라메터 넘김
			$('#ofapElctAddrRegnList tr').each(function(){
				var chk = $(this).find('input[name=pop_chk_info]').is(":checked");
				popuChk += chk + ",";
				
				if(chk == true) checkPoint++; 
			});
			
			if(checkPoint == 0){
				alert("체크된 항목이 없습니다.");
				return;
			}
			
			var popuName = "";
			var popuCiNum = "";
			var popuRnrsCls = "";
			var popuFronBrdt = "";
			var popuSexClcd = "";
			
			$('#ofapElctAddrRegnList tr').each(function(){
				popuName += $(this).find('#popuName').val() + ",";
				popuCiNum += $(this).find('#popuCiNum').val() + ",";
				popuRnrsCls += $(this).find('#popuRnrsCls').val() + ",";
				popuFronBrdt += $(this).find('#popuFronBrdt').val() + ",";
				popuSexClcd += $(this).find('#popuSexClcd').val() + ",";
			});
			
			var parameters = { 
					popuChk				: popuChk,
					popuName			: popuName,
					popuCiNum			: popuCiNum,
					popuRnrsCls			: popuRnrsCls,
					popuBsrpCls			: "100",
					popuEn				: "",
					popuFronBrdt		: popuFronBrdt,
					popuSexClcd			: popuSexClcd,
				};
			
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "ofapElctAddrListCheck.ajax",
				data : parameters,
				success : function(data) {
					var result = data.result.split(',');
					
					var count = 0;
					var size = result.length -1;
					for(var i = 0; i < size; i++){
						if(result[i] == "Y"){
							$('#ofapElctAddrRegnList tr').eq(i).css("background", "#fef8eb");	
							count++;
						}
						else{
							$('#ofapElctAddrRegnList tr').eq(i).css("background", "");	
						}
					}
					
					if(count == 0){
						$.ajax({
							type : "post",
							dataType : "json",
							async : false,
							url : "ofapElctAddrListInsert.ajax",
							data : parameters,
							success : function(data) {
								if(data.errorMessage != ""){
									alert(data.errorCount + "번째 " + data.errorMessage);
									
									var colCi = $("input[name=popuCiNum]:eq("+(Number(data.errorCount)-1)+")");
									colCi.focus();	
								}else{
									alert("저장되었습니다.");
									closePopu();
									
									ofapElctAddrListSelect();
								}
							},
							complete : function(data) {
							}
						});
					}
					else{
						alert("이미 등록되거나 등록진행중인 건은 제외하고 등록해주시기 바랍니다.");
					}
				},
				complete : function(data) {
				}
			});		
			
		}
		
		//공인전자주소 추가(법인)
		function ofapElctAddrListInsert_co(){
			if(reqValOnCheckTable('ofapElctAddrRegnList_co', 'pop_chk_info_co', 0, 'popuName_co', '사업자명')) return;
			if(reqValOnCheckTable('ofapElctAddrRegnList_co', 'pop_chk_info_co', 0, 'popuEn_co', '사업자 영문 약자')) return;
			if(reqValOnCheckTable('ofapElctAddrRegnList_co', 'pop_chk_info_co', 0, 'popuCiNum_co', '사업자번호')) return;
			
			var length = $('#ofapElctAddrRegnList_co tr').length;
			for(var i = 0; i < length; i ++){
				 var chk_info = $("input[name=pop_chk_info_co]:eq("+i+")");
				 var colCi = $("input[name=popuCiNum_co]:eq("+i+")");
				 var colEn = $("input[name=popuEn_co]:eq("+i+")");
				 
				 if(chk_info.is(":checked") && colCi.val().length != 10){
					 alert((i + 1) + "번째 사업자번호 값이 10Byte가 아닙니다. 다시 입력해주세요.");
					 colCi.focus();
					 return; 
				 }
			 }
			
			var checkPoint = 0;
			
			var popuChk = "";
			
			//inputChk에 체크중인지 아닌지 파라메터 넘김
			$('#ofapElctAddrRegnList_co tr').each(function(){
				var chk = $(this).find('input[name=pop_chk_info_co]').is(":checked");
				popuChk += chk + ",";
				
				if(chk == true) checkPoint++; 
			});
			
			if(checkPoint == 0){
				alert("체크된 항목이 없습니다.");
				return;
			}
			
			var popuName = "";
			var popuCiNum = "";
			var popuEn = "";
			
			$('#ofapElctAddrRegnList_co tr').each(function(){
				popuName += $(this).find('#popuName_co').val() + ",";
				popuCiNum += $(this).find('#popuCiNum_co').val() + ",";
				popuEn += $(this).find('#popuEn_co').val() + ",";
			});
			
			var parameters = { 
					popuChk				: popuChk,
					popuName			: popuName,
					popuCiNum			: popuCiNum,
					popuEn				: popuEn,
					popuBsrpCls			: "200",
					popuRnrsCls			: "",
					popuFronBrdt		: "",
					popuSexClcd			: "",
				};
			
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "ofapElctAddrListCheck.ajax",
				data : parameters,
				success : function(data) {
					var result = data.result.split(',');
					
					var count = 0;
					var size = result.length -1;
					for(var i = 0; i < size; i++){
						if(result[i] == "Y"){
							$('#ofapElctAddrRegnList_co tr').eq(i).css("background", "#fef8eb");	
							count++;
						}
						else{
							$('#ofapElctAddrRegnList_co tr').eq(i).css("background", "");	
						}
					}
					
					if(count == 0){
						$.ajax({
							type : "post",
							dataType : "json",
							async : false,
							url : "ofapElctAddrListInsert.ajax",
							data : parameters,
							success : function(data) {
								if(data.errorMessage != ""){
									alert(data.errorCount + "번째 " + data.errorMessage);
									
									var colCi = $("input[name=popuCiNum]:eq("+(Number(data.errorCount)-1)+")");
									colCi.focus();	
								}else{
									alert("저장되었습니다.");
									closePopu();
									
									ofapElctAddrListSelect();
								}
							},
							complete : function(data) {
							}
						});
					}
					else{
						alert("이미 등록되거나 탈퇴된 건은 제외하고 등록해주시기 바랍니다.");
					}
				},
				complete : function(data) {
				}
			});		
			
		}
		
		function exelUpload(){
			alert("추후 구현 예정");
		}

	    //다음페이지
	    function linkPage(pageNo){
	    	if(pageIndex == ""){
	    		pageIndex = 1;
	    	}
	    		  
	    	$('#pageIndex').val(pageNo);
	    		
	    	var ofapElctAddrListForm = document.getElementById("ofapElctAddrMgnt");
	    	ofapElctAddrListForm.action = "ofapElctAddrListSelect";
	    	ofapElctAddrListForm.method = "post";
	    	ofapElctAddrListForm.submit();
	    }	
	
	    function callBack(sel_id, codeNum){
    		if(sel_id == "#inputSttsClcd") {
	    		$("#inputSttsClcd").prepend("<option value='' selected='true'>전체</option>");
	    		
	    		var value = '${inputSttsClcd}';
	    		
	    		if(value == "") return;
	    		
	    		var size = $("#inputSttsClcd option").size();
	    		for(var i = 0; i < size; i++){
	    			var selector = $("#inputSttsClcd option:eq("+ i +")");
	    			if(selector.val() == value){
	    				selector.attr("selected", "selected");
	    			}
	    		}	
	    	}
    		
	    	if(sel_id == "#inputBsrpCls") {
	    		$("#inputBsrpCls").prepend("<option value='' selected='true'>전체</option>");
	    		
	    		var value = '${inputBsrpCls}';
	    		
	    		if(value == "") return;
	    		
	    		var size = $("#inputBsrpCls option").size();
	    		for(var i = 0; i < size; i++){
	    			var selector = $("#inputBsrpCls option:eq("+ i +")");
	    			if(selector.val() == value){
	    				selector.attr("selected", "selected");
	    			}
	    		}	
	    	}
	    }
	    
	    //팝업 닫기
        function closePopu(){
        	jQuery(".layerBox").hide();
        }	    
    </script>
</head>

<body>
<form id="ofapElctAddrMgnt">
<input type="hidden" id="pageIndex" name="pageIndex" value="${searchVO.pageIndex}"/> 
    <!-- wrap -->
    <div id="wrap">
        <!-- contents -->
        <div id="contents">
            <!-- section -->
            <div class="section">
                <!-- caption-pnl -->
                <div class="caption-pnl">
                    <h2>공인전자주소관리</h2>
                    <div class="buttonset">
                        <button type="button" onclick="ofapElctAddrReSend()">재전송</button>
                        <button type="button" class="state-highlight" onclick="goFapElctAddrRegn()">발급(개인)</button>
                        <button type="button" class="state-highlight" onclick="goFapElctAddrRegn_co()">발급(법인)</button>
                        <button type="button" onclick="ofapElctAddrSece()">탈퇴</button>
                    </div>
                </div>
                <!-- //caption-pnl -->
                <!-- search-pnl -->
                <div class="search-pnl">
                    <table>
                        <colgroup>
                            <col width="140px" />
                            <col width="*" />
                            <col width="150px" />
                            <col width="*" />
                            <col width="120px" />
                            <col width="160px" />
                            <col width="30px" />
                            <col width="200px" />
                        </colgroup>
                        <tr>
                            <th>상태일자</th>
                            <td><input type="text" size="13" class="dateinput" id="inputSttsStDt" name="inputSttsStDt" value="${inputSttsStDt}" onkeypress="dateFormat(this)" onKeyDown="enterSerch()"> ~ <input type="text" size="13" class="dateinput" id="inputSttsClosDt" name="inputSttsClosDt" value="${inputSttsClosDt}" onkeypress="dateFormat(this)" onKeyDown="enterSerch()"></td>
                            <th>개인/법인구분</th>
                            <td><select id="inputBsrpCls" name="inputBsrpCls"></select></td>
                            <th>고객명</th>
                            <td><input type="text" size="8" id="inputName" name="inputName" value="${inputName}" onKeyDown="enterSerch()"></td>
                            <td></td>
                            <td class="buttonset" rowspan="2"><button type="button" onclick="ofapElctAddrListSelect()">조회</button>
                            </td>
                        </tr>
                        <tr>
                            <th>공인전자주소</th>
                            <td><input type="text" size="10" maxlength="15" onkeypress="onlyNumber(); _maxLengthCheck(this, 12);" onKeyUp="this.value=this.value.replace(/[^0-9]/g,'');" id="inputOfapElctAddr" name="inputOfapElctAddr" value="${inputOfapElctAddr}" onKeyDown="enterSerch()"></td>
                            <th>연계정보(CI)</th>
                            <td><input type="text" size="10" id="inputCiNum" name="inputCiNum" value="${inputCiNum}" onKeyDown="enterSerch()"></td>
                            <th>상태여부</th>
                            <td>
                            	<select id="inputSttsClcd" name="inputSttsClcd"></select>
                            </td>
                        </tr>
                    </table>
                </div>
                <!-- //search-pnl -->
                <!-- grid-pnl -->
                <div class="grid-pnl">
                    <!-- table -->
                    <table class="align-center">
                        <colgroup>
                            <col width="50px">
                            <col width="*">
                            <col width="*">
                            <col width="*">
                            <col width="*">
                            <col width="*">
                            <col width="*">
                        </colgroup>
                        <thead>
                            <tr>
                                <th scope="col"><input type="checkbox" onclick="chkBoxMgnt('ofapElctAddrList', $(this))" id="designcheck"><label for="designcheck"></label></th>
                                <th scope="col">개인/법인구분</th>
                                <th scope="col">고객명</th>
                                <th scope="col">공인전자주소</th>
                                <th scope="col">상태일자</th>
                                <th scope="col">상태여부</th>
                                <th scope="col">이력조회</th>
                            </tr>
                        </thead>
                        <tbody id="ofapElctAddrList">
	                        <c:forEach var="ofapElctAddrList" items="${ofapElctAddrList}" varStatus="status">
								<c:if test="${ofapElctAddrList.sttsClcd != '400'}">
									<tr>
										<th scope="row"><input type="checkbox" name="chk_info" id="designcheck_${status.count}"><label for="designcheck_${status.count}"></label></th>
										<td>${ofapElctAddrList.bsrpCls}</td>
										<td>${ofapElctAddrList.name}</td>
										<td style="cursor:pointer;" onclick="ofapElctAddrToCi('${ofapElctAddrList.ciNum}')"><input type="hidden" name="ofapElctAddr" value="${ofapElctAddrList.ofapElctAddr}"/><input type="hidden" name="ciNum" value="${ofapElctAddrList.ciNum}"/>${ofapElctAddrList.ofapElctAddr}</td>
										<td>${ofapElctAddrList.sttsDt}</td>
										<td><input type="hidden" name="sttsClcd" value="${ofapElctAddrList.sttsClcd}"/>${ofapElctAddrList.sttsClcdNm}</td>
										<td><button type="button" class="icon-search" onclick="ofapElctAddrHistSelect('${ofapElctAddrList.ofapElctAddr}')"></button></td>
									</tr>
								</c:if>
								<c:if test="${ofapElctAddrList.sttsClcd == '400'}">
									<tr class="text-point">
										<th scope="row"><input type="checkbox" name="chk_info" id="designcheck_${status.count}"><label for="designcheck_${status.count}"></label></th>
										<td>${ofapElctAddrList.bsrpCls}</td>
										<td>${ofapElctAddrList.name}</td>
										<td style="cursor:pointer;" onclick="ofapElctAddrToCi('${ofapElctAddrList.ciNum}')" ><input type="hidden" name="ofapElctAddr" value="${ofapElctAddrList.ofapElctAddr}"/><input type="hidden" name="ciNum" value="${ofapElctAddrList.ciNum}"/>${ofapElctAddrList.ofapElctAddr}</td>
										<td>${ofapElctAddrList.sttsDt}</td>
										<td><input type="hidden" name="sttsClcd" value="${ofapElctAddrList.sttsClcd}"/>${ofapElctAddrList.sttsClcdNm}</td>
										<td><button type="button" class="icon-search" onclick="ofapElctAddrHistSelect('${ofapElctAddrList.ofapElctAddr}')"></button></td>
									</tr>
								</c:if>
							</c:forEach>
                        </tbody>
                    </table>
                    <!-- //table -->
                </div>
                <!-- //grid-pnl -->
            </div>
            <!--// section -->
            <!-- paging -->
			<div class="paging">
				<c:if test="${fn:length(ofapElctAddrList) ne 0 }">  
					<ul>
						<ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="linkPage" />
					</ul>
				</c:if>
			</div>
			<!-- //paging -->
        </div>
        <!-- //contents -->

    </div>
    <!-- //wrap -->
    
    <!-- layerBox Message -->
	<div id="ofapElctAddrRegn" class="layerBox" style="display:none;">
		<div class="layer">
			<div class="topBox">
				<h3>공인전자주소발급(개인)</h3>
				<a class="close" onclick="closePopu()">&#10005;</a>
			</div>
			<div class="contBox">
				<div class="section">
					<div class="caption-pnl">
						<h3></h3>
						<div class="buttonset">
						<button type="button" class="state-highlight" onclick="ofapElctAddrListInsert()">저장</button>
						<button type="button" onclick="exelUpload()">엑셀 업로드</button>
						<button type="button" class="icon-plus" onclick="addRow()">행추가</button>
						<button type="button" class="icon-minus" onclick="table_row_remove('pop_chk_info')">행삭제</button>
						</div>
					</div>
					<div class="grid-pnl" style="overflow-x:hidden; overflow-y:auto; height:300px;">
						<!-- table -->
						<table class="align-center">
							<colgroup>
								<col width="50px">
								<col width="15%">
								<col width="15%">
								<col width="15%">
								<col width="8%">
								<col width="*">
							</colgroup>
						<thead>
							<tr>
								<th scope="col"><input type="checkbox" onclick="chkBoxMgntName('ofapElctAddrRegnList', $(this), 'pop_chk_info')" id="popCheck"><label for="popCheck"></label></th>
								<th scope="col">내국인/외국인 구분</th>
								<th scope="col">고객명</th>
								<th scope="col">생년월일</th>
								<th scope="col">성별</th>
								<th scope="col">연계정보(CI)</th>
							</tr>
						</thead>
						<tbody id="ofapElctAddrRegnList">
						</tbody>
						</table>
						<!-- //table -->
					</div>
				<!-- footer -->
				<div class="footer">
					<div class="buttonset">
						<button type="button" class="state-submit" onclick="closePopu()">닫기</button>
					</div>
				</div>
				<!-- //footer -->
			</div>
			</div>
			<!-- //contBox -->
		</div>
	<div class="fade"></div>
	</div>
	<!-- //layerBox Message -->
	
	<!-- layerBox Message -->
	<div id="ofapElctAddrRegn_co" class="layerBox" style="display:none;">
		<div class="layer">
			<div class="topBox">
				<h3>공인전자주소발급(법인)</h3>
				<a class="close" onclick="closePopu()">&#10005;</a>
			</div>
			<div class="contBox">
				<div class="section">
					<div class="caption-pnl">
						<h3></h3>
						<div class="buttonset">
						<button type="button" class="state-highlight" onclick="ofapElctAddrListInsert_co()">저장</button>
						<button type="button" onclick="exelUpload()">엑셀 업로드</button>
						<button type="button" class="icon-plus" onclick="addRow_co()">행추가</button>
						<button type="button" class="icon-minus" onclick="table_row_remove('pop_chk_info_co')">행삭제</button>
						</div>
					</div>
					<div class="grid-pnl" style="overflow-x:hidden; overflow-y:auto; height:300px;">
						<!-- table -->
						<table class="align-center">
							<colgroup>
								<col width="50px">
								<col width="20%">
								<col width="20%">
								<col width="*">
							</colgroup>
						<thead>
							<tr>
								<th scope="col"><input type="checkbox" onclick="chkBoxMgntName('ofapElctAddrRegnList_co', $(this), 'pop_chk_info_co')" id="popCheck_co"><label for="popCheck_co"></label></th>
								<th scope="col">사업자명</th>
								<th scope="col">사업자 영문 약자</th>
								<th scope="col">사업자번호</th>
							</tr>
						</thead>
						<tbody id="ofapElctAddrRegnList_co">
						</tbody>
						</table>
						<!-- //table -->
					</div>
				<!-- footer -->
				<div class="footer">
					<div class="buttonset">
						<button type="button" class="state-submit" onclick="closePopu()">닫기</button>
					</div>
				</div>
				<!-- //footer -->
			</div>
			</div>
			<!-- //contBox -->
		</div>
	<div class="fade"></div>
	</div>
	<!-- //layerBox Message -->
	
	<!-- layerBox Message -->
	<div id="ofapElctAddrHist" class="layerBox" style="display:none;">
		<div class="layer" style="width:500px">
			<div class="topBox">
				<h3>공인전자주소 이력</h3>
				<a class="close" onclick="closePopu()">&#10005;</a>
			</div>
			<div class="contBox">
				<div class="section">
					<div class="grid-pnl" style="overflow-x:hidden; overflow-y:auto; height:300px;">
						<!-- table -->
						<table class="align-center">
							<colgroup>
								<col width="20%">
								<col width="*">
								<col width="*">
							</colgroup>
						<thead>
							<tr>
								<th scope="col">순번</th>
								<th scope="col">상태일자</th>
								<th scope="col">상태여부</th>
							</tr>
						</thead>
						<tbody id="ofapElctAddrHistList">
						</tbody>
						</table>
						<!-- //table -->
					</div>
				<!-- footer -->
				<div class="footer">
					<div class="buttonset">
						<button type="button" class="state-submit" onclick="closePopu()">닫기</button>
					</div>
				</div>
				<!-- //footer -->
			</div>
			</div>
			<!-- //contBox -->
		</div>
	<div class="fade"></div>
	</div>
	<!-- //layerBox Message -->
	
	<!-- layerBox Message -->
	<div id="ofapElctAddrToCi" class="layerBox" style="display:none;"></div>
	<!-- //layerBox Message -->
</form>
</body>

</html>