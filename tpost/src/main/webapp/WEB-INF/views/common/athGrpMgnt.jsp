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

/*테이블 row 클릭시 이벤트 발생 */
$( document ).ready(function() {

	jQuery(".close, .state-submit").click(function () {
		jQuery(".layerBox").hide();
	});
	
	commCd_bas("custInfPopu #oganCdSel", "00001");  /*공단종류*/
});

function callBack(sel_id, codeNum)
{	
   /*콤보박스 콜백 메세드입니다.*/	
	$("#oganCdSel").prepend("<option value=''>전체</option>");

}

/*체크박스 선택된것가지고 자바에서 선택된것만 인서트 삭제*/
function chk_info_chk(){
	
	$('input:checkbox[id="checkbox_id"]').val(1); 
	
}


function enterSerch(){
	if(window.event.keyCode == 13){
		athGrp_sel();
	}
}


/****************************************************************************************************/
/* 권한 관리 조회 */
function athGrp_sel(){
	  
	  $('#pageIndex').val(1); /*첫조회는 페이지가 1로 조회*/
	  var custInf_sel = document.getElementById("athGrpMgnt_sel");
	  custInf_sel.action = "athGrpMgnt-Select";
	  
	  custInf_sel.method = "post";
	  custInf_sel.submit();
	  
}

/* 매핑정보조회 */
function info_sel(athGrpIdLink){
	
	if(athGrpIdLink != "delete"){
		$('#athGrpMgnt_sel #athGrpIdLink').val(athGrpIdLink);
	}
	
	custInfo_sel($('#athGrpMgnt_sel #athGrpIdLink').val() );
	aoInfo_sel($('#athGrpMgnt_sel #athGrpIdLink').val());
}

/* 매핑고객정보정보조회 */
function custInfo_sel(athGrpIdLink){
	var parameters = "athGrpIdLink="+ athGrpIdLink;
	custInfo_ajax("select", "athGrpMgntCust-Select.ajax" ,parameters );
}


/*ao 매핑조회*/
function aoInfo_sel(athGrpIdLink){
	
	var parameters = "athGrpIdLink="+ athGrpIdLink;
	aoInfo_ajax("select", "athGrpMgntAO-Select.ajax" , parameters);
}
 
/*매핑된 AO정보를 삭제합니다. */
function ath_ao_delete(athGrpInfoAoSeq){
	
	if (confirm("매핑AO정보를 삭제하시겠습니까?")) { 
		var parameters = "athGrpInfoAoSeq="+ athGrpInfoAoSeq;
		aoInfo_ajax("delete","athGrpMgntAO-update.ajax" , parameters);
	}
}

/*매핑된 Cust정보를 삭제합니다. */
function ath_cust_delete(athGrpInfoCustSeq){
	if (confirm("매핑고객정보를 삭제하시겠습니까?")) { 
		
		var parameters = "athGrpInfoCustSeq="+ athGrpInfoCustSeq;
		custInfo_ajax( "delete","athGrpMgntCust-Update.ajax" ,parameters );
	}
	
} 
 
/* 매핑고객정보정보조회 */
function custInfo_ajax(st, url ,parameters){
	
	var custResultListTable ="";

	$.ajax({
		type : "post",
		dataType : "json",
		async : false,
		url : url,
		data : parameters,
		success : function(data) {
			$("#custResultListTable").html(''); //테이블 초기화
			if( data.status == 'delete'){
				info_sel("delete");					
			}else{
				if(data.athGrpMgntCustList.length != 0){
					for(var i = 0; i < data.athGrpMgntCustList.length; i++){
						var athGrpMgntCustList = data.athGrpMgntCustList[i];
						custResultListTable +=	"<tr>"  ;
						custResultListTable +=	"	<td>"+athGrpMgntCustList.custNum+"</td>" ;
						custResultListTable +=	"	<td>"+athGrpMgntCustList.custNm+"</td>" ;
						custResultListTable +=	"	<td>"+athGrpMgntCustList.orgaNm+"</td>" ;
						custResultListTable +=	'	<td> <button type="button" onclick="ath_cust_delete( ' + athGrpMgntCustList.athGrpInfoCustSeq +' );"> 삭제 </button> <td>' ;					
						custResultListTable +=	"</tr> " ;
					}
				}
			}
			$("#athGrpMgnt_sel #custResultListTable").append(custResultListTable);
		},
		complete : function(data) {
					}
	
	});
	  
}


/*ao 매핑정보조회*/
function aoInfo_ajax(st ,url, parameters){
	
	var aoResultListTable = "";
	$.ajax({
		type : "post",
		dataType : "json",
		async : false,
		url : url,
		data : parameters,
		success : function(data) {
			$("#athGrpMgnt_sel #aoResultListTable").html(''); //테이블 초기화
			
			if( data.status == 'delete'){
				info_sel("delete");					
			}else{
				if(data.athGrpMgntAOList.length != 0){
					var athGrpMgntAOList ="";
					for(var i = 0; i < data.athGrpMgntAOList.length; i++){
						var athGrpMgntAOList = data.athGrpMgntAOList[i];
						//'"+ athGrpMgntAOList.athGrpInfoAoSeq +"'
						aoResultListTable +=	'<tr>' ;
						aoResultListTable +=	'	<td>'+athGrpMgntAOList.menuId+'</td>' ;
						aoResultListTable +=	'	<td>'+athGrpMgntAOList.menuNm+'</td>' ;
// 						aoResultListTable +=	'	<td>'+athGrpMgntAOList.aoDesc+'</td>' ;
						aoResultListTable +=	'	<td> <button type="button" onclick="ath_ao_delete( ' + athGrpMgntAOList.athGrpInfoAoSeq +' );"> 삭제 </button> ' ; 
						aoResultListTable +=	'</tr> ' ;
							
					}
				}
			}
			$("#athGrpMgnt_sel #aoResultListTable").append(aoResultListTable);
		},
		complete : function(data) {
			
		}

	});
}


/*매핑 고객정보 추가 */
function cust_add_pop(athGrpIdLink){
	/*고객 정보조회 초기화 */
	$("#custInfPopu #custInfPopuTable").html('');
	$("#custInfPopu #oganCdSel").val('01');
	$("#custInfPopu #custNmSel").val('');
	
	var parameters = "athGrpIdLink=" + athGrpIdLink ;
	jQuery("#custInfPopu1").show();
	jQuery("#custInfPopu1 #athGrpIdLink").val(athGrpIdLink);
	
	/*기본으로 고객정보 보여줌*/
	custInf_sel();
}


/*매핑 AO정보 추가 */
function ao_add_pop(athGrpIdLink){
	/*매핑 AO조회 초기화 */
	$("#aoMgntInfPopu #aoMgntInfPopuTable").html(''); //테이블 초기화
	$("#aoMgntInfPopu #aoIdSel").val('');
	$("#aoMgntInfPopu #aoNmSel").val('');
	
	var parameters = "athGrpIdLink=" + athGrpIdLink ;
	jQuery("#aoMgntInfPopu1").show();
	jQuery("#aoMgntInfPopu #athGrpIdLink").val(athGrpIdLink);
	
	/*기본으로 AO정보 조회*/
	ao_select();
    
}


/*권한그룹정보 행추가*/
function ath_row_add(){
	
	var ath_row_add = "";
	
	var chk_info = $("input[name=chk_info]");
	var chkInfoLen = chk_info.length + 2; 
	
	ath_row_add = "<tr>"
	ath_row_add += '<td scope="row"><input type="hidden" id="inputChk"    name="inputChk"  >'
	ath_row_add += '    <input type="checkbox" id="designcheck_'+chkInfoLen+'"    name="chk_info" > <label for="designcheck_'+chkInfoLen+'"> </td>' ;
	
	ath_row_add += '<td><input type="text"    id="athGrpId"    name="athGrpId" ></td>';
	ath_row_add += '<td><input type="text"    id="athGrpNm"    name="athGrpNm" ></td>';
 	ath_row_add += '<td><input type="text"    id="athGrpDesc"  name="athGrpDesc" ></td>';
	ath_row_add += '<td>저장후등록가능</td>';
	ath_row_add += '<td>저장후등록가능</td>';
	ath_row_add += "</tr>"
	
	$('#athGrpMgnt_sel #resultListTable').append(ath_row_add);

	$("#designcheck_"+  chkInfoLen  ).attr("checked",	true);
	$("input[name=athGrpId]:eq(" +  ( chkInfoLen - 3 )  + ")").focus();	

	 	 
	
}



/*권한그룹정보 등록*/
function ath_insert(){
	
    $('#pageIndex').val(1);
	
    if(reqValCheckTable('athGrpId', "권한그룹ID")){
    	return false;
    }
    
    if(reqValCheckTable('athGrpNm', "권한그룹명")){
    	return false;
    }
   
    var cnt = 0 ;
    /*라디오 박스 셋팅*/
    var checkbox = $("input[name=chk_info]");
    for(var i=0;i<checkbox.length;i++){
        if(checkbox[i].checked == true) {
        	document.getElementsByName("inputChk")[i].value=true;
        	cnt= cnt +1;
        }else{
        	document.getElementsByName("inputChk")[i].value=false;
        }
    }
    if(cnt == 0 ){
    	alert("등록할 건을 체크하여 주십시오");
    	return;
    }
    /*수정삭제 아작스*/
    ajax_cud("athGrpMgnt-insert.ajax");	
}

/*권한그룹정보 삭제*/
function ath_delete(){
	
	$('#pageIndex').val(1);
	/*라디오 박스 셋팅*/
    
    var checkbox = $("input[name=chk_info]");
    
    for(var i=0;i<checkbox.length;i++){
        if(checkbox[i].checked == true) {
        	document.getElementsByName("inputChk")[i].value=true;
        }else{
        	document.getElementsByName("inputChk")[i].value=false;
        }
    }
      
    /*수정삭제 아작스*/
    ajax_cud( "athGrpMgnt-delete.ajax" );
} 

function ajax_cud(url){

	var queryString = $("form[name=athGrpMgnt_sel]").serialize() ;
    var url = url;
    
    $.ajax({
		type : "post",
		dataType : "json",
		async : false,
		url : url,
		data : queryString,
		success : function(data) {
			if(data.status == "insert"){
			    alert("저장 성공하였습니다.");
			}else{
			    alert("삭제에 성공하였습니다.");
			}
			athGrp_sel();
		},
		complete : function(data) {
			
		}
		
	});
	
}

/******************************************/
/*ao 팝업 스크립트 */
/* 메뉴조회 */
function ao_select(){
	
 	  $('#pageIndex').val(1); /*첫조회는 페이지가 1로 조회*/
  	  var url = "aoMgntSel-popu";
 	  
 
	var parameters = $("#aoMgntInfPopu").serialize();
	$.ajax({
		type:"post", 
		dataType:"json",
		async:false,
		url:url,
		data:parameters ,
		success:function(data){
			
			$("#aoMgntInfPopu #aoMgntInfPopuTable").html(''); //테이블 초기화
			
			if(data.result == "N" ) {
				alert(data.errorMessage);
			}else{
 	 			if(data.athGrpMgntAOList.length != 0){
 	 				var athGrpMgntAOList ="";
 	 				var aoResultListTable="";
 	 				for(var i = 0; i < data.athGrpMgntAOList.length; i++){
 	 					var athGrpMgntAOList = data.athGrpMgntAOList[i];
	 						aoResultListTable +=	'<tr>' ;
	 						aoResultListTable +=	'	<td scope="row">' ;
	 						aoResultListTable +=	'       <input type="checkbox" id="aoMgntInfPopuTable' + i + '" name="aoMgntInfPopuTableChk"> ' ;
	 						aoResultListTable +=    '       <label for="aoMgntInfPopuTable' + i + '" ></label></td>' ;
	 						aoResultListTable +=	'	<td>'+athGrpMgntAOList.menuId+'</td>' ;
	 						aoResultListTable +=	'	<td>'+athGrpMgntAOList.menuNm+'</td>' ;
// 	 						aoResultListTable +=	'	<td>'+athGrpMgntAOList.aoDesc+'</td>' ;
	 						aoResultListTable +=	'	<td>'+athGrpMgntAOList.grpNm+'</td>' ;
	 						aoResultListTable +=	'	<td>'+athGrpMgntAOList.dispNo+'</td>' ;
	 						aoResultListTable +=	'</tr> ' ;
 	 				}
 	 			}
 	 		}
 	 		$("#aoMgntInfPopu #aoMgntInfPopuTable").append(aoResultListTable);
			}
		
		});
	  
}

/*ao insert*/
 function ao_confirm(){
		//aoMgntInfPopuTableChk
	 var cnt = 0 ;
	    var checkbox = $("input[name=aoMgntInfPopuTableChk]");
	    for(var i=1;i<checkbox.length;i++){
	        if(checkbox[i].checked == true) {
	        	cnt= cnt +1;
	        }
	    }
	    if(cnt == 0 ){
	    	alert("등록할 건을 체크하여 주십시오");
	    	return;
	  }
	 
	aoInf_array(); /*체크된 데이터 만들기 */
	var url = "athGrpMgntAO-insert.ajax";
	 var queryString = $("form[name=aoMgntInfPopu]").serialize() ;
	    var url = url;
	    
	    $.ajax({
			type : "post",
			dataType : "json",
			async : false,
			url : url,
			data : queryString,
			success : function(data) {
				if(data.result == "Y"){
				    alert("저장 성공하였습니다.");
				    jQuery(".layerBox").hide();
				}
			},
			complete : function(data) {
				
			}
			
		});

 }
 

 /*체크박스로 보낼 array 값 만들기 */
 function aoInf_array(){
 	 var checkbox = $("input[name=aoMgntInfPopuTableChk]:checked") ;
 	 var custId= "";
 	 
 	 var menuId = Array();
 	 checkbox.each(function(i) {
 		 var  tr= checkbox.parent().parent().eq(i);
 		 var td = tr.children();
// 		aoId[i] = td.eq(1).text();
 		menuId[i] = td.eq(1).text();
 	 	}
 	 )
 	 
 	 $('#menuId').val(menuId);
 }  


 /*고객정보확인목록 취소*/
 function ao_cancel(){
 	
 	jQuery(".layerBox").hide();
 }

 
 /****************************************************************************************/
 /*고객정보 팝업*/
function js_init()
{
	
	commCd_bas("oganCdSel", "00001");  /*공단종류*/
	commCd_bas("oganCd", "00001");  /*공단종류*/
}



/*테이블 row 클릭시 이벤트 발생 */
$( document ).ready(function() {

	/*그리드 클릭시 이벤트 전달하는것*/
	$("#resultListTable tr").click(function(event) { // 실행하고자 하는 jQuery 코드
			// 현재 클릭된 Row(<tr>)
			var tr = $(this);
//			resultList(tr);		
	});
});



/****************************************************************************************************/
/* 화면조회 */
function custInf_sel(){
	  
	//custInfPopu
	  var url = "custInfMgntInfSel-popu.ajax";
	  
		var parameters = $("#custInfPopu").serialize();
		$("#custInfPopu #custInfPopuTable").html('');
		$.ajax({
			type:"post", 
			dataType:"json",
			async:false,
			url:url,
			data:parameters ,
			success:function(data){
				
				$("#custInfPopu #aoMgntInfPopuTable").html(''); //테이블 초기화
				
				if(data.result == "N" ) {
					alert(data.errorMessage);
					
				}else{
	 	 			if(data.custInfList.length != 0){
	 	 				var custInfList ="";
	 	 				var custResultListTable="";
	 	 				for(var i = 0; i < data.custInfList.length; i++){
	 	 					var custInfList = data.custInfList[i];
	 	 						custResultListTable +=	'<tr>' ;
	 	 						custResultListTable +=	'	<td scope="row">' ;
	 	 						custResultListTable +=	'       <input type="checkbox" id="custInfPopuTable' + i + '" name="custInfPopuTableChk"> ' ;
	 	 						custResultListTable +=    '       <label for="custInfPopuTable' + i + '" ></label></td>' ;
	 	 						custResultListTable +=	'	<td>'+custInfList.oganNm+'</td>' ;
	 	 						custResultListTable +=	'	<td>'+custInfList.custNum+'</td>' ;
	 	 						custResultListTable +=	'	<td>'+custInfList.custNm+'</td>' ;
	// 	 						custResultListTable +=	'	<td>'+custInfList.deptNm+'</td>' ;
	 	 						custResultListTable +=	'	<td>'+custInfList.useYn+'</td>' ;
	 	 						custResultListTable +=	'	<td>'+custInfList.telNum+'</td>' ;
	 	 						custResultListTable +=	'	<td>'+custInfList.celpNum  +'</td>' ;
//	 	 						custResultListTable +=	'	<td>'+custInfList.emaiId+'</td>' ;
	 	 						custResultListTable +=	'</tr> ' ;
//		 						"aoMgntInfPopuTable"
	 	 				}
	 	 			}
	 	 		}
				
	 	 		$("#custInfPopu #custInfPopuTable").append(custResultListTable);
				}
			
			});
}

function custInf_confirm(){
	
	/*체크박스 선택여부*/
	var cnt = 0 ;
    var checkbox = $("input[name=custInfPopuTableChk]");
    for(var i=1;i<checkbox.length;i++){
        if(checkbox[i].checked == true) {
        	cnt= cnt +1;
        }
    }
    if(cnt == 0 ){
    	alert("등록할 건을 체크하여 주십시오");
    	return;
    }
    
	
	$('#pageIndex').val(1);
	custInf_array();

	var url = "athGrpMgntCust-insert.ajax";
	var queryString = $("form[name=custInfPopu]").serialize() ;
	    
	    $.ajax({
			type : "post",
			dataType : "json",
			async : false,
			url : url,
			data : queryString,
			success : function(data) {
				if(data.result == "Y"){
				    alert("저장 성공하였습니다.");
				    jQuery(".layerBox").hide();
//					close();
				}
			},
			complete : function(data) {
				
			}
			
		});


}

function custInf_confirm2(){
	custInf_array();
}

/*체크박스로 보낼 array 값 만들기 */
function custInf_array(){
	 var checkbox = $("input[name=custInfPopuTableChk]:checked") ;
	 
	 var custId = Array();
	checkbox.each(function(i) {
		 var  tr= checkbox.parent().parent().eq(i);
		 var td = tr.children();
		 custId[i] = td.eq(2).text();
	 	}
	 )
	 
	 $('#custId').val(custId);
}  


/*고객정보확인목록 취소*/
function custInf_cancel(){
	

}


/*엔터키시 조회*/
function enterSerch(frm){
	
	
	if(window.event.keyCode == 13){
		if(frm == 'athGrpMgnt_sel'){
			athGrp_sel();
		}else if(frm == 'aoMgntInfPopu1'){
			ao_select();
		}else if(frm == 'custInfPopu'){
			
			
			custInf_sel();
		}
		
		event.preventDefault();
			
	}
	
}


 
</script>
</head>
<body>

    <!-- wrap -->
    <div id="wrap">
	<form id="athGrpMgnt_sel" name="athGrpMgnt_sel">	
	<input type="hidden" id="athGrpIdLink" name="athGrpIdLink" />
	<input type="hidden" id="pageIndex" name="pageIndex" value="${searchVO.pageIndex}"/>
        <!-- contents -->
        <div id="contents">
            <!-- section -->
            <div class="section">
                <!-- caption-pnl -->
                <div class="caption-pnl">
                    <h2>권한 관리</h2>
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
                            <th>권한그룹명</th>
                            <td><input type="text" id="athGrpNmSel"  value="${athGrpNmSel}" name="athGrpNmSel"  onKeyDown="enterSerch('athGrpMgnt_sel');" ></td>
                            <th>권한그룹ID</th>
                            <td><input type="text" id="athGrpIdSel"  value="${athGrpIdSel}" name="athGrpIdSel"  onKeyDown="enterSerch('athGrpMgnt_sel');"  ></td>
                            <td></td>
                            <td class="buttonset"><button type="button" onclick="athGrp_sel();" >조회</button></td>
                        </tr>
                    </table>
                </div>
                <!-- //search-pnl -->
                <!-- caption-pnl -->
            	<div class="caption-pnl">
                	<h3>권한그룹 정보</h3>
					<div class="buttonset">
					    <button type="button" class="icon-plus" onclick="ath_row_add();" >행추가</button>
					    <button type="button" class="icon-minus" onclick="table_row_remove('chk_info');" >행삭제</button>
					    <button type="button" class="state-highlight" onclick="ath_insert();"  >등록</button>
					    <button type="button" onclick="ath_delete();" >삭제</button>
					</div>
                </div>
                <!-- //caption-pnl -->
                <!-- grid-pnl -->
                <div class="grid-pnl" style="overflow-x:hidden; overflow-y:auto; height:300px;">
                    <!-- table -->
                    <table class="align-center">
                        <colgroup>
                            <col width="50px">
                            <col width="15%">
                            <col width="15%">
                            <col width="40%">
                            <col width="10%">
                            <col width="10%">
                            <col width="10%">
                        </colgroup>
                        <thead>
                             <tr>
                                <th scope="col"><input type="checkbox" id="designcheck00" name="chk_info" onclick="chkBoxMgnt('resultListTable', $(this))" ><label for="designcheck00"></label>
                                 <input type="hidden" id="inputChk" name="inputChk">
                                </th>
                                <th scope="col">권한그룹ID</th>
                                <th scope="col">권한그룹명</th>
                                <th scope="col">권한그룹설명</th>
                                <th scope="col">고객등록</th>
                                <th scope="col">메뉴등록</th>
                                <th scope="col">매핑정보</th>
                            </tr>
                            
                        </thead>
                        <tbody id="resultListTable" name="resultListTable">
                        
                           <c:forEach var="arhGrpResultList" items="${arhGrpResultList}" varStatus="status">
								<tr>
									<th scope="row">
									     <input type="hidden" id="inputChk" name="inputChk">
									     <input type="checkbox" id="designcheck_${status.count}" name="chk_info" ><label for="designcheck_${status.count}"></label>
									</th>
									<td> <input type="text" id="athGrpId" name="athGrpId"  value="${arhGrpResultList.athGrpId}"></td>
									<td> <input type="text" id="athGrpNm" name="athGrpNm"  value="${arhGrpResultList.athGrpNm}"></td>
									<td> <input type="text" id="athGrpDesc" name="athGrpDesc"  value="${arhGrpResultList.athGrpDesc}"></td>
									<td><button type="button"  onclick="cust_add_pop('${arhGrpResultList.athGrpId}');" >고객</button></td>
									<td><button type="button"  onclick="ao_add_pop('${arhGrpResultList.athGrpId}');">메뉴</button></td>
									<td><button type="button"  onclick="info_sel('${arhGrpResultList.athGrpId}');">매핑정보</button></td>
								</tr>
							</c:forEach>
                        
                        
                        </tbody>
                    </table>
                    <!-- //table -->
                </div>
                <!-- //grid-pnl -->

                <div class="colgroup-wrap">
                    <div class="colgroup-1-2">
                        <!-- caption-pnl -->
                        <div class="caption-pnl">
                            <h3>매핑고객정보</h3>
                        </div>
                        <!-- //caption-pnl -->
                        <!-- grid-pnl -->
                        <div class="grid-pnl" style="overflow-x:hidden; overflow-y:auto; height:300px;">
                            <!-- table -->
                            <table class="align-center">
                                <colgroup>
                                    <col width="25%">
                                    <col width="25%">
                                    <col width="25%">
                                    <col width="25%">
                                </colgroup>
                                <thead>
                                
                                    <tr>
                                        <th scope="col">고객번호</th>
                                        <th scope="col">고객명</th>
                                        <th scope="col">기관(회사)명</th>
                                        <th scope="col">삭제</th>
                                    </tr>
                                </thead>
                                <tbody id="custResultListTable" name="resultListTable" >
                                   
                                </tbody>
                            </table>
                            <!-- //table -->
                        </div>
                        <!-- //grid-pnl -->
                    </div>
                    <div class="colgroup-1-2">
                        <!-- caption-pnl -->
                        <div class="caption-pnl">
                            <h3>매핑메뉴정보</h3>
                        </div>
                        <!-- //caption-pnl -->
                        <!-- grid-pnl -->
                        <div class="grid-pnl" style="overflow-x:hidden; overflow-y:auto; height:300px;">
                            <!-- table -->
                            <table class="align-center">
                                <colgroup>
                                    <col width="25%">
                                    <col width="25%">
<%--                                     <col width="25%"> --%>
                                    <col width="25%">
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th scope="col">메뉴ID</th>
                                        <th scope="col">메뉴명</th>
<!--                                         <th scope="col">AO설명</th> -->
                                        <th scope="col">삭제</th>
                                    </tr>
                                </thead>
                                <tbody id="aoResultListTable" name="aoResultListTable" >
                                  
                                </tbody>
                            </table>
                            <!-- //table -->
                        </div>
                        <!-- //grid-pnl -->
                    </div>
                </div>
            </div>
            <!--// section -->
        </div>
        <!-- //contents -->
	</form>
    </div>
    <!-- //wrap -->
    
    <!--AO조회 팝업  -->
    <!-- layerBox Message -->
	<div class="layerBox" id="aoMgntInfPopu1" name="aoMgntInfPopu1" class="layerBox pwchange" style="display:none;" >
	 <form name="aoMgntInfPopu" id="aoMgntInfPopu">
	
	 <input type="hidden" id="athGrpIdLink" name="athGrpIdLink" value="${athGrpIdLink}"/>
	 <input type="hidden" id="aoId" name="aoId" value=""/>
	 <input type="hidden" id="menuId" name="menuId" value=""/>
	 
		<div class="layer">
			<div class="topBox">
				<h3>메뉴조회</h3>
				<a class="close" href="#">&#10005;</a>
			</div>
			<div class="contBox" >
				<div class="section">
					<div class="search-pnl">

						<table>
							<colgroup>
								<col width="110px"
								><col width="*">
								<col width="110px">
								<col width="*">
								<col width="200px">
							</colgroup>
							<tbody><tr>
								<th>메뉴ID</th>
								<td><input type="text" id="aoIdSel" name="menuIdSer" value="${aoIdSel}" onKeyDown="enterSerch('aoMgntInfPopu1');" ></td>
								<th>메뉴명</th>
								<td><input type="text" id="aoNmSel" name="menuNmSer" value="${aoNmSel}" onKeyDown="enterSerch('aoMgntInfPopu1');" ></td>
								<td class="buttonset"><button type="button" onclick="ao_select();" >조회</button></td>
							</tr>
						</tbody></table>
					</div>

					<div class="grid-pnl" style="overflow-x:hidden; overflow-y:auto; height:300px;" >
						<!-- table -->
						<table class="align-center">
							<colgroup>
								<col width="50px">
								<col width="20%">
								<col width="20%">
<%-- 								<col width="20%"> --%>
								<col width="20%">
								<col width="20%">
							</colgroup>
						<thead>
							<tr>
								<th scope="col">
									<input type="checkbox" id="aoMgntInfPopuTableChk" name="aoMgntInfPopuTableChk" onclick="chkBoxMgntName('aoMgntInfPopuTable', $(this) , 'aoMgntInfPopuTableChk' )" >
									<label for="aoMgntInfPopuTableChk"></label>
								</th>
								<th scope="col">메뉴ID</th>
								<th scope="col">메뉴명</th>
<!-- 								<th scope="col">AO설명</th> -->
								<th scope="col">대표그룹명</th>
								<th scope="col">표시순서</th>
							</tr>
						</thead>
						<tbody id="aoMgntInfPopuTable" >  <!-- ao 팝업  -->
							
						</tbody>
						</table>
						<!-- //table -->
					</div>
				<!-- footer -->
				<div class="footer">
					<div class="buttonset">
						<button type="button" class="state-highlight" onclick="ao_confirm();">등록</button>
						<button type="button" class="state-submit" onclick="ao_cancel();">닫기</button>
					</div>
				</div>
				<!-- //footer -->
			</div>
			</div>
			<!-- //contBox -->
		</div>
		<div class="fade"></div>
		</form>
	</div>
	<!-- //layerBox Message -->
	
	<!--매핑 고객정보 팝업  -->
	<!-- layerBox Message -->
	<div class="layerBox" id="custInfPopu1" name="custPopu1" class="layerBox pwchange" style="display:none;">
	<form name="custInfPopu" id="custInfPopu">
	<input type="hidden" id="athGrpIdLink" name="athGrpIdLink" value="${athGrpIdLink}"/>
	<input type="hidden" id="custId" name="custId" value=""/>

		<div class="layer">
			<div class="topBox">
				<h3>고객정보조회</h3>
				<a class="close" href="#">&#10005;</a>
			</div>
			<div class="contBox">
				<div class="section">
					<div class="search-pnl">

						<table>
							<colgroup>
								<col width="15%">
								<col width="25%">
								<col width="15%">
								<col width="25%">
<%-- 								<col width="15%"> --%>
								<col width="20%">
							</colgroup>
							<tbody><tr>
								<th>기관(회사)</th>
								<td><select id="oganCdSel" name="oganCdSel"  onKeyDown="enterSerch('custInfPopu');" ></select></td>
								<th>고객명</th>
								<td><input type="text" id="custNmSel" name="custNmSel"  onKeyDown="enterSerch('custInfPopu');" ></td>
<!-- 								<td></td> -->
								<td class="buttonset"><button type="button" onclick="custInf_sel();"  >조회</button></td>
							</tr>
						</tbody></table>
					</div>

					<div class="grid-pnl" style="overflow-x:hidden; overflow-y:auto; height:300px;" >
						<!-- table -->
						<table class="align-center">
							<colgroup>
								<col width="70px">
								<col width="*">
								<col width="*">
<%-- 								<col width="*"> --%>
								<col width="*">
								<col width="*">
								<col width="*">
								<col width="*">
<%-- 								<col width="*"> --%>
							</colgroup>
						<thead>
							<tr>
								<th scope="col">
									<input type="checkbox" id="custInfPopuTableChk" name="custInfPopuTableChk" onclick="chkBoxMgntName('custInfPopuTable', $(this) , 'custInfPopuTableChk' )" >
									<label for="custInfPopuTableChk"></label>
								</th>
								<th scope="col">기관<br>(회사명)</th>
								<th scope="col">고객번호</th>
								<th scope="col">고객명</th>
<!-- 								<th scope="col">부서명</th> -->
								<th scope="col">사용여부</th>
								<th scope="col">전화번호</th>
								<th scope="col">휴대전화</th>
<!-- 								<th scope="col">E-mail</th> -->
							</tr>
						</thead>
						<tbody id="custInfPopuTable">
							<tr>
								<th scope="row"><input type="checkbox" id="designcheck01"><label for="designcheck01"></label></th>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
<!-- 								<td></td> -->
								<td></td>
								<td></td>
<!-- 								<td></td> -->
							</tr>
							
						</tbody>
						</table>
						<!-- //table -->
					</div>
				<!-- footer -->
				<div class="footer">
					<div class="buttonset">
						<button type="button" class="state-highlight" onclick="custInf_confirm();" >등록</button>
						<button type="button" class="state-submit" onclick="custInf_cancel();">취소</button>
					</div>
				</div>
				<!-- //footer -->
			</div>
			</div>
			<!-- //contBox -->
		</div>
		<div class="fade"></div>
		</form>
	</div>

</body>
</html>