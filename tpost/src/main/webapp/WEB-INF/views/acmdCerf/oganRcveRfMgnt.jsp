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
    <title>기관별 수신/거부 현황</title>

    <!-- CSS  STYLE -->
	<link rel="stylesheet" href="/tpost/resource/css/common.css">
	<!-- ---------- -->
    <!--     JS     -->
	<script src="/tpost/resource/js/jquery.min.js"></script>
	<script src="/tpost/resource/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="/tpost/resource/js/jquery-ui.min.js"></script>
	<script src="/tpost/resource/js/comm.js"></script>
	<!-- ---------- -->
	<script type="text/javascript">
	 	$('document').ready(function(){
	 		var value='${inputRcveRfClcd}';
			var size = $('#inputRcveRfClcd option').size();
			
			for(var i = 0; i < size; i++){
				var selector = $("#inputRcveRfClcd option:eq(" + i + ")");
				if(selector.val() == value){
					selector.attr("selected", "selected");
				}
			}
			
	 		commCd_bas('inputOganCd', '00001');
	 		commCd_bas('execIOganCd', '00001');
	 		
	 		
	 		if($("#inputStDt").val() == "" && $("#inputClosDt").val() == "") {
				getYYYYMMFirstDay('inputStDt');
				getYYYYMMLastDay('inputClosDt');
			}
	 		
	 		if($("#execIStDt").val() == "" && $("#execIClosDt").val() == "") {
	 			getToday('execIStDt');
	 			getToday('execIClosDt');
			}
	 		
	    	datepicker();
	    });
	 	
	 	function enterSerch(){
	    	if(window.event.keyCode == 13){
	    		oganRcveRfListSelect();
	    	}
	    }
	 	
	 	//상단 조회 버튼 클릭 시
	 	function oganRcveRfListSelect(){
	 		$("#inputOganCd").prop("disabled", false);
	 		
	 		$('#pageIndex').val(1); /*첫조회는 페이지가 1로 조회*/
			var oganRcveRfForm = document.getElementById("oganRcveRf");
			oganRcveRfForm.action = "oganRcveRfListSelect";
			oganRcveRfForm.method = "post";
			oganRcveRfForm.submit();

			$("#inputOganCd").prop("disabled", true);	
	 	}
	 	
	 	//다음페이지
		function linkPage(pageNo){
			if(pageIndex == ""){
				pageIndex = 1;
			}
				  
			$('#pageIndex').val(pageNo);
			$("#inputOganCd").prop("disabled", false);
			
			var oganRcveRfForm = document.getElementById("oganRcveRf");
			oganRcveRfForm.action = "oganRcveRfListSelect";
			oganRcveRfForm.method = "post";
			oganRcveRfForm.submit();

			$("#inputOganCd").prop("disabled", true);	
		}	
	 	
	 	//발송예와관리 셋팅
	 	function rcveExecInit(oganCd, ofapElctAddr){
	 		ofapElctAddr = ofapElctAddr.replace(/-/gi, '');
	 		
	 		$('#execIOfapElctAddr').val(ofapElctAddr);
	 		
	 		var size = $("#execIOganCd option").size();
			for(var i = 0; i < size; i++){
				var selector = $("#execIOganCd option:eq("+ i +")");
				if(selector.val() == oganCd){
					selector.attr("selected", "selected");
				}
			}
			
			sndnExecSelect();
	 	}
	 	
	 	//발송예외관리 보여줌
	 	function sndnExecSelect(){
	 		var parameters = {
	 			execIOganCd			: $('#execIOganCd').val(),
				execIOfapElctAddr	: $('#execIOfapElctAddr').val(),
	 		}
	 		
	 		$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "sndnExecSelect.ajax",
				data : parameters,
				success : function(data) {
					$('#sndnExecList').html('');
					
					var today = new Date();
					var yyyy = String(today.getFullYear());
					var mm = String(today.getMonth()+1);
					var dd = String(today.getDate());
					
					if(mm.length != 2) mm = "0" + mm;
					if(dd.length != 2) dd = "0" + dd;
					
					var date = yyyy + "-" + mm + "-" + dd;
					
					var oganCd = $('#execIOganCd').val();
					var ofapElctAddr = $('#execIOfapElctAddr').val();
					
					var inner = '';
					if(data.sndnExecList.length != 0){
						for(var i = 0; i < data.sndnExecList.length; i++){
							var sndnExecList = data.sndnExecList[i];
							
							inner += '<tr>';
							inner += '<td>' + sndnExecList.execOganNm + '</td>';
							inner += '<td>' + sndnExecList.execName + '</td>';
							inner += '<td style="cursor:pointer;" onclick="ofapElctAddrToCi(\''+ sndnExecList.execCiNum +'\')">' + sndnExecList.execOfapElctAddr + '</td>';
							inner += '<td>';
							inner += '<select id="execSndnClcd" name="execSndnClcd">';
							if(sndnExecList.execSndnClcd == '1'){
								inner += '<option value="1" selected>발송</option>';
								inner += '<option value="0">미발송</option>';	
							}else{
								inner += '<option value="1">발송</option>';
								inner += '<option value="0" selected>미발송</option>';	
							}
							inner += '</select>';
							inner += '</td>';
							inner += '<td><input type="text" size="13" class="dateinput" id="execStDt_'+i+'" name="execStDt_'+i+'" value="' + sndnExecList.execStDt + '" onkeypress="dateFormat(this)" onKeyDown="enterSerch()"></td>';
							inner += '<td><input type="text" size="13" class="dateinput" id="execClosDt_'+i+'" name="execClosDt_'+i+'" value="' + sndnExecList.execClosDt + '" onkeypress="dateFormat(this)" onKeyDown="enterSerch()"></td>';
							inner += '<td>' + sndnExecList.execDt + '</td>';
							inner += '<td>' + sndnExecList.execPrsr + '</td>';
							
							if(new Date(sndnExecList.execClosDt).getTime() >= new Date(date).getTime()){
								inner += '<td><button type="button" onclick="sndnExecUpdate(' + sndnExecList.execPk + ', '+i+', \''+ oganCd +'\', \''+ ofapElctAddr +'\')">수정</button></td>';		
							}
							else{
								inner += '<td></td>';
							}
							
							inner += '</tr>';
						}
						$('#sndnExecList').append(inner);
					}

			    	datepicker();
				},
				complete : function(data) {
				}
			});
	 	}
	 	
	 	//발송예외 등록
	 	function sndnExecInsert(){
			if(reqValCheck('execIOfapElctAddr', '공인전자주소')) return;
			if(reqValCheck('execIStDt', '발송예외처리기간 시작')) return;
			if(reqValCheck('execIClosDt', '방송예외처리기간 종료')) return;
			
			if(new Date($('#execIStDt').val()).getTime() > new Date($('#execIClosDt').val()).getTime()){
				alert("발송예외처리기간 종료값이 시작값보다 작습니다. \n종료값을 다시 설정해주세요.");
				$('#execIClosDt').focus();
				return;
			}
			
			var parameters = { 
					execIOganCd			: $('#execIOganCd').val(),
					execIOfapElctAddr	: $('#execIOfapElctAddr').val(),
					execISndnClcd		: $('#execISndnClcd').val(),
					execIStDt			: $('#execIStDt').val(),
					execIClosDt			: $('#execIClosDt').val()
				};
			
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "sndnExecInsert.ajax",
				data : parameters,
				success : function(data) {
					if(data.errorMessage == ""){
						alert("저장되었습니다.");
						sndnExecSelect();	
					}
					else{
						alert(data.errorMessage);
					}
				},
				complete : function(data) {
				}
			});
	 	}
	 	
	 	//발송예외 업데이트
	 	function sndnExecUpdate(seq, index, oganCd, ofapElctAddr){
	 		var selector = document.getElementsByName('execSndnClcd');
	 		
	 		var stdt = 'execStDt_' + index;
	 		var closdt = 'execClosDt_' + index;
	 		
	 		if(reqValCheck(stdt, '발송예외처리기간 시작')) return;
			if(reqValCheck(closdt, '방송예외처리기간 종료')) return;
			
			if(new Date($('#'+stdt).val()).getTime() > new Date($('#'+closdt).val()).getTime()){
				alert("발송예외처리기간 종료값이 시작값보다 작습니다. \n종료값을 다시 설정해주세요.");
				$('#'+closdt).focus();
				return;
			}
	
	 		var parameters = { 
 				execIPk				: seq,
 				execISndnClcd		: selector[index].value,
 				execIOganCd			: oganCd,
 				execIOfapElctAddr	: ofapElctAddr,
 				execIStDt			: $("input[name='execStDt_"+index+"']").val(),
 				execIClosDt			: $("input[name='execClosDt_"+index+"']").val()
			};
	 		
	 		console.log("debug : ", parameters);
	 		
	 		$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "sndnExecUpdate.ajax",
				data : parameters,
				success : function(data) {
					if(data.errorMessage == ""){
						alert("수정되었습니다.");
						sndnExecSelect();
					}
					else{
						alert(data.errorMessage);
						sndnExecSelect();
					}
				},
				complete : function(data) {
				}
			});
	 		
	 	}
	 	
	 	function callBack(sel_id, codeNum){
			if(sel_id == "#inputOganCd") {
				
				if($('#athLev').val() == "00"){
					$(sel_id).prepend('<option value="" selected="true">전체</option>');	
				}
				
				if($('#athLev').val() == "01"){
					$("#inputOganCd").prop("disabled", "disabled");
				}

				var value = '${inputOganCd}';
				
				if(value == "") return;
				
				var size = $("#inputOganCd option").size();
				for(var i = 0; i < size; i++){
					var selector = $("#inputOganCd option:eq("+ i +")");
					if(selector.val() == value){
						selector.attr("selected", "selected");
					}
				}
			}
			
			if(sel_id == "#execIOganCd") {
				if($('#athLev').val() == "01"){
					$("#execIOganCd").prop("disabled", "disabled");
				}

				var value = '${execIOganCd}';
				
				if(value == "") return;
				
				var size = $("#execIOganCd option").size();
				for(var i = 0; i < size; i++){
					var selector = $("#execIOganCd option:eq("+ i +")");
					if(selector.val() == value){
						selector.attr("selected", "selected");
					}
				}
			}
			
			var value='${inputRcveRfYn}';
			var size = $('#inputRcveRfYn option').size();
			
			for(var i = 0; i < size; i++){
				var selector = $("#inputRcveRfYn option:eq(" + i + ")");
				if(selector.val() == value){
					selector.attr("selected", "selected");
				}
			}
			
			if($('#athLev').val() == "01"){
				$("#execIOfapElctAddr").prop("disabled", true);
			}
		}
	 	
	</script>
</head>

<body>
<form id="oganRcveRf">
<input type="hidden" id="pageIndex" name="pageIndex" value="${searchVO.pageIndex}"/>
<input type="hidden" id="athLev" name="athLev" value="${athLev}"/> 
<!-- wrap -->
    <div id="wrap">
        <!-- contents -->
        <div id="contents">
            <!-- section -->
            <div class="section">
                <!-- caption-pnl -->
                <div class="caption-pnl">
                    <h2>기관별 수신/거부 현황</h2>
                </div>
                <!-- //caption-pnl -->
                <!-- search-pnl -->
                <div class="search-pnl">
                    <table>
                        <colgroup>
                            <col width="180px" />
                            <col width="*" />
                            <col width="150px" />
                            <col width="*" />
                            <col width="150px" />
                            <col width="160px" />
                            <col width="30px" />
                            <col width="200px" />
                        </colgroup>
                        <tr>
                            <th>수신동의/거부일자</th>
                            <td><input type="text" size="13" class="dateinput" id="inputStDt" name="inputStDt" value="${inputStDt}" onkeypress="dateFormat(this)" onKeyDown="enterSerch()"> ~ <input type="text" size="13" class="dateinput" id="inputClosDt" name="inputClosDt" value="${inputClosDt}" onkeypress="dateFormat(this)" onKeyDown="enterSerch()"></td>
                            <th>기관명</th>
                            <td><select id="inputOganCd" name="inputOganCd"></select></td>
                            <th>고객명</th>
                            <td><input type="text" size="8" id="inputName" name="inputName" value="${inputName}" onKeyDown="enterSerch()"></td>
                            <td></td>
                            <td class="buttonset" rowspan="2"><button type="button" onclick="oganRcveRfListSelect()">조회</button>
                            </td>
                        </tr>
                        <tr>
                            <th>공인전자주소</th>
                            <td><input type="text" size="16" id="inputOfapElctAddr" name="inputOfapElctAddr" value="${inputOfapElctAddr}" onKeyDown="enterSerch()" maxlength="15" onkeypress="onlyNumber(); _maxLengthCheck(this, 12);" onKeyUp="this.value=this.value.replace(/[^0-9]/g,'');" ></td>
                            <th>연계정보(CI)</th>
                            <td><input type="text" size="10" id="inputCiNum" name="inputCiNum" value="${inputCiNum}" onKeyDown="enterSerch()"></td>
                            <th>수신동의여부</th>
                            <td>
                            	<select id="inputRcveRfClcd" name="inputRcveRfClcd">
                            		<option value="" selected>전체</option>
                            		<option value="1">동의</option>
                            		<option value="0">거부</option>
                            	</select>
                            </td>
                            <td></td>
                        </tr>
                    </table>
                </div>
                <!-- //search-pnl -->
                <!-- caption-pnl -->
            	<div class="caption-pnl">
                	<p class="text-r">총 수신동의 : ${allCsntCount} | 총 수신거부 : ${allRfslCount}</p>
                </div>
                <!-- //caption-pnl -->
                <!-- grid-pnl -->
                <div class="grid-pnl" style="overflow-x:hidden; overflow-y:hidden; height:300px;" >
                 <!-- table -->
                    <table class="align-center">
                        <colgroup>
                            <col width="*">
                            <col width="*">
                            <col width="*">
                            <col width="*">
                            <col width="*">
                            <col width="*">
                        </colgroup>
                        <thead>
                            <tr>
                                <th scope="col">기관명</th>
                                <th scope="col">고객명</th>
                                <th scope="col">공인전자주소</th>
                                <th scope="col">수신동의일자</th>
                                <th scope="col">수신거부일자</th>
                                <th scope="col">수신동의여부</th>
                            </tr>
                        </thead>
                        <tbody id="oganRcveRfList">
                        	<c:forEach var="oganRcveRfList" items="${oganRcveRfList}">
                        		<tr style="cursor:pointer;"onclick="rcveExecInit('${oganRcveRfList.oganCd}','${oganRcveRfList.ofapElctAddr}')">
                        			<td>${oganRcveRfList.oganNm}</td>
                        			<td>${oganRcveRfList.name}</td>
                        			<td style="cursor:pointer;" onclick="ofapElctAddrToCi('${oganRcveRfList.ciNum}')">${oganRcveRfList.ofapElctAddr}</td>
                       				<c:if test="${oganRcveRfList.rcveRfClcd.equals('1')}" >
                        				<td>${oganRcveRfList.rcveRfDt}</td>
                        				<td></td>
                        				<td>동의</td>
                        			</c:if>
                        			<c:if test="${oganRcveRfList.rcveRfClcd.equals('0')}" >
                        				<td></td>
                        				<td>${oganRcveRfList.rcveRfDt}</td>
                        				<td>거부</td>
                        			</c:if>
                        		</tr>
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
				<c:if test="${fn:length(oganRcveRfList) ne 0 }">  
					<ul>
						<ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="linkPage" />
					</ul>
				</c:if>
			</div>
			<!-- //paging -->
			<!-- caption-pnl -->
            <div class="caption-pnl">
              	<h3>발송예외관리</h3>
              	<div class="buttonset">
					<button type="button" onclick="sndnExecInsert()">등록</button>
				</div>
            </div>
            <!-- //caption-pnl -->
            <div class="colgroup-wrap">
                <div class="colgroup-1-2">
                    <!-- table-pnl -->
                    <div class="table-pnl">
                        <!-- table -->
                        <table class="vtable">
                            <colgroup><col width="180px"><col width="*"></colgroup>
                            <tbody>
                            	<tr>
                                    <th>기관명</th>
                                    <td><select id="execIOganCd" name="execIOganCd"></select></td>
                                </tr>
                                <tr>
                                    <th>공인전자주소</th>
                                    <td><input id="execIOfapElctAddr" name="execIOfapElctAddr" type="text" maxlength="15" onkeypress="onlyNumber(); _maxLengthCheck(this, 12);" onKeyUp="this.value=this.value.replace(/[^0-9]/g,'');"></td>
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
                            <colgroup><col width="180px"><col width="*"></colgroup>
                            <tbody>
                                <tr>
                                    <th>발송구분</th>
                                    <td>
                                    	<select id="execISndnClcd" name="execISndnClcd">
                                    		<option value="1" selected>발송</option>
                                    		<option value="0">미발송</option>
                                    	</select>
                                    </td>
                                </tr>
                                <tr>
                                    <th>발송예외처리기간</th>
                                    <td><input type="text" size="13" class="dateinput" id="execIStDt" name="execIStDt" value="${execIStDt}" onkeypress="dateFormat(this)" onKeyDown="enterSerch()"> ~ <input type="text" size="13" class="dateinput" id="execIClosDt" name="execIClosDt" value="${execIClosDt}" onkeypress="dateFormat(this)" onKeyDown="enterSerch()"></td>
                                </tr>
                            </tbody>
                        </table>
                        <!-- //table -->
                    </div>
                    <!-- //table-pnl -->
                </div>
            </div>
            <!-- grid-pnl -->
            <div class="grid-pnl" style="overflow-x:hidden; overflow-y:auto; height:300px;" >
             <!-- table -->
                <table class="align-center">
                    <colgroup>
                        <col width="*">
                        <col width="*">
                        <col width="*">
                        <col width="*">
                        <col width="*">
                        <col width="*">
                        <col width="*">
                        <col width="*">
                        <col width="*">
                    </colgroup>
                    <thead>
                        <tr>
                            <th scope="col">기관명</th>
                            <th scope="col">고객명</th>
                            <th scope="col">공인전자주소</th>
                            <th scope="col">발송구분</th>
                            <th scope="col">예외적용시작일자</th>
                            <th scope="col">예외적용종료일자</th>
                            <th scope="col">예외처리일자</th>
                            <th scope="col">처리자</th>
                            <th scope="col">수정</th>
                        </tr>
                    </thead>
                    <tbody id="sndnExecList">
                    </tbody>
                </table>
                <!-- //table -->
            </div>
            <!-- //grid-pnl -->
        </div>
        <!-- //contents -->
    </div>
    <!-- //wrap -->
    
    <!-- layerBox Message -->
	<div id="ofapElctAddrToCi" class="layerBox" style="display:none;"></div>
	<!-- //layerBox Message -->
</form>
</body>
</html>