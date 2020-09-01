package tpost.batch.batchMoblSndnMsg.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpost.batch.batchMoblSndnMsg.dao.batMoblSndnMsgDao;
import tpost.batch.batchMoblSndnMsg.vo.batMoblSndnMsgVO;
import tpost.batch.batchMoblSndnMsg.vo.tbMoblSndnMsgVO;


@Service
public class batMoblSndnMsgService {

  @Autowired
  batMoblSndnMsgDao	 dao;
  
  Logger log = (Logger) LogManager.getLogger(batMoblSndnMsgService.class);

  
	public void tbMoblSndnMsg_upload(batMoblSndnMsgVO batMoblSndnMsgVO) {
		// TODO Auto-generated method stub
//		dao.tbMoblSndnMsg_upload(transDt); /*한방쿼리 */
		 
		/*건바이건 인서트로 전환 이미지 생성때문*/
		/*step 1 발송목록 가져오기*/
		List<tbMoblSndnMsgVO> sendList = dao.tbMoblSndnMsgSendList(batMoblSndnMsgVO.getExeDt() ); 
		
		/*step 2 발송목록 가져온건에대해 이미지 경로 매핑 */
		String oranNm = "";
		for(int i = 0 ; i < sendList.size() ; i ++ ) {
			if("100".equals(sendList.get(i).getSvcOrgCd())) {
				oranNm = "nhis";
			}

			/*이미지파일 만드는 부분입니다. */
//			String chk = makeImage.makeImage(sendList.get(i).getName(), sendList.get(i).getMmsImg() , oranNm);
			/*
			if(!chk.equals("FAIL")) {
				sendList.get(i).setMmsImg (chk);      
				sendList.get(i).setMmsImgSize((long) chk.length());      
			}
			*/
			
		}
		
		
		/*step 3 발송목록 테이블에 insert */
		List<Map<String, Object>> tempList = new ArrayList<Map<String, Object>>();
		for(int i = 0; i < sendList.size(); i++) {
			
			Map<String, Object> temp = new HashMap<String, Object>();
				
			temp.put("transDt"      	, sendList.get(i).getTransDt      ());
			temp.put("svcOrgCd"     	, sendList.get(i).getSvcOrgCd     ());
			temp.put("seqNo"        	, sendList.get(i).getSeqNo        ());
			temp.put("dataGbnCd"    	, sendList.get(i).getDataGbnCd    ());
			temp.put("ipinCi"       	, sendList.get(i).getIpinCi       ());
			temp.put("svcOrgNm"     	, sendList.get(i).getSvcOrgNm     ());
			temp.put("msgType"      	, sendList.get(i).getMsgType      ());
			temp.put("msgInfo"      	, sendList.get(i).getMsgInfo      ());
			temp.put("msgSize"      	, sendList.get(i).getMsgSize      ());
			temp.put("msgText"      	, sendList.get(i).getMsgText      ());
			temp.put("mmsImgSize"   	, sendList.get(i).getMmsImgSize   ());
			temp.put("mmsImg"       	, sendList.get(i).getMmsImg       ());
			temp.put("srcCallNo"    	, sendList.get(i).getSrcCallNo    ());
			temp.put("cnForm"          	, sendList.get(i).getCnForm       ());
			temp.put("msgTitle"        	, sendList.get(i).getMsgTitle     ());
			temp.put("balsongGbn"      	, sendList.get(i).getBalsongGbn     ());
			temp.put("multiMblPrcGbn"  	, sendList.get(i).getMultiMblPrcGbn ());
			temp.put("cellNoListSize"  	, sendList.get(i).getCellNoListSize ());
			temp.put("cellNoList"		, sendList.get(i).getCellNoList     ());
			temp.put("sndnDtm"			, sendList.get(i).getSndnDtm        ());
			temp.put("sndnVldDtm"		, sendList.get(i).getSndnVldDtm     ());
			temp.put("sndnProcCl"		, sendList.get(i).getSndnProcCl     ());
			temp.put("tmpltCd"			, sendList.get(i).getTmpltCd        ());
			temp.put("anocInfoUrl"		, sendList.get(i).getAnocInfoUrl        ());
			temp.put("rcveRfslUrl"		, sendList.get(i).getRcveRfslUrl        ());
			temp.put("ofapElctAddr"		, sendList.get(i).getOfapElctAddr        ());
				
			tempList.add(temp); 
		
		}
		if(tempList.size() > 0) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("tempList", tempList);
		
			dao.tbMoblSndnMsgtInsert(paramMap);
		}
		
		
	}
}
	
	



	 
