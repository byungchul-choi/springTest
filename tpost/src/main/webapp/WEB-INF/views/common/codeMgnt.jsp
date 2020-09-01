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
    <title>코드관리</title>

    <!-- CSS  STYLE -->
	<link rel="stylesheet" href="/tpost/resource/css/common.css">
	<!-- ---------- -->
	<style>
        td > input, td > select {box-sizing: border-box; width:100%}
        .table-pnl{margin-top: 15px !important;}
        .layerBox .layer {width:430px; height:470px;}
        .layerBox .table-pnl {margin:15px}
        .layer .footer {position: absolute; bottom: 28px; left: 50%; width:100%; transform: translate(-50%, 0);}
    </style>
	<!--     JS     -->
	<script src="/tpost/resource/js/jquery.min.js"></script>
	<script src="/tpost/resource/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="/tpost/resource/js/jquery-ui.min.js"></script>
	<script src="/tpost/resource/js/comm.js"></script>
	<!-- ---------- -->
    <script type="text/javascript">
	    $('document').ready(function(){	
	    	var cfcd = "";
			var bascd = "";
			var dtcd = "";
			
			if($("#cfcdTable tr:eq(0)>td:eq(0)").html() != null) 
				cfcd = $("#cfcdTable tr:eq(0)>td:eq(0)").html();
			
			if($("#bascTable tr:eq(0)>td:eq(0)").html() != null) 
				bascd = $("#bascTable tr:eq(0)>td:eq(0)").html();
			
			if($("#dtcdTable tr:eq(0)>td:eq(0)").html() != null) 
				dtcd = $("#dtcdTable tr:eq(0)>td:eq(0)").html();
			
			brkdSelect(cfcd, bascd, dtcd);
		});
    
	    function enterSerch(){
	    	if(window.event.keyCode == 13){
	    		codeMgntSelect();
	    	}
	    }
	    
	    //팝업 닫기
        function closePopu(){
        	jQuery(".layerBox").hide();
        }
    	
		//상단 조회 버튼 클릭 시
		function codeMgntSelect(){
	  		var codeMgntForm = document.getElementById("codeMgnt");
	  		codeMgntForm.action = "codeMgntSelect";
	  		codeMgntForm.method = "post";
	  		codeMgntForm.submit();
		}
		
		//분류코드 명 클릭 시 기본코드 조회
		function cfcdToBascSelect(cfcd){
			var cfcd = cfcd;
			var bascd = "";
			var dtcd = "";
			
			var parameters = {
					inputCfcd : cfcd,
					inputBasc : '',
					inputDtcd : '',
					inputBascNm : '',
			}
			
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "cfcdToBascSelect.ajax",
				data : parameters,
				success : function(data) {
					$('#bascTable').html('');
					$('#dtcdTable').html('');
					
					var inner = '';
					if(data.bascdLst.length > 0){
						inner = '';
						bascd = data.bascdLst[0].basc; 
						
						for(var i = 0; i < data.bascdLst.length; i++){
							var bascdLst = data.bascdLst[i];
							
							inner += '<tr>';
							inner += '<td class="text-c" onclick="updBascPopup(\'' + bascdLst.cfcd + '\', \'' + bascdLst.basc + '\')">'+ bascdLst.basc +'</td>';
							inner += '<td onclick="bascToDtcdSelect(\'' + bascdLst.cfcd + '\', \'' + bascdLst.basc + '\')">' + bascdLst.bascNm + '</td>';
							inner += '</tr>';
						}

						$('#bascTable').append(inner);
						
						if(data.dtcdLst.length > 0){
							inner = '';
							dtcd = data.dtcdLst[0].dtcd;
							
							for(var i = 0; i < data.dtcdLst.length; i++){
								var dtcdLst = data.dtcdLst[i];
								
								inner += '<tr>';
								inner += '<td class="text-c" onclick="updDtcdPopup(\'' + dtcdLst.cfcd + '\', \'' + dtcdLst.basc + '\' , \'' + dtcdLst.dtcd + '\')">' + dtcdLst.dtcd + '</td>';
								inner += '<td onclick="brkdSelect(\'' + dtcdLst.cfcd + '\', \'' + dtcdLst.basc + '\' , \'' + dtcdLst.dtcd + '\')">' + dtcdLst.dtcdNm + '</td>';
								inner += '</tr>';
							}
							
							$('#dtcdTable').append(inner);
						}
					}
					
					brkdSelect(cfcd, bascd, dtcd);
				},
				complete : function(data) {
				}
			});
		}
		
		//기본코드 명 클릭 시 세부코드 조회
		function bascToDtcdSelect(cfcd, basc){
			var cfcd = cfcd;
			var bascd = basc;
			var dtcd = "";
			
			var parameters = {
					inputCfcd : cfcd,
					inputBasc : basc,
					inputDtcd : '',
			}
			
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "bascToDtcdSelect.ajax",
				data : parameters,
				success : function(data) {
					$('#dtcdTable').html('');
					
					var inner = '';
					if(data.dtcdLst.length > 0){
						inner = '';
						dtcd = data.dtcdLst[0].dtcd; 
						
						for(var i = 0; i < data.dtcdLst.length; i++){
							var dtcdLst = data.dtcdLst[i];
							
							inner += '<tr>';
							inner += '<td class="text-c" onclick="updDtcdPopup(\'' + dtcdLst.cfcd + '\', \'' + dtcdLst.basc + '\' , \'' + dtcdLst.dtcd + '\')">' + dtcdLst.dtcd + '</td>';
							inner += '<td onclick="brkdSelect(\'' + dtcdLst.cfcd + '\', \'' + dtcdLst.basc + '\' , \'' + dtcdLst.dtcd + '\')">' + dtcdLst.dtcdNm + '</td>';
							inner += '</tr>';
						}
						
						$('#dtcdTable').append(inner);
					}
					
					brkdSelect(cfcd, bascd, dtcd);
				},
				complete : function(data) {
				}
			});
		}
		
		//하단의 상세 내역 조회
		function brkdSelect(cfcd, bascd, dtcd){
			var parameters = {
					cfcd : cfcd,
					basc : bascd,
					dtcd : dtcd,
			}
			
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "brkdSelect.ajax",
				data : parameters,
				success : function(data) {
					var brkd = data.brkd;
					
					document.getElementById('_cfcd').innerHTML = brkd.cfcd;
					document.getElementById('_cfcdNm').innerHTML = brkd.cfcdNm;
					document.getElementById('_cfcdDesc').innerHTML = brkd.cfcdDesc;
					
					document.getElementById('_basc').innerHTML = brkd.basc;
					document.getElementById('_bascNm').innerHTML = brkd.bascNm;
					brkd.basc == null ? document.getElementById('_bascUpr').innerHTML = "" :	document.getElementById('_bascUpr').innerHTML = brkd.cfcd;
					document.getElementById('_bascApplStDt').innerHTML = brkd.bascApplStDt;
					document.getElementById('_bascApplClosDt').innerHTML = brkd.bascApplClosDt;
					document.getElementById('_bascUseYn').innerHTML = brkd.bascUseYn;
					document.getElementById('_bascDesc').innerHTML = brkd.bascDesc;
					
					document.getElementById('_dtcd').innerHTML = brkd.dtcd;
					document.getElementById('_dtcdNm').innerHTML = brkd.dtcdNm;
					brkd.dtcd == null ? document.getElementById('_dtcdUpr').innerHTML = "" :	document.getElementById('_dtcdUpr').innerHTML = brkd.cfcd;
					document.getElementById('_dtcdApplStDt').innerHTML = brkd.dtcdApplStDt;
					document.getElementById('_dtcdApplClosDt').innerHTML = brkd.dtcdApplClosDt;
					document.getElementById('_dtcdUseYn').innerHTML = brkd.dtcdUseYn;
					document.getElementById('_dtcdDesc').innerHTML = brkd.dtcdDesc;
				},
				complete : function(data) {
				}
			});
		}
		
		//분류코드 등록 팝업
		function insCfcdPopup(){
			jQuery("#popuCfcd").show();
			
			$("#popuCfcd .topBox").html("");
			$("#popuCfcd .buttonset").html("");
			$("#popuCfcd #dtlCfcd").html("");
			
			var inner = '';
			inner += '<h3>분류코드 등록</h3>';
			inner += '<a class="close" href="#" onclick="closePopu()">&#10005;</a>';
			$('#popuCfcd .topBox').append(inner);
			
			inner = '';
			inner += '<button type="button" class="state-highlight" onclick="insCfcd()">저장</button>';
			inner += '<button type="button" class="close" onclick="closePopu()">닫기</button>';
			$("#popuCfcd .buttonset").append(inner);
			
			inner = "";
			inner += '<tr>';
			inner += '<th>분류코드<span style="color: #fea338;">*</span></th>';
			inner += '<td><input type="text" class="width100" id="cfcd" name="cfcd"></td>';
			inner += '</tr>';
			inner += '<tr>';
			inner += '<th>분류코드명<span style="color: #fea338;">*</span></th>';
			inner += '<td><input type="text" class="width100" id="cfcdNm" name="cfcdNm"></td>';
			inner += '</tr>';
			inner += '<tr>';
			inner += '<th>분류코드 설명</th>';
			inner += '<td><input type="text" class="width100" id="cfcdDesc" name="cfcdDesc"></td>';
			inner += '</tr>';
			$("#popuCfcd #dtlCfcd").append(inner);
		}
		
		//기본코드 등록 팝업
		function insBascPopup(){
			jQuery("#popuBasc").show();
			
			$("#popuBasc .topBox").html("");
			$("#popuBasc .buttonset").html("");
			$("#popuBasc #dtlBasc").html("");
			
			var inner = '';
			inner += '<h3>기본코드 등록</h3>';
			inner += '<a class="close" href="#" onclick="closePopu()">&#10005;</a>';
			$('#popuBasc .topBox').append(inner);
			
			inner = '';
			inner += '<button type="button" class="state-highlight" onclick="insBasc()">저장</button>';
			inner += '<button type="button" class="close" onclick="closePopu()">닫기</button>';
			$("#popuBasc .buttonset").append(inner);
			
			inner = '';
			inner += '<tr>';
			inner += '<th>기본코드<span style="color: #fea338;">*</span></th>';
			inner += '<td><input type="text" class="width100" id="basc" name="basc"></td>';
			inner += '</tr>';
			inner += '<tr>';
			inner += '<th>기본코드명<span style="color: #fea338;">*</span></th>';
			inner += '<td><input type="text" class="width100" id="bascNm" name="bascNm"></td>';
			inner += '</tr>';
			inner += '<tr>';
			inner += '<th>기본코드 설명</th>';
			inner += '<td><input type="text" class="width100" id="bascDesc" name="bascDesc"></td>';
			inner += '</tr>';
			inner += '<tr>';
			inner += '<th>적용시작일자<span style="color: #fea338;">*</span></th>';
			inner += '<td><input type="text" size="13" class="dateinput" id="bascApplStDt" name="bascApplStDt" onkeypress="dateFormat(this)" onkeypress="dateFormat(this)"></td>';
			inner += '</tr>';
			inner += '<tr>';
			inner += '<th>적용종료일자<span style="color: #fea338;">*</span></th>';
			inner += '<td><input type="text" size="13" class="dateinput" id="bascApplClosDt" name="bascApplClosDt" onkeypress="dateFormat(this)" onkeypress="dateFormat(this)"></td>';
			inner += '</tr>';
			inner += '<tr>';
			inner += '<th>사용여부<span style="color: #fea338;">*</span></th>';
			inner += '<td>';
			inner += '<select id="bascUseYn" name="bascUseYn" class="width25">';
			inner += '<option value="Y" selected="true">Y</option>';
			inner += '<option value="N">N</option>';
			inner += '</select>';
			inner += '</td>';
			inner += '</tr>';
			$("#popuBasc #dtlBasc").append(inner);
			
			getToday('bascApplStDt');
			$('#bascApplClosDt').val('9999-12-31');
			
			datepicker();
		}
		
		//상세코드 등록 팝업
		function insDtcdPopup(){
			jQuery("#popuDtcd").show();
			
			$("#popuDtcd .topBox").html("");
			$("#popuDtcd .buttonset").html("");
			$("#popuDtcd #dtlDtcd").html("");
			
			var inner = '';
			inner += '<h3>상세코드 등록</h3>';
			inner += '<a class="close" href="#" onclick="closePopu()">&#10005;</a>';
			$('#popuDtcd .topBox').append(inner);
			
			inner = '';
			inner += '<button type="button" class="state-highlight" onclick="insDtcd()">저장</button>';
			inner += '<button type="button" class="close" onclick="closePopu()">닫기</button>';
			$("#popuDtcd .buttonset").append(inner);
			
			inner = '';
			inner += '<tr>';
			inner += '<th>상세코드<span style="color: #fea338;">*</span></th>';
			inner += '<td><input type="text" class="width100" id="dtcd" name="dtcd"></td>';
			inner += '</tr>';
			inner += '<tr>';
			inner += '<th>상세코드명<span style="color: #fea338;">*</span></th>';
			inner += '<td><input type="text" class="width100" id="dtcdNm" name="dtcdNm"></td>';
			inner += '</tr>';
			inner += '<tr>';
			inner += '<th>상세코드 설명</th>';
			inner += '<td><input type="text" class="width100" id="dtcdDesc" name="dtcdDesc"></td>';
			inner += '</tr>';
			inner += '<tr>';
			inner += '<th>적용시작일자<span style="color: #fea338;">*</span></th>';
			inner += '<td><input type="text" size="13" class="dateinput" id="dtcdApplStDt" name="dtcdApplStDt" onkeypress="dateFormat(this)"></td>';
			inner += '</tr>';
			inner += '<tr>';
			inner += '<th>적용종료일자<span style="color: #fea338;">*</span></th>';
			inner += '<td><input type="text" size="13" class="dateinput" id="dtcdApplClosDt" name="dtcdApplClosDt" onkeypress="dateFormat(this)"></td>';
			inner += '</tr>';
			inner += '<tr>';
			inner += '<th>사용여부<span style="color: #fea338;">*</span></th>';
			inner += '<td>';
			inner += '<select id="dtcdUseYn" name="dtcdUseYn" class="width25">';
			inner += '<option value="Y" selected="true">Y</option>';
			inner += '<option value="N">N</option>';
			inner += '</select>';
			inner += '</td>';
			inner += '</tr>';
			$("#popuDtcd #dtlDtcd").append(inner);
			
			getToday('dtcdApplStDt');
			$('#dtcdApplClosDt').val('9999-12-31');
			
			datepicker();
		}
		
		//분류코드 등록
		function insCfcd(){
			if(reqValCheck('cfcd', '분류코드')) return;
			if(reqValCheck('cfcdNm', '분류코드명')) return;
			
			var parameters = { 
					cfcd		: $('#cfcd').val(),
					cfcdNm		: $('#cfcdNm').val(),
					cfcdDesc	: $('#cfcdDesc').val()
			}
			
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "cfcdInsert.ajax",
				data : parameters,
				success : function(data) {
					if(data.errorMessage == ""){
						alert("저장되었습니다.");
						closePopu();
						codeMgntSelect();	
					}
					else{
						alert(data.errorMessage);
					}
				},
				complete : function(data) {
				}
			});
		}
		
		//기본코드 등록
		function insBasc(){
			var cfcd = document.getElementById('_cfcd').innerHTML;
			
			var parameters = { 
					cfcd			: cfcd,
					basc			: $('#basc').val(),
					bascNm			: $('#bascNm').val(),
					bascDesc		: $('#bascDesc').val(),
					bascApplStDt	: $('#bascApplStDt').val(),
					bascApplClosDt 	: $('#bascApplClosDt').val(),
					bascUseYn 		: $('#bascUseYn').val(),
			}
			
			if(cfcd == ""){
				alert("분류코드를 먼저 선택해주세요.");
			}
			else{
				
				if(reqValCheck('basc', '기본코드')) return;
				if(reqValCheck('bascNm', '기본코드명')) return;
				if(reqValCheck('bascApplStDt', '적용시작일자')) return;
				if(reqValCheck('bascApplClosDt', '적용종료일자')) return;
				
				$.ajax({
					type : "post",
					dataType : "json",
					async : false,
					url : "bascInsert.ajax",
					data : parameters,
					success : function(data) {
						if(data.errorMessage == ""){
							alert("저장되었습니다.");
							closePopu();
							codeMgntSelect();	
						}
						else{
							alert(data.errorMessage);
						}
					},
					complete : function(data) {
					}
				});
			}
		}
		
		//상세코드 등록
		function insDtcd(){
			var cfcd = document.getElementById('_cfcd').innerHTML;
			var bascd = document.getElementById('_basc').innerHTML;
			
			var parameters = { 
					cfcd			: cfcd,
					basc			: bascd,
					dtcd			: $('#dtcd').val(),
					dtcdNm			: $('#dtcdNm').val(),
					dtcdDesc		: $('#dtcdDesc').val(),
					dtcdApplStDt	: $('#dtcdApplStDt').val(),
					dtcdApplClosDt 	: $('#dtcdApplClosDt').val(),
					dtcdUseYn 		: $('#dtcdUseYn').val(),
			}
			
			if(cfcd == "" || bascd == ""){
				alert("분류코드와 기본코드를 먼저 선택해주세요.");
			}
			else{
				if(reqValCheck('dtcd', '상세코드')) return;
				if(reqValCheck('dtcdNm', '상세코드명')) return;
				if(reqValCheck('dtcdApplStDt', '적용시작일자')) return;
				if(reqValCheck('dtcdApplClosDt', '적용종료일자')) return;
				
				$.ajax({
					type : "post",
					dataType : "json",
					async : false,
					url : "dtcdInsert.ajax",
					data : parameters,
					success : function(data) {
						if(data.errorMessage == ""){
							alert("저장되었습니다.");
							closePopu();
							codeMgntSelect();	
						}
						else{
							alert(data.errorMessage);
						}
					},
					complete : function(data) {
					}
				});
			}
		}
		
		//분류코드 수정 팝업
		function updCfcdPopup(cfcd){
			var parameters = {
					cfcd : cfcd,
					basc : '',
					dtcd : '',
			}
			
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "brkdSelect.ajax",
				data : parameters,
				success : function(data) {
					jQuery("#popuCfcd").show();
					
					$("#popuCfcd .topBox").html("");
					$("#popuCfcd .buttonset").html("");
					$("#popuCfcd #dtlCfcd").html("");
					
					var inner = '';
					inner += '<h3>분류코드 수정</h3>';
					inner += '<a class="close" href="#" onclick="closePopu()">&#10005;</a>';
					$('#popuCfcd .topBox').append(inner);
					
					inner = '';
					inner += '<button type="button" class="close" onclick="closePopu()">닫기</button>';
					$("#popuCfcd .buttonset").append(inner);
					
					inner = '';
					inner += '<tr>';
					inner += '<th>분류코드<span style="color: #fea338;">*</span></th>';
					inner += '<td><input type="text" class="width100" value="' + data.brkd.cfcd + '" id="cfcd" name="cfcd" readonly></td>';
					inner += '</tr>';
					inner += '<tr>';
					inner += '<th>분류코드명<span style="color: #fea338;">*</span></th>';
					inner += '<td><input type="text" class="width100" value="' + data.brkd.cfcdNm + '" id="cfcdNm" name="cfcdNm"></td>';
					inner += '</tr>';
					inner += '<tr>';
					inner += '<th>분류코드 설명</th>';
					inner += '<td><input type="text" class="width100" value="' + data.brkd.cfcdDesc + '" id="cfcdDesc" name="cfcdDesc"></td>';
					inner += '</tr>';
					$("#popuCfcd #dtlCfcd").append(inner);
				},complete : function(data) {
					datepicker();
				}
			});
		}
		
		
		//기본코드 수정 팝업
		function updBascPopup(cfcd, bascd){
			var parameters = {
					cfcd : cfcd,
					basc : bascd,
					dtcd : '',
			}
			
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "brkdSelect.ajax",
				data : parameters,
				success : function(data) {
					jQuery("#popuBasc").show();
					
					$("#popuBasc .topBox").html("");
					$("#popuBasc .buttonset").html("");
					$("#popuBasc #dtlBasc").html("");
					
					var inner = '';
					inner += '<h3>기본코드 수정</h3>';
					inner += '<a class="close" href="#" onclick="closePopu()">&#10005;</a>';
					$('#popuBasc .topBox').append(inner);
					
					inner = '';
					inner += '<button type="button" class="state-highlight" onclick="updBasc()">수정</button>';
					inner += '<button type="button" class="close" onclick="closePopu()">닫기</button>';
					$("#popuBasc .buttonset").append(inner);
					
					inner = '';
					inner += '<tr>';
					inner += '<th>기본코드<span style="color: #fea338;">*</span></th>';
					inner += '<td><input type="text" class="width100" id="basc" name="basc" value="' + data.brkd.basc + '" readonly></td>';
					inner += '</tr>';
					inner += '<tr>';
					inner += '<th>기본코드명<span style="color: #fea338;">*</span></th>';
					inner += '<td><input type="text" class="width100" id="bascNm" name="bascNm" value="' + data.brkd.bascNm + '"></td>';
					inner += '</tr>';
					inner += '<tr>';
					inner += '<th>기본코드 설명</th>';
					inner += '<td><input type="text" class="width100" id="bascDesc" name="bascDesc" value="' + data.brkd.bascDesc + '"></td>';
					inner += '</tr>';
					inner += '<tr>';
					inner += '<th>적용시작일자<span style="color: #fea338;">*</span></th>';
					inner += '<td><input type="text" size="13" class="dateinput" id="bascApplStDt" name="bascApplStDt" value="' + data.brkd.bascApplStDt + '" onkeypress="dateFormat(this)"></td>';
					inner += '</tr>';
					inner += '<tr>';
					inner += '<th>적용종료일자<span style="color: #fea338;">*</span></th>';
					inner += '<td><input type="text" size="13" class="dateinput" id="bascApplClosDt" name="bascApplClosDt" value="' + data.brkd.bascApplClosDt + '" onkeypress="dateFormat(this)"></td>';
					inner += '</tr>';
					inner += '<tr>';
					inner += '<th>사용여부<span style="color: #fea338;">*</span></th>';
					inner += '<td>';
					inner += '<select id="bascUseYn" name="bascUseYn" class="width25">';
					if(data.brkd.bascUseYn == "Y"){
						inner += '<option value="Y" selected="true">Y</option>';
						inner += '<option value="N">N</option>';			
					} else{
						inner += '<option value="Y">Y</option>';
						inner += '<option value="N" selected="true">N</option>';
					}
					inner += '</select>';
					inner += '</td>';
					inner += '</tr>';
					$("#popuBasc #dtlBasc").append(inner);
					
				},complete : function(data) {
					datepicker();
				}
			});
		}
		
		//상세코드 수정 팝업
		function updDtcdPopup(cfcd, bascd, dtcd){
			var parameters = {
					cfcd : cfcd,
					basc : bascd,
					dtcd : dtcd,
			}
			
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "brkdSelect.ajax",
				data : parameters,
				success : function(data) {
					jQuery("#popuDtcd").show();
					
					$("#popuDtcd .topBox").html("");
					$("#popuDtcd .buttonset").html("");
					$("#popuDtcd #dtlDtcd").html("");
					
					var inner = '';
					inner += '<h3>상세코드 수정</h3>';
					inner += '<a class="close" href="#" onclick="closePopu()">&#10005;</a>';
					$('#popuDtcd .topBox').append(inner);
					
					inner = '';
					inner += '<button type="button" class="state-highlight" onclick="updDtcd()">수정</button>';
					inner += '<button type="button" class="close" onclick="closePopu()">닫기</button>';
					$("#popuDtcd .buttonset").append(inner);
					
					inner = '';
					inner += '<tr>';
					inner += '<th>상세코드<span style="color: #fea338;">*</span></th>';
					inner += '<td><input type="text" class="width100" id="dtcd" name="dtcd" value="' + data.brkd.dtcd + '" readonly></td>';
					inner += '</tr>';
					inner += '<tr>';
					inner += '<th>상세코드명<span style="color: #fea338;">*</span></th>';
					inner += '<td><input type="text" class="width100" id="dtcdNm" name="dtcdNm" value="' + data.brkd.dtcdNm + '"></td>';
					inner += '</tr>';
					inner += '<tr>';
					inner += '<th>상세코드 설명</th>';
					inner += '<td><input type="text" class="width100" id="dtcdDesc" name="dtcdDesc" value="' + data.brkd.dtcdDesc + '"></td>';
					inner += '</tr>';
					inner += '<tr>';
					inner += '<th>적용시작일자<span style="color: #fea338;">*</span></th>';
					inner += '<td><input type="text" size="13" class="dateinput" id="dtcdApplStDt" name="dtcdApplStDt" value="' + data.brkd.dtcdApplStDt + '" onkeypress="dateFormat(this)"></td>';
					inner += '</tr>';
					inner += '<tr>';
					inner += '<th>적용종료일자<span style="color: #fea338;">*</span></th>';
					inner += '<td><input type="text" size="13" class="dateinput" id="dtcdApplClosDt" name="dtcdApplClosDt" value="' + data.brkd.dtcdApplClosDt + '" onkeypress="dateFormat(this)"></td>';
					inner += '</tr>';
					inner += '<tr>';
					inner += '<th>사용여부<span style="color: #fea338;">*</span></th>';
					inner += '<td>';
					inner += '<select id="dtcdUseYn" name="dtcdUseYn" class="width25">';
					if(data.brkd.dtcdUseYn == "Y"){
						inner += '<option value="Y" selected="true">Y</option>';
						inner += '<option value="N">N</option>';			
					} else{
						inner += '<option value="Y">Y</option>';
						inner += '<option value="N" selected="true">N</option>';
					}
					inner += '</select>';
					inner += '</td>';
					inner += '</tr>';
					$("#popuDtcd #dtlDtcd").append(inner);
					
				},complete : function(data) {
					datepicker();
				}
			});
		}
		
		
		//기본코드 수정
		function updBasc(){
			var cfcd = document.getElementById('_cfcd').innerHTML;
			
			var parameters = { 
					cfcd			: cfcd,
					basc			: $('#basc').val(),
					bascNm			: $('#bascNm').val(),
					bascDesc		: $('#bascDesc').val(),
					bascApplStDt	: $('#bascApplStDt').val(),
					bascApplClosDt 	: $('#bascApplClosDt').val(),
					bascUseYn 		: $('#bascUseYn').val(),
			}
			
			if(reqValCheck('basc', '기본코드')) return;
			if(reqValCheck('bascNm', '기본코드명')) return;
			if(reqValCheck('bascApplStDt', '적용시작일자')) return;
			if(reqValCheck('bascApplClosDt', '적용종료일자')) return;
			
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "bascUpdate.ajax",
				data : parameters,
				success : function(data) {
					alert("수정되었습니다.");
					closePopu();
					codeMgntSelect();
				},
				complete : function(data) {
				}
			});
			
		}
		
		//상세코드 수정
		function updDtcd(){
			var cfcd = document.getElementById('_cfcd').innerHTML;
			var bascd = document.getElementById('_basc').innerHTML;
			
			var parameters = { 
					cfcd			: cfcd,
					basc			: bascd,
					dtcd			: $('#dtcd').val(),
					dtcdNm			: $('#dtcdNm').val(),
					dtcdDesc		: $('#dtcdDesc').val(),
					dtcdApplStDt	: $('#dtcdApplStDt').val(),
					dtcdApplClosDt 	: $('#dtcdApplClosDt').val(),
					dtcdUseYn 		: $('#dtcdUseYn').val(),
			}
			
			if(reqValCheck('dtcd', '상세코드')) return;
			if(reqValCheck('dtcdNm', '상세코드명')) return;
			if(reqValCheck('dtcdApplStDt', '적용시작일자')) return;
			if(reqValCheck('dtcdApplClosDt', '적용종료일자')) return;
			
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "dtcdUpdate.ajax",
				data : parameters,
				success : function(data) {
					alert("수정되었습니다.");
					closePopu();
					codeMgntSelect();
				},
				complete : function(data) {
				}
			});
			
		}
    </script>

</head>

<body>
<form id="codeMgnt">
    <!-- wrap -->
    <div id="wrap">
        <!-- contents -->
        <div id="contents">
            <!-- section -->
            <div class="section">
                <!-- caption-pnl -->
                <div class="caption-pnl">
                    <h2>코드관리</h2>
                </div>
                <!-- //caption-pnl -->
                <!-- search-pnl -->
                <div class="search-pnl">
                    <div class="buttonset">
                    </div>
                    <table>
                        <colgroup>
                            <col width="120px" />
                            <col width="*" />
                            <col width="120px" />
                            <col width="*" />
                            <col width="120px" />
                            <col width="*" />
                            <col width="120px" />
                            <col width="*" />
                            <col width="200px" />
                        </colgroup>
                        <tr>
                            <th>분류코드</th>
                            <td><input type="text" size="8" id="inputCfcd" name="inputCfcd" value='${inputCfcd}' onKeyDown="enterSerch()"></td>
                            <th>분류코드명</th>
                            <td><input type="text" size="8" id="inputCfcdNm" name="inputCfcdNm" value='${inputCfcdNm}' onKeyDown="enterSerch()"></td>
                            <th>기본코드</th>
                            <td><input type="text" size="8" id="inputBasc" name="inputBasc" value='${inputBasc}' onKeyDown="enterSerch()"></td>
                            <th>기본코드명</th>
                            <td><input type="text" size="8" id="inputBascNm" name="inputBascNm" value='${inputBascNm}' onKeyDown="enterSerch()"></td>
                            <td class="buttonset"><button type="button" onclick="codeMgntSelect()">조회</button>
                            </td>
                        </tr>
                    </table>
                </div>
                <!-- //search-pnl -->

                <!-- colgroup-wrap -->
                <div class="colgroup-wrap">
                    <div class="colgroup-1-3">
                        <div class="caption-pnl">
                            <div class="buttonset">
                                <button type="button" onclick="insCfcdPopup()">등록</button>
                            </div>
                        </div>
                        <div class="grid-pnl">
	                        <!-- table-pnl -->
	                        <div class="table-pnl" style="overflow-x:hidden; overflow-y:auto; height:300px;">
	                            <!-- table -->
	                            <table class="vtable bordertd">
	                                <colgroup>
	                                    <col width="120px">
	                                    <col width="*">
	                                </colgroup>
	                                <thead>
	                                    <tr>
	                                        <th>분류코드</th>
	                                        <th>분류코드명</th>
	                                    </tr>
	                                </thead>
	                                <tbody id="cfcdTable">
	                                <c:forEach var="codeMgnt" items="${cfcdLst}">
							            <tr>
							                <td class="text-c" onclick="updCfcdPopup('${codeMgnt.cfcd}')">${codeMgnt.cfcd}</td>
							                <td onclick="cfcdToBascSelect('${codeMgnt.cfcd}')">${codeMgnt.cfcdNm}</td>
							            </tr>
						       		</c:forEach>
	                                </tbody>
	                            </table>
	                            <!-- //table -->
	                        </div>
	                        <!-- //table-pnl -->
                        </div>
                    </div>
                    <div class="colgroup-1-3">
                        <div class="caption-pnl">
                            <div class="buttonset">
                                <button type="button" onclick="insBascPopup()">등록</button>
                            </div>
                        </div>
                        <div class="grid-pnl">
	                        <!-- table-pnl -->
	                        <div class="table-pnl" style="overflow-x:hidden; overflow-y:auto; height:300px;">
	                            <!-- table -->
	                            <table class="vtable bordertd">
	                                <colgroup>
	                                    <col width="120px">
	                                    <col width="*">
	                                </colgroup>
	                                <thead>
	                                    <tr>
	                                        <th>기본코드</th>
	                                        <th>기본코드명</th>
	                                    </tr>
	                                </thead>
	                                <tbody id="bascTable">
	                                <c:forEach var="codeMgnt" items="${bascdLst}">
							            <tr>
							                <td class="text-c" onclick="updBascPopup('${codeMgnt.cfcd}', '${codeMgnt.basc}')">${codeMgnt.basc}</td>
							                <td onclick="bascToDtcdSelect('${codeMgnt.cfcd}', '${codeMgnt.basc}')">${codeMgnt.bascNm}</td>
							            </tr>
						       		</c:forEach>
	                                </tbody>
	                            </table>
	                            <!-- //table -->
	                        </div>
	                        <!-- //table-pnl -->
                        </div>
                    </div>
                    <div class="colgroup-1-3">
                        <div class="caption-pnl">
                            <div class="buttonset">
                                <button type="button" onclick="insDtcdPopup()">등록</button>
                            </div>
                        </div>
                        <div class="grid-pnl">
	                        <!-- table-pnl -->
	                        <div class="table-pnl" style="overflow-x:hidden; overflow-y:auto; height:300px;">
	                            <!-- table -->
	                            <table class="vtable bordertd">
	                                <colgroup>
	                                    <col width="120px">
	                                    <col width="*">
	                                </colgroup>
	                                <thead>
	                                    <tr>
	                                        <th>상세코드</th>
	                                        <th>상세코드명</th>
	                                    </tr>
	                                </thead>
	                                <tbody id="dtcdTable">
	                                <c:forEach var="codeMgnt" items="${dtcdLst}">
							            <tr>
							                <td class="text-c" onclick="updDtcdPopup('${codeMgnt.cfcd}', '${codeMgnt.basc}', '${codeMgnt.dtcd}')">${codeMgnt.dtcd}</td>
							                <td onclick="brkdSelect('${codeMgnt.cfcd}', '${codeMgnt.basc}', '${codeMgnt.dtcd}')">${codeMgnt.dtcdNm}</td>
							            </tr>
					       			</c:forEach>
	                                </tbody>
	                            </table>
	                            <!-- //table -->
	                        </div>
	                        <!-- //table-pnl -->
                        </div>
                    </div>
                </div>
                <!-- // colgroup-wrap -->

                <div class="colgroup-wrap">
                    <!-- table-pnl -->
                    <div class="table-pnl">
                        <!-- table -->
                        <table>
                            <colgroup>
                                <col width="11%">
                                <col width="22%">
                                <col width="11%">
                                <col width="22%">
                                <col width="11%">
                                <col width="22%">
                            </colgroup>
                            <tbody id="detlCfcd">
                             <tr>
                                 <th>분류코드</th>
                                 <td id="_cfcd"></td>
                                 <th>분류코드명</th>
                                 <td id="_cfcdNm"></td>
                                 <th>분류코드설명</th>
                                 <td id="_cfcdDesc"></td>
                             </tr>
                            </tbody>
                        </table>
                        <!-- //table -->
                    </div>
                    <!-- //table-pnl -->
              	</div>
                <!--// colgroup-wrap -->
                
                <div class="colgroup-wrap">
                    <!-- table-pnl -->
                    <div class="table-pnl">
                        <!-- table -->
                        <table>
                            <colgroup>
                                <col width="11%">
                                <col width="22%">
                                <col width="11%">
                                <col width="22%">
                                <col width="11%">
                                <col width="22%">
                            </colgroup>
                            <tbody id="detlBasc">
                             <tr>
                                 <th>기본코드</th>
                                 <td id="_basc"></td>
                                 <th>기본코드명</th>
                                 <td id="_bascNm"></td>
                                 <th>상위분류코드</th>
                                 <td id="_bascUpr"></td>
                             </tr>
                             <tr>
                                 <th>적용시작일자</th>
                                 <td id="_bascApplStDt"></td>
                                 <th>적용종료일자</th>
                                 <td id="_bascApplClosDt"></td>
                                 <th>현재사용여부</th>
                                 <td id="_bascUseYn"></td>
                             </tr>
                             <tr>
                                 <th>기본코드설명</th>
                                 <td colspan="5" id="_bascDesc"></td>
                             </tr>
                            </tbody>
                        </table>
                        <!-- //table -->
                    </div>
                    <!-- //table-pnl -->
                </div>
                <!--// colgroup-wrap -->
                <div class="colgroup-wrap">
                    <!-- table-pnl -->
                    <div class="table-pnl">
                        <!-- table -->
                        <table>
                            <colgroup>
                                <col width="11%">
                                <col width="22%">
                                <col width="11%">
                                <col width="22%">
                                <col width="11%">
                                <col width="22%">
                            </colgroup>
                            <tbody id="detlDtcd">
                             <tr>
                                 <th>상세코드</th>
                                 <td id="_dtcd"></td>
                                 <th>상세코드명</th>
                                 <td id="_dtcdNm"></td>
                                 <th>상위분류코드</th>
                                 <td id="_dtcdUpr"></td>
                             </tr>
                             <tr>
                                 <th>적용시작일자</th>
                                 <td id="_dtcdApplStDt"></td>
                                 <th>적용종료일자</th>
                                 <td id="_dtcdApplClosDt"></td>
                                 <th>현재사용여부</th>
                                 <td id="_dtcdUseYn"></td>
                             </tr>
                             <tr>
                                 <th>상세코드설명</th>
                                 <td colspan="5" id="_dtcdDesc"></td>
                             </tr>
                            </tbody>
                        </table>
                        <!-- //table -->
                    </div>
                    <!-- //table-pnl -->
                </div>
                <!--// colgroup-wrap -->
        </div>
        <!-- //contents -->
    </div>
    <!-- //wrap -->
</form>

    <div id="popuCfcd" class="layerBox" style="display:none;">
		<div class="layer">
			<div class="topBox">
			</div>
			<div class="contBox">
				<div class="table-pnl">
					<table>
                        <colgroup>
                            <col width="160px">
                            <col width="*">
                        </colgroup>
                        <tbody id="dtlCfcd">
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
			<!-- //contBox -->
		</div>
	<div class="fade"></div>
	</div>
    <div id="popuBasc" class="layerBox" style="display:none;">
		<div class="layer">
			<div class="topBox">
			</div>
			<div class="contBox">
				<div class="table-pnl">
					<table>
                        <colgroup>
                            <col width="160px">
                            <col width="*">
                        </colgroup>
                        <tbody id="dtlBasc">
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
			<!-- //contBox -->
		</div>
	<div class="fade"></div>
	</div>
    <div id="popuDtcd" class="layerBox" style="display:none;">
		<div class="layer">
			<div class="topBox">
			</div>
			<div class="contBox">
				<div class="table-pnl">
					<table>
                        <colgroup>
                            <col width="160px">
                            <col width="*">
                        </colgroup>
                        <tbody id="dtlDtcd">
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
			<!-- //contBox -->
		</div>
		<div class="fade"></div>
	</div>
</body>

</html>