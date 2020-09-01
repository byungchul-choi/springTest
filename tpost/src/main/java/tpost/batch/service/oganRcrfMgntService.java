package tpost.batch.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpost.batch.dao.oganRcrfMgntDao;
import tpost.batch.vo.oganRcrfMgntVO;
import tpost.commCode.controller.commCdMgntController;
import tpost.egovcomm.EgovUserDetailsHelper;

@Service
public class oganRcrfMgntService {

	@Autowired
	oganRcrfMgntDao dao;
	
	/* 로그설정 */
	Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);
	
	@Transactional
	public void oganRcrfMgnt(oganRcrfMgntVO oganRcrfMgntVO) {
		//발송 리스트
		List<oganRcrfMgntVO> allOganRcrfList = moblMsgLstSelect(oganRcrfMgntVO);		
		
		if(allOganRcrfList.size() != 0) {
			List<String> oganList = new ArrayList();		
			oganList.add(allOganRcrfList.get(0).getOganCd());
			for(int i = 0; i < allOganRcrfList.size(); i++) {
				if(!oganList.get(oganList.size() - 1).equals(allOganRcrfList.get(i).getOganCd()))
					oganList.add(allOganRcrfList.get(i).getOganCd());
			}
			
			//인서트 될 리스트
			List<oganRcrfMgntVO> insertList = new ArrayList<oganRcrfMgntVO>();
			
			//업데이트도 인서트도 안되는 리스트
			List<oganRcrfMgntVO> delList = new ArrayList<oganRcrfMgntVO>();
			
			for(int i = 0; i < oganList.size(); i++) {
				oganRcrfMgntVO tempVO = new oganRcrfMgntVO();
				tempVO.setOganCd(oganList.get(i));
				
				//기관별수신/거부관리에 등록되어있는 리스트
				List<oganRcrfMgntVO> oganRcveRfList = oganRcveRfListSelect(tempVO);	

				for(int j = 0; j < oganRcveRfList.size(); j++) {
					for(int n = 0; n < allOganRcrfList.size(); n++) {
						if(oganRcveRfList.get(j).getOganCd().equals(allOganRcrfList.get(n).getOganCd())
						&& oganRcveRfList.get(j).getOfapElctAddr().equals(allOganRcrfList.get(n).getOfapElctAddr())) {
							delList.add(allOganRcrfList.get(n));
							
							if(oganRcveRfList.get(j).getBfcsSndnYn().equals("N")) {
								oganRcrfMgntVO updateVO = new oganRcrfMgntVO();
								updateVO.setOganCd(allOganRcrfList.get(n).getOganCd());
								updateVO.setOfapElctAddr(allOganRcrfList.get(n).getOfapElctAddr());
								updateVO.setAmdr("batch");
								
								dao.oganRcveRfUpdate(updateVO);
							}
						}
					}
				}
			}
			
			insertList.addAll(allOganRcrfList);
			insertList.removeAll(delList);
			
			if(insertList.size() > 0) {
				List<Map<String, Object>> iList = new ArrayList<Map<String, Object>>();
				for(int i = 0; i < insertList.size(); i++) {
					Map<String, Object> temp = new HashMap<String, Object>();
					temp.put("oganCd", insertList.get(i).getOganCd());
					temp.put("ofapElctAddr", insertList.get(i).getOfapElctAddr());
					temp.put("crtr", "batch");
					temp.put("amdr", "batch");
					iList.add(temp);
				}
				
				Map<String, Object> iMap = new HashMap<String, Object>();
				iMap.put("iList", iList);

				dao.oganRcveRfInsert(iMap);
				dao.oganRcveRfDtlInsert(iMap);
			}
		}
	}
	
	public List<oganRcrfMgntVO> moblMsgLstSelect(oganRcrfMgntVO oganRcrfMgntVO){
		return dao.moblMsgLstSelect(oganRcrfMgntVO);
	}
	 
	public List<oganRcrfMgntVO> oganRcveRfListSelect(oganRcrfMgntVO oganRcrfMgntVO){
		return dao.oganRcveRfListSelect(oganRcrfMgntVO);
	}
}
