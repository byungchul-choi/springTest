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
<!-- ---------- -->

<script type="text/javascript">
function addRow(){	
	var rowLength = $('#rcveList tr').length;
	
	var rcveListTr = '';
	rcveListTr += '<tr>';
	rcveListTr += '<th><input type="checkbox"></th>';
	rcveListTr += '<td><input type="text" class="width100" name="inputCiNum" id="inputCiNum"></td>';
	rcveListTr += '<td><input type="text" class="width100" name="inputNm" id="inputNm"></td>';
	rcveListTr += '<td><input type="text" class="width100" name="inputTel" id="inputTel"></td>';
	rcveListTr += '<td></td>';
	rcveListTr += '<td><input type="text" class="width100" name="inputValue1" id="inputValue1"></td>';
	rcveListTr += '<td><input type="text" class="width100" name="inputValue2" id="inputValue2"></td>';
	rcveListTr += '<td><input type="text" class="width100" name="inputValue3" id="inputValue3"></td>';
	rcveListTr += '<td><input type="text" class="width100" name="inputValue4" id="inputValue4"></td>';
	rcveListTr += '</tr>';
	
	$('#rcveList').append(rcveListTr);
}

function crowdInsert(){
	alert("crowdInsert");
	var commCdMgntForm = document.getElementById("inquiryCommCd");
	commCdMgntForm.action = "crowdInsert";
	commCdMgntForm.method = "post";
	commCdMgntForm.submit();
}
</script>
</head>
<body>
<form id="inquiryCommCd">											
 <div id="wrap">
	<section class="contents-wrap">
           <h2 class="title1">메시지 발송</h2>
           
           <div class="table-wrapper">
               <table class="table th-left">
                   <colgroup>
                       <col style="width: 14%">
                       <col>
                   </colgroup>
                   <tbody>
                       <tr>
                           <th>발송 기관*</th>
                           <td>
                             <select class="width25">
								<option value="">테스트 발송기관 1</option>
								<option value="">테스트 발송기관 2</option>
								<option value="">테스트 발송기관 3</option>
								<option value="">테스트 발송기관 4</option>
								<option value="">테스트 발송기관 5</option>
								<option value="">테스트 발송기관 6</option>
							 </select>
                           </td>
                       </tr>
                       <tr>
                           <th>발송 유형*</th>
                           <td>
                             <select class="width25">
								<option value="">알림톡</option>
								<option value="">SMS</option>
								<option value="">LMS</option>
							 </select>
                           </td>
                       </tr>
                       <tr>
                           <th>템플릿 유형*</th>
                           <td>
                             <select class="width50">
								<option value="">테스트 템플릿 유형 1</option>
								<option value="">테스트 템플릿 유형 2</option>
								<option value="">테스트 템플릿 유형 3</option>
								<option value="">테스트 템플릿 유형 4</option>
								<option value="">테스트 템플릿 유형 5</option>
								<option value="">테스트 템플릿 유형 6</option>
							 </select>
                           </td>
                       </tr>
                       <tr>
                           <th>발송 제목*</th>
                           <td>
                           	<input type="text" class="width98" placeholder="제목을 입력해주세요.">
                           </td>
                       </tr>
                       <tr>
                           <th>발송 방법*</th>
                           <td>
								<label><input name="ex2" type="radio"><span>즉시발송</span></label>
								<label><input name="ex2" type="radio"><span>예약발송</span></label>
								<input class="datepicker" type="text" placeholder="2020-02-02">  
								<label><input type="text" class="width5" placeholder="00"><span class="width3">시</span></label>
								<label><input type="text" class="width5" placeholder="00"><span class="width3">분</span></label>
                           </td>
                       </tr>
                       <tr>
                           <th>발신 번호*</th>
                           <td>
                           	<input type="text" class="width25" placeholder="테스트 (010-1234-5678)">
                           </td>
                       </tr>
                       <tr>
                           <th>수신자 수*</th>
                           <td>1명</td>
                       </tr>
                   </tbody>
               </table>
           </div> <!--//table-wrapper-->
           
           
		
		<h3 class="title2">수신 목록</h3>
		
			<div class="align-col2-wrapper">
                <div class="l-area" style="width: 75%">
                    <span class ="title3">&nbsp;&nbsp;CI 번호 : </span>
                    <input style="width: 20%" type="text">
                    <span class ="title3">&nbsp;&nbsp;이름 : </span>
                    <input style="width: 20%" type="text">
                    <span class ="title3">&nbsp;&nbsp;전화번호 : </span>
                    <input style="width: 20%" type="text">
                </div>
                <div class="r-area" style="width: 25%">
                    <button type="button" class="btn black">조회</button>
                    <button type="button" class="btn black" onclick="addRow()">추가</button>
                    <button type="button" class="btn green">EXCEL</button>
                </div>
            </div> <!--//align-col2-wrapper-->

		<table class="table center">
          <colgroup>
              <col style="width: 5%">
          </colgroup>
          <thead>
              <tr>
                  <th><input type="checkbox"></th>
                  <th>CI번호</th>
                  <th>이름</th>
                  <th>전화번호</th>
                  <th>성공여부</th>
                  <th>값1</th>
                  <th>값2</th>
                  <th>값3</th>
                  <th>값4</th>
              </tr>
          </thead>
          <tbody id="rcveList">
			<tr>
				<th><input type="checkbox"></th>
				<td><input type="text" class="width100" name="inputCiNum" id="inputCiNum"></td>
				<td><input type="text" class="width100" name="inputNm" id="inputNm"></td>
				<td><input type="text" class="width100" name="inputTel" id="inputTel"></td>
				<td></td>
				<td><input type="text" class="width100" name="inputValue1" id="inputValue1"></td>
				<td><input type="text" class="width100" name="inputValue2" id="inputValue2"></td>
				<td><input type="text" class="width100" name="inputValue3" id="inputValue3"></td>
				<td><input type="text" class="width100" name="inputValue4" id="inputValue4"></td>
			</tr>
         </tbody>
     	 </table>
      
      	    <div class="align-col2-wrapper">
                <div class="l-area">
                   <button type="button" class="btn black">선택항목 삭제</button>
                </div>
                <div class="r-area">
                    <button type="button" class="btn black" onclick="crowdInsert()">보내기</button>
                </div>
            </div> <!--//align-col2-wrapper-->			

	</section>


	<footer id="footer">
		
	</footer>
</div> <!--//wrap-->
</form>
</body>
</html>