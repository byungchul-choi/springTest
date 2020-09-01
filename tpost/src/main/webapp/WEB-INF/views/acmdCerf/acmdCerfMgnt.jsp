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
    <title>유통증명서 현황관리</title>

    <!-- CSS  STYLE -->
	<link rel="stylesheet" href="/tpost/resource/css/common.css">
	<!-- ---------- -->
	<style>
		#acmdCerfInsPopu .layer {height:440px; width:700px;}
	</style>
    <!--     JS     -->
	<script src="/tpost/resource/js/jquery.min.js"></script>
	<script src="/tpost/resource/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="/tpost/resource/js/jquery-ui.min.js"></script>
	<script src="/tpost/resource/js/comm.js"></script>
	<!-- ---------- -->
    <script type="text/javascript">
	    $('document').ready(function(){	
	    	commCd_bas('inputIssCls', '00007');
	    	
	    	if($("#inputRengStDt").val() == "" && $("#inputRengClosDt").val() == "") {
	    		getYYYYMMFirstDay('inputRengStDt');
				getYYYYMMLastDay('inputRengClosDt');
	    	}
	    	
	    	datepicker();
	    })
	    
	    function enterSerch(){
	    	if(window.event.keyCode == 13){
	    		acmdCerfListSelect();
	    	}
	    }
	    
	    //상단 조회 버튼 클릭 시
		function acmdCerfListSelect(){
			$('#pageIndex').val(1); /*첫조회는 페이지가 1로 조회*/
			
			var acmdCerfListForm = document.getElementById("acmdCerfMgnt");
			acmdCerfListForm.action = "acmdCerfListSelect";
			acmdCerfListForm.method = "post";
			acmdCerfListForm.submit();
		}
		
		//goAcmdCerfInsPopu(유통증명서신청) 팝업 오픈
		function goAcmdCerfInsPopu(){
			jQuery("#acmdCerfInsPopu").show();
			
			$("#inputPopuOganCd").html("");
			commCd_bas('inputPopuOganCd', '00001');

			$("#inputPopuBsrpCls").html("");
			commCd_bas('inputPopuBsrpCls', '00006');

			$("#inputPopuName").val("");
			$("#inputPopuCiNum").val("");
			
			if($("#inputPopuTrnsDate").val() == "") {
				getToday('inputPopuTrnsDate');
			}
			
			changeTmpltList();
			changeInput();
		}
		
		//기관코드에 따른 템플릿 명
		function changeTmpltList(){
			var oganCd = document.getElementById("inputPopuOganCd");
			tmpltCd_bas('inputPopuTmpltCd', oganCd.options[oganCd.options.selectedIndex].value);
		}
		
		//개인 혹은 법인에 따라 CINum 인풋값 셋팅
		function changeInput(){
			var bsrpCls = $('#inputPopuBsrpCls option:selected').val();
			var td = $('#acmdCerfInsPopuTbody tr:eq(3)').children();
			td.eq(2).html('');
			
			var inner = '';
			if(bsrpCls == "100"){//개인일경우
				inner += '<input type="text" size="45" id="inputPopuCiNum" name="inputPopuCiNum" placeholder="연계정보(CI) 88Byte를 입력해주세요." maxlength="88" onKeyUp="noHangle(event)">';
				td.eq(2).append(inner);
			}else if(bsrpCls == "200"){//법인일경우
				inner += '<input type="text" size="45" id="inputPopuCiNum" name="inputPopuCiNum" placeholder="사업자번호 10Byte를 입력해주세요." maxlength="12" onkeypress="onlyNumber(); _maxLengthCheck(this, 9);" onKeyUp="this.value=this.value.replace(/[^0-9]/g,\'\');">';
				td.eq(2).append(inner);
			}
		}
		
		//유통증명서 신청
		function acmdCerfInsert(){
			if(reqValCheck('inputPopuName', '고객명')) return;
			if(reqValCheck('inputPopuCiNum', '연계정보(CI) 또는 사업자번호')) return;
			if(reqValCheck('inputPopuTrnsDate', '송신일자')) return;
			
			var bsrpCls = $('#inputPopuBsrpCls option:selected').val();
			if(bsrpCls == "100"){//개인일경우
				if($('#inputPopuCiNum').val().length != 88){
					alert('연계정보(CI) 값이 88Byte가 아닙니다. 다시 입력해주세요.');
					return;
				}
			}else if(bsrpCls == "200"){//법인일경우
				if($('#inputPopuCiNum').val().length != 10){
					alert('사업자번호 값이 88Byte가 아닙니다. 다시 입력해주세요.');
					return;
				}
			}
			
			var inputPopuOganCd = $('#inputPopuOganCd option:selected').val();
			var inputPopuTmpltCd = $('#inputPopuTmpltCd option:selected').val();;
			var inputPopuName = $('#inputPopuName').val();
			var inputPopuCiNum = $('#inputPopuCiNum').val();
			var inputPopuTrnsDate = $('#inputPopuTrnsDate').val();
			
			var parameters = "inputPopuOganCd=" + inputPopuOganCd
							+"&inputPopuTmpltCd=" + inputPopuTmpltCd
							+"&inputPopuName=" + inputPopuName
							+"&inputPopuCiNum=" + inputPopuCiNum
							+"&inputPopuTrnsDate=" + inputPopuTrnsDate;
			
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "acmdCerfInsert.ajax",
				data : parameters,
				success : function(data) {
					if(data.errorMessage.length > 0){
						alert(data.errorMessage);				
					}
					else{
						alert("신청되었습니다.");
						closePopu();
						
						acmdCerfListSelect();
					}
				},
				complete : function(data) {
				}
			});
		}
		
		
		//유통증명서 다운로드
		function acmdCertFildDownload(dwldUrl, oganCd){
			$('#dwldUrl').val(dwldUrl);
			$('#oganCd').val(oganCd);
			
			var acmdCerfListForm = document.getElementById("acmdCerfMgnt");
			acmdCerfListForm.action = "acmdCertFildDownload";
			acmdCerfListForm.method = "post";
			acmdCerfListForm.submit();
		}
		
		//다음페이지
		function linkPage(pageNo){
			if(pageIndex == ""){
				pageIndex = 1;
			}
				  
			$('#pageIndex').val(pageNo);
				
			var acmdCerfListForm = document.getElementById("acmdCerfMgnt");
			acmdCerfListForm.action = "acmdCerfListSelect";
			acmdCerfListForm.method = "post";
			acmdCerfListForm.submit();
		}	
			    
	    function callBack(sel_id, codeNum){
	    	if(sel_id == "#inputPopuOganCd") {
	    		if($("#inputPopuOganCd option").size() == 1){
					$("#inputPopuOganCd").prop("disabled", "disabled");
				}
	    	}
	    	
			if(sel_id == "#inputIssCls") {
				$("#inputIssCls").prepend("<option value='' selected='true'>전체</option>");
				
				var value = '${inputIssCls}';
				
				if(value == "") return;
				
				var size = $("#inputIssCls option").size();
				for(var i = 0; i < size; i++){
					var selector = $("#inputIssCls option:eq("+ i +")");
					if(selector.val() == value){
						selector.attr("selected", "selected");
					}
				}
			}
			
			if(sel_id == "#inputPopuTmpltCd") {
				$("#inputPopuTmpltCd").find("option").each(function() {
					 if(this.value == "") {
					  $(this).remove();
					 }
				});
			}
		}
	    
	  //팝업 닫기
        function closePopu(){
        	jQuery(".layerBox").hide();
        }
    </script>

</head>

<body>
<form id="acmdCerfMgnt">
<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex}"/> 
<input type="hidden" id="dwldUrl" name="dwldUrl"/>
    <!-- wrap -->
    <div id="wrap">
        <!-- contents -->
        <div id="contents">
            <!-- section -->
            <div class="section">
                <!-- caption-pnl -->
                <div class="caption-pnl">
                    <h2>유통증명서 현황관리</h2>
                    <div class="buttonset">
                        <button type="button" onclick="goAcmdCerfInsPopu()">신청</button>
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
                            <col width="100px" />
                            <col width="160px" />
                            <col width="30px" />
                            <col width="200px" />
                        </colgroup>
                        <tr>
                            <th>신청일자</th>
                            <td><input type="text" size="13" class="dateinput" id="inputRengStDt" name="inputRengStDt" value="${inputRengStDt}" onkeypress="dateFormat(this)" onKeyDown="enterSerch()"> ~ <input type="text" size="13" class="dateinput" id="inputRengClosDt" name="inputRengClosDt" value="${inputRengClosDt}" onkeypress="dateFormat(this)" onKeyDown="enterSerch()"></td>
                            <th>발급구분</th>
                            <td><select id="inputIssCls" name="inputIssCls"></select></td>
                            <th>고객명</th>
                            <td><input type="text" size="8" id="inputName" name="inputName" value="${inputName}" onKeyDown="enterSerch()"></td>
                            <td></td>
                            <td class="buttonset" rowspan="2"><button type="button" onclick="acmdCerfListSelect()">조회</button>
                            </td>
                        </tr>
                        <tr>
                            <th>공인전자주소</th>
                            <td><input type="text" size="16" maxlength="15" onkeypress="onlyNumber(); _maxLengthCheck(this, 12);" onKeyUp="this.value=this.value.replace(/[^0-9]/g,'');" id="inputOfapElctAddr" name="inputOfapElctAddr" value="${inputOfapElctAddr}" onKeyDown="enterSerch()"></td>
                            <th>연계정보(CI)</th>
                            <td><input type="text" size="10" id="inputCiNum" name="inputCiNum" value="${inputCiNum}" onKeyDown="enterSerch()"></td>
                            <td colspan="3"></td>
                        </tr>
                    </table>
                </div>
                <!-- //search-pnl -->
                <!-- grid-pnl -->
                <div class="grid-pnl">
                    <!-- table -->
                    <table class="align-center">
                        <colgroup>
                            <col width="10%">
                            <col width="10%">
                            <col width="10%">
                            <col width="*">
                            <col width="*">
                            <col width="*">
                            <col width="8%">
                            <col width="8%">
                        </colgroup>
                        <thead>
                            <tr>
                                <th scope="col">신청일자</th>
                                <th scope="col">송신일자</th>
                                <th scope="col">신청자</th>
                                <th scope="col">고지(안내)서</th>
                                <th scope="col">고객명</th>
                                <th scope="col">공인전자주소</th>
                                <th scope="col">발급구분</th>
                                <th scope="col">다운로드</th>
                            </tr>
                        </thead>
                        <tbody>
	                        <c:forEach var="acmdCerfList" items="${acmdCerfList}">
				         		<tr>
				         			<td>${acmdCerfList.apctDate}</td>
				         			<td>${acmdCerfList.trnsDate}</td>
				         			<c:if test="${acmdCerfList.apctCls.equals('0')}" >
				         			<td>${acmdCerfList.apctOgan}</td>
				         			</c:if>
				         			<c:if test="${acmdCerfList.apctCls.equals('1')}" >
				         			<td>${acmdCerfList.name}</td>
				         			</c:if>
				         			<td>${acmdCerfList.tmpltCd}</td>
				         			<td>${acmdCerfList.name}</td>
				         			<td style="cursor:pointer;" onclick="ofapElctAddrToCi('${acmdCerfList.ciNum}')">${acmdCerfList.ofapElctAddr}</td>
				         			<td>${acmdCerfList.issCls}</td>
				         			<c:if test="${acmdCerfList.issCls.equals('다운가능')}" >
										<!-- <td onclick="location.href='${acmdCerfList.dwldUrl}'"><button type="button" class="image-folder"></button></td> -->
										<td><button type="button" class="image-folder" onclick="acmdCertFildDownload('${acmdCerfList.dwldUrl}', '${acmdCerfList.oganCd}')"></button></td> 
									</c:if>
									<c:if test="${!acmdCerfList.issCls.equals('다운가능')}" >
										<td></td>
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
				<c:if test="${fn:length(acmdCerfList) ne 0 }">  
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
	<div id="acmdCerfInsPopu" class="layerBox" style="display:none;">
		<div class="layer">
			<div class="topBox">
				<h3>유통증명서 신청</h3>
				<a class="close" onclick="closePopu()">&#10005;</a>
			</div>
			<div class="contBox">
				<div class="section">
					<div class="table-pnl">
                		<!-- table -->
                		<table>
						<colgroup><col width="175px"><col width="100px"><col width="*"></colgroup>
                    	<tbody id ="acmdCerfInsPopuTbody"><tr>
                        	<th>신청기관<span style="color: #fea338;">*</span></th>
                            <td colspan="2"><select id="inputPopuOganCd" name="inputPopuOganCd" onchange="changeTmpltList()"></select></td>
                        </tr>
						<tr>
                        	<th>안내문<span style="color: #fea338;">*</span></th>
							<td colspan="2"><select id="inputPopuTmpltCd" name="inputPopuTmpltCd"></select></td>
                        </tr>
						<tr>
                        	<th>고객명<span style="color: #fea338;">*</span></th>
							<td colspan="2"><input type="text" id="inputPopuName" name="inputPopuName"></td>
                        </tr>
						<tr>
                        	<th>연계정보(CI)<br>또는 사업자번호<span style="color: #fea338;">*</span></th>
							<td><select id="inputPopuBsrpCls" name="inputPopuBsrpCls" onchange="changeInput()"></select></td>
							<td></td>
                        </tr>
						<tr>
                        	<th>송신일자<span style="color: #fea338;">*</span></th>
							<td colspan="2"><input type="text" size="13" class="dateinput" id="inputPopuTrnsDate" name="inputPopuTrnsDate" onkeypress="dateFormat(this)"></td>
                        </tr>
						</tbody>
						</table>
                    	<!-- //table -->
                	</div>
				<!-- footer -->
				<div class="footer">
					<div class="buttonset">
						<button type="button" class="state-highlight" onclick="acmdCerfInsert()">신청</button>
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