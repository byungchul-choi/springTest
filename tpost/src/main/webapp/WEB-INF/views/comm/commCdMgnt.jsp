
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>  <!-- 전자정부태그라이브러리 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  <!-- jstl펑션 -->


<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="format-detection" content="telephone=no">
	
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script src="/resource/js/comm.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>

    <title>코드관리</title>
	
	<!-- css -->
	
	<link rel="stylesheet" href="/resource/css/admin/common.css">
	
	<!-- script -->
<!--	<script src="../../js/comm/jquery-3.4.1.js"></script>   -->
<!--	<script src="../../js/comm/jquery-ui-1.12.1.js"></script> -->
<!--	<script src="../../js/admin/html_comm.js"></script>  -->
<!--	<script src="../../js/admin/html_style.js"></script> -->
	
	<script>
	  
	  /*코드조회*/
	  function commCdMgnt_sel(){
		  /*
		  var commCdMgntForm = document.getElementById("commCdMgnt");
		  commCdMgntForm.action = "commCdMgntSel";
		  commCdMgntForm.method = "post";
		  commCdMgntForm.submit();
		  */
		  $('#pageIndex').val(1); /*첫조회는 페이지가 1로 조회*/
		  var commCdMgntForm = document.getElementById("commCdMgnt");
		  commCdMgntForm.action = "commCdMgnt/commCdMgnt-paging";
		  commCdMgntForm.method = "post";
		  commCdMgntForm.submit();
		  
	  }
	  /*팝업호출*/
	  function commCdMgnt_pop_up(){
		  var commCdMgntForm = document.getElementById("commCdMgnt");
		  commCdMgntForm.action = "commCdMgnt/cfcdPopu";
		  commCdMgntForm.method = "post";
		  commCdMgntForm.submit();
		  
	  }
	  
	  /*팝업호출 한글테스트 */
	  function commCdMgnt_pop_up(){
		  alert("한글 테스트입니다.");
		  $('#pageIndex').val(1);
		  var commCdMgntForm = document.getElementById("commCdMgnt");
		  commCdMgntForm.action = "commCdMgnt_kr";
		  commCdMgntForm.method = "post";
		  commCdMgntForm.submit();
		  
	  }
	  
	  /*다음페이지 */
	  function linkPage(pageNo){
	  
		  if(pageIndex == ""){
			  pageIndex = 1;
		  }
		  
			$('#pageIndex').val(pageNo);
  		

   	     var commCdMgntForm = document.getElementById("commCdMgnt");
			  commCdMgntForm.action = "commCdMgnt-paging";
			  commCdMgntForm.method = "post";
			  commCdMgntForm.submit();
			
		}	
	</script>
						 

</head>
<body>
<form id="commCdMgnt">

<!-- 히든으로 페이지 인덱스 가지고 잇기 -->
<input type="hidden" id="pageIndex" name="pageIndex" value="${searchVO.pageIndex}"/>  

<input type="text" id="cfcd"   name="cfcd" value=""/>  
<input type="text" id="cfcdNm" name="cfcdNm" value=""/>  
<input type="text" id="cfcdDesc" name="cfcdDesc" value=""/>  

<div id="wrap">
	

	<main id="main">
		<section class="contents-wrap">
            <h2 class="title1">공통코드조회</h2>
			
            <div class="table-wrapper">
		        <table class="table">
		            <tbody>
		                <tr>
		                    <th class="text-left" > 분류코드 </th>
		                    <td></td>
		                    <td class="text-left" > 분류코드명 </td>
		                    <td></td>
		                    <td class="text-left" > 세부코드 </td>
		                    <td></td>
		                    <td class="text-left" > 세부코드명 </td>
		                    <td></td>
		                    <td><button type="button" class="btn black" onclick="commCdMgnt_sel();" >조회</button></td>
		                </tr>
		            </tbody>
		        </table>
		    </div> <!--//table-wrapper-->
		    
		     <div class="table-wrapper">
		        <table class="table">
		            <tbody>
		                <tr>
		                    <th class="text-left" > 분류코드 </th>
		                    <td></td>
		                     <td><button type="button" class="btn black" onclick="commCdMgnt_pop_up();" >등록</button></td>
		                    <td class="text-left" > 세부코드 </td>
		                    <td></td>
		                     <td><button type="button" class="btn black" onclick="" >등록</button></td>
		                    <td class="text-left" > 상세코드 </td>
		                    <td></td>
		                     <td><button type="button" class="btn black" onclick="" >등록</button></td>
		                </tr>
		            </tbody>
		        </table>
		    </div> <!--//table-wrapper-->
		    
		    <div class="item-col3-wrapper">
		    	<div class="col">
		    		<div>
		    		<table class="table center">
						<colgroup>
							<col style="width: 50%">
							<col>
						</colgroup>
						<thead>
							<tr>
								<th>분류코드</th>
								<th>분류코드명</th>
							</tr>
						</thead>
						<tbody>
						
							<c:forEach var="resultList" items="${resultList}">
					            <tr>
					                <td>${resultList.cfcd}</td>
					                <td>${resultList.cfcdNm}</td>
					                
					            </tr>
				        	</c:forEach>
				        </tbody>
				    </table>
				    </div>
				   		 <div >
							<tbody>
								<div class="paging-wrap">
									<c:if test="${fn:length(resultList) ne 0 }">  
										<ul>
											<ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="linkPage" />
										</ul>
									</c:if>
								</div>
							</tbody>
							</div>
				      
				    </div>  	
				    	
		    	
		    	<div class="col">
		    		
		    		<table class="table center">
						<colgroup>
							<col style="width: 50%">
							<col>
						</colgroup>
						<thead>
							<tr>
								<th>세부코드</th>
								<th>세부코드명</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td></td>
								<td></td>
							</tr>
						</tbody>
					</table>
		    	</div>
		    	<div class="col">
		    		<table class="table center">
						<colgroup>
							<col style="width: 50%">
							<col>
						</colgroup>
						<thead>
							<tr>
								<th>상세코드</th>
								<th>상세코드명</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td></td>
							</tr>
						</tbody>
					</table>
		    	</div>
		    </div> <!-- //item-col3-wrapper -->
		   
            <!--//change contents (ââì½íì¸  ë³ê²½ââ)-->
		</section> <!--//contents-wrap-->
	</main>

	
</div> <!--//wrap-->

</form>
</body>
</html>