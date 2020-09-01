<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>  <!-- 전자정부태그라이브러리 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  <!-- jstl펑션 -->


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- CSS  STYLE -->
<!-- <link rel="stylesheet" href="/resource/css/admin/common.css"> -->
<link rel="stylesheet" href="/tpost/resource/css/common.css">
<!-- ---------- -->


<!--     JS     -->
<script src="/tpost/resource/js/jquery.min.js"></script>
	<script src="/tpost/resource/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="/tpost/resource/js/jquery-ui.min.js"></script>
	<script src="/tpost/resource/js/comm.js"></script>
<!-- ---------- -->

<script type="text/javascript">

/*테이블 row 클릭시 이벤트 발생 */
$( document ).ready(function() {

	datepicker();
	commCd_bas('oganCd', '00001'); /*기관명 조회*/

	if($("#inputIssStDt").val() == "" && $("#inputIssClosDt").val() == "") {
		getYYYYMMFirstDay('inputIssStDt');
		getYYYYMMLastDay('inputIssClosDt');
	}
});


function callBack(sel_id, codeNum){
	 
	 if(sel_id == '#oganCd' ){
		 
		 var value = '${oganCd}';
		 var size = $("#oganCd option").size();
			for(var i = 0; i < size; i++){
				var selector = $("#oganCd option:eq("+ i +")");
				if(selector.val() == value){
					selector.attr("selected", "selected");
				}
			}
			
			var oganCd = document.getElementById("oganCd");
			 tmpltCd_bas('tmpltCd', oganCd.options[oganCd.options.selectedIndex].value);
			 
	 }
	 
	 
	 if(sel_id == '#tmpltCd' ){
		 
		 var value = '${tmpltCd}';
		 
		 var size = $("#tmpltCd option").size();
			for(var i = 0; i < size; i++){
				var selector = $("#tmpltCd option:eq("+ i +")");
				if(selector.val() == value){
					selector.attr("selected", "selected");
				}
			}
	 }
	 
	 
}


function changeTmpltList(type){
	if(type == 0){
		var oganCd = document.getElementById("oganCd");
		tmpltCd_bas('tmpltCd', oganCd.options[oganCd.options.selectedIndex].value);		
	}
	
}

/*라디오 체크 박스 관리*/
function radioChk(val){
	var today = new Date();
	
	var yyyy = String(today.getFullYear());
	var mm = String(today.getMonth()+1);
	var dd = String(today.getDate());
	
	
	if(mm.length != 2) mm = "0" + mm;
	if(dd.length != 2) dd = "0" + dd;
	var dateSt = yyyy + "-" + mm + "-" + dd;
	$('#inputIssClosDt').val(dateSt);
	
	$('#radioChk1').val(val);
	
	if(val=='week'){
		$('#inputIssStDt').val(getMinusDt(dateSt, 7));
	}else if(val=='mon'){
		$('#inputIssStDt').val(getMinusMon(dateSt, 1));
	}else if(val=='year'){
		
		$('#inputIssStDt').val(getMinusYear(dateSt, 1));
	}
	
	
		
}

function enterSerch(){
	if(window.event.keyCode == 13){
		sttcInfoListSelect();
	}
}

function sttcInfoListSelect(){
	
	var acmdCerfListForm = document.getElementById("inputSearch");
	acmdCerfListForm.action = "sttcInfo";
	acmdCerfListForm.method = "post";
	acmdCerfListForm.submit();
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
                <div class="caption-pnl">
                    <h2>통계정보-본문자</h2>
                </div>
                <!-- //caption-pnl -->
                <!-- search-pnl -->
                <div class="search-pnl">
                <form id="inputSearch" name="inputSearch" >
                <input type="hidden" id = "radioChk1" name="radioChk1" value="${radioChk1}" />
                    <table >
                        <colgroup>
                            <col width="120px" />
                            <col width="*" />
                            <col width="130px" />
                            <col width="*" />
                            <col width="200px" />
                        </colgroup>
                        <tr>
                            <th>기관명</th>
                            <td><select id="oganCd" name="oganCd" onchange="changeTmpltList(0);"></select></td>
                            <th>템플릿명</th>
                            <td><select id="tmpltCd" name="tmpltCd"></select></td>
                            <td class="buttonset" rowspan="2"><button type="button" onclick="sttcInfoListSelect();" >조회</button>
                        </tr>
                        <tr>
                            <th>기간</th>
                            <td colspan="4">
	                            <input type="radio" name="prdRadio" id="prdRadio_week"  onclick="radioChk('week');"  ${radioChk1 eq 'week' ? 'checked' : ''} />
	                               <label for="prdRadio_week"></label><label for="prdRadio_week">최근1주일</label>
	                            <input type="radio" name="prdRadio" id="radio_mon"  onclick="radioChk('mon');"    ${radioChk1 eq 'mon' ? 'checked' : ''} />
	                               <label for="radio_mon"></label><label for="radio_1mon">최근1개월</label>
	                            <input type="radio" name="prdRadio" id="radio_year"  onclick="radioChk('year');"  ${radioChk1 eq 'year' ? 'checked' : ''} />
	                                <label for="radio_year"></label><label for="radio_year">최근1년</label>
								&nbsp;&nbsp;&nbsp;&nbsp;기간지정&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                            <input type="text" size="13" class="dateinput" id="inputIssStDt" name="inputIssStDt" value="${inputIssStDt}" onkeypress="dateFormat(this)" onKeyDown="enterSerch()">
	                             ~ 
	                            <input type="text" size="13" class="dateinput" id="inputIssClosDt" name="inputIssClosDt" value="${inputIssClosDt}" onkeypress="dateFormat(this)" onKeyDown="enterSerch()">
                            </td>
                        </tr>
                    </table>
                    </form>
                </div>
                <!-- //search-pnl -->
                <!-- grid-pnl -->
                 <!-- //search-pnl -->
                <div class="table-pnl">
                	<!-- table -->
                	<table class="vtable">
                        <colgroup>
                            <col width="16%">
                            <col width="16%">
                            <col width="16%">
                            <col width="16%">
                            <col width="18%">
                            <col width="18%">
                        </colgroup>
						<thead>
	                    	<tr>
	                        	<th rowspan="2">전체발송건수</th>
	                        	<th colspan="3">전송건수</th>
	                        	<th colspan="2">열람건수</th>
	                        </tr>
	                    	<tr>
	                        	<th>성공</th>
	                        	<th>실패</th>
	                        	<th>성공율(%)</th>
	                        	<th>전체열람건수</th>
	                        	<th>성공율(%)</th>
	                        </tr>
						</thead>
						<tbody id="detlTopTable">
							<tr>
								<td class="text-r">${resultList.allSndnCnt}</td>
								<td class="text-r">${resultList.allSuccCnt}</td>
								<td class="text-r">${resultList.allFailCnt}</td>
								<td class="text-r">${resultList.allSuccRate}</td>
								<td class="text-r">${resultList.allRdngCnt}</td>
								<td class="text-r">${resultList.allRdngRate}</td>
	                        </tr>
						</tbody>
                    </table>
                    <!-- //table -->
                </div>
                <!-- grid-pnl -->
                <div class="grid-pnl">
                    <!-- table -->
                    <table class="align-center">
                        <colgroup>
                            <col width="11%">
                            <col width="15%">
                            <col width="11%">
                            <col width="9%">
                            <col width="9%">
                            <col width="9%">
                            <col width="9%">
                            <col width="8%">
                            <col width="8%">
                        </colgroup>
                        <thead>
                            <tr>
                                <th scope="col" rowspan="2" >기관</th>
                                <th scope="col" rowspan="2" >템플릿명</th>
                                <th scope="col" rowspan="2" >일자</th>
                                <th scope="col" rowspan="2" >총건수</th>
                                <th scope="col" colspan="3">전송건수</th>
                                <th scope="col" colspan="2">열람건수</th>
                            </tr>
                            <tr>
                                <th scope="col">성공</th>
                                <th scope="col">실패</th>
                                <th scope="col">성공률</th>
                                <th scope="col">전체열람건</th>
                                <th scope="col">성공률</th>
                            </tr>
                        </thead>
                        <tbody id="elctDocDetlListTable">
                        	<c:forEach var="resultListDetail" items="${resultListDetail}">
					          	<tr>
									<td>${resultListDetail.oganCd}</td>
									<td>${resultListDetail.tmpltCd}</td>
									<td>${resultListDetail.transDt}</td>
									<td>${resultListDetail.sndnCnt}</td>
									<td>${resultListDetail.succCnt}</td>
									<td>${resultListDetail.failCnt}</td>
									<td>${resultListDetail.succRate}</td>
									<td>${resultListDetail.rdngCnt}</td>
									<td>${resultListDetail.rdngRate}</td>
								</tr>
							</c:forEach>
                        </tbody>
                    </table>
           
            </div>
            <!--// section -->
        </div>
        <!-- //contents -->

    </div>
    </div>
</body>
</html>
