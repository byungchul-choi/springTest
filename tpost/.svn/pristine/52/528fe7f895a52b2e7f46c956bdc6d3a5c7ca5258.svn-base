<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>  <!-- 전자정부태그라이브러리 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  <!-- jstl펑션 -->

<!DOCTYPE>
<html lang="ko">
<head>
	<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>공공알림문자</title>
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
			commCd_bas('inputOganCd', '00001');
			commCd_bas('inputSndnCd', '00002');
			
			if($("#inputStDt").val() == "" && $("#inputLastDt").val() == "") {
				getYYYYMMFirstDay('inputStDt');
				getYYYYMMLastDay('inputLastDt');
			}
			
			oganTmpltList();
			datepicker();
		})
		
		function enterSerch(){
	    	if(window.event.keyCode == 13){
	    		elctDocListSelect();
	    	}
	    }
		
		//기관코드에 따른 템플릿 명
		function changeTmpltList(){
			oganTmpltList();
			
			if($("#inputOganCd option:selected").val() == ""){
				$("#inputTmpltCd option:eq(0)").prop("selected", true);
			}
		}
		
		function oganTmpltList(){
			var oganCd = document.getElementById("inputOganCd");
			tmpltCd_bas('inputTmpltCd', oganCd.options[oganCd.options.selectedIndex].value);
		}
		
		//상단 조회 버튼 클릭 시
		function elctDocListSelect(){
			$("#inputOganCd").prop("disabled", false);
			
			$('#pageIndex').val(1); /*첫조회는 페이지가 1로 조회*/
			
			var elctDocListForm = document.getElementById("elctDocList");
			elctDocListForm.action = "elctDocListSelect";
			elctDocListForm.method = "post";
			elctDocListForm.submit();
			
			$("#inputOganCd").prop("disabled", true);
		}
		
		//상세페이지 이동
		function goElctDocDetl(oganCd, tmpltCd, sndnDate){			
			var elctDocListForm = document.getElementById("elctDocDetl");
			
			elctDocListForm.mOganCd.value = oganCd;
			elctDocListForm.mTmpltCd.value = tmpltCd;
			elctDocListForm.mSndnDate.value = sndnDate;
			
			elctDocListForm.pOganCd.value = $("#inputOganCd option:selected").val();
			elctDocListForm.pSndnCd.value = $("#inputSndnCd option:selected").val();
			elctDocListForm.pTmpltCd.value = $("#inputTmpltCd option:selected").val();
			elctDocListForm.pStDt.value = $("#inputStDt").val();
			elctDocListForm.pLastDt.value = $("#inputLastDt").val();
			elctDocListForm.pCi.value = $("#inputCi").val();
			elctDocListForm.pPgIndex.value = $("#pageIndex").val();
			
			elctDocListForm.action = "elctDocDetlInit";
			elctDocListForm.method = "post";
			elctDocListForm.submit();
		}
		
		//다음페이지
		function linkPage(pageNo){
			if(pageIndex == ""){
				pageIndex = 1;
			}
				  
			$('#pageIndex').val(pageNo);
			$("#inputOganCd").prop("disabled", false);
				
			var elctDocListForm = document.getElementById("elctDocList");
			elctDocListForm.action = "elctDocListSelect";
			elctDocListForm.method = "post";
			elctDocListForm.submit();
			
			$("#inputOganCd").prop("disabled", true);
		}	
		
		function callBack(sel_id, codeNum){
			if(sel_id == "#inputOganCd") {
				if($("#inputOganCd option").size() > 1){
					$(sel_id).prepend('<option value="" selected="true">전체</option>');	
				}
				if($("#inputOganCd option").size() == 1){
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
			
			if(sel_id == "#inputSndnCd") {
				$(sel_id).prepend('<option value="" selected="true">전체</option>');
			
				var value = '${inputSndnCd}';
		
				if(value == "") return;
				
				var size = $("#inputSndnCd option").size();
				for(var i = 0; i < size; i++){
					var selector = $("#inputSndnCd option:eq("+ i +")");
					if(selector.val() == value){
						selector.attr("selected", "selected");
					}
				}
			}
			
			if(sel_id == "#inputTmpltCd") {
				var value = '${inputTmpltCd}';
				
				if(value == "") return;
				
				var size = $("#inputTmpltCd option").size();
				for(var i = 0; i < size; i++){
					var selector = $("#inputTmpltCd option:eq("+ i +")");
					if(selector.val() == parseInt(value)){
						selector.attr("selected", "selected");
					}
				}
			}
		}
	</script>
</head>
<body>
<form id="elctDocDetl">
<input type="hidden" id="mOganCd" name="mOganCd"/>
<input type="hidden" id="mSndnDate" name="mSndnDate"/>
<input type="hidden" id="mTmpltCd" name="mTmpltCd"/>

<input type="hidden" id="pOganCd" name="pOganCd"/>
<input type="hidden" id="pSndnCd" name="pSndnCd"/>
<input type="hidden" id="pTmpltCd" name="pTmpltCd"/>
<input type="hidden" id="pStDt" name="pStDt"/>
<input type="hidden" id="pLastDt" name="pLastDt"/>
<input type="hidden" id="pCi" name="pCi"/>
<input type="hidden" id="pPgIndex" name="pPgIndex"/>
</form>
<form id="elctDocList">
<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex}"/> 
	<!-- wrap -->
	<div id="wrap">
		<!-- contents -->
		<div id="contents">
			<!-- section -->
			<div id="section">
				<!-- caption-pnl -->
				<div class="caption-pnl">
					<h2>전자문서 송/수신목록</h2>
				</div>
				<!-- //caption-pnl -->
				<!-- search-pnl -->
				<div class="search-pnl">
					<div class="buttonset">
					</div>
					<table>
						<colgroup>
							<col width="140px"/>
							<col width="*"/>
							<col width="140px"/>
							<col width="*"/>
							<col width="140px"/>
							<col width="200px"/>
							<col width="200px"/>
						</colgroup>
						<tr>
							<th>발송일자</th>
							<td><input type="text" size="13" class="dateinput" id="inputStDt" name="inputStDt" value="${inputStDt}" onkeypress="dateFormat(this)" onKeyDown="enterSerch()"> ~ <input type="text" size="13" class="dateinput" id="inputLastDt" name="inputLastDt" value="${inputLastDt}" onkeypress="dateFormat(this)" onKeyDown="enterSerch()"></td>
							<th>연계정보(CI)</th>
							<td><input type="text" size="8" id="inputCi" name="inputCi" value="${inputCi}" onKeyDown="enterSerch()"></td>
							<th>발송유형</th>
							<td><select id="inputSndnCd" name="inputSndnCd"></select></td>
							<td class="buttonset" rowspan="2"><button type="button" onclick="elctDocListSelect()">조회</button></td>
						</tr>
						<tr>
							<th>기관명</th>
							<td><select id="inputOganCd" name="inputOganCd" onchange="changeTmpltList()"></select></td>
							<th>템플릿명</th>
							<td><select id="inputTmpltCd" name="inputTmpltCd"></select></td>
							<th></th>
							<td></td>
						</tr>	
					</table>
				</div>
				<!-- //search-pnl -->
				<!-- grid-pnl -->
				<div class="grid-pnl">
					<table class="align-center">
						<thead>
							<tr>
								<th scope="col">발송 기관(회사)명</th>
								<th scope="col">발송유형</th>
								<th scope="col">템플릿명</th>
								<th scope="col">전체발송건수</th>
								<th scope="col">성공건수</th>
								<th scope="col">실패건수</th>
								<th scope="col">발송일자</th>
							</tr>
						</thead>
						<tbody id="elctDocTable">
			          		<c:forEach var="elctDocList" items="${elctDocList}">
				          		<tr style="cursor:pointer;" onclick="goElctDocDetl('${elctDocList.oganCd}', '${elctDocList.tmpltCd}', '${elctDocList.sndnDate}')">
									<td>${elctDocList.oganNm}</td>
									<td>${elctDocList.sndnCd}</td>
									<td>${elctDocList.tmpltNm}</td>
									<td>${elctDocList.allSndnNum}</td>
									<td>${elctDocList.succNum}</td>
									<td>${elctDocList.failNum}</td>
									<td>${elctDocList.sndnDate}</td>
								</tr>
							</c:forEach>	
			        	</tbody>
					</table>
					<!-- //table -->
				</div>
				<!-- //grid-pnl -->
			</div>
			<!-- //section -->
			<!-- paging -->
			<div class="paging">
				<c:if test="${fn:length(elctDocList) ne 0 }">  
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
</form>
</body>
</html>