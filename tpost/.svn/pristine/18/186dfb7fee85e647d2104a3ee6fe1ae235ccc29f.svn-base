<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- CSS  STYLE -->
<link rel="stylesheet" href="/resource/css/admin/common.css">
<!-- ---------- -->

<!--     JS     -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="/resource/js/comm.js"></script>
<!-- ---------- -->
<script type="text/javascript">
/*공통코드조회*/
function inquiryCommCd_Select(){
	var commCdMgntForm = document.getElementById("inquiryCommCd");
	commCdMgntForm.action = "inquiryCommCdSel";
	commCdMgntForm.method = "post";
	commCdMgntForm.submit();
}

/*코드저장*/
function inquiryCommCd_insert(){
	alert("inquiryCommCd_insert");
	var commCdMgntForm = document.getElementById("inquiryCommCd");
	commCdMgntForm.action = "insertCommCd";
	commCdMgntForm.method = "post";
	commCdMgntForm.submit();
}

/*코드수정*/
function inquiryCommCd_update(){
	alert("inquiryCommCd_update");
	var commCdMgntForm = document.getElementById("inquiryCommCd");
	commCdMgntForm.action = "updateCommCd";
	commCdMgntForm.method = "post";
	commCdMgntForm.submit();
}

/*코드삭제*/
function inquiryCommCd_delete(){
	alert("inquiryCommCd_delete");
	var commCdMgntForm = document.getElementById("inquiryCommCd");
	commCdMgntForm.action = "deleteCommCd";
	commCdMgntForm.method = "post";
	commCdMgntForm.submit();
}


function test(){
	alert("test : " + $("#datePick1").val());
}


/* ajax 처리 시작  
 * _value 값
 * _cdKnd : 코드종류(분류코드 : 0, 기본코드 : 1, 상세코드  : 2)
 */
function fn_SearchMarketList(_value, _cdKnd) {	
	var cdKnd = "";
	
	if(_cdKnd == "0") cdKnd = "분류";
	else if(_cdKnd == "1") cdKnd = "기본";
	else if(_cdKnd == "2") cdKnd = "상세";
	
	$("#popupTitle").html('');	// 타이틀 초기화
	$("#popupTitle").append(cdKnd + "코드  수정");
	
	var parameters = "cfcd="+ _value;

	
	
	return;
	$.ajax({
		type : "post",
		dataType : "json",
		async : false,
		url : "<c:url value='/html/inquiryCommCd/cfcdPopu-select.ajax'/>",
		data : parameters,
		success : function(data) {
			if ( "1" != "1") {
					
			} else {
				$("#popupTableList").html('');	// 테이블 초기화
				
				var popupTableListTr = '';
			
				if (data.popupTableList.length != 0) {
					var popupTableList = data.popupTableList[0];
					popupTableListTr += '<tr>';
					popupTableListTr += '<th>' + cdKnd + '코드</th>';
					popupTableListTr += '<td><input type="text" class="width100" value="' + popupTableList.cfcd + '" id="popUpdateCd" name="popUpdateCd"><input type="text" style="display:none;" value="' + popupTableList.cfcd + '" id="targetCd" name="targetCd"></td>';
					popupTableListTr += '</tr>';
					popupTableListTr += '<tr>';
					popupTableListTr += '<th>' + cdKnd + '코드명</th>';
					popupTableListTr += '<td><input type="text" class="width100" value="' + popupTableList.cfcdNm + '" id="popUpdateCdNm" name="popUpdateCdNm"></td>';
					popupTableListTr += '</tr>';
					popupTableListTr += '<tr>';
					popupTableListTr += '<th>' + cdKnd + '코드 설명</th>';
					popupTableListTr += '<td><input type="text" class="width100" value="' + popupTableList.cfcdDesc + '" id="popUpdateCdDesc" name="popUpdateCdDesc"></td>';
					popupTableListTr += '</tr> ';
					
				} else {
					  
					popupTableListTr += '<tr>';
					popupTableListTr += '	<td>';
					popupTableListTr += '	표시할 항목이 없습니다.';
					popupTableListTr += '	</td>';
					popupTableListTr += '</tr>';
 				
				}
				
				$("#popupTableList").append(popupTableListTr);
			}
		},
		complete : function(data) {
		}
		,
		error: function(request, status, error){

			if(request.status == "505"){
				alert("로그인 정보가 없습니다. 로그인 후 진행하시기 바랍니다.");
				location.reload(true);
			}else{
				console.log("에러가 발생했습니다!");
				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		}
            
	});
}

function fn_SearchDetailInformation(_value, _cdKnd) {	
	if(_cdKnd == "0"){
		var parameters = "cfcd="+ _value;
		
		var win = window.open("/html/inquiryCommCd/cfcdPopu-select.ajax?" + parameters, "PopupWin", "width=500,height=600");
		
		return;
		
		$.ajax({
			type : "post",
			dataType : "json",
			async : false,
			url : "<c:url value='/html/inquiryCommCd/cfcdPopu-select.ajax'/>",
			data : parameters,
			success : function(data) {
				if ( "1" != "1") {
						
				} else {
					if(data.popupTableList.length != 0){
						$("#infCfcd").html('');	// 테이블 초기화
						var popupTableListTr = '';
						
						var popupTableList = data.popupTableList[0];

						popupTableListTr += '<tr>';
						popupTableListTr += '<th>분류코드</th>';
						popupTableListTr += '<td><input type="text" class="width100" value="' + popupTableList.cfcd + '"></td>';
						popupTableListTr += '<th>분류코드명</th>';
						popupTableListTr += '<td><input type="text" class="width100" value="' + popupTableList.cfcdNm + '"></td>';
						popupTableListTr += '<th>분류코드 설명</th>';
						popupTableListTr += '<td><input type="text" class="width100" value="' + popupTableList.cfcdDesc + '"></td>';
						popupTableListTr += '</tr>';	
						
						$("#infCfcd").append(popupTableListTr);
					}
					
				}
			}    
		});
	}
}
		
	
/* ajax 처리 끝 */

</script>
</head>
<body>				

<form id="inquiryCommCd">					
 <div id="wrap">
	<section class="contents-wrap">
           <h2 class="title1">공통 코드 조회</h2>
           		<div class="align-col2-wrapper">
	                <div class="l-area" style="width: 90%">
	                    <span class ="title3">&nbsp;&nbsp;분류코드 : </span>
	                    <input style="width: 13%" type="text" id="inputCfcd" name="inputCfcd">
	                    <span class ="title3">&nbsp;&nbsp;분류코드명 : </span>
	                    <input style="width: 13%" type="text" id="inputCfcdNm" name="inputCfcdNm">
	                    <span class ="title3">&nbsp;&nbsp;기본코드 : </span>
	                    <input style="width: 13%" type="text" id="inputDtcd" name="inputDtcd">
	                    <span class ="title3">&nbsp;&nbsp;기본코드명 : </span>
	                    <input style="width: 13%" type="text" id="inputDtcdNm" name="inputDtcdNm">
	                </div>
	                <div class="r-area" style="width: 10%">
	                    <button type="button" class="btn black" onclick="inquiryCommCd_Select();">조회</button>
	                </div>
            	</div> <!--//align-col2-wrapper-->
           <div class="item-col3-wrapper" style="width:100%; height:350px">
		    	<div class="col">
		    		<div class="table-title">
		    			<div class="align-col2-wrapper">
                			<div class="l-area" style="width: 75%">
                    			<h4 class="title">분류코드</h4>
                			</div>
                			<div class="r-area" style="width: 25%">
                    			<button type="button" class="btn black" onclick="layer_popup('#cfcdPopUp');">등록</button>
               			 	</div>
            			</div> <!--//align-col2-wrapper-->
		    		</div>
		    		<div style="overflow-x:hidden; overflow-y:auto; height:300px;">
		    		<table class="table center">
						<colgroup>
							<col style="width: 30%">
							<col>
						</colgroup>
						<thead>
							<tr>
								<th>분류코드</th>
								<th>분류코드명</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="inquiryCommCd" items="${inquiryCommCdList}">
					            <tr>
					                <td onclick="fn_SearchMarketList('${inquiryCommCd.cfcd}', 0); layer_popup('#cfcdtListPopUp');">${inquiryCommCd.cfcd}</td>
					                <td onclick="fn_SearchDetailInformation('${inquiryCommCd.cfcd}', 0)">${inquiryCommCd.cfcdNm}</td>
					            </tr>
				       		</c:forEach>
						</tbody>
					</table>
					</div>
		    	</div>
		    	<div class="col">
		    		<div class="table-title">
		    			<div class="align-col2-wrapper">
                			<div class="l-area" style="width: 75%">
                    			<h4 class="title">기본코드</h4>
                			</div>
                			<div class="r-area" style="width: 25%">
                    			<button type="button" class="btn black" onclick="layer_popup('#dtcdPopUp'); ">등록</button>
               			 	</div>
            			</div> <!--//align-col2-wrapper-->
		    		</div>
		    		<div style="overflow-x:hidden; overflow-y:auto; height:300px;">
		    		<table class="table center">
						<colgroup>
							<col style="width: 30%">
							<col>
						</colgroup>
						<thead>
							<tr>
								<th>기본코드</th>
								<th>기본코드명</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					</div>
		    	</div>
		    	<div class="col">
		    		<div class="table-title">
		    			<div class="align-col2-wrapper">
                			<div class="l-area" style="width: 75%">
                    			<h4 class="title">상세코드</h4>
                			</div>
                			<div class="r-area" style="width: 25%">
                    			<button type="button" class="btn black" onclick="layer_popup('#subdPopUp');">등록</button>
               			 	</div>
            			</div> <!--//align-col2-wrapper-->
		    		</div>
		    		<div style="overflow-x:hidden; overflow-y:auto; height:300px;">
		    		<table class="table center">
						<colgroup>
							<col style="width: 30%">
							<col>
						</colgroup>
						<thead>
							<tr>
								<th>상세코드</th>
								<th>상세코드명</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					</div>
		    	</div>
		    </div> <!-- //item-col3-wrapper -->
           
           
		
		<h3 class="title2">상세 정보</h3>
		<table class="table">
			<colgroup>
				<col style="width: 10%">
				<col style="width: 23%">
				<col style="width: 10%">
				<col style="width: 23%">
				<col style="width: 10%">
				<col style="width: 23%">
			</colgroup>
			<tbody id = "infCfcd">
				<tr>
					<th>분류코드</th>
					<td><input type="text" class="width100"></td>
					<th>분류코드명</th>
					<td><input type="text" class="width100"></td>
					<th>분류코드 설명</th>
					<td><input type="text" class="width100"></td>
				</tr>
			</tbody>
		</table>	
		</br>
		<table class="table">
			<colgroup>
				<col style="width: 10%">
				<col style="width: 23%">
				<col style="width: 10%">
				<col style="width: 23%">
				<col style="width: 10%">
				<col style="width: 23%">
			</colgroup>
			<tbody>
				<tr>
					<th>기본코드</th>
					<td><input type="text" class="width100"></td>
					<th>기본코드명</th>
					<td><input type="text" class="width100"></td>
					<th>상위분류코드</th>
					<td><input type="text" class="width100"></td>
				</tr>
				<tr>
					<th>적용시작일자</th>
					<td><input type="text" class="width100"></td>
					<th>적용종료일자</th>
					<td><input type="text" class="width100"></td>
					<th>현재사용여부</th>
					<td>
						<select class="width25">
							<option value="">Y</option>
							<option value="">N</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>기본코드 설명</th>
					<td colspan = "3"><input type="text" class="width100"></td>
					<td colspan = "2"></td>
				</tr>
			</tbody>
		</table>
		</br>
		<table class="table">
			<colgroup>
				<col style="width: 10%">
				<col style="width: 23%">
				<col style="width: 10%">
				<col style="width: 23%">
				<col style="width: 10%">
				<col style="width: 23%">
			</colgroup>
			<tbody>
				<tr>
					<th>상세코드</th>
					<td><input type="text" class="width100"></td>
					<th>상세코드명</th>
					<td><input type="text" class="width100"></td>
					<th>상위분류코드</th>
					<td><input type="text" class="width100"></td>
				</tr>
				<tr>
					<th>적용시작일자</th>
					<td><input type="text" class="width100"></td>
					<th>적용종료일자</th>
					<td><input type="text" class="width100"></td>
					<th>현재사용여부</th>
					<td>
						<select class="width25">
							<option value="">Y</option>
							<option value="">N</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>상세코드 설명</th>
					<td colspan = "3"><input type="text" class="width100"></td>
					<td colspan = "2"></td>
				</tr>
			</tbody>
		</table>	

	
	
	 <!-- 분류코드 ajax st -->
	<div id="cfcdtListPopUp" class="layer-popup">
	<div class="content-wrap"> 
			<div class="content">
				<div class="head">
					<div class="align-col2-wrapper">
               			<div class="l-area" style="width: 50%">
                   			<h3 class="title" id="popupTitle"></h3>
               			</div>
               			<div class="r-area" style="width: 50%">
                   			<button type="button" class="close btn">X</button>
              			 	</div>
           			</div> <!--//align-col2-wrapper-->
				</div>
				<div class="main">
					<div style="height:400px">
						<table class="table">
							<colgroup>
								<col style="width: 25%">
							</colgroup>
							<tbody id="popupTableList">
							<!-- 데이터 들어가는 부분 st -->
								
							<!-- 데이터 들어가는 부분 ed -->
							</tbody>
						</table>	
					</div>
				</div>
				<div class="foot">
					<div class="align-col2-wrapper">
               			<div class="l-area" style="width: 50%">
                   			<button type="button" class="btn black" onclick="inquiryCommCd_update(); layer_close('#cfcdtListPopUp'); inquiryCommCd_Select();">수정</button>
               			</div>
               			<div class="r-area" style="width: 50%">
               				<button type="button" class="btn red" onclick="inquiryCommCd_delete(); layer_close('#cfcdtListPopUp'); inquiryCommCd_Select();">삭제</button>
              			</div>
           			</div> <!--//align-col2-wrapper-->
				</div>
			</div>
		</div>	
	</div>
	<!-- 분류코드 ajax ed -->
	
	
	<!-- layer popup -->
	<div id="cfcdPopUp" class="layer-popup">
		<div class="content-wrap"> 
			<div class="content">
				<div class="head">
					<div class="align-col2-wrapper">
               			<div class="l-area" style="width: 50%">
                   			<h3 class="title">분류코드 등록</h3>
               			</div>
               			<div class="r-area" style="width: 50%">
                   			<button type="button" class="close btn">X</button>
              			 	</div>
           			</div> <!--//align-col2-wrapper-->
				</div>
				<div class="main">
					<div style="height:400px">
						<table class="table">
							<colgroup>
								<col style="width: 25%">
							</colgroup>
							<tbody>
								<tr>
									<th>분류코드</th>
									<td><input type="text" class="width100" id="popInputCfcd" name="popInputCfcd"></td>
								</tr>
								<tr>
									<th>분류코드명</th>
									<td><input type="text" class="width100" id="popInputCfcdNm" name="popInputCfcdNm"></td>
								</tr>
								<tr>
									<th>분류코드 설명</th>
									<td><input type="text" class="width100" id="popInputCfcdDesc" name="popInputCfcdDesc"></td>
								</tr>
							</tbody>
						</table>	
					</div>
				</div>
				<div class="foot">
					<div class="align-col2-wrapper">
               			<div class="l-area" style="width: 50%">
                   			<button type="button" class="btn black" onclick="inquiryCommCd_insert(); layer_close('#cfcdPopUp'); inquiryCommCd_Select();">저장</button>
               			</div>
               			<div class="r-area" style="width: 50%">
              			</div>
           			</div> <!--//align-col2-wrapper-->
				</div>
			</div>
		</div>
	</div> 
	<div id="dtcdPopUp" class="layer-popup">
		<div class="content-wrap"> 
			<div class="content">
				<div class="head">
					<div class="align-col2-wrapper">
               			<div class="l-area" style="width: 50%">
                   			<h3 class="title">기본코드 등록</h3>
               			</div>
               			<div class="r-area" style="width: 50%">
                   			<button type="button" class="close btn">X</button>
              			 </div>
           			</div> <!--//align-col2-wrapper-->
				</div>
				<div class="main">
					<div style="height:400px">
						<table class="table">
							<colgroup>
								<col style="width: 25%">
							</colgroup>
							<tbody>
								<tr>
									<th>기본코드</th>
									<td><input type="text" class="width100"></td>
								</tr>
								<tr>
									<th>기본코드명</th>
									<td><input type="text" class="width100"></td>
								</tr>
								<tr>
									<th>기본코드 설명</th>
									<td><input type="text" class="width100"></td>
								</tr>
								<tr>
									<th>적용일자</th>
									<td>
										<input class="datepicker" type="date" id = "datePick1" name = "datePick1">
										<span>&nbsp;~&nbsp;</span>
										<input class="datepicker" type="date">
									</td>
								</tr>
								<tr>
									<th>사용여부</th>
									<td>
										<select class="width25">
											<option value="">Y</option>
											<option value="">N</option>
										</select>
									</td>
								</tr>
							</tbody>
						</table>	
					</div>
				</div>
				<div class="foot">
					<div class="align-col2-wrapper">
               			<div class="l-area" style="width: 50%">
                   			<button type="button" class="btn black" onclick="test();layer_close('#dtcdPopUp');">저장</button>
               			</div>
               			<div class="r-area" style="width: 50%">
              			</div>
           			</div> <!--//align-col2-wrapper-->
				</div>
			</div>
		</div>
	</div> 
	<div id="subdPopUp" class="layer-popup">
		<div class="content-wrap"> 
			<div class="content">
				<div class="head">
					
					<div class="align-col2-wrapper">
               			<div class="l-area" style="width: 50%">
                   			<h3 class="title">상세코드 등록</h3>
               			</div>
               			<div class="r-area" style="width: 50%">
                   			<button type="button" class="close btn">X</button>
              			 	</div>
           			</div> <!--//align-col2-wrapper-->
				</div>
				<div class="main">
					<div style="height:400px">
						<table class="table">
							<colgroup>
								<col style="width: 25%">
							</colgroup>
							<tbody>
								<tr>
									<th>상세코드</th>
									<td><input type="text" class="width100"></td>
								</tr>
								<tr>
									<th>상세코드명</th>
									<td><input type="text" class="width100"></td>
								</tr>
								<tr>
									<th>상세코드 설명</th>
									<td><input type="text" class="width100"></td>
								</tr>
								<tr>
									<th>적용일자</th>
									<td>
										<input class="datepicker" type="date">
										<span>&nbsp;~&nbsp;</span>
										<input class="datepicker" type="date">
									</td>
								</tr>
								<tr>
									<th>사용여부</th>
									<td>
										<select class="width25">
											<option value="">Y</option>
											<option value="">N</option>
										</select>
									</td>
								</tr>
							</tbody>
						</table>	
					</div>
				</div>
				<div class="foot">
					<div class="align-col2-wrapper">
               			<div class="l-area" style="width: 50%">
                   			<button type="button" class="btn black" onclick="layer_close('#subdPopUp');">저장</button>
               			</div>
               			<div class="r-area" style="width: 50%">
              			</div>
           			</div> <!--//align-col2-wrapper-->
				</div>
			</div>
		</div>
	</div> 
	<!-- layer popup -->
	
	</section>


	
</div> <!--//wrap-->
</form>
</body>
</html>