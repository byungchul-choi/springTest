package tpost.batch.controller;

import java.util.HashMap;
import java.text.SimpleDateFormat;
import org.apache.log4j.Logger;

/**
 * 폴링으로 결과확인요청 전송샘플 (단일건 상태조회)
 */

public class PollingV1Demo_status {

//	final static Logger logger = Logger.getLogger(PollingV1Demo.class);
	final static Logger logger = Logger.getLogger(PollingV1Demo_status.class);
	
	public static void main(String[] args) throws Exception {

		// 전송요청 관련
		String    txId = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; // 폴링할 tx_id를 적어주세요
        String    status = "";
//        KakaoSend kakaoSend = new KakaoSend();
		
		// 현재 상태값 확인 
//        RecvData resp = kakaoSend.send("/api/v1/sign/status?tx_id=" + txId,"GET",null);
//		if (resp.errcode == null) {
//	        HashMap data = (HashMap) resp.data;
//			logger.info("현재 상태값:" + (String) data.get("status"));
//			logger.info("접수시각:" + timeStampToString((Long)(data.get("request_at"))));
//		} else {
//			logger.info("상태조회중 카카오 에러:" + resp.errcode + " " + resp.errmsg);
//		}

	}
	// timestamp변환
	public static String timeStampToString(long timestamp) {
		java.util.Date date = new java.util.Date(timestamp);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return sdf.format(date);
	}
	
}
