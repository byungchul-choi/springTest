<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script src="/tpost/resource/js/comm.js"></script>
<!-- ---------- -->

<script type="text/javascript">

/*테이블 row 클릭시 이벤트 발생 */
$( document ).ready(function() {

	/*그리드 클릭시 이벤트 전달하는것*/
	$("#resultListTable tr").click(function(event) { // 실행하고자 하는 jQuery 코드
			// 현재 클릭된 Row(<tr>)
			var tr = $(this);
			resultList(tr);		
	});
	
	$('#insert').prop('disabled', true);  /*등록버튼 비활성화*/
	$('#update').prop('disabled', true);  /*수정버튼 활성화*/
});

/*row 이벤트 처리 */
function resultList(tr){
		var str = ""
		var tdArr = new Array();	// 배열 선언
		var td = tr.children();
		
		// tr.text()는 클릭된 Row 즉 tr에 있는 모든 값을 가져온다.
		console.log("클릭한 Row의 모든 데이터 : "+tr.text());
		
		// 반복문을 이용해서 배열에 값을 담아 사용할 수 도 있다.
		td.each(function(i){
			tdArr.push(td.eq(i).text());
		});
		
		console.log("배열에 담긴 값 : "+tdArr);
	
		
		// td.eq(index)를 통해 값을 가져올 수도 있다.
		var aoId     = td.eq(1).text();
		var aoNm     = td.eq(2).text();
		var pathNm   = td.eq(3).text();
		var aoParam   = td.eq(4).text();
		var aoDesc       = td.eq(5).text();
		var useYn       = td.eq(6).text();
		

		$('#aoId').val(aoId); 
		$('#aoNm').val(aoNm); 
		
		$('#pathNm').val(pathNm); 
		$('#aoParam').val(aoParam); 
		$('#aoDesc').val(aoDesc); 
		$('#useYn').val(useYn); 
		
		$('#aoId').prop('readonly', true);
		if(useYn == 'Y'){
			jQuery("#radio_yes").attr('checked', true);
		}else{
			jQuery("#radio_no").attr('checked', true);
		}
				 
		$('#insert').prop('disabled', true);  /*등록버튼 비활성화*/
		$('#update').prop('disabled', false);  /*수정버튼 활성화*/
}

/****************************************************************************************************/
/* 메뉴조회 */
function ao_sel(){
	  
	  $('#pageIndex').val(1); /*첫조회는 페이지가 1로 조회*/
	  var ao_sel = document.getElementById("ao_sel");
	  ao_sel.action = "aoMgntSel";
	  
	  ao_sel.method = "post";
	  ao_sel.submit();
	  
}


/*insert */
function ao_insert(){
	/*필수값 체크가 false면 return;*/  
	if(!fn_input_chk()){ 
		return ;
	}
	
	
	$('#pageIndex').val(1); /*첫조회는 페이지가 1로 조회*/
	  var parameters = $("#ao_input").serialize();
		$.ajax({
			type:"post", 
			dataType:"json",
			async:false,
			url:'aoMgnt-insert.ajax',
			data:parameters ,
			success:function(data){
				if(data.result == 'Y'){
					alert(data.errmsg);
					ao_sel();
				}else{
					alert(data.errmsg);
				}
			}
		});
	  
}


function fn_input_chk(){
	
	/*공백체크*/
	if( reqValCheck( "aoId" , "AOID") ){return false;}
	
	return true;
	
}

/*update */
function ao_update(){
	$('#pageIndex').val(1); /*첫조회는 페이지가 1로 조회*/
	  var parameters = $("#ao_input").serialize();
		$.ajax({
			type:"post", 
			dataType:"json",
			async:false,
			url:'aoMgnt-update.ajax',
			data:parameters ,
			success:function(data){
				alert(data.errmsg);
				ao_sel();
			},
			error: function(request, status, error){  
			} 
		});
	  
	  
}

/*delete */
function ao_delete(){
	
	
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
    
    
	  
	  $('#pageIndex').val(1); /*첫조회는 페이지가 1로 조회*/

	  var checkbox = $("input[name=chk_info]");
	    
	   for(var i=0;i<checkbox.length;i++){
	       if(checkbox[i].checked == true) {
	       	document.getElementsByName("inputChk")[i].value=true;
	       }else{
	       	document.getElementsByName("inputChk")[i].value=false;
	       }
	   }
	    
	  
	  var parameters = $("#ao_sel").serialize();
		$.ajax({
			type:"post", 
			dataType:"json",
			async:false,
			url:'aoMgnt-delete.ajax',
			data:parameters ,
			success:function(data){
				alert(data.errmsg);
				ao_sel();
			},
			error: function(request, status, error){  
			} 
		});
	  
	  
}

/*다음페이지*/
 function linkPage(pageNo){
	  
	  if(pageIndex == ""){
		  pageIndex = 1;
	  }
	  
	$('#pageIndex').val(pageNo);

	var ao_selForm = document.getElementById("ao_sel");
	ao_selForm.action = "aoMgntSel";
	ao_selForm.method = "post";
	ao_selForm.submit();
		
}	

/*라디오 체크 박스 관리*/
function radioChk(val){
	
	$('#useYn').val(val);
}


/*초기화 관리*/
function inputInit(){
	
	$('#aoId').val(""); 
	$('#aoNm').val(""); 
	
	$('#pathNm').val(""); 
	$('#aoParam').val(""); 
	$('#aoDesc').val(""); 
	$('#useYn').val("Y"); 
	
	$('#insert').prop('disabled', false);  /*등록버튼 활성화*/
	$('#update').prop('disabled', true);   /*등록버튼 수정버튼*/
	
	jQuery("#radio_yes").attr('checked', true);
	
	$('#aoId').prop('readonly', false);
	
	
}

/*엔터키시 조회*/
function enterSerch(){
	if(window.event.keyCode == 13){
		ao_sel();
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
            <form id="ao_sel">	
                <!-- caption-pnl -->
                <div class="caption-pnl">
                    <h2>AO관리</h2>
                    <div class="buttonset">
                        <button type="button" id="insert" class="state-highlight" onclick="ao_insert();" >저장</button>
                        <button type="button" id="update" onclick="ao_update();" >수정</button>
                        <button type="button" id="delete" onclick="ao_delete();" >삭제</button>
                    </div>
                </div>
                <!-- //caption-pnl -->
                <!-- search-pnl -->
                <div class="search-pnl">
                    <table>
                        <colgroup>
                            <col width="120px" />
                            <col width="*" />
                            <col width="130px" />
                            <col width="*" />
                            <col width="550px" />
                            <col width="200px" />
                        </colgroup>
                        <tr>
                            <th>AO명</th>
                            <td><input type="text" style="width:100%"  id="aoNmSel" name="aoNmSel" value="${aoNmSel}" onKeyDown="enterSerch();" ></td>
                            <th>AOID</th>
                            <td><input type="text" style="width:100%" id="aoIdSel" name="aoIdSel"  value="${aoidSel}"  onKeyDown="enterSerch();" ></td>
                            <td></td>
                            <td class="buttonset"><button type="button" onclick="ao_sel();" >조회</button>
                        </tr>
                    </table>
                </div>
                <!-- //search-pnl -->
                <!-- grid-pnl -->
                <div class="grid-pnl" style="overflow-x:hidden; overflow-y:auto; height:300px;">
                    <!-- table -->
                    <table class="align-center">
                        <colgroup>
                            <col width="50px">
                            <col width="15%">
                            <col width="15%">
                            <col width="25%">
                            <col width="25%">
                            <col width="40%">
                             <col width= "0%">
                        </colgroup>
                        <thead>
                            <tr>
                                <th scope="col"><input type="checkbox" id="designcheck00" onclick="chkBoxMgnt('resultListTable', $(this))" ><label for="designcheck00"></label></th>
                                <th scope="col">AOID</th>
                                <th scope="col">AO명</th>
                                <th scope="col">경로명</th>
                                <th scope="col">파라미터</th>
                                <th scope="col">AO설명</th>
                                  <th></th>
                            </tr>
                        </thead>
                        <tbody id="resultListTable" name="resultListTable" >
                           <c:forEach var="resultList" items="${resultList}" varStatus="status" >
								<tr>
									<th scope="row">
									     <input type="hidden" id="inputChk" name="inputChk">
									     <input type="checkbox" id="designcheck_${status.count}" name="chk_info" ><label for="designcheck_${status.count}"></label>
									</th>
									<td><input type="hidden" id="aoIdDel" name="aoIdDel" value="${resultList.aoId}" >${resultList.aoId}</td>
									<td>${resultList.aoNm}</td>
									<td>${resultList.pathNm}</td>
									<td>${resultList.aoParam}</td>
									<td>${resultList.aoDesc}</td>
									<td style="visibility:hidden;">${resultList.useYn}</td>
								</tr>
								</c:forEach>
                        </tbody>
                    </table>
                    <!-- //table -->
                </div>
                </form>
                <!-- //grid-pnl -->

                <!-- caption-pnl -->
                 <form id="ao_input">	
            	<div class="caption-pnl">
                	<h3>AO 상세</h3>
					<div class="buttonset">
					<button type="button" onclick="inputInit();">초기화</button>
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
                                    <th>AOID<span style="color: #fea338;">*</span></th>
                                    <td><input type="text" name="aoId" id="aoId"></td>
                                </tr>
                                <tr>
                                    <th>경로명</th>
                                   <td><input type="text" name="pathNm" id="pathNm"></td>
                                </tr>
                                <tr>
                                    <th>사용여부</th>
                                    <td><div class="select_group">
                                    	<input type="hidden"  id="useYn" name="useYn"  value="Y" >
                                        <span><input type="radio" name="useYnRadio" id="radio_yes" checked="checked" onclick="radioChk('Y');" ><label for="radio_yes"></label><label for="radio_yes">예</label></span>
                                        <span><input type="radio" name="useYnRadio" id="radio_no" onclick="radioChk('N');" ><label for="radio_no"></label><label for="radio_no">아니오</label></span>
                                    </div>
                                    </td>
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
                                <colgroup><col width="120px"><col width="*"></colgroup>
                                <tbody>
                                <tr>
                                    <th>AO명</th>
                                  <td><input type="text"  name="aoNm" id="aoNm"></td>
                                </tr>
                                <tr>
                                    <th>파라미터</th>
                                   <td><input type="text"  name="aoParam" id="aoParam"></td>
                                </tr>
                                <tr>
                                    <th>AO설명</th>
                                    <td><input type="text" name="aoDesc" id="aoDesc"></td>
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
    
</body>
</html>