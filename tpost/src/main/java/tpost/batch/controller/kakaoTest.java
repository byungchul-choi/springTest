package tpost.batch.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tpost.batch.service.kakaoTestService;
import tpost.batch.vo.kakaoTestVO;

@Component
@Controller
@RequestMapping(value = "/batch/fileTest")
public class kakaoTest{

	@Autowired
	kakaoTestService kakaoTestService;
	
	/*로그설정 입니다. */
	Logger logger = (Logger) LogManager.getLogger(kakaoTest.class);

	 /*파일 배치 테스트 입니다.  */
	 @ResponseBody
	 @RequestMapping(value = "/kakaoSend", method = RequestMethod.POST)
	 public Map kakaoSendTest(Model model , @ModelAttribute kakaoTestVO kakaoTestVO ) {
		 logger.debug("kakaoSend Start");
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		 
		    String    uri  = "https://gw-cert.kakao.com/api/sign/request/S510"; 
			String    accToken = "CC1A5FC67E94E73F69630673A76873ECFD7EC14386B448983B4D65D79E4D99FD";
			String param = "a=Pb310010B202Grc000p2i005rN91";
			
			RecvData  recvData;

			// 전송DATA 작성 
			Map<String, Object> req = new HashMap<String, Object>();
//	        req.put("phone_no", "01099262528");        // 폰번호를 적어주세요 
//	        req.put("name"    , "최병철");               // 성명을 적어주세요
//	        req.put("birthday", "830830");            // 생일을 적어주세요
			
			String url = "안녕하세요 최주영님 국민건강보험공단입니다.\r\n" + 
					"귀하의 건강보험 가입내역 안내를 문자메시지로 보내 드립니다.\r\n" + 
					"\r\n" + 
					"□ 열람기간 : 발송일로부터 90일 이내\r\n" + 
					"□ 문의전화 : 공단 상담센터 1577-1000 \r\n" + 
					"\r\n" + 
					"아래 '안내문 확인하기 URL'을 누른 후 본인인증을 하시면 안내문을 확인하실 수 있습니다.\r\n" + 
					"\r\n" + 
					" 안내문확인하기 : http://10.10.19.64:8080/tpost/msgRcve/msgRcveGdc?a=Uyb100aSs202Fkk000YG8005a2p1\r\n" + 
					" 수신거부/해제하기 : http://10.10.19.64:8080/tpost/msgRcve/msgRcveRfslDsms?a=JyI100BOh202Yom000M1v005BRa1" ;
			
			req.put("ci", "TJxR5fzbt9mETMSe+XKqsLwM4d9VPCjy6o0eVjRW/uSoQOBp82V1VF8ISXG6OmVsClhbwI7rtJMf3qlK6d22Jg==");               // 서명 제한시간(초)
	        req.put("expires_in", 21600);               // 서명 제한시간(초)
	        req.put("call_center_no", "020000000");     // 콜센타번호를 적어주세요 
	        req.put("title", "웹연계인증 테스트"); // 타이틀을 적어주세요  (39자 까지 한글도 1문자)
	        req.put("message", url);// 추가 메시지를 적어주세요 (아래내용을 \n 확인해 주세요)
	        req.put("allow_simple_registration", "N");  // 간편등록회원 허용여부(Y/N선택)
	        req.put("verify_auth_name", "N");           // 고객성명 체크여부(Y/N선택)
	        req.put("redirect_url", "http://10.10.19.64:8080/tpost/batch/fileTest/kakaoResult?"+ param); // 최종 Redirect될 URL을 적어주세요
			req.put("publish_certified_electronic_doc","N"); // 공인전자문서 유통정보 등록 여부(Y/N선택)

			kakaoTestVO.setKakaoGubun("00"); 
			kakaoTestVO.setCelpNum(""+ req.get("phone_no"));
			kakaoTestVO.setName(""+ req.get("name"));
			kakaoTestVO.setSsnoFronBrdt(""+ req.get("birthday"));
			kakaoTestVO.setSendUrl(""+ req.get("redirect_url"));
			
			kakaoTestService.kakaoTestInsert(kakaoTestVO);
			
			// 전송 및 결과확인
	        logger.info("S510 전송요청---->");
	        
			try {
				
				KakaoHttpUtils utils = new KakaoHttpUtils();
		
				HttpResponse response = utils.sendToKakao(uri, KakaoHttpMethod.POST, accToken, req);
				
		        if( utils.getHttpStatusCd(response) == HttpStatus.SC_OK )
		        {
		        	 recvData = utils.getRecvData(response);
		        	 
		        	 if( "".equals(utils.getResponseCd(recvData)))
		        	 {
		        		 HashMap data = (HashMap) utils.getResponseData(recvData);

		        		 if (data != null && "Y".equals(data.get("result"))) {
		        			logger.info("전송성공 -- tx_id:" + data.get("tx_id"));
							logger.info("전송성공 -- token:" + data.get("token"));
							
							 resultMap.put("tx_id", data.get("tx_id"));
							 resultMap.put("token", data.get("token"));
							 resultMap.put("errmsg", "전송성공.");
					
							 kakaoTestVO.setKakaoGubun("01"); 
							 kakaoTestVO.setCelpNum(""+ req.get("phone_no"));
							 kakaoTestVO.setName(""+ req.get("name"));
							 kakaoTestVO.setSsnoFronBrdt(""+ req.get("birthday"));
							 kakaoTestVO.setSendUrl(""+ req.get("redirect_url"));
							 kakaoTestVO.setTxId(""+ data.get("tx_id"));
							 
							 kakaoTestService.kakaoTestInsert(kakaoTestVO);
		        	    } else {
			           		 switch( utils.getResponseCd(recvData))
			           		 {
			           		 	case KaKaoResponseCode.E300 :
			           		 		break;
			           		 	case KaKaoResponseCode.E400 :;
			           		 	    break;
			           		 	default:
			           		 }
			           		logger.warn("전송실패 -- errcode:" + recvData.getErrcode() + " " + recvData.getErrmsg());
							resultMap.put("errmsg", "전송실패.");
		        	    }
		        	 }else {
		        		logger.warn("전송실패 -- errcode:" + recvData.getErrcode() + " " + recvData.getErrmsg());
						resultMap.put("errmsg", "전송실패.");
		        	 }
		        }else {
		        	
		        }
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		 return resultMap;
	 }
	 
	 /*파일 배치 테스트 입니다.  */
	 @RequestMapping(value = "/kakaoResult")
	 public String kakaoResultTest(Model model , @ModelAttribute kakaoTestVO kakaoTestVO ) {
		 
		 logger.debug("kakaoResult Start");
		 logger.debug("kakaoResult getTx_id ==> " +  kakaoTestVO.getTx_id());
		 logger.debug("kakaoResult getToken ==> " + kakaoTestVO.getToken());
		 logger.debug("kakaoResult a ==> " + kakaoTestVO.getA());
		 logger.debug("kakaoResult b ==> " + kakaoTestVO.getB());

		String    uri  = "https://gw-cert.kakao.com/api/sign/token/status"; 
		String    accToken = "CC1A5FC67E94E73F69630673A76873ECFD7EC14386B448983B4D65D79E4D99FD";
		RecvData  recvData;
			
		 Map<String, Object> resultMap = new HashMap<String, Object>();
			
			// 전송DATA 작성 
			Map<String, Object> req = new HashMap<String, Object>();
			req.put("tx_id", kakaoTestVO.getTx_id());   // 사용자휴대폰에서 받은 tx_id
			req.put("token", kakaoTestVO.getToken());	// 사용자휴대폰에서 받은 1회용토큰
	        
			logger.debug("tx_id ==> " + kakaoTestVO.getTx_id());
			logger.debug("token ==> " + kakaoTestVO.getToken());
			
			kakaoTestVO.setKakaoGubun("02"); 
			kakaoTestVO.setTxId(""+ req.get("tx_id"));
			kakaoTestVO.setToken(""+ req.get("token"));
			
			kakaoTestService.kakaoTestInsert(kakaoTestVO);
			
	        // 전송 및 결과확인
			logger.info("S510 결과체크 시작---->");
	        
			try {
				KakaoHttpUtils utils = new KakaoHttpUtils();
				HttpResponse response = utils.sendToKakao(uri, KakaoHttpMethod.POST, accToken, req);
				
		        if( utils.getHttpStatusCd(response) == HttpStatus.SC_OK )
		        {
		        	 recvData = utils.getRecvData(response);
		        	 
		        	 if( "".equals(utils.getResponseCd(recvData)))
		        	 {
		        		 HashMap data = (HashMap) utils.getResponseData(recvData);

		        		 if (data != null && "Y".equals(data.get("result"))) {
		        			logger.info("전송성공 -- tx_id:" + data.get("tx_id"));
							logger.info("전송성공 -- token:" + data.get("token"));
							
							 resultMap.put("tx_id", data.get("tx_id"));
							 resultMap.put("token", data.get("token"));
							 resultMap.put("errmsg", "전송성공.");
					
							 kakaoTestVO.setKakaoGubun("01"); 
							 kakaoTestVO.setCelpNum(""+ req.get("phone_no"));
							 kakaoTestVO.setName(""+ req.get("name"));
							 kakaoTestVO.setSsnoFronBrdt(""+ req.get("birthday"));
							 kakaoTestVO.setSendUrl(""+ req.get("redirect_url"));
							 
							 kakaoTestService.kakaoTestInsert(kakaoTestVO);
		        	    } else {
			           		 switch( utils.getResponseCd(recvData))
			           		 {
			           		 	case KaKaoResponseCode.E300 :
			           		 		break;
			           		 	case KaKaoResponseCode.E400 :;
			           		 	    break;
			           		 	default:
			           		 }
			           		logger.warn("전송실패 -- errcode:" + recvData.getErrcode() + " " + recvData.getErrmsg());
							resultMap.put("errmsg", "전송실패.");
		        	    }
		        	 }else {
		        		logger.warn("전송실패 -- errcode:" + recvData.getErrcode() + " " + recvData.getErrmsg());
						resultMap.put("errmsg", "전송실패.");
		        	 }
		        }else {
		        	
		        }
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		 
			model.addAttribute("a", kakaoTestVO.getA());
			model.addAttribute("b", kakaoTestVO.getB());
			
		return "msgRcve/msgRcveGdc";
	 }
	 

}