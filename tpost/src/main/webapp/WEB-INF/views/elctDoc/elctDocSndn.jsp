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
    #elctDocSndnEx.layerBox .layer {width:880px; height:670px;}
    #container.mb-container {top: 8px;box-shadow: none;background: url(/tpost/resource/images/mobilebg.png) no-repeat; background-size: 600px;}
	#container.mb-container h2 {height: 45px;font-size: 12px;;color: #14161b;font-weight: bold;background-color: inherit;text-align: left;padding-left: 20px; line-height:80px}
	.contents-wrap {margin-top: 30px;margin-left: 340px;width: 225px;}
	.dateinfo {font-size: 11px; color: #787878;text-align: left;padding-left: 20px;}
	.contents {margin-top:20px; text-align:left; font-size:11px;}
	.contents a{color:#0f96fe;     text-decoration-line: underline;}
	.pnl::-webkit-scrollbar {width:3px;background-color: #999999;}
	.pnl::-webkit-scrollbar-thumb {background-color: #d3d3d3;}
	.pnl::-webkit-scrollbar-button {width: 3px; height:1px;background-color: #7b7b7b;color:#f2f2f2;}
	</style>
	
	<!--     JS     -->
	<script src="/tpost/resource/js/jquery.min.js"></script>
	<script src="/tpost/resource/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="/tpost/resource/js/jquery-ui.min.js"></script>
	<script src="/tpost/resource/js/comm.js"></script>
	<!-- ---------- -->
	<script type="text/javascript">
		$('document').ready(function(){	
			commCd_bas('iOganCd', '00001');
			commCd_bas('iSndnCd', '00002');
			
			if($("#iSndnStDt").val() == "" && $("#iSndnClosDt").val() == "") {
				getYYYYMMFirstDay('iSndnStDt');
				getYYYYMMLastDay('iSndnClosDt');
			}
			
			oganTmpltList(0);
			datepicker();
			
			setElctDocSndnDetl();
		});
		
		function enterSerch(){
	    	if(window.event.keyCode == 13){
	    		elctDocSndnListSelect();
	    	}
	    }
		
		//팝업 닫기
        function closePopu(){
        	jQuery(".layerBox").hide();
        }
		
       //기관코드에 따른 템플릿 명
		function changeTmpltList(type){
			oganTmpltList(type);
			
			if(type == 0 && $("#iOganCd option:selected").val() == ""){
				$("#iTmpltCd option:eq(0)").prop("selected", true);
			}
		}
		
		function oganTmpltList(type){
			if(type == 0){
				var oganCd = document.getElementById("iOganCd");
				tmpltCd_bas('iTmpltCd', oganCd.options[oganCd.options.selectedIndex].value);		
			}
			
			if(type == 1){
				var oganCd = document.getElementById("oganCd");
				tmpltCd_bas('tmpltCd', oganCd.options[oganCd.options.selectedIndex].value);
			}
			
			if(type == 2){
				var oganCd = document.getElementById("oganCd");
				tmpltCd_bas('tmpltCd', oganCd.options[oganCd.options.selectedIndex].value);
				
				getToday('transDt');
				getToday('sndnD');
				setHM();
				
				$('#msgTitle').val('');
				$('#msgText').val('');
				
				$('#sndnCd').val("0").prop("selected", true);
				$('#msgSndnGbn').val("1").prop("selected", true);
				
				$('input[name=multiMblPrcGbn]:radio[value=N]').prop('checked', true);
				$('input[name=urlYn]:radio[value=N]').prop('checked', true);
				
				jQuery("#saveDiv").hide();
				$('#saveFn').val('');
				
				onFileUpload();
			}
		}
		
		//기관코드에 따른 대표번호
		function changeSrcCallNo(){
			$('#srcCallNo').html('');
			var oganCd = document.getElementById("oganCd");
			commDetlCd_bas('srcCallNo', '00010', oganCd.options[oganCd.options.selectedIndex].value);
		}
		
		function setHM(){
			$('#sndnH').html('');
			$('#sndnM').html('');
			
			for(var i = 19; i > 7; i--){
				i < 10 ? $('#sndnH').prepend("<option value='0"+ i +"' >0"+ i +"</option>") : $('#sndnH').prepend("<option value='"+ i +"' >"+ i +" </option>");
			}
			
			for(var i = 5; i > -1; i--){
				$('#sndnM').prepend("<option value='"+ i +"0' >"+ i +"0</option>");
			}

			$('#sndnH').prepend('<option value="" selected="true">선택</option>'); 
			$('#sndnM').prepend('<option value="" selected="true">선택</option>'); 
		}
		
		//상단조회버튼 클릭 시
		function elctDocSndnListSelect(){
			$("#iOganCd").prop("disabled", false);
			
			var elctDocSndnForm = document.getElementById("elctDocSndn");
			elctDocSndnForm.action = "elctDocSndnListSelect";
			elctDocSndnForm.method = "post";
			elctDocSndnForm.submit();
			
			$("#iOganCd").prop("disabled", true);
		}
		
		//승인미승인여부 업데이트
		function elctDocSndnAprYnUpdate(oganCd, tmpltCd, transDt, sndnDtm, aprYn){
			var parameters = {
					oganCd	: oganCd,
					tmpltCd : tmpltCd,
					transDt : transDt,
					sndnDtm : sndnDtm,
					aprYn	: aprYn,
			};
			
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "elctDocSndnAprYnUpdate.ajax",
				data : parameters,
				success : function(data) {
					alert("승인여부가 변경되었습니다.");
					elctDocSndnListSelect();
				},
				complete : function(data) {
				}
			});
		}
		
		//전자문서 발송 상세내역 가져옴
		function elctDocSndnDetlSelect(oganCd, tmpltCd, transDt, sndnDtm, aprYn){
			if(aprYn == 'N'){
				$("#btnUpd").attr('disabled', false);
				$("#btnIns").attr('disabled', true);
			}
			else{
				$("#btnUpd").attr('disabled', true);
				$("#btnIns").attr('disabled', true);
			}
			
			var parameters = {
					oganCd	: oganCd,
					tmpltCd : tmpltCd,
					transDt : transDt,
					sndnDtm : sndnDtm,
			};
			
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "elctDocSndnDetlSelect.ajax",
				data : parameters,
				success : function(data) {
					if(data.sndnDetl.length != 0){
						var sndnDetl = data.sndnDetl;
						
						setElctDocSndnDetl();						
						
						$('#oganCd').val(sndnDetl.oganCd).prop("selected", true);
						$('#transDt').val(sndnDetl.transDt);
						$('#sndnD').val(sndnDetl.sndnD);
						$('#sndnH').val(sndnDetl.sndnH).prop("selected", true);
						$('#sndnM').val(sndnDetl.sndnM).prop("selected", true);
						$('#sndnCd').val(sndnDetl.sndnCd).prop("selected", true);
						$('input[name=multiMblPrcGbn]:radio[value='+sndnDetl.multiMblPrcGbn+']').prop('checked', true);
						$('#msgTitle').val(sndnDetl.msgTitle);
						$('#msgText').val(sndnDetl.msgText);
						$('input[name=urlYn]:radio[value='+sndnDetl.urlYn+']').prop('checked', true);
						$('#msgSndnGbn').val(sndnDetl.msgSndnGbn).prop("selected", true);
						
						changeTmpltList(1);
						$('#tmpltCd').val(sndnDetl.tmpltCd).prop("selected", true);
						 
						changeSrcCallNo();
						$("select[name='srcCallNo'] option:contains('"+sndnDetl.srcCallNo+"')").attr("selected", "selected");
						
						$("#oganCd").attr("disabled", true);
						$("#transDt").attr("disabled", true);
						$("#tmpltCd").attr("disabled", true);
						
						onTextLimit();
						onFileUpload();
						if(sndnDetl.mmsImg != ""){
							$('#saveFn').val(sndnDetl.mmsImg);
							jQuery("#saveDiv").show();
						}
						else{
							jQuery("#saveDiv").hide();
						}
					}
				},
				complete : function(data) {
				}
			});
		}
		
		//전자문서 발송 상세내역 초기화
		function elctDocSndnDetlInit(){
			setElctDocSndnDetl();	
			
			$("#btnIns").attr('disabled', false);
			$("#btnUpd").attr('disabled', true);
		}
		
		function setElctDocSndnDetl(){
			$('#elctDocSndnDetl').html('');
			
			var inner = '';
			inner += '<tr>';
			inner += '<th>기관(회사)명<span style="color: #fea338;">*</span></th>';
			inner += '<td><select id="oganCd" name="oganCd" onchange="changeTmpltList(2); changeSrcCallNo();"></select></td>';
			inner += '<th>템플릿명<span style="color: #fea338;">*</span></th>';
			inner += '<td colspan="2"><select id="tmpltCd" name="tmpltCd" onchange="changeMsgTmpltSelect()"></select></td>';
			inner += '</tr>';
			inner += '<tr>';
			inner += '<th>파일송신일자<span style="color: #fea338;">*</span></th>';
			inner += '<td><input type="text" class="dateinput" id="transDt" name="transDt" onkeypress="dateFormat(this)"></td>';
			inner += '<th>발송일시<span style="color: #fea338;">*</span></th>';
			inner += '<td colspan="2">';
			inner += '<input type="text" size="13" class="dateinput" id="sndnD" name="sndnD" onkeypress="dateFormat(this)">';
			inner += '<select id="sndnH" name="sndnH"></select>시';
			inner += '<select id="sndnM" name="sndnM"></select>분';
			inner += '</td>';
			inner += '</tr>';
			inner += '<tr>';
			inner += '<th>발송유형<span style="color: #fea338;">*</span></th>';
			inner += '<td><select id="sndnCd" name="sndnCd" onchange="onFileUpload(); onTextLimit();"></select></td>';
			inner += '<th>다회선전송여부<span style="color: #fea338;">*</span></th>';
			inner += '<td colspan="2">';
			inner += '<div class="select_group">';
			inner += '<span><input type="radio" id="radio_yes" name="multiMblPrcGbn" value="Y"><label for="radio_yes"></label><label for="radio_yes">예</label></span>';
			inner += '<span><input type="radio" id="radio_no" name="multiMblPrcGbn" value="N" checked="checked"><label for="radio_no"></label><label for="radio_no">아니오</label></span>';
			inner += '</div>';
			inner += '</td>';
			inner += '</tr>';
			inner += '<tr>';
			inner += '<th>메시지제목<span style="color: #fea338;">*</span></th>';
			inner += '<td colspan="4"><input type="text" id="msgTitle" name="msgTitle"></td>';
			inner += '</tr>';
			inner += '<tr>';
			inner += '<th>메시지내용<span style="color: #fea338;">*</span></th>';
			inner += '<td colspan="3"><textarea class="markup" id="msgText" name="msgText" placeholder="메시지내용입력" onkeyup="onTextLimit()"></textarea><input type="text" id="msgSize" name="msgSize" style="display:none"><div id="textLimit" name="textLimit" style="margin:5px; float:right; font-size:12px; color:gray;"></div></td>';
			inner += '<td style="font-size:12px;text-align:left;vertical-align:top"><br>*매핑 파라메터<br><br>{#name} : 고객명<br>{#안내_URL} : 안내문확인 URL<br>{#수신거부_URL} : 수신거부URL</td>';
			inner += '</tr>';
			inner += '<tr>';
			inner += '<th>이미지</th>';
			inner += '<td><div id="saveDiv"><input type="text" id="saveFn" name="saveFn" readonly><button type="button" onclick="deleteFile()">삭제</button></div><input type="file" accept=".jpg, .jpeg, .gif, .sis" id="img" name="img" onchange="fileCheck()"><input type="hidden" id="imgSize" name="imgSize"></td>';
			inner += '<th>URL포함여부<span style="color: #fea338;">*</span></th>';
			inner += '<td colspan="2">';
			inner += '<div class="select_group">';
			inner += '<span><input type="radio" id="radio_yes1" name="urlYn" value="Y"><label for="radio_yes1"></label><label for="radio_yes1">예</label></span>';
			inner += '<span><input type="radio" id="radio_no1" name="urlYn" value="N" checked="checked"><label for="radio_no1"></label><label for="radio_no1">아니오</label></span>';
			inner += '</div>';
			inner += '</td>';
			inner += '</tr>';
			inner += '<tr>';
			inner += '<th>회신번호<span style="color: #fea338;">*</span></th>';
			inner += '<td><select id="srcCallNo" name="srcCallNo"></select></td>';
			inner += '<th>메시지발송구분<span style="color: #fea338;">*</span></th>';
			inner += '<td colspan="2"><select id="msgSndnGbn" name="msgSndnGbn"></select></td>';
			inner += '</tr>';
			
			$('#elctDocSndnDetl').append(inner);
			
			datepicker();
			commCd_bas('oganCd', '00001');
			commCd_bas('sndnCd', '00002');
			commCd_bas('msgSndnGbn', '00011');
			changeTmpltList(1); 
			changeSrcCallNo();
			onFileUpload();
			onTextLimit();
			getToday('transDt');
			getToday('sndnD');
			setHM();
			jQuery("#saveDiv").hide();
		}
		
		function changeMsgTmpltSelect(){
			var oganCd = document.getElementById("oganCd");
			var tmpltCd = document.getElementById("tmpltCd");
			
			var parameters = {
					oganCd : oganCd.options[oganCd.options.selectedIndex].value,
					tmpltCd : tmpltCd.options[tmpltCd.options.selectedIndex].value, 
			}
			
			
			if(tmpltCd.options[tmpltCd.options.selectedIndex].value != ''){
				$.ajax({
					type : "post",
					dataType : "json",
					async : false,
					url : "changeMsgTmpltSelect.ajax",
					data : parameters,
					success : function(data) {
						setElctDocSndnDetl();
						
						var msgTmplt = data.msgTmplt;
						
						if(msgTmplt != null){
							$('#oganCd').val(msgTmplt.oganCd).prop("selected", true);
							
							changeTmpltList(1);
							$('#tmpltCd').val(msgTmplt.tmpltCd).prop("selected", true);
							
							changeSrcCallNo();
							
							//이미지가 있을 경우
							if(!(msgTmplt.msgImg == '' || msgTmplt.msgImgSize == '' || msgTmplt.msgImgSize == '0')){
								$('#sndnCd').val('2').prop("selected", true);	
								$("#img").attr("disabled", false);
								
								jQuery("#saveDiv").show();
								$("#saveFn").val(msgTmplt.msgImg);
							}
							
							var length = calByte.getByteLength(msgTmplt.msgText);
							if(length > 88){
								$('#sndnCd').val('2').prop("selected", true);
								$("#img").attr("disabled", false);
							}
							
							if(msgTmplt.msgText.indexOf('{#수신거부_URL}') == -1 && msgTmplt.msgText.indexOf('{#안내_URL}') == -1){
								$('input[name=urlYn]:radio[value=N]').prop('checked', true);
							}
							else{
								$('input[name=urlYn]:radio[value=Y]').prop('checked', true);
							}
							
							
							$('#msgTitle').val(msgTmplt.msgTitle);
							$('#msgText').val(msgTmplt.msgText);
						}
					},
					complete : function(data) {
					}
				});	
			}
			else{
				setElctDocSndnDetl();
				$('#oganCd').val(oganCd.options[oganCd.options.selectedIndex].value).prop("selected", true);
				
				changeTmpltList(1);
			}
		}
		
		function onTextLimit(){
			var text = $('#msgText').val();
			var length = calByte.getByteLength(text);
			
			if($('#sndnCd').val() == '0'){
				$('#textLimit').text(length+'byte/88byte');
				
				if(length > 88){
					$('#msgText').val(calByte.cutByteLength(text, 88));
					$('#textLimit').text('88byte/88byte');
					alert('입력 가능 글자수를 초과하였습니다.');
				}
			}
			else if($('#sndnCd').val() == '1'){
				$('#textLimit').text(length+'byte/2000byte');
				
				if(length > 2000){
					$('#msgText').val(calByte.cutByteLength(text, 2000));
					$('#textLimit').text('2000byte/2000byte');
					alert('입력 가능 글자수를 초과하였습니다.');
				}
			}
			else if($('#sndnCd').val() == '2'){
				$('#textLimit').text('글자 제한 없음');
			}
		}
		
		function onFileUpload(){
			if($('#sndnCd').val() == '2'){
				$("#img").attr("disabled", false);
			}
			else{
				if($('#saveFn').val() == ''){
					$("#img").attr("disabled", true);
					$("#img").val('');	
					jQuery("#saveDiv").hide();
				}
				else{
					$("#sndnCd option:eq(2)").prop("selected", true);
					alert('현재 저장되어있는 파일을 삭제 후 변경해주세요.');
				}
			}
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
		
		//전자문서 발송 저장
		function elctDocSndnInsert(){
			if(reqValCheck('tmpltCd', '템플릿명')) return;
			if(reqValCheck('transDt', '파일송신일자')) return;
			if(reqValCheck('sndnD', '발송일시')) return;
			if(reqValCheck('sndnH', '발송일시')) return;
			if(reqValCheck('sndnM', '발송일시')) return;
			if(reqValCheck('msgTitle', '메시지 제목')) return;
			if(reqValCheck('msgText', '메시지 내용')) return;
			
			if(new Date($('#transDt').val()).getTime() > new Date($('#sndnD').val()).getTime()){
				alert("발송일시 값이 파일송신일자 값보다 작습니다. \n발송일시 값을 다시 설정해주세요.");
				$('#sndnD').focus();
				return;
			}
			
			if($('input[name=urlYn]:checked').val() == "Y"){
				var mt = $('#msgText').val();
				if(!mt.includes('{#안내_URL}') && !mt.includes('{#수신거부_URL}')){
					alert("메시지 내용에 {#안내_URL} 또는 {#수신거부_URL} 파라메터가 존제하지 않습니다. \nURL 포함여부를 'N'으로 변경하시거나, 하나 이상의 파라메터를 포함시켜주세요.");
					return;
				}
			}
			

			var file = document.getElementById("img").files[0];
			/*파일 이미지 사이즈 입력*/
			if(file){
				$("#imgSize").val(file.size);	
			} else{
				$("#imgSize").val(0);
			}
			
			$("#msgSize").val(calByte.getByteLength($('#msgText').val()));
			$("#oganCd").attr("disabled", false);
			
			var formData = new FormData($('#elctDocSndnFile')[0]); 
			$.ajax({
				type: "POST"
				,enctype: 'multipart/form-data'
				,url : "elctDocSndnInsert.ajax"
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
						$("#oganCd").attr("disabled", true);
						alert("저장되었습니다.");
						elctDocSndnListSelect();	
					}		  
				}			   
			});
		}
		
		//전자문서 발송 수정
		function elctDocSndnUpdate(){
			var file = document.getElementById("img").files[0];
			var fileSize = 1024*300; //300KB
			
			if(file && file.size > fileSize){
				alert("첨부파일 사이즈는 300KB 이내로 등록 가능합니다.");
				return;
			}	

			if(reqValCheck('sndnD', '발송일시')) return;
			if(reqValCheck('sndnH', '발송일시')) return;
			if(reqValCheck('sndnM', '발송일시')) return;
			if(reqValCheck('msgTitle', '메시지 제목')) return;
			if(reqValCheck('msgText', '메시지 내용')) return;
			
			if(new Date($('#transDt').val()).getTime() > new Date($('#sndnD').val()).getTime()){
				alert("발송일시 값이 파일송신일자 값보다 작습니다. \n발송일시 값을 다시 설정해주세요.");
				$('#sndnD').focus();
				return;
			}
			
			if($('input[name=urlYn]:checked').val() == "Y"){
				var mt = $('#msgText').val();
				if(!mt.includes('{#안내_URL}') && !mt.includes('{#수신거부_URL}')){
					alert("메시지 내용에 {#안내_URL} 또는 {#수신거부_URL} 파라메터가 존제하지 않습니다. \nURL 포함여부를 'N'으로 변경하시거나, 하나 이상의 파라메터를 포함시켜주세요.");
					return;
				}
			}
			
			$("#oganCd").attr("disabled", false);
			$("#transDt").attr("disabled", false);
			$("#tmpltCd").attr("disabled", false);
			
			/*파일 이미지 사이즈 입력*/
			if(file){
				$("#imgSize").val(file.size);	
			} else{
				$("#imgSize").val(0);
			}
			
			$("#msgSize").val(calByte.getByteLength($('#msgText').val()));
			
			var formData = new FormData($('#elctDocSndnFile')[0]); 
			$.ajax({
				type: "POST"
				,enctype: 'multipart/form-data'
				,url : "elctDocSndnUpdate.ajax"
				,data: formData
				,processData: false
				,contentType: false
				,cache: false
				,success: function (data) { 
					$("#oganCd").attr("disabled", true);
					$("#transDt").attr("disabled", true);
					$("#tmpltCd").attr("disabled", true);
						  
					if(data.errorMessage != ""){
						alert(data.errorMessage);
						
						if(data.errorMessage == "지원하는 이미지 파일 형식이 아닙니다."){
							$("#img").replaceWith($("#img").val('').clone(true));
							file = null;
						}
					}else{
						alert("수정되었습니다.");
						elctDocSndnListSelect();	
					}		  
				}			   
			});
		}
		
		//미리보기
		function elctDocSndnEx(oganCd, tmpltCd, transDt, sndnDtm){
			jQuery("#elctDocSndnEx").show();
			
			var parameters = {
					oganCd	: oganCd,
					tmpltCd : tmpltCd,
					transDt : transDt,
					sndnDtm : sndnDtm,
			};
			
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "elctDocSndnDetlSelect.ajax",
				data : parameters,
				success : function(data) {
					$('#elctDocSndnEx .contents-wrap').html('');
					
					if(data.sndnDetl.length != 0){
						var sndnDetl = data.sndnDetl;

						var title = '['+sndnDetl.oganNm+'] ' + sndnDetl.msgTitle;
						var Dtm = dtmSubstr(sndnDetl.sndnDtm);
						
						var msg = sndnDetl.msgText.replace(/(?:\r\n|\r|\n)/g, '<br />');
						
						var host = location.host;
						
						msg = msg.replace('{#안내_URL}', '<br>* 안내문확인하기:<br /><a href="javascript:void(0)" onclick="preventClick(event)">https://www.xxx.co.kr/al.do?a=nkdsnfndskfndskjnfkdsnskdn</a> (접속무료)<br>')
						msg = msg.replace('{#수신거부_URL}', '* 수신 거부/해제하기:<br /><a href="javascript:void(0)" onclick="preventClick(event)">https://www.xxx.co.kr/al.do?a=nkdsnfndskfndskjnfkdsnskdn</a> (접속무료)<br>')
						
						var inner = '';
						inner += '<h2>'+title+'</h2>';
						inner += '<div class="dateinfo">'+Dtm+'</div>';
						inner += '<div class="pnl" style="overflow-x:hidden; overflow-y:auto; height:400px;">'
						if(sndnDetl.mmsImg != ""){
							inner += '<div class="contents"><img src="/image/file/'+sndnDetl.mmsImg +'" width="220" height="auto"><br>'+msg+'</div>';	
						}
						else{
							inner += '<div class="contents">'+msg+'</div>';	
						}
						inner += '</div>'
						
						$('#elctDocSndnEx .contents-wrap').append(inner);
					}
				},
				complete : function(data) {
				}
			});
		}
		
		function preventClick(e){
			e.preventDefalut();
		}
		
		function deleteFile(){
			$('#saveFn').val('');
			alert('삭제되었습니다.');
		}
		
		function dtmSubstr(dtm){
			var MM = dtm.substring(4, 6);
			var DD = dtm.substring(6, 8);
			var hh = dtm.substring(8, 10);
			var mm = dtm.substring(10, 12);
			
			var t = '오전';
			if(Number(hh) > 11){
				hh = hh - 12;
				t = '오후';
				
				if(hh < 10){
					hh = '0' + hh;
				}
			}
			
			if(hh == "00") hh = "12";
				
			return MM + '월 ' + DD + '일, ' + t + ' ' + hh + ':' + mm;
		}
		
		function callBack(sel_id, codeNum){
			if(sel_id == "#iOganCd") {
				if($('#athLev').val() == "00"){
					$(sel_id).prepend('<option value="" selected="true">전체</option>');	
				}
				
				if($('#athLev').val() == "01"){
					$("#iOganCd").prop("disabled", "disabled");
				}
				
				var value = '${iOganCd}';
				
				if(value == "") return;
				
				var size = $("#iOganCd option").size();
				for(var i = 0; i < size; i++){
					var selector = $("#iOganCd option:eq("+ i +")");
					if(selector.val() == value){
						selector.attr("selected", "selected");
					}
				}
				
			}
			
			if(sel_id == "#oganCd") {
				if($('#athLev').val() == "01"){
					$("#oganCd").prop("disabled", "disabled");
				}
				
				var value = '${oganCd}';
				
				if(value == "") return;
				
				var size = $("#oganCd option").size();
				for(var i = 0; i < size; i++){
					var selector = $("#oganCd option:eq("+ i +")");
					if(selector.val() == value){
						selector.attr("selected", "selected");
					}
				}
			}
			
			if(sel_id == "#iSndnCd") {
				$(sel_id).prepend('<option value="" selected="true">전체</option>');
			
				var value = '${iSndnCd}';
		
				if(value == "") return;
				
				var size = $("#iSndnCd option").size();
				for(var i = 0; i < size; i++){
					var selector = $("#iSndnCd option:eq("+ i +")");
					if(selector.val() == value){
						selector.attr("selected", "selected");
					}
				}
			}
			
			if(sel_id == "#iTmpltCd") {
				var value = '${iTmpltCd}';
		
				if(value == "") return;
				
				var size = $("#iTmpltCd option").size();
				for(var i = 0; i < size; i++){
					var selector = $("#iTmpltCd option:eq("+ i +")");
					if(selector.val() == parseInt(value)){
						selector.attr("selected", "selected");
					}
				}
			}
		}
	</script>
</head>
<body>

<input type="hidden" id="athLev" name="athLev" value="${athLev}"/> 
	<!-- wrap -->
	<div id="wrap">
		<!-- contents -->
		<div id="contents">
			<!-- section -->
			<div id="section">
				<!-- caption-pnl -->
				<form id="elctDocSndn">
				<div class="caption-pnl">
					<h2>전자문서 발송</h2>
					<div class="buttonset">
                        <button type="button" class="state-highlight" id="btnIns" onclick="elctDocSndnInsert()" disabled="false">저장</button>
                        <button type="button" id="btnUpd" onclick="elctDocSndnUpdate()" disabled="false">수정</button>
                    </div>
				</div>
				<!-- //caption-pnl -->
				<!-- search-pnl -->
				<div class="search-pnl">
					<div class="buttonset">
					</div>
					<table>
						<colgroup>
							<col width="140px"/>
							<col width="*"/>
							<col width="140px"/>
							<col width="*"/>
							<col width="140px"/>
							<col width="200px"/>
							<col width="200px"/>
						</colgroup>
						<tr>
							<th>요청일자</th>
							<td><input type="text" size="13" class="dateinput" id="iSndnStDt" name="iSndnStDt" value="${iSndnStDt}" onkeypress="dateFormat(this)" onKeyDown="enterSerch()"> ~ <input type="text" size="13" class="dateinput" id="iSndnClosDt" name="iSndnClosDt" value="${iSndnClosDt}" onkeypress="dateFormat(this)" onKeyDown="enterSerch()"></td>
							<th>메시지제목</th>
							<td><input type="text" size="13" id="iMsgTitle" name="iMsgTitle" value="${iMsgTitle}" onKeyDown="enterSerch()"></td>
							<th>발송유형</th>
							<td><select id="iSndnCd" name="iSndnCd"></select></td>
							<td class="buttonset" rowspan="2"><button type="button" onclick="elctDocSndnListSelect()">조회</button></td>
						</tr>
						<tr>
							<th>기관명</th>
							<td><select id="iOganCd" name="iOganCd" onchange="changeTmpltList(0)"></select></td>
							<th>템플릿명</th>
							<td><select id="iTmpltCd" name="iTmpltCd"></select></td>
							<th></th>
							<td></td>
						</tr>	
					</table>
				</div>
				<!-- //search-pnl -->
				<!-- grid-pnl -->
				<div class="grid-pnl" style="overflow-x:hidden; overflow-y:auto; height:300px;">
					<table class="align-center">
						<colgroup>
                            <col width="10%">
                            <col width="10%">
                            <col width="10%">
                            <col width="8%">
                            <col width="*">
                            <col width="*">
                            <col width="10%">
                            <col width="8%">
                            <col width="10%">
                        </colgroup>
						<thead>
							<tr>
								<th scope="col">요청일자</th>
								<th scope="col">발송일시</th>
								<th scope="col">발송 기관(회사)명</th>
								<th scope="col">발송유형</th>
								<th scope="col">템플릿명</th>
								<th scope="col">메시지제목</th>
								<th scope="col">회신번호</th>
								<th scope="col">미리보기</th>
								<th scope="col">승인여부</th>
							</tr>
						</thead>
						<tbody id="elctDocSndnlist">
							<c:forEach var="elctDocSndnlist" items="${elctDocSndnlist}">
								<tr>
									<td style="cursor:pointer;" onclick="elctDocSndnDetlSelect('${elctDocSndnlist.oganCd}','${elctDocSndnlist.tmpltCd}','${elctDocSndnlist.transDt}','${elctDocSndnlist.sndnDtm}','${elctDocSndnlist.aprYn}')">${elctDocSndnlist.transDt}</td>
									<td style="cursor:pointer;" onclick="elctDocSndnDetlSelect('${elctDocSndnlist.oganCd}','${elctDocSndnlist.tmpltCd}','${elctDocSndnlist.transDt}','${elctDocSndnlist.sndnDtm}','${elctDocSndnlist.aprYn}')">${elctDocSndnlist.sndnD}</td>
									<td style="cursor:pointer;" onclick="elctDocSndnDetlSelect('${elctDocSndnlist.oganCd}','${elctDocSndnlist.tmpltCd}','${elctDocSndnlist.transDt}','${elctDocSndnlist.sndnDtm}','${elctDocSndnlist.aprYn}')">${elctDocSndnlist.oganNm}</td>
									<td style="cursor:pointer;" onclick="elctDocSndnDetlSelect('${elctDocSndnlist.oganCd}','${elctDocSndnlist.tmpltCd}','${elctDocSndnlist.transDt}','${elctDocSndnlist.sndnDtm}','${elctDocSndnlist.aprYn}')">${elctDocSndnlist.sndnCd}</td>
									<td style="cursor:pointer;" onclick="elctDocSndnDetlSelect('${elctDocSndnlist.oganCd}','${elctDocSndnlist.tmpltCd}','${elctDocSndnlist.transDt}','${elctDocSndnlist.sndnDtm}','${elctDocSndnlist.aprYn}')">${elctDocSndnlist.tmpltNm}</td>
									<td style="cursor:pointer;" onclick="elctDocSndnDetlSelect('${elctDocSndnlist.oganCd}','${elctDocSndnlist.tmpltCd}','${elctDocSndnlist.transDt}','${elctDocSndnlist.sndnDtm}','${elctDocSndnlist.aprYn}')">${elctDocSndnlist.msgTitle}</td>
									<td style="cursor:pointer;" onclick="elctDocSndnDetlSelect('${elctDocSndnlist.oganCd}','${elctDocSndnlist.tmpltCd}','${elctDocSndnlist.transDt}','${elctDocSndnlist.sndnDtm}','${elctDocSndnlist.aprYn}')">${elctDocSndnlist.srcCallNo}</td>
									<td><button type="button" class="icon-search" onclick="elctDocSndnEx('${elctDocSndnlist.oganCd}','${elctDocSndnlist.tmpltCd}','${elctDocSndnlist.transDt}','${elctDocSndnlist.sndnDtm}')"></button></td>
									<c:if test="${elctDocSndnlist.aprYn.equals('Y')}">
									<td><button type="button" onclick="elctDocSndnAprYnUpdate('${elctDocSndnlist.oganCd}','${elctDocSndnlist.tmpltCd}','${elctDocSndnlist.transDt}','${elctDocSndnlist.sndnDtm}','N')">승인</button></td>
									</c:if>
									<c:if test="${elctDocSndnlist.aprYn.equals('N')}">
									<td><button type="button" onclick="elctDocSndnAprYnUpdate('${elctDocSndnlist.oganCd}','${elctDocSndnlist.tmpltCd}','${elctDocSndnlist.transDt}','${elctDocSndnlist.sndnDtm}','Y')">미승인</button></td>
									</c:if>
									<c:if test="${elctDocSndnlist.aprYn.equals('F')}">
									<td style="color:red">발송완료</td>
									</c:if>
									<c:if test="${elctDocSndnlist.aprYn.equals('D')}">
									<td>발송진행중</td>
									</c:if>
								</tr>
							</c:forEach>
			        	</tbody>
					</table>
					<!-- //table -->
				</div>
				<!-- //grid-pnl -->
				<!-- caption-pnl -->
            	<div class="caption-pnl">
                	<h3>발송상세내역</h3>
					<div class="buttonset">
					<button type="button" onclick="elctDocSndnDetlInit()">초기화</button>
					</div>
                </div>
                <!-- //caption-pnl -->
				<!-- table-pnl -->
				</form>
				<form id="elctDocSndnFile">	
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
						<tbody id="elctDocSndnDetl">
                   	    </tbody>
					</table>
                    <!-- //table -->
               </div>
               </form>
               <!-- //table-pnl -->
			</div>
			<!-- //section -->
		</div>
		<!-- //contents -->
	</div>
	
	<!-- //wrap -->
    <div id="elctDocSndnEx" class="layerBox" style="display:none;">
		<div class="layer">
			<div class="topBox">
				<h3>미리보기</h3>
				<a class="close" href="#" onclick="closePopu()">&#10005;</a>
			</div>
			<!--Container-->
			<div id="container" class="mb-container">
				<div class="contents-wrap">
				</div>
			</div>
			<!--//(e)Container-->
		</div>
		<div class="fade"></div>
	</div>

</body>
</html>