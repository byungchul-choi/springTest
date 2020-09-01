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
	<style>
	td textarea {padding: 10px; box-sizing: border-box; width:100%; height:160px; resize: none; line-height: 1.6;}
    .markup{height:200px}
	</style>
	<!--     JS     -->
	<script src="/tpost/resource/js/jquery.min.js"></script>
	<script src="/tpost/resource/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="/tpost/resource/js/jquery-ui.min.js"></script>
	<script src="/tpost/resource/js/comm.js"></script>
	<!-- ---------- -->
	<script type="text/javascript">
		$('document').ready(function(){	
			commCd_bas('inputFaqCd', '00014');
			commCd_bas('wrtgFaqCd', '00014');
			
			if($("#inputWrtgStDt").val() == "" && $("#inputWrtgClosDt").val() == "") {
				getYYYYMMFirstDay('inputWrtgStDt');
				getYYYYMMLastDay('inputWrtgClosDt');
			}
			
			datepicker();
			faqWrtInit(true);
		});
		
		function faqWrtInit(val){
			$('#wrtgTtl').prop("disabled", val);
			$('#wrtgCnts').prop("disabled", val);
			$('#wrtgFaqCd').prop("disabled", val);

			$('#btnIns').prop("disabled", val);

			$('#wrtgTtl').val("");
			$('#wrtgCnts').val("");
			$('#wrtgFaqCd option:eq(0)').attr("selected", "selected");
		}
		
		function enterSerch(){
			if(window.event.keyCode == 13){
				faqListSelect();
			}
		}
		
		//상단 조회 버튼 클릭 시
		function faqListSelect(){
			$('#pageIndex').val(1); /*첫조회는 페이지가 1로 조회*/
			
			var faqMgntForm = document.getElementById("faqMgnt");
			faqMgntForm.action = "faqListSelect";
			faqMgntForm.method = "post";
			faqMgntForm.submit();
		}
		
		//다음페이지
		function linkPage(pageNo){
			if(pageIndex == ""){
				pageIndex = 1;
			}
			
			$('#pageIndex').val(pageNo);
			
			var faqMgntForm = document.getElementById("faqMgnt");
			faqMgntForm.action = "faqListSelect";
			faqMgntForm.method = "post";
			faqMgntForm.submit();
		}
		
		function faqDetlSelect(wrtgNum){
			var parameters = {
					wrtgNum 	: wrtgNum,
			}
			
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "faqDetlSelect.ajax",
				data : parameters,
				success : function(data) {
					faqWrtInit(true);
					
					$('#wrtgFaqCd').val(data.faqCd).prop("selected", true);
					$('#wrtgTtl').val(data.wrtgTtl);
					$('#wrtgCnts').val(data.wrtgCnts);
				},
				complete : function(data) {
				}
			});
		}
		
		//FAQ 글 등록
		function faqInsert(){
			if(reqValCheck('wrtgTtl', 'FAQ 제목')) return;
			if(reqValCheck('wrtgCnts', 'FAQ 내용')) return;
			
			var parameters = {
					FaqCd 			: $('#wrtgFaqCd option:selected').val(),
					wrtgTtl 		: $('#wrtgTtl').val(),
					wrtgCnts 		: $('#wrtgCnts').val(),
			}
			
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "faqInsert.ajax",
				data : parameters,
				success : function(data) {
					alert("등록되었습니다.");
					faqListSelect();
				},
				complete : function(data) {
				}
			});
		}
		
		//FAQ 삭제
		function faqUpdate(wrtgNum){
			var parameters = {
					wrtgNum 	: wrtgNum,
			}
			
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "faqUpdate.ajax",
				data : parameters,
				success : function(data) {
					alert("삭제되었습니다.");
					faqListSelect();
				},
				complete : function(data) {
				}
			});
		}
		
		function callBack(sel_id, codeNum){
			if(sel_id == "#inputFaqCd") {
				$(sel_id).prepend('<option value="" selected="true">전체</option>');	
				
				var value = '${inputFaqCd}';
				
				if(value == "") return;
				
				var size = $("#inputFaqCd option").size();
				for(var i = 0; i < size; i++){
					var selector = $("#inputFaqCd option:eq("+ i +")");
					if(selector.val() == value){
						selector.attr("selected", "selected");
					}
				}
				
			}
			
			if(sel_id == "#wrtgFaqCd") {
				$(sel_id).prepend('<option value="" selected="true">전체</option>');	
				
				var value = '${wrtgFaqCd}';
				
				if(value == "") return;
				
				var size = $("#wrtgFaqCd option").size();
				for(var i = 0; i < size; i++){
					var selector = $("#wrtgFaqCd option:eq("+ i +")");
					if(selector.val() == value){
						selector.attr("selected", "selected");
					}
				}
				
			}
		}
	</script>
</head>

<body>
<form id="faqMgnt">
<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex}"/> 
	<!-- wrap -->
	<div id="wrap">
		<!-- contents -->
		<div id="contents">
			<!-- section -->
			<div id="section">
				<!-- caption-pnl -->
				<div class="caption-pnl">
					<h2>FAQ 관리</h2>
					<div class="buttonset">
                    </div>
				</div>
				<!-- //caption-pnl -->
				<!-- search-pnl -->
				<div class="search-pnl">
					<table>
                        <colgroup>
                            <col width="140px">
                            <col width="*">
                            <col width="110px">
                            <col width="200px">
                            <col width="110px">
                            <col width="*">
                            <col width="*">
                            <col width="200px">
                        </colgroup>
                        <tr>
                            <th>작성일자</th>
                            <td><input type="text" size="13" class="dateinput" id="inputWrtgStDt" name="inputWrtgStDt" value="${inputWrtgStDt}" onkeypress="dateFormat(this)" onKeyDown="enterSerch()"> ~ <input type="text" size="13" class="dateinput" id="inputWrtgClosDt" name="inputWrtgClosDt" value="${inputWrtgClosDt}" onkeypress="dateFormat(this)" onKeyDown="enterSerch()"></td>
                            <th>구분</th>
                            <td><select id="inputFaqCd" name="inputFaqCd"></select></td>
                            <th>제목</th>
                            <td colspan="2"><input type="text" id="inputWrtgTtl" name="inputWrtgTtl" value="${inputWrtgTtl}" onKeyDown="enterSerch()"></td>
                            <td class="buttonset"><button type="button" onclick="faqListSelect()">조회</button></td>
                        </tr>
                    </table>
				</div>
				<!-- //search-pnl -->
				<!-- grid-pnl -->
				<div class="grid-pnl" style="overflow-x:hidden; overflow-y:hidden; height:300px;">
					<table class="align-center">
						<colgroup>
                            <col width="10%">
                            <col width="15%">
                            <col width="*">
                            <col width="15%">
                            <col width="10%">
                        </colgroup>
						<thead>
							<tr>
								<th scope="col">글번호</th>
								<th scope="col">구분</th>
								<th scope="col">제목</th>
								<th scope="col">작성일자</th>
								<th scope="col">삭제</th>
							</tr>
						</thead>
						<tbody id="faqList">
							<c:forEach var="faqList" items="${faqList}">
								<tr onclick="faqDetlSelect('${faqList.wrtgNum}')">
									<td>${faqList.wrtgNum}</td>
									<td>${faqList.faqNm}</td>
									<td>${faqList.wrtgTtl}</td>
									<td>${faqList.wrtDt}</td>
									<td><button type="button" onclick="faqUpdate('${faqList.wrtgNum}')">삭제</button></td>
								</tr>
							</c:forEach>
			        	</tbody>
					</table>
					<!-- //table -->
				</div>
				<!-- //grid-pnl -->
				
				<!-- paging -->
				<div class="paging">
					<c:if test="${fn:length(faqList) ne 0 }">  
						<ul>
							<ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="linkPage" />
						</ul>
					</c:if>
				</div>
				<!-- //paging -->
				
				
				<!-- caption-pnl -->
            	<div class="caption-pnl">
                	<h3>FAQ 세부</h3>
					<div class="buttonset">
						<button type="button" class="state-highlight" id="btnIns" onclick="faqInsert()" disabled="false">저장</button>
						<button type="button" onclick="faqWrtInit(false)">초기화</button>
					</div>
                </div>
                <!-- //caption-pnl -->
				<!-- table-pnl -->
                <div class="table-pnl">
                	<!-- table -->
					<table>
                    	<colgroup>
                        	<col width="25%">
                            <col width="*">
                       	</colgroup>
						<tbody id="faqWrt">
							<tr>
								<th>구분<span style="color: #fea338;">*</span></th>
								<td><select id="wrtgFaqCd" name="wrtgFaqCd" disabled></select></td>
							</tr>
							<tr>
								<th>FAQ 제목<span style="color: #fea338;">*</span></th>
								<td><input type="text" id="wrtgTtl" name="wrtgTtl" disabled></td>
							</tr>
							<tr>
								<th>FAQ 내용<span style="color: #fea338;">*</span></th>
								<td><textarea class="markup" placeholder="FAQ 내용 입력" id="wrtgCnts" name="wrtgCnts" disabled></textarea></td>
							</tr>
                   	    </tbody>
					</table>
                    <!-- //table -->
               </div>
               <!-- //table-pnl -->
			</div>
			<!-- //section -->
		</div>
		<!-- //contents -->
	</div>
</form>
</body>
</html>