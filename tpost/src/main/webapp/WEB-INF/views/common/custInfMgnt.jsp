<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>  <!-- 전자정부태그라이브러리 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  <!-- jstl펑션 -->


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- CSS  STYLE -->
<link rel="stylesheet" href="/tpost/resource/css/common.css">

<!--     JS     -->
<script src="/tpost/resource/js/jquery.min.js"></script>
<script src="/tpost/resource/js/jquery-migrate-1.2.1.min.js"></script>
<script src="/tpost/resource/js/comm.js"></script>
<!-- ---------- -->

<script type="text/javascript">

/*초기 콤보박스 셋팅*/
function js_init()
{
	commCd_bas("oganCdSel", "00001");  /*공단종류*/
	
	$('#insert').prop('disabled', true);  /*등록버튼 활성화*/
	$('#update').prop('disabled', true);   /*등록버튼 수정버튼*/
	
}

function callBack(sel_id, codeNum)
{	
	if(sel_id == "#oganCdSel" ){
		$("#oganCdSel").prepend("<option value=''>전체</option>");
		commCd_bas("oganCd", "00001");  /*공단종류*/
	}

	
	$("#oganCdSel").val($("#oganCdSel1").val() ).prop("selected", true);
	
	
	if(sel_id == "#oganCd" ){
		commCd_bas("athLev", "00012");  /*권한레벨 셋팅*/
	}
	
}


/*테이블 row 클릭시 이벤트 발생 */
$( document ).ready(function() {

	/*그리드 클릭시 이벤트 전달하는것*/
	$("#resultListTable tr").click(function(event) { // 실행하고자 하는 jQuery 코드
			// 현재 클릭된 Row(<tr>)
			var tr = $(this);
			resultList(tr);		
	});
});

/*row 이벤트 처리 */
function resultList(tr){
		var str = ""
		var tdArr = new Array();	// 배열 선언
		var td = tr.children();
		
		$('#insert').prop('disabled', true);  /*등록버튼 비활성화*/
		$('#update').prop('disabled', false);  /*수정버튼 활성화*/
		
		$('#oganCd').prop('disabled', true);  /*등록버튼 비활성화*/
		
		// tr.text()는 클릭된 Row 즉 tr에 있는 모든 값을 가져온다.
		console.log("클릭한 Row의 모든 데이터 : "+tr.text());
		
		// 반복문을 이용해서 배열에 값을 담아 사용할 수 도 있다.
		td.each(function(i){
			tdArr.push(td.eq(i).text());
		});
		
		console.log("배열에 담긴 값 : "+tdArr);
	
		// td.eq(index)를 통해 값을 가져올 수도 있다.
		var custNum     = td.eq(1).text();
		var oganCd     = td.eq(9).text();
		var custNm     = td.eq(3).text();
		var deptNm     = td.eq(4).text();
		var useYn      = td.eq(5).text();
		var telNum     = td.eq(6).text();
		var celpNum    = td.eq(7).text();
		var emaiId     = td.eq(8).text();
		var athLev     = td.eq(10).text();
 
		$('#oganCd').val(oganCd); 
//		$('#custNum').val(custNum); 
		$('#custNm').val(custNm); 
		$('#deptNm').val(deptNm); 
		$('#useYn').val(useYn); 
		$('#telNum').val(telNum); 
		$('#celpNum').val(celpNum); 
		$('#emaiId').val(emaiId); 
		$('#athLev').val(athLev); 
		
		if(useYn == 'Y'){
			jQuery("#radio_yes").attr('checked', true);
		}else{
			jQuery("#radio_no").attr('checked', true);
		}
		
		$("#oganCd").val(oganCd ).prop("selected", true);
		$('#custInfMgnt_input #custNum').val(custNum);
}

function custInfMgnt_init(){

	$('#oganCd').val(""); 
	$('#custNum').val(""); 
	$('#custNm').val(""); 
	$('#deptNm').val(""); 
	$('#useYn').val(""); 
	$('#telNum').val(""); 
	$('#celpNum').val(""); 
	$('#emaiId').val(""); 
	$('#athLev').val(""); 
	$("#radio_yes").attr('checked', true);
	$('#custInfMgnt_input #custNum').val("");
	
	$('#insert').prop('disabled', false);  /*등록버튼 비활성화*/
	$('#update').prop('disabled', true);  /*수정버튼 활성화*/
	$('#oganCd').prop('disabled', false);  /*등록버튼 비활성화*/
}


/****************************************************************************************************/
/* 메뉴조회 */
function custInf_sel(){
	  
	  $('#pageIndex').val(1); /*첫조회는 페이지가 1로 조회*/
	  var custInf_sel = document.getElementById("custInfMgnt_sel");
	  custInf_sel.action = "custInfMgntSel";
	  
	  custInf_sel.method = "post";
	  custInf_sel.submit();
	  
}


/*엔터키시 조회*/
function enterSerch(){
	if(window.event.keyCode == 13){
		custInf_sel();
	}
}



/*insert */
function custInf_insert(){
	
	/*필수값 체크가 false면 return;*/  
	if(!fn_input_chk()){ 
		return ;
	}
	
	$('#oganCd').prop('disabled', false);  /*등록버튼 비활성화*/
	
	  $('#pageIndex').val(1); /*첫조회는 페이지가 1로 조회*/
	  var parameters = $("#custInfMgnt_input").serialize();
		$.ajax({
			type:"post", 
			dataType:"json",
			async:false,
			url:'custInfMgnt-insert.ajax',
			data:parameters ,
			success:function(data){
				alert(data.errmsg);
				custInf_sel();
			},
			error: function(request, status, error){  
			} 
		});
}

/* 인풋 박스 체크 */
function fn_input_chk(){
	
	/*공백체크*/
// 	if( reqValCheck( "deptNm" , "부서이름") ){return false;}
// 	if( reqValCheck( "emaiId" , "이메일") ){return false;}
	
	if( reqValCheck( "celpNum" , "휴대전화번호") ){return false;}
	if( reqValCheck( "custNm" , "고객명") ){return false;}
	
	return true;
	
}

/*update */
function custInf_update(){
	
	  $('#pageIndex').val(1); /*첫조회는 페이지가 1로 조회*/
// 	  var parameters = $("#custInfMgnt_sel").serialize();
	  $('#oganCd').prop('disabled', false);  /*등록버튼 비활성화*/
	  var parameters = $("#custInfMgnt_input").serialize();
	  
		$.ajax({
			type:"post", 
			dataType:"json",
			async:false,
			url:'custInfMgnt-update.ajax',
			data:parameters ,
			success:function(data){
				alert(data.errmsg);
				custInf_sel();
				$('#oganCd').prop('disabled', true);  /*등록버튼 비활성화*/
			},
			error: function(request, status, error){  
			} 
		});
	  
}

/*update */
function custInf_pw_init(){
	
	alert($('#custNm').val() + "의 비밀번호를 초기화 하겠습니까?"  );
	  $('#pageIndex').val(1); /*첫조회는 페이지가 1로 조회*/
// 	  var parameters = $("#custInfMgnt_sel").serialize();
	  $('#oganCd').prop('disabled', false);  /*등록버튼 비활성화*/
	  var parameters = $("#custInfMgnt_input").serialize();
	  
		$.ajax({
			type:"post", 
			dataType:"json",
			async:false,
			url:'custInfMgnt-pwInit.ajax',
			data:parameters ,
			success:function(data){
				alert(data.errmsg);
				custInf_sel();
				$('#oganCd').prop('disabled', true);  /*등록버튼 비활성화*/
			},
			error: function(request, status, error){  
			} 
		});
	  
}

/*delete */
function custInf_delete(){
	
	/*삭제건 체크여부*/
	/*체크박스 선택여부*/
	var cnt = 0 ;
    var checkbox = $("input[name=chk_info]");
    for(var i=0;i<checkbox.length;i++){
        if(checkbox[i].checked == true) {
        	cnt= cnt +1;
        }
    }
    if(cnt == 0 ){
    	alert("등록할 건을 체크하여 주십시오");
    	return;
    }
    
	
	 var inputChk1 = document.getElementsByName("chk_info");
	    for(var i=0;i<inputChk1.length;i++){
	        if(inputChk1[i].checked == true) {
	        	document.getElementsByName("inputChk")[i].value=true;
	        }else{
	          	document.getElementsByName("inputChk")[i].value=false;
	        }
	    }
	    
	  $('#pageIndex').val(1); /*첫조회는 페이지가 1로 조회*/
	  var parameters = $("#custInfMgnt_sel").serialize();
	  var queryString = $("form[name=custInfMgnt_sel]").serialize() ;
	  
	
		$.ajax({
			type:"post", 
			dataType:"json",
			async:false,
			url:'custInfMgnt-delete.ajax',
			data:parameters ,
//			data:queryString ,
			success:function(data){
				alert(data.errmsg);
				custInf_sel();
			}
		});
	
	  
}

/*다음페이지*/
 function linkPage(pageNo){
	  
	  if(pageIndex == ""){
		  pageIndex = 1;
	  }
	  
	$('#pageIndex').val(pageNo);

	var custInf_selForm = document.getElementById("custInfMgnt_sel");
	custInf_selForm.action = "custInfMgntSel";
	custInf_selForm.method = "post";
	custInf_selForm.submit();
		
}	

/*라디오 체크 박스 관리*/
function radioChk(val){
	
	$('#useYn').val(val);
}


</script>
</head>

<body onload= "js_init();">
                                                                                           
    <!-- wrap -->
    <div id="wrap" >
        <!-- contents -->
        <div id="contents">
            <!-- section -->
            <div class="section" >
                <!-- caption-pnl -->
                <div class="caption-pnl">
                    <h2>고객정보등록</h2>
                    <div class="buttonset">
                        <button type="button" id="insert"  class="state-highlight" onclick="custInf_insert();" >등록</button>
                        <button type="button" id="update" onclick="custInf_update();" >수정</button>
                        <button type="button" id="delete" onclick="custInf_delete();" >삭제</button>
                        <button type="button" id="delete" onclick="custInf_pw_init();" >비밀번호초기화</button>
                    </div>
                </div>
                <!-- //caption-pnl -->
                <!-- search-pnl -->
                <form id="custInfMgnt_sel">	  
                <div class="search-pnl" >
                    <table>
                        <colgroup>
                            <col width="130px">
                            <col width="200px">
                            <col width="100px">
                            <col width="200px">
                            <col width="*">
                            <col width="200px">
                        </colgroup>
                        <tr>
                            <th>기관(회사)</th>
                            <td><select id="oganCdSel" name="oganCdSel"  style="width:100%"></select>
                                <input type="hidden" id="oganCdSel1" name="oganCdSel1" value="${oganCdSel}"  onKeyDown="enterSerch();" >
                            </td>    
                            <th>고객명</th>
                            <td><input type="text" size="8" id="custNmSel" name="custNmSel"  value="${custNmSel}" onKeyDown="enterSerch();" ></td>
                            <td></td>
                            <td class="buttonset"><button type="button" onclick="custInf_sel();" >조회</button></td>
                        </tr>
                    </table>
                </div>
                <!-- //search-pnl -->
                <!-- grid-pnl -->
                <div class="grid-pnl" style="overflow-x:hidden; overflow-y:auto; height:300px;">
                    <!-- table -->
                    <table class="align-center">
                        <colgroup>
                          	  <col style="width: 3%">                                                                                   
                          	  <col style="width: 9%">                                                                                   
				              <col style="width: 9%">                                                                                   
				              <col style="width: 9%">                                                                                   
				              <col style="width: 9%">                                                                                   
				              <col style="width: 9%">                                                                                   
				              <col style="width: 9%">                                                                                   
				              <col style="width: 9%">                                                                                   
				              <col style="width: 9%">             
				              <col style="display: none">             
				              <col style="display: none">             
				              <col style="width: 9%">             
                        </colgroup>
                        <thead>
                            <tr>
                                <th scope="col"><input type="checkbox" id="designcheck00" name="chk_info" onclick="chkBoxMgnt('resultListTable', $(this))" ><label for="designcheck00"></label><input type = "hidden" id="custNum" name="custNum" ><input type="hidden" id="inputChk" name="inputChk"></th>                                                                                        
                                <th scope="col">고객번호</th>                                                                                        
								<th scope="col">기관(회사)명</th>                                                                                  
				                <th scope="col">고객명</th>                                                                                        
				                <th scope="col">부서명</th>                                                                                        
				                <th scope="col">사용여부</th>                                                                                      
				                <th scope="col">전화번호</th>                                                                                      
				                <th scope="col">휴대전화</th>                                                                                      
				                <th scope="col">E-mail</th>     
				                <th scope="col" style="display: none" >기관코드</th>     
				                <th scope="col" style="display: none" >권한등급</th>     
				                <th scope="col" >권한등급</th>     
                            </tr>
                        </thead>
                        <tbody id="resultListTable" name="resultListTable">
                         <c:forEach var="resultList" items="${resultList}" varStatus="status" >                                                             
							<tr> 
							    <td scope="row"><input type="checkbox" id="designcheck01_${status.count}" name="chk_info"><label for="designcheck01_${status.count}"></label><input type = "hidden" id="custNum" name="custNum" value="${resultList.custNum}"> <input type="hidden" id="inputChk" name="inputChk"></td>                                                                                                              
								<td>${resultList.custNum}</td>                                                                                    
								<td>${resultList.oganNm}</td>                                                                                    
								<td>${resultList.custNm}</td>                                                                                    
								<td>${resultList.deptNm}</td>                                                                                    
								<td>${resultList.useYn}</td>                                                                                     
								<td>${resultList.telNum}</td>                                                                                    
								<td>${resultList.celpNum}</td>                                                                                   
								<td>${resultList.emaiId}</td>
								<td style="display: none" >${resultList.oganCd}</td>                                                                                     
								<td style="display: none" >${resultList.athLev}</td>                                                                                     
								<td>${resultList.athLevNm}</td>                                                                                     
							</tr>                         
							</c:forEach>
                        </tbody>
                    </table>
                    <!-- //table -->
                </div>
                </form>
                <!-- //grid-pnl -->

                <!-- caption-pnl -->
                <form id="custInfMgnt_input">	
            	<div class="caption-pnl">
                	<h3>고객정보상세</h3>
					<div class="buttonset">
						<button type="button" onclick="custInfMgnt_init()" >초기화</button>
					</div>
                </div>
                <!-- //caption-pnl -->
                <div class="colgroup-wrap">
                    <div class="colgroup-1-2">
                        <!-- table-pnl -->
                        <div class="table-pnl">
                            <!-- table -->
                            <table class="vtable">
                                <colgroup><col width="120px"><col width="*"></colgroup>
                                <tbody>
                                <tr>
                                    <th>기관(회사)</th>
                                    <td>
                                        <select id="oganCd" name="oganCd" ></select> </td>
                                </tr>
                                <tr>
                                    <th>부서명</th>
                                    <td><input type="text"  name="deptNm" id="deptNm" maxlength="20" oninput="maxLengthCheck(this)" ></td>
                                </tr>
                                <tr>
                                    <th>사용여부</th>
                                    <td><input type="hidden"  id="useYn" name="useYn"  value="Y" >                                   
	                                    <div class="select_group">
	                                        <span><input type="radio" name="useYnRadio" onclick="radioChk('Y');" id="radio_yes" checked="checked"><label for="radio_yes"></label><label for="radio_yes">예</label></span>
	                                        <span><input type="radio" name="useYnRadio" onclick="radioChk('N');" id="radio_no"><label for="radio_no"></label><label for="radio_no">아니오</label></span>
	                                    </div>
                                    </td>
                                </tr>
                                <tr>
                                    <th>E-Mail</th>
                                    <td><input type="text"  id="emaiId" name="emaiId" maxlength="50" oninput="maxLengthCheck(this)" ></td>
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
                                <colgroup><col width="130px"><col width="*"></colgroup>
                                <tbody>
                                <tr>
                                    <th>고객명<span style="color: #fea338;">*</span><input type="hidden" name="custNum" id="custNum"></th>
                                    <td><input type="text" name="custNm" id="custNm" maxlength="20" oninput="maxLengthCheck(this)" ></td>
                                </tr>
                                <tr>
                                    <th>전화번호</th>
                                    <td><input type="text" name="telNum" id="telNum" maxlength="12" onkeypress="onlyNumber();  _maxLengthCheck(this, 11);" onkeyup="this.value=this.value.replace(/[^0-9]/g,'');"   ></td>
                                </tr>
                                <tr>
                                    <th>휴대전화번호<span style="color: #fea338;">*</span></th>
                                    <td><input type="text" name="celpNum" id="celpNum" maxlength="11" onkeypress="onlyNumber(); _maxLengthCheck(this, 10);"  
                                    onkeyup="this.value=this.value.replace(/[^0-9]/g,'');"  
                                    ></td>
                                </tr>
                                <tr>
                                    <th>권한등급</th>
                                    <td><select id="athLev" name="athLev" ></select> </td>
                                </tr>
                                </tbody>
                            </table>
                            <!-- //table -->
                        </div>
                        <!-- //table-pnl -->
                    </div>
                </div>
                </form>
            </div>
            <!--// section -->
        </div>
        <!-- //contents -->

    </div>
    <!-- //wrap -->
    
</body>
</html>