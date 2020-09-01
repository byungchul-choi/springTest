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
    <title>템플릿목록</title>

    <!-- CSS  STYLE -->
	<link rel="stylesheet" href="/tpost/resource/css/common.css">
	<!-- ---------- -->
	<style>
		.icon-minus {margin-right: 15px !important;}
    </style>
	<!--     JS     -->
	<script src="/tpost/resource/js/jquery.min.js"></script>
	<script src="/tpost/resource/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="/tpost/resource/js/jquery-ui.min.js"></script>
	<script src="/tpost/resource/js/comm.js"></script>
	<!-- ---------- -->
	<script type="text/javascript">
		var idx = 1;
	
		$('document').ready(function(){	
			commCd_bas('inputSndnOgan', '00001');
		});
		
		function enterSerch(){
	    	if(window.event.keyCode == 13){
	    		tmpltMgntSelect();
	    	}
	    }

		//상단 조회 버튼 클릭 시
		function tmpltMgntSelect(){
			$("#btnIns").attr('disabled', false);
			$("#btnUpd").attr('disabled', false);
			
			
			var tmpltMgntForm = document.getElementById("tmpltMgnt");
			tmpltMgntForm.action = "tmpltMgntSelect";
			tmpltMgntForm.method = "post";
			tmpltMgntForm.submit();	
		}
	
		//템플릿 기본 정보 셋팅
		function tmpltInfoSelect(tmpltCd){
			$("#btnIns").attr('disabled', true);
			$("#btnUpd").attr('disabled', false);
			$("#sndnOgan").attr('disabled', true);
			
			var parameters = "tmpltCd="+tmpltCd;
	
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "tmpltInfoSelect.ajax",
				data : parameters,
				success : function(data) {
					$("select#sndnOgan option").remove();
					$("#sndnOgan").prepend("<option value='"+ data.tmpltInfo.sndnOganCd +"' selected='true'>"+ data.tmpltInfo.sndnOgan+"</option>");
					
					$('#tmpltNm').val(data.tmpltInfo.tmpltNm);
					$('#tmpltCd').val(data.tmpltInfo.tmpltCd);
					$('#tmpltDesc').val(data.tmpltInfo.tmpltDesc);
					
					var tmpltUseYn = data.tmpltInfo.tmpltUseYn;
					$('input[name=tmpltUseYn]:radio[value='+tmpltUseYn+']').prop('checked', true);
					
					var tmpltSchemaYn = data.tmpltInfo.tmpltSchemaYn;
					$('input[name=tmpltSchemaYn]:radio[value='+tmpltSchemaYn+']').prop('checked', true);
					
					var tmpltCrtYn = data.tmpltInfo.tmpltCrtYn;
					$('input[name=tmpltCrtYn]:radio[value='+tmpltCrtYn+']').prop('checked', true);
				},
				complete : function(data) {
				}
			});
		}
		
		//템플릿 기본 정보 수정 시
		function tmpltInfoUpdate(){
			var parameters = { 
					sndnOganCd				: $('#sndnOgan option:selected').val(),
					tmpltNm					: $('#tmpltNm').val(),
					tmpltCd					: $('#tmpltCd').val(),
					tmpltDesc				: $('#tmpltDesc').val(),
					tmpltUseYn				: $('input[name=tmpltUseYn]:checked').val(),
					tmpltSchemaYn			: $('input[name=tmpltSchemaYn]:checked').val(),
					tmpltCrtYn				: $('input[name=tmpltCrtYn]:checked').val()
				};

			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "tmpltInfoUpdate.ajax",
				data : parameters,
				success : function(data) {

				},
				complete : function(data) {
					alert("수정되었습니다.");
					tmpltMgntSelect();
				}
			});
		}

		//템플릿 기본 정보 저장 시
		function tmpltInfoInsert(){
			if(reqValCheck('tmpltNm', '템플릿명')) return;
			if(reqValCheck('tmpltCd', '템플릿코드')) return;
			
			var parameters = { 
					sndnOganCd				: $('#sndnOgan option:selected').val(),
					tmpltNm					: $('#tmpltNm').val(),
					tmpltCd					: $('#tmpltCd').val(),
					tmpltDesc				: $('#tmpltDesc').val(),
					tmpltUseYn				: $('input[name=tmpltUseYn]:checked').val(),
					tmpltSchemaYn			: $('input[name=tmpltSchemaYn]:checked').val(),
					tmpltCrtYn				: $('input[name=tmpltCrtYn]:checked').val(),
				};
			
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "tmpltInfoInsert.ajax",
				data : parameters,
				success : function(data) {

				},
				complete : function(data) {
					alert("저장되었습니다.");
					
					$('#inputSndnOgan').val('');
					$('#inputTmpltNm').val('');
					$('#inputTmpltUseYn option:eq(0)').prop("selected", true);
					
					tmpltMgntSelect();
				}
			});
		}
		
		//템플릿 기본 항목 초기화
		function tmpltInfoInit(){
			$("#btnIns").attr('disabled', false);
			$("#btnUpd").attr('disabled', true);
			$("#sndnOgan").attr('disabled', false);
			
			
			$("select#sndnOgan option").remove();
			commCd_bas('sndnOgan', '00001');		//기관(회사)명 초기화
			
			$("#tmpltNm").val('');		//템플릿 명 초기화
			$("#tmpltDesc").val(''); 	//템플릿 설명 초기화
			
			$('input[name=tmpltUseYn]:radio[value="Y"]').prop('checked', true);			//사용여부 초기화
			$('input[name=tmpltSchemaYn]:radio[value="N"]').prop('checked', true);		//스키마생성여부 초기화
			$('input[name=tmpltCrtYn]:radio[value="N"]').prop('checked', true);			//템플릿생성여부 초기화
			$('input[name=tmpltMultLinSendYn]:radio[value="N"]').prop('checked', true);	//다회선전송여부 초기화
			
			tmpltCdSet();
		}
		
		//기관(회사)명에 따라 템플릿 코드 셋팅
		function tmpltCdSet(){
			var parameters = "sndnOgan="+ $('#sndnOgan option:selected').val();
			
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "tmpltCdSet.ajax",
				data : parameters,
				success : function(data) {
					$('#tmpltCd').val(data.maxTmpltCd);			
				},
				complete : function(data) {
				}
			});
		}
		
		//tmpltSchemaPopu(템플릿 스키마 등록/수정) 팝업 오픈
		function goTmpltSchemaPopu(OganCd, tmpltCd){
			idx = 1;
			
			$("#editOganCd").val(OganCd);
			$("#editTmpltCd").val(tmpltCd);	
					
			jQuery("#schemaPopu").show();
			$("input:checkbox[id=designcheck]").prop("checked", false); 
			
			var parameters = "tmpltCd=" + tmpltCd;
			
			
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "goTmpltSchemaPopu.ajax",
				data : parameters,
				success : function(data) {
					$("#schemaPopu .topBox").html("");
					$("#schemaPopu .footer .buttonset").html("");
					$("#schemaPopu #tmpltSchemaList").html("");
					
					var inner = '';
					inner += '<h3>' + data.popuTitle + '</h3>';
					inner += '<a class="close" href="#" onclick="closePopu()">&#10005;</a>';
					$('#schemaPopu .topBox').append(inner);
					
					if(data.schemaYn == "Y"){
						inner = "";
						inner += '<button type="button" class="state-highlight" onclick="tmpltSchemaUpdate()">수정</button>';
						inner += '<button type="button" class="state-submit" onclick="closePopu()">취소</button>';
						$("#schemaPopu .footer .buttonset").append(inner);
						
						if(data.tmpltSchemaList.length != 0){
							inner = "";
							for(var i = 0; i < data.tmpltSchemaList.length; i++){
								var sList = data.tmpltSchemaList[i];
								
								if(i < 4){
									inner += '<tr>';
									inner += '<th scope="row"><input id="designcheck_'+ (i + 1) +'" type="checkbox" checked="true" disabled><label for="designcheck_'+ (i + 1) +'"></label></th>';
									inner += '<td><input type="text" id="itemNm" name="itemNm" value="' + sList.itemNm + '" readonly></td>';
									inner += '<td><input type="number" id="itemLen" name="itemLen" value="' + sList.itemLen + '" onkeypress="onlyNumber()" maxlength="5" oninput="maxLengthCheck(this)" readonly></td>';
									inner += '<td>' + sList.itemAcmsLen + '</td>';
									inner += '<td><input type="number" id="itemLocSeqn" name="itemLocSeqn" value="' + sList.itemLocSeqn + '" onkeypress="onlyNumber()" maxlength="3" oninput="maxLengthCheck(this)" readonly></td>';
									inner += '</tr>';
								} else{
									inner += '<tr>';
									inner += '<th scope="row"><input id="designcheck_'+ (i + 1) +'" name="chk_info" type="checkbox"><label for="designcheck_'+ (i + 1) +'"></label></th>';
									inner += '<td><input type="text" id="itemNm" name="itemNm" value="' + sList.itemNm + '"></td>';
									inner += '<td><input type="number" id="itemLen" name="itemLen" value="' + sList.itemLen + '" onkeypress="onlyNumber()" maxlength="5" oninput="maxLengthCheck(this)"></td>';
									inner += '<td>' + sList.itemAcmsLen + '</td>';
									inner += '<td><input type="number" id="itemLocSeqn" name="itemLocSeqn" value="' + sList.itemLocSeqn + '" onkeypress="onlyNumber()" maxlength="3" oninput="maxLengthCheck(this)"></td>';
									inner += '</tr>';	
								}
							}
							$("#schemaPopu #tmpltSchemaList").append(inner);

							for(var i = 0; i < data.tmpltSchemaList.length; i++){
								$("#designcheck_"+(i+1)).attr("checked", true);
							}
							
							idx = data.tmpltSchemaList.length + 1;
						}
					}
					
					if(data.schemaYn == "N"){
						inner = "";
						inner += '<button type="button" class="state-highlight" onclick="tmpltSchemaInsert()">저장</button>';
						inner += '<button type="button" class="close" onclick="closePopu()">닫기</button>';
						$("#schemaPopu .footer .buttonset").append(inner);
						
						inner = "";
						inner += '<tr>';
						inner += '<th scope="row"><input id="designcheck_1" type="checkbox" checked="true" disabled><label for="designcheck_1"></label></th>';
						inner += '<td><input type="text" id="itemNm" name="itemNm" value="name" readonly></td>';
						inner += '<td><input type="number" id="itemLen" name="itemLen" value="30" onkeypress="onlyNumber()" maxlength="5" oninput="maxLengthCheck(this)" readonly></td>';
						inner += '<td>30</td>';
						inner += '<td><input type="number" id="itemLocSeqn" name="itemLocSeqn" value="1" onkeypress="onlyNumber()" maxlength="3" oninput="maxLengthCheck(this)" readonly></td>';
						inner += '</tr>';
						inner += '<tr>';
						inner += '<th scope="row"><input id="designcheck_2" type="checkbox" checked="true" disabled><label for="designcheck_2"></label></th>';
						inner += '<td><input type="text" id="itemNm" name="itemNm" value="ciNum" readonly></td>';
						inner += '<td><input type="number" id="itemLen" name="itemLen" value="88" onkeypress="onlyNumber()" maxlength="5" oninput="maxLengthCheck(this)" readonly></td>';
						inner += '<td>118</td>';
						inner += '<td><input type="number" id="itemLocSeqn" name="itemLocSeqn" value="2" onkeypress="onlyNumber()" maxlength="3" oninput="maxLengthCheck(this)" readonly></td>';
						inner += '</tr>';
						inner += '<tr>';
						inner += '<th scope="row"><input id="designcheck_3" type="checkbox" checked="true" disabled><label for="designcheck_3"></label></th>';
						inner += '<td><input type="text" id="itemNm" name="itemNm" value="brdt" readonly></td>';
						inner += '<td><input type="number" id="itemLen" name="itemLen" value="6" onkeypress="onlyNumber()" maxlength="5" oninput="maxLengthCheck(this)" readonly></td>';
						inner += '<td>124</td>';
						inner += '<td><input type="number" id="itemLocSeqn" name="itemLocSeqn" value="3" onkeypress="onlyNumber()" maxlength="3" oninput="maxLengthCheck(this)" readonly></td>';
						inner += '</tr>';
						inner += '<tr>';
						inner += '<th scope="row"><input id="designcheck_4" type="checkbox" checked="true" disabled><label for="designcheck_4"></label></th>';
						inner += '<td><input type="text" id="itemNm" name="itemNm" value="sexCls" readonly></td>';
						inner += '<td><input type="number" id="itemLen" name="itemLen" value="1" onkeypress="onlyNumber()" maxlength="5" oninput="maxLengthCheck(this)" readonly></td>';
						inner += '<td>125</td>';
						inner += '<td><input type="number" id="itemLocSeqn" name="itemLocSeqn" value="4" onkeypress="onlyNumber()" maxlength="3" oninput="maxLengthCheck(this)" readonly></td>';
						inner += '</tr>';
						
						$("#schemaPopu #tmpltSchemaList").append(inner);
						idx = 5;
					}
					
				},complete : function(data) {
					
				}
			});	
		}
		
		//저장
		function tmpltSchemaInsert(){	
			if(reqValOnCheckTable('tmpltSchemaList', 'chk_info', 4, 'itemNm', '항목(칼럼)명')) return;
			if(reqValOnCheckTable('tmpltSchemaList', 'chk_info', 4, 'itemLen', '최대길이')) return;
			if(reqValOnCheckTable('tmpltSchemaList', 'chk_info', 4, 'itemLocSeqn', '값위치(순서)')) return;
			
			var checkPoint = 0;
			
			var inputChk = "true,true,true,true,";
			
			var index = 0;
			//inputChk에 체크중인지 아닌지 파라메터 넘김
			$('#tmpltSchemaList tr').each(function(){
				if(index > 3){
					var chk = $(this).find("input[name=chk_info]").is(":checked");
					inputChk += chk + ",";
					
					if(chk == true) checkPoint++;		
				}
				index++;
			});
			
			if(checkPoint == 0){
				alert("체크된 항목이 없습니다.");
				return;
			}
			
			var itemNm = "";
			var itemLen = "";
			var itemLocSeqn = "";
			
			$('#tmpltSchemaList tr').each(function(){
				itemNm += $(this).find('#itemNm').val() + ",";
				itemLen += $(this).find('#itemLen').val() + ",";
				itemLocSeqn += $(this).find('#itemLocSeqn').val() + ",";
			});
			
			//값 위치(순서) 중복체크
			var length = $("#tmpltSchemaList tr").length;
			var target = new Array();
			for(var i = 0; i < length; i++){
				var chk1 = $("input[id^=designcheck_]:eq("+i+")");
				
				for(var j = 0; j < length; j++){
					var chk2 = $("input[id^=designcheck_]:eq("+j+")");
					
					if(chk1.is(":checked") && chk2.is(":checked")){
						if(i != j && $("input[name=itemNm]:eq("+i+")").val() == $("input[name=itemNm]:eq("+j+")").val()) {
							alert((i+1) + "번째 행과 " +(j+1) + "번째 행의 항목(칼럼)명이 같습니다. \n같은값은 사용할 수 없습니다.");
							$("input[name=itemNm]:eq("+j+")").focus();
							return;
						}
						if(i != j && $("input[name=itemLocSeqn]:eq("+i+")").val() == $("input[name=itemLocSeqn]:eq("+j+")").val()) {
							alert((i+1) + "번째 행과 " +(j+1) + "번째 행의 값위치(순서)가 같습니다. \n같은값은 사용할 수 없습니다.");
							$("input[name=itemLocSeqn]:eq("+j+")").focus();
							return;
						}	
					}
				}
				
				if(chk1.is(":checked")){
					target.push($("input[name=itemLocSeqn]:eq("+i+")").val());	
				}	
			}
			
			target.sort(function(a, b){ return a-b; });
			
			for(var i = 0; i < target.length; i++){
				if(target[i] != (i + 1)) {
					alert("값위치(순서)에 " + (i+1) + "값이 누락되었습니다.");
					return;
				}
			}
			
			var parameters = { 
					tmpltCd			: $('#editTmpltCd').val(),
					sndnOganCd		: $('#editOganCd').val(),
					inputChk		: inputChk,
					itemNm			: itemNm,
					itemLen			: itemLen,
					itemLocSeqn		: itemLocSeqn,
				};
					
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "tmpltSchemaInsert.ajax",
				data : parameters,
				success : function(data) {
					alert("저장되었습니다.");
					jQuery("#schemaPopu").hide();
					
					tmpltMgntSelect();
				},
				complete : function(data) {
				}
			});
			
		}
		
		//수정
		function tmpltSchemaUpdate(){	
			if(reqValOnCheckTable('tmpltSchemaList', 'chk_info', 4, 'itemNm', '항목(칼럼)명')) return;
			if(reqValOnCheckTable('tmpltSchemaList', 'chk_info', 4, 'itemLen', '최대길이')) return;
			if(reqValOnCheckTable('tmpltSchemaList', 'chk_info', 4, 'itemLocSeqn', '값위치(순서)')) return;
			
			var checkPoint = 0;
			
			var inputChk = "true,true,true,true,";
			
			var index = 0;
			//inputChk에 체크중인지 아닌지 파라메터 넘김
			$('#tmpltSchemaList tr').each(function(){
				if(index > 3){
					var chk = $(this).find("input[name=chk_info]").is(":checked");
					inputChk += chk + ",";
					
					if(chk == true) checkPoint++;		
				}
				
				index++;
			});
			
			if(checkPoint == 0){
				alert("체크된 항목이 없습니다.");
				return;
			}
			
			var itemNm = "";
			var itemLen = "";
			var itemLocSeqn = "";
			
			$('#tmpltSchemaList tr').each(function(){
				itemNm += $(this).find('#itemNm').val() + ",";
				itemLen += $(this).find('#itemLen').val() + ",";
				itemLocSeqn += $(this).find('#itemLocSeqn').val() + ",";
			});
			
			//값 위치(순서) 중복체크
			var length = $("#tmpltSchemaList tr").length;
			var target = new Array();
			for(var i = 0; i < length; i++){
				var chk1 = $("input[id^=designcheck_]:eq("+i+")");
				
				for(var j = 0; j < length; j++){
					var chk2 = $("input[id^=designcheck_]:eq("+j+")");
					
					if(chk1.is(":checked") && chk2.is(":checked")){
						if(i != j && $("input[name=itemNm]:eq("+i+")").val() == $("input[name=itemNm]:eq("+j+")").val()) {
							alert((i+1) + "번째 행과 " +(j+1) + "번째 행의 항목(칼럼)명이 같습니다. \n같은값은 사용할 수 없습니다.");
							$("input[name=itemNm]:eq("+j+")").focus();
							return;
						}
						if(i != j && $("input[name=itemLocSeqn]:eq("+i+")").val() == $("input[name=itemLocSeqn]:eq("+j+")").val()) {
							alert((i+1) + "번째 행과 " +(j+1) + "번째 행의 값위치(순서)가 같습니다. \n같은값은 사용할 수 없습니다.");
							$("input[name=itemLocSeqn]:eq("+j+")").focus();
							return;
						}	
					}
				}
				
				if(chk1.is(":checked")){
					target.push($("input[name=itemLocSeqn]:eq("+i+")").val());	
				}	
			}
			
			target.sort(function(a, b){ return a-b; });
			
			for(var i = 0; i < target.length; i++){
				if(target[i] != (i + 1)) {
					alert("값위치(순서)에 " + (i+1) + "값이 누락되었습니다.");
					return;
				}
			}
			
			var parameters = {  
					tmpltCd			: $('#editTmpltCd').val(),
					sndnOganCd		: $('#editOganCd').val(),
					inputChk		: inputChk,
					itemNm			: itemNm,
					itemLen			: itemLen,
					itemLocSeqn		: itemLocSeqn,
				};

			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "tmpltSchemaInsert.ajax",
				data : parameters,
				success : function(data) {
					alert("수정되었습니다.");
					jQuery("#schemaPopu").hide();
					

					tmpltMgntSelect();
				},
				complete : function(data) {
				}
			});
			
		}
		
		//행추가
		function addRow(){
			var tmpltSchemaListTr = '';
			
			tmpltSchemaListTr += '<tr>';
			tmpltSchemaListTr += '<th scope="row"><input id="designcheck_' + idx + '" name="chk_info" type="checkbox"><label for="designcheck_' + idx + '"></label></th>';
			tmpltSchemaListTr += '<td><input type="text" id="itemNm" name="itemNm"></td>';
			tmpltSchemaListTr += '<td><input type="number" id="itemLen" name="itemLen" onkeypress="onlyNumber()" maxlength="5" oninput="maxLengthCheck(this)"></td>';
			tmpltSchemaListTr += '<td></td>';
			tmpltSchemaListTr += '<td><input type="number" id="itemLocSeqn" name="itemLocSeqn" onkeypress="onlyNumber()" maxlength="3" oninput="maxLengthCheck(this)"></td>';
			tmpltSchemaListTr += '</tr>';
			
			$('#tmpltSchemaList').append(tmpltSchemaListTr);
			$("#designcheck_"+idx).attr("checked", true);
			$("input[name=itemNm]:eq("+(idx-1)+")").focus();
			
			idx++;
		}

		//goTmpltEdit(템플릿 등록/수정) 페이지 이동
		function goTmpltEdit(OganCd, tmpltCd){
			$("#editOganCd").val(OganCd);
			$("#editTmpltCd").val(tmpltCd);
			
			var tmpltEditForm = document.getElementById("tmpltEdit");
			tmpltEditForm.action = "goTmpltEdit";
			tmpltEditForm.method = "post";
			tmpltEditForm.submit();
		}

		function callBack(sel_id, codeNum){
			$("#inputTmpltUseYn").val('${inputTmpltUseYn}');
			
			if(sel_id == "#inputSndnOgan") {
				if($('#athLev').val() == "00"){
					$(sel_id).prepend('<option value="" selected="true">전체</option>');	
				}
				if($('#athLev').val() == "01"){
					$("#inputSndnOgan").prop("disabled", "disabled");
				}
				var value = '${inputSndnOgan}';
				
				if(value == "") return;
				
				var size = $("#inputSndnOgan option").size();
				for(var i = 0; i < size; i++){
					var selector = $("#inputSndnOgan option:eq("+ i +")");
					if(selector.val() == value){
						selector.attr("selected", "selected");
					}
				}
				
			}
		}

		function radioController(radio_id, isController){
			$("input[name="+radio_id+"]").attr("disabled", isController);
		}
		
		//팝업 닫기
        function closePopu(){
        	jQuery(".layerBox").hide();
        }
	</script>
</head>

<body>
<form id="tmpltEdit">
<input type="hidden" id="editOganCd" name="editOganCd"/>
<input type="hidden" id="editTmpltCd" name="editTmpltCd"/>
</form>
<form id="tmpltMgnt">
<input type="hidden" id="athLev" name="athLev" value="${athLev}"/> 
    <!-- wrap -->
    <div id="wrap">
        <!-- contents -->
        <div id="contents">
            <!-- section -->
            <div class="section">
                <!-- caption-pnl -->
                <div class="caption-pnl">
                    <h2>템플릿목록</h2>
                    <div class="buttonset">
                        <button type="button" class="state-highlight" id="btnIns" onclick="tmpltInfoInsert()" disabled="false">저장</button>
                        <button type="button" id="btnUpd" onclick="tmpltInfoUpdate()" disabled="false">수정</button>
                    </div>
                </div>
                <!-- //caption-pnl -->
                <!-- search-pnl -->
                <div class="search-pnl">
                    <table>
                        <colgroup>
                            <col width="140px">
                            <col width="200px">
                            <col width="110px">
                            <col width="200px">
                            <col width="110px">
                            <col width="200px">
                            <col width="*">
                            <col width="200px">
                        </colgroup>
                        <tr>
                            <th>기관(회사)명</th>
                            <td><select id="inputSndnOgan" name="inputSndnOgan"></select></td>
                            <th>템플릿명</th>
                            <td><input type="text" size="8" id="inputTmpltNm" name="inputTmpltNm" value="${inputTmpltNm}" onKeyDown="enterSerch()"></td>
                            <th>사용여부</th>
                            <td>
                            	<select id="inputTmpltUseYn" name="inputTmpltUseYn">
                            	<option value="" selected>전체</option>
                            	<option value="Y">예</option>
                            	<option value="N">아니오</option>
                            	</select>
                            </td>
                            <td></td>
                            <td class="buttonset"><button type="button" onclick="tmpltMgntSelect()">조회</button></td>
                        </tr>
                    </table>
                </div>
                <!-- //search-pnl -->
                <!-- grid-pnl -->
                <div class="grid-pnl" style="overflow-x:hidden; overflow-y:auto; height:300px;">
                    <!-- table -->
                    <table class="align-center">
                        <colgroup>
                            <col width="17%">
                            <col width="27%">
                            <col width="17%">
                            <col width="13%">
                            <col width="13%">
                            <col width="13%">
                        </colgroup>
                        <thead>
                            <tr>
                                <th scope="col">기관(회사)명</th>
                                <th scope="col">템플릿명</th>
                                <th scope="col">템플릿코드</th>
                                <th scope="col">스키마</th>
                                <th scope="col">템플릿</th>
                                <th scope="col">사용여부</th>
                            </tr>
                        </thead>
                        <tbody id="tmpltMgntList">
                        	<c:forEach var="tmpltMgntList" items="${tmpltMgntList}">
				          		<tr>
									<td>${tmpltMgntList.sndnOgan}</td>
									<td style="cursor:pointer;" onclick="tmpltInfoSelect('${tmpltMgntList.tmpltCd}')">${tmpltMgntList.tmpltNm}</td>
									<td>${tmpltMgntList.tmpltCd}</td>
									<c:if test="${tmpltMgntList.tmpltSchemaYn.equals('Y')}">
									<td onclick="goTmpltSchemaPopu('${tmpltMgntList.sndnOganCd}', '${tmpltMgntList.tmpltCd}')"><button type="button" class="image-folder"></button></td>
									</c:if>
									<c:if test="${tmpltMgntList.tmpltSchemaYn.equals('N')}">
									<td onclick="goTmpltSchemaPopu('${tmpltMgntList.sndnOganCd}', '${tmpltMgntList.tmpltCd}')"><button type="button" class="image-plus"></button></td>
									</c:if>
									<c:if test="${tmpltMgntList.tmpltCrtYn.equals('Y')}">
									<td onclick="goTmpltEdit('${tmpltMgntList.sndnOganCd}', '${tmpltMgntList.tmpltCd}')"><button type="button" class="image-folder"></button></td>
									</c:if>
									<c:if test="${tmpltMgntList.tmpltCrtYn.equals('N')}">
									<td onclick="goTmpltEdit('${tmpltMgntList.sndnOganCd}', '${tmpltMgntList.tmpltCd}')"><button type="button" class="image-plus"></td>
									</c:if>
									<c:if test="${tmpltMgntList.tmpltUseYn.equals('Y')}">
									<td>예</td>
									</c:if>
									<c:if test="${tmpltMgntList.tmpltUseYn.equals('N')}">
									<td>아니오</td>
									</c:if>
								</tr>
							</c:forEach>	
                        </tbody>
                    </table>
                    <!-- //table -->
                </div>
                <!-- //grid-pnl -->

                <!-- caption-pnl -->
            	<div class="caption-pnl">
                	<h3>템플릿기본</h3>
					<div class="buttonset">
					<button type="button" onclick="tmpltInfoInit()">초기화</button>
					</div>
                </div>
                <!-- //caption-pnl -->
                <div class="colgroup-wrap">
                    <div class="colgroup-1-2">
                        <!-- table-pnl -->
                        <div class="table-pnl">
                            <!-- table -->
                            <table class="vtable">
                                <colgroup><col width="150px"><col width="*"></colgroup>
                                <tbody>
                                <tr>
                                    <th>기관(회사)<span style="color: #fea338;">*</span></th>
                                    <td><select id="sndnOgan" name="sndnOgan" onchange="tmpltCdSet()"></select></td>
                                </tr>
                                <tr>
                                    <th>템플릿코드<span style="color: #fea338;">*</span></th>
                                    <td><input type="text" id="tmpltCd" name="tmpltCd" readonly></td>
                                </tr>
                                <tr>
                                    <th>스키마생성여부<span style="color: #fea338;">*</span></th>
                                    <td><div class="select_group">
                                        <span><input type="radio" id="radio_yes" name="tmpltSchemaYn" value="Y" disabled><label for="radio_yes"></label><label for="radio_yes">예</label></span>
                                        <span><input type="radio" id="radio_no" name="tmpltSchemaYn" value="N" checked="checked" disabled><label for="radio_no"></label><label for="radio_no">아니오</label></span>
                                    </div></td>
                                </tr>
                                <tr>
                                    <th>템플릿설명</th>
                                    <td><input type="text" id="tmpltDesc" name="tmpltDesc"></td>
                                </tr>
                                
                                </tbody>
                            </table>
                            <!-- //table -->
                        </div>
                        <!-- //table-pnl -->
                    </div>
                    <div class="colgroup-1-2">
                        <!-- table-pnl -->
                        <div class="table-pnl">
                            <!-- table -->
                            <table class="vtable">
                                <colgroup><col width="150px"><col width="*"></colgroup>
                                <tbody>
                                <tr>
                                    <th>템플릿명<span style="color: #fea338;">*</span></th>
                                    <td><input type="text" id="tmpltNm" name="tmpltNm"></td>
                                </tr>
                                <tr>
                                    <th>사용여부<span style="color: #fea338;">*</span></th>
                                    <td><div class="select_group">
                                        <span><input type="radio" id="radio_yes2" name="tmpltUseYn" value="Y" checked="checked"><label for="radio_yes2"></label><label for="radio_yes2">예</label></span>
                                        <span><input type="radio" id="radio_no2" name="tmpltUseYn" value="N"><label for="radio_no2"></label><label for="radio_no2">아니오</label></span>
                                    </div></td>
                                </tr>
                                <tr>
                                    <th>템플릿생성여부<span style="color: #fea338;">*</span></th>
                                    <td><div class="select_group">
                                        <span><input type="radio" id="radio_yes3" name="tmpltCrtYn" value="Y" disabled><label for="radio_yes3"></label><label for="radio_yes3">예</label></span>
                                        <span><input type="radio" id="radio_no3" name="tmpltCrtYn" value="N" checked="checked" disabled><label for="radio_no3"></label><label for="radio_no3">아니오</label></span>
                                    </div></td>
                                </tr>
                                </tbody>
                            </table>
                            <!-- //table -->
                        </div>
                        <!-- //table-pnl -->
                    </div>
                </div>
            </div>
            <!--// section -->
        </div>
        <!-- //contents -->

    </div>
    <!-- //wrap -->
</form>

	<div id="schemaPopu" class="layerBox" style="display:none;">
		<div class="layer">
			<div class="topBox">
			</div>
			<div class="contBox">
				<div class="section">
					<div class="caption-pnl">
						<div class="buttonset">
						    <button type="button" class="icon-plus" onclick="addRow()">행추가</button>
						    <button type="button" class="icon-minus" onclick="table_row_remove('chk_info')">행삭제</button>
						</div>
					</div>
					<div class="grid-pnl" style="overflow-x:hidden; overflow-y:auto; height:300px;">
						<table class="align-center">
							 <colgroup>
	                            <col width="5%">
	                            <col width="50%">
	                            <col width="15%">
	                            <col width="15%">
	                            <col width="15%">
	                        </colgroup>
	                        <thead>
	                            <tr>
	                                <th scope="col"><input type="checkbox"  onclick="chkBoxMgnt('tmpltSchemaList', $(this))" id="designcheck"><label for="designcheck"></label></th>
	                                <th scope="col">항목(칼럼)명</th>
	                                <th scope="col">최대길이</th>
	                                <th scope="col">누적길이</th>
	                                <th scope="col">값위치(순서)</th>
	                            </tr>
	                        </thead>
	                        <tbody id="tmpltSchemaList">
	                        </tbody>
		                </table>
					</div>
					<!-- footer -->
					<div class="footer">
						<div class="buttonset">
						
						</div>
					</div>
					<!-- //footer -->
				</div>
			</div>
			<!-- //contBox -->
		</div>
	<div class="fade"></div>
	</div>
</body>
</html>