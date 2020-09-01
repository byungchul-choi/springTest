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
			commCd_bas('inputOganCd', '00001');
			commCd_bas('wrtgOganCd', '00001');
			
			if($("#inputWrtgStDt").val() == "" && $("#inputWrtgClosDt").val() == "") {
				getYYYYMMFirstDay('inputWrtgStDt');
				getYYYYMMLastDay('inputWrtgClosDt');
			}
			
			datepicker();
			noticeWrtInit(true);
		});
		
		function noticeWrtInit(val){
			$('#wrtgTtl').prop("disabled", val);
			$('#wrtgCnts').prop("disabled", val);
			$('#wrtgOganCd').prop("disabled", val);

			$('#btnIns').prop("disabled", val);

			$('#wrtgTtl').val("");
			$('#wrtgCnts').val("");
			$('#wrtgOganCd option:eq(0)').attr("selected", "selected");
		}
		
		function enterSerch(){
			if(window.event.keyCode == 13){
				noticeListSelect();
			}
		}
		
		//상단 조회 버튼 클릭 시
		function noticeListSelect(){
			$('#pageIndex').val(1); /*첫조회는 페이지가 1로 조회*/
			
			var noticeMgntForm = document.getElementById("noticeMgnt");
			noticeMgntForm.action = "noticeListSelect";
			noticeMgntForm.method = "post";
			noticeMgntForm.submit();
		}
		
		//다음페이지
		function linkPage(pageNo){
			if(pageIndex == ""){
				pageIndex = 1;
			}
			
			$('#pageIndex').val(pageNo);
			
			var noticeMgntForm = document.getElementById("noticeMgnt");
			noticeMgntForm.action = "noticeListSelect";
			noticeMgntForm.method = "post";
			noticeMgntForm.submit();
		}
		
		//공지사항 상세
		function noticeDetlSelect(wrtgNum){
			var parameters = {
					wrtgNum 	: wrtgNum,
			}
			
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "noticeDetlSelect.ajax",
				data : parameters,
				success : function(data) {
					noticeWrtInit(true);
					
					$('#wrtgOganCd').val(data.oganCd).prop("selected", true);
					$('#wrtgTtl').val(data.wrtgTtl);
					$('#wrtgCnts').val(data.wrtgCnts);
				},
				complete : function(data) {
				}
			});
		}
		
		//공지사항 글 등록
		function noticeInsert(){
			if(reqValCheck('wrtgTtl', '공지사항 제목')) return;
			if(reqValCheck('wrtgCnts', '공지사항 내용')) return;
			
			var parameters = {
					oganCd 			: $('#wrtgOganCd option:selected').val(),
					wrtgTtl 		: $('#wrtgTtl').val(),
					wrtgCnts 		: $('#wrtgCnts').val(),
			}
			
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "noticeInsert.ajax",
				data : parameters,
				success : function(data) {
					alert("등록되었습니다.");
					noticeListSelect();
				},
				complete : function(data) {
				}
			});
		}
		
		//공지사항 삭제
		function noticeUpdate(wrtgNum){
			
			var parameters = {
					wrtgNum 	: wrtgNum,
			}
			
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "noticeUpdate.ajax",
				data : parameters,
				success : function(data) {
					alert("삭제되었습니다.");
					noticeListSelect();
				},
				complete : function(data) {
				}
			});
		}
		
		function callBack(sel_id, codeNum){
			if(sel_id == "#inputOganCd") {
				$(sel_id).prepend('<option value="" selected="true">전체</option>');	
				
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
			
			if(sel_id == "#wrtgOganCd") {
				$(sel_id).prepend('<option value="" selected="true">전체</option>');	
				
				var value = '${wrtgOganCd}';
				
				if(value == "") return;
				
				var size = $("#wrtgOganCd option").size();
				for(var i = 0; i < size; i++){
					var selector = $("#wrtgOganCd option:eq("+ i +")");
					if(selector.val() == value){
						selector.attr("selected", "selected");
					}
				}
				
			}
		}
	</script>
</head>

<body>
<form id="noticeMgnt">
<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex}"/> 
	<!-- wrap -->
	<div id="wrap">
		<!-- contents -->
		<div id="contents">
			<!-- section -->
			<div id="section">
				<!-- caption-pnl -->
				<div class="caption-pnl">
					<h2>공지사항 관리</h2>
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
                            <col width="200px">
                            <col width="200px">
                            <col width="110px">
                            <col width="*">
                            <col width="*">
                            <col width="200px">
                        </colgroup>
                        <tr>
                            <th>작성일자</th>
                            <td><input type="text" size="13" class="dateinput" id="inputWrtgStDt" name="inputWrtgStDt" value="${inputWrtgStDt}" onkeypress="dateFormat(this)" onKeyDown="enterSerch()"> ~ <input type="text" size="13" class="dateinput" id="inputWrtgClosDt" name="inputWrtgClosDt" value="${inputWrtgClosDt}" onkeypress="dateFormat(this)" onKeyDown="enterSerch()"></td>
                            <th>기관(회사)명</th>
                            <td><select id="inputOganCd" name="inputOganCd"></select></td>
                            <th>제목</th>
                            <td colspan="2"><input type="text" id="inputWrtgTtl" name="inputWrtgTtl" value="${inputWrtgTtl}" onKeyDown="enterSerch()"></td>
                            <td class="buttonset"><button type="button" onclick="noticeListSelect()">조회</button></td>
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
								<th scope="col">기관(회사)명</th>
								<th scope="col">제목</th>
								<th scope="col">작성일자</th>
								<th scope="col">삭제</th>
							</tr>
						</thead>
						<tbody id="noticeList">
							<c:forEach var="noticeList" items="${noticeList}">
								<tr onclick="noticeDetlSelect('${noticeList.wrtgNum}')">
									<td>${noticeList.wrtgNum}</td>
									<td>${noticeList.oganNm}</td>
									<td>${noticeList.wrtgTtl}</td>
									<td>${noticeList.wrtDt}</td>
									<td><button type="button" onclick="noticeUpdate('${noticeList.wrtgNum}')">삭제</button></td>
								</tr>
							</c:forEach>
			        	</tbody>
					</table>
					<!-- //table -->
				</div>
				<!-- //grid-pnl -->
				
				<!-- paging -->
				<div class="paging">
					<c:if test="${fn:length(noticeList) ne 0 }">  
						<ul>
							<ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="linkPage" />
						</ul>
					</c:if>
				</div>
				<!-- //paging -->
				
				
				<!-- caption-pnl -->
            	<div class="caption-pnl">
                	<h3>공지사항 세부</h3>
					<div class="buttonset">
						<button type="button" class="state-highlight" id="btnIns" onclick="noticeInsert()" disabled="false">저장</button>
						<button type="button" onclick="noticeWrtInit(false)">초기화</button>
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
						<tbody id="noticeWrt">
							<tr>
								<th>기관(회사)명<span style="color: #fea338;">*</span></th>
								<td><select id="wrtgOganCd" name="wrtgOganCd" disabled></select></td>
							</tr>
							<tr>
								<th>공지사항 제목<span style="color: #fea338;">*</span></th>
								<td><input type="text" id="wrtgTtl" name="wrtgTtl" disabled></td>
							</tr>
							<tr>
								<th>공지사항 내용<span style="color: #fea338;">*</span></th>
								<td><textarea class="markup" placeholder="공지사항 내용 입력" id="wrtgCnts" name="wrtgCnts" disabled></textarea></td>
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