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
	<style>
	td textarea {padding: 10px; box-sizing: border-box; width:100%; height:160px; resize: none; line-height: 1.6;}
	.markup{height:200px}
	</style>
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
	});
	
	function enterSerch(){
    	if(window.event.keyCode == 13){
    		tmpltLstSelect();
    	}
    }
	
	function init(){
		$('#msgTitle').prop("disabled", true);
		$('#msgText').prop("disabled", true);
		$('#img').prop("disabled", true);
		
		$('#btnIns').prop("disabled", true);
		$('#btnUpd').prop("disabled", true);
		
		$("#img").replaceWith($("#img").val('').clone(true));
		document.getElementById("img").files[0] = null;
		$("#msgImgSize").val('');

		var deleteBtn = document.getElementById('deleteBtn');
		deleteBtn.style.display = 'none';
	
		var saveDiv = document.getElementById('saveDiv');
		saveDiv.style.display = 'none';
		$('#saveFn').val('');
		
		$('#msgTitle').val('');
		$('#msgText').val('');
	}
	
	//상단 조회 버튼 클릭 시
	function tmpltLstSelect(){	
		$("#inputOganCd").prop("disabled", false);
 		
		$('#pageIndex').val(1); /*첫조회는 페이지가 1로 조회*/
		var msgTmpltMgntForm = document.getElementById("msgTmpltMgnt");
		msgTmpltMgntForm.action = "tmpltLstSelect";
		msgTmpltMgntForm.method = "post";
		msgTmpltMgntForm.submit();
		
		$("#inputOganCd").prop("disabled", true);
	}
	
	//다음페이지
	function linkPage(pageNo){
		if(pageIndex == ""){
			pageIndex = 1;
		}
		
		$('#pageIndex').val(pageNo);
		$("#inputOganCd").prop("disabled", false);
		
		var msgTmpltMgntForm = document.getElementById("msgTmpltMgnt");
		msgTmpltMgntForm.action = "tmpltLstSelect";
		msgTmpltMgntForm.method = "post";
		msgTmpltMgntForm.submit();

		$("#inputOganCd").prop("disabled", true);	
	}
	
	function msgTmpltDetlSelect(oganCd, tmpltCd){		
		var parameters = {
				oganCd : oganCd,
				tmpltCd : tmpltCd
		}
		
		$.ajax({
			type : "post",
			dataType : "json",
			async : false,
			url : "msgTmpltDetlSelect.ajax",
			data : parameters,
			success : function(data) {
				var msgTmpltDetl = data.msgTmpltDetl;
				
				$('#oganCd').val(msgTmpltDetl.oganCd);
				$('#oganNm').val(msgTmpltDetl.oganNm);
				$('#tmpltCd').val(msgTmpltDetl.tmpltCd);
				$('#tmpltNm').val(msgTmpltDetl.tmpltNm);
				
				$('#msgTitle').val(msgTmpltDetl.msgTitle);
				$('#msgText').val(msgTmpltDetl.msgText);
				
				if(msgTmpltDetl.msgImg != null && msgTmpltDetl.msgImg != ''){
					var saveDiv = document.getElementById('saveDiv');
					saveDiv.style.display = 'block';
					
					$('#saveFn').val(msgTmpltDetl.msgImg);
					$('#msgImgSize').val(msgTmpltDetl.msgImgSize);
				}				
			},
			complete : function(data) {
			}
		});
	}
	
	function msgTmpltDetlAtv(oganCd, tmpltCd, param){
		init();
		
		$('#msgTitle').prop("disabled", false);
		$('#msgText').prop("disabled", false);
		$('#img').prop("disabled", false);
		
		if(param == 'ins'){
			$('#btnIns').prop("disabled", false);
			$('#btnUpd').prop("disabled", true);
		}
		
		if(param == 'upd'){
			$('#btnIns').prop("disabled", true);
			$('#btnUpd').prop("disabled", false);
		}
		
		msgTmpltDetlSelect(oganCd, tmpltCd);
		
		var deleteBtn = document.getElementById('deleteBtn');
		deleteBtn.style.display = 'inline';
	}
	
	function msgTmpltMgntInsert(){
		if($('#tmpltCd').val().substring(4) == '00001'){
			if($('#msgText').val().indexOf('{#수신거부_URL}') == -1){
				alert('해당 템플릿은 {#수신거부_URL}이 필수적으로 포함되어야합니다.');
				return;
			}
		} else{
			if($('#msgText').val().indexOf('{#안내_URL}') == -1){
				alert('해당 템플릿은 {#안내_URL}이 필수적으로 포함되어야합니다.');
				return;
			}
		}
		
		if(reqValCheck('msgTitle', '메시지 제목')) return;
		if(reqValCheck('msgText', '메시지 내용')) return;
		
		var file = document.getElementById("img").files[0];
		/*파일 이미지 사이즈 입력*/
		if(file){
			$("#msgImgSize").val(file.size);	
		} else{
			$("#msgImgSize").val(0);
		}
		
		var formData = new FormData($('#msgTmpltFile')[0]); 
		$.ajax({
			type: "POST"
			,enctype: 'multipart/form-data'
			,url : "msgTmpltMgntInsert.ajax"
			,data: formData
			,processData: false
			,contentType: false
			,cache: false
			,success: function (data) { 
				if(data.errorMessage != ""){
					alert(data.errorMessage);
					
					if(data.errorMessage == "지원하는 이미지 파일 형식이 아닙니다."){
						$("#img").replaceWith($("#img").val('').clone(true));
						file = null;
					}
				}else{
					alert("저장되었습니다.");
					init();
					tmpltLstSelect();
				}		  
			}			   
		});
	}
	
	function msgTmpltMgntUpdate(){
		if($('#tmpltCd').val().substring(4) == '00001'){
			if($('#msgText').val().indexOf('{#수신거부_URL}') == -1){
				alert('해당 템플릿은 {#수신거부_URL}이 필수적으로 포함되어야합니다.');
				return;
			}
		} else{
			if($('#msgText').val().indexOf('{#안내_URL}') == -1){
				alert('해당 템플릿은 {#안내_URL}이 필수적으로 포함되어야합니다.');
				return;
			}
		}
		
		if(reqValCheck('msgTitle', '메시지 제목')) return;
		if(reqValCheck('msgText', '메시지 내용')) return;
		
		var file = document.getElementById("img").files[0];
		/*파일 이미지 사이즈 입력*/
		if(file){
			$("#msgImgSize").val(file.size);	
		} else{
			$("#msgImgSize").val(0);
		}
		
		var formData = new FormData($('#msgTmpltFile')[0]); 
		$.ajax({
			type: "POST"
			,enctype: 'multipart/form-data'
			,url : "msgTmpltMgntUpdate.ajax"
			,data: formData
			,processData: false
			,contentType: false
			,cache: false
			,success: function (data) { 
				if(data.errorMessage != ""){
					alert(data.errorMessage);
					
					if(data.errorMessage == "지원하는 이미지 파일 형식이 아닙니다."){
						$("#img").replaceWith($("#img").val('').clone(true));
						file = null;
					}
				}else{
					alert("저장되었습니다.");
					init();
					tmpltLstSelect();
				}		  
			}			   
		});
	}
	
	function deleteFile(){
		$('#saveFn').val('');
		$('#msgImgSize').val('0');
		alert('삭제되었습니다.');
	}
	
	//파일 체크
	function fileCheck() {
		var file = document.getElementById("img").files[0];
		var fileSize = 1024*300; //300KB
		
		var temp = $('#img').val().split('.');
		var ext = temp[temp.length - 1].toLowerCase();
		
		
		if(file && !(ext == 'jpg' || ext == 'jpeg' || ext == 'gif' || ext == 'sis')){
			$("#img").replaceWith($("#img").val('').clone(true));
			file = null;
			
			alert("지원하는 이미지 파일 형식이 아닙니다.");
			return;
		}
		
		if(file && file.size > fileSize){
			$("#img").replaceWith($("#img").val('').clone(true));
			file = null;
			
			alert("첨부파일 사이즈는 300KB 이내로 등록 가능합니다.");
			return;
		}
		
		if(file){
			var _URL = window.URL || window.webkitURL;
			var img = new Image();
			img.src = _URL.createObjectURL(file);
			img.onload = function(){
				if(this.width > 2000 || this.height > 2000){
					$("#img").replaceWith($("#img").val('').clone(true));
					file = null;
					
					alert("첨부파일은 가로 2000px, 세로 2000px 이내로 등록 가능합니다.");
					return;
				}
			};	
		}
		
	}
	
	function callBack(sel_id, codeNum){
		$("#inputTmpltUseYn").val('${inputTmpltUseYn}');
		
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
	}
	
	</script>
</head>
<body>
    <!-- wrap -->
    <div id="wrap">
        <!-- contents -->
        <div id="contents">
            <!-- section -->
            <div class="section">
                <!-- caption-pnl -->
                <form id="msgTmpltMgnt">
                <input hidden="hidden"/>
				<input type="hidden" id="athLev" name="athLev" value="${athLev}"/> 
				<input type="hidden" id="pageIndex" name="pageIndex" value="${searchVO.pageIndex}"/>
                <div class="caption-pnl">
                    <h2>메시지 템플릿 목록</h2>
                    <div class="buttonset">
                        <button type="button" class="state-highlight" id="btnIns" onclick="msgTmpltMgntInsert()" disabled="false">저장</button>
                        <button type="button" id="btnUpd" onclick="msgTmpltMgntUpdate()" disabled="false">수정</button>
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
                            <td><select id="inputOganCd" name="inputOganCd"></select></td>
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
                            <td class="buttonset"><button type="button" onclick="tmpltLstSelect()">조회</button></td>
                        </tr>
                    </table>
                </div>
                <!-- //search-pnl -->
                <!-- grid-pnl -->
                <div class="grid-pnl">
                    <!-- table -->
                    <table class="align-center">
                        <colgroup>
                            <col width="17%">
                            <col width="*">
                            <col width="17%">
                            <col width="13%">
                            <col width="13%">
                        </colgroup>
                        <thead>
                            <tr>
                                <th scope="col">기관(회사)명</th>
                                <th scope="col">템플릿명</th>
                                <th scope="col">템플릿코드</th>
                                <th scope="col">메시지 템플릿</th>
                                <th scope="col">사용여부</th>
                            </tr>
                        </thead>
                        <tbody id="tmpltList">
                        	<c:forEach var="tmpltList" items="${tmpltList}">
				          		<tr>
				          			<td style="cursor:pointer;" onclick="init();msgTmpltDetlSelect('${tmpltList.oganCd}', '${tmpltList.tmpltCd}')">${tmpltList.oganNm}</td>
				          			<td style="cursor:pointer;" onclick="init();msgTmpltDetlSelect('${tmpltList.oganCd}', '${tmpltList.tmpltCd}')">${tmpltList.tmpltNm}</td>
				          			<td style="cursor:pointer;" onclick="init();msgTmpltDetlSelect('${tmpltList.oganCd}', '${tmpltList.tmpltCd}')">${tmpltList.tmpltCd}</td>
				          			<c:if test="${tmpltList.msgTmpltYn.equals('Y')}">
				          			<td><button type="button" class="image-folder" onclick="msgTmpltDetlAtv('${tmpltList.oganCd}', '${tmpltList.tmpltCd}', 'upd')"></button></td>
				          			</c:if>
				          			<c:if test="${tmpltList.msgTmpltYn.equals('N')}">
				          			<td><button type="button" class="image-plus" onclick="msgTmpltDetlAtv('${tmpltList.oganCd}', '${tmpltList.tmpltCd}', 'ins')"></button></td>
				          			</c:if>
				          			<c:if test="${tmpltList.tmpltUseYn.equals('Y')}">
									<td style="cursor:pointer;" onclick="init();msgTmpltDetlSelect('${tmpltList.oganCd}', '${tmpltList.tmpltCd}')">예</td>
									</c:if>
									<c:if test="${tmpltList.tmpltUseYn.equals('N')}">
									<td style="cursor:pointer;" onclick="init();msgTmpltDetlSelect('${tmpltList.oganCd}', '${tmpltList.tmpltCd}')">아니오</td>
									</c:if>
				          		</tr>
				          	</c:forEach>
                        </tbody>
                    </table>
                    <!-- //table -->
                </div>
                <!-- //grid-pnl -->
                
                <!-- paging -->
				<div class="paging">
					<c:if test="${fn:length(tmpltList) ne 0 }">  
						<ul>
							<ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="linkPage" />
						</ul>
					</c:if>
				</div>
				<!-- //paging -->
				</form>
				
                <!-- caption-pnl -->
            	<div class="caption-pnl">
                	<h3>템플릿기본</h3>
                </div>
                <!-- //caption-pnl -->
                <form id="msgTmpltFile">
                <input hidden="hidden" id="tmpltCd" name="tmpltCd">
                <input hidden="hidden" id="oganCd" name="oganCd">	
                <div class="table-pnl">
                	<!-- table -->
					<table>
                    	<colgroup>
                        	<col width="15%">
                            <col width="35%">
                            <col width="15%">
                            <col width="20%">
                            <col width="15%">
                       	</colgroup>
						<tbody id="msgTmpltDetl">
							<tr>
								<th>기관(회사)명<span style="color: #fea338;">*</span></th>
								<td><input type="text" id="oganNm" name="oganNm" disabled="true"></td>
								<th>템플릿명<span style="color: #fea338;">*</span></th>
								<td colspan="2"><input type="text" id="tmpltNm" name="tmpltNm" disabled="true"></td>
							</tr>
							<tr>
								<th>메시지제목<span style="color: #fea338;">*</span></th>
								<td colspan="4"><input type="text" id="msgTitle" name="msgTitle" disabled="true"></td>
							</tr>
							<tr>
								<th>메시지내용<span style="color: #fea338;">*</span></th>
								<td colspan="3">
									<textarea class="markup" placeholder="메시지내용입력" id="msgText" name="msgText" disabled="true"></textarea>
									<div id="textLimit" name ="textLimit" style="margin:5px; float:right; font-size:12px; color:gray;">글자제한없음</div>
								</td>
								<td style="font-size:12px; text-align:left; vertical-align:top"><br>*매핑 파라메터<br><br>{#name} : 고객명 <br>{#안내_URL} : 안내문확인 URL<br>{#수신거부_URL} : 수신거부URL</td>
							</tr>
							<tr>
								<th>이미지</th>
								<td colspan="4">
									<div id="saveDiv" style="display:none;">
										<input type="text" id="saveFn" name="saveFn" style="width:40%" readonly>
										<button type="button" id="deleteBtn" onclick="deleteFile()" style="display:none;">삭제</button>
									</div>
									<input type="file" accept=".jpg, .jpeg, .gif, .sis" id="img" name="img" onchange="fileCheck()" disabled="true">
									<input type="hidden" id="msgImgSize" name="msgImgSize">
								</td>
							</tr>
                   	    </tbody>
					</table>
                    <!-- //table -->
               </div>
               </form>
               <!-- //table-pnl -->
            </div>
            <!--// section -->
        </div>
        <!-- //contents -->

    </div>
    <!-- //wrap -->

</body>
</html>