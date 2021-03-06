package tpost.batch.batchSttcInfo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpost.batch.batchSttcInfo.dao.sttcInfoBatDao;
import tpost.batch.batchSttcInfo.vo.sttcInfoBatVO;



@Service
public class sttcInfoBatService {

	@Autowired
	sttcInfoBatDao dao;
	
	/* 로그설정 */
	Logger log = (Logger) LogManager.getLogger(sttcInfoBatService.class);
	
	@Transactional
	public void sttcInfoBatIns(sttcInfoBatVO sttcInfoBatVO) {
		
		log.debug("sttcInfoBatIns start");
		
		
		List<sttcInfoBatVO> sttcInfoBatList = new ArrayList<sttcInfoBatVO>();
		/*통계정보생성을 위한 모바일 성공 메세지 합쿼리*/
//		dao.sndnSuccMsgSel(sttcInfoBatVO);
		log.debug("1");
		sttcInfoBatList.addAll( dao.sndnSuccMsgSel(sttcInfoBatVO));
		log.debug("2");

		sttcInfoBatVO sttcInfoBat = null;
		for(int i = 0; i < sttcInfoBatList.size() ; i++) {
			sttcInfoBat = new sttcInfoBatVO();
			sttcInfoBat.setTransDt(sttcInfoBatList.get(i).getTransDt());
			sttcInfoBat.setOganCd(sttcInfoBatList.get(i).getOganCd());
			sttcInfoBat.setTmpltCd(sttcInfoBatList.get(i).getTmpltCd());
			sttcInfoBat.setSuccCnt(sttcInfoBatList.get(i).getSuccCnt());
			sttcInfoBat.setSndnCd(sttcInfoBatList.get(i).getSndnCd());
			
			dao.sndnSuccMsgIns(sttcInfoBat);
		}
		
		/*통계정보생성을 위한 모바일 실패 메세지 합쿼리*/
		List<sttcInfoBatVO> sttcInfoBatList1 = new ArrayList<sttcInfoBatVO>();
		sttcInfoBatList1.addAll( dao.sndnFailMsgSel(sttcInfoBatVO));
		for(int i = 0; i < sttcInfoBatList1.size() ; i++) {
			sttcInfoBat = new sttcInfoBatVO();
			sttcInfoBat.setTransDt(sttcInfoBatList1.get(i).getTransDt());
			sttcInfoBat.setOganCd(sttcInfoBatList1.get(i).getOganCd());
			sttcInfoBat.setTmpltCd(sttcInfoBatList.get(i).getTmpltCd());
			sttcInfoBat.setFailCnt(sttcInfoBatList1.get(i).getFailCnt());
			sttcInfoBat.setSndnCd(sttcInfoBatList.get(i).getSndnCd());
			dao.sndnFailMsgIns(sttcInfoBat);
		}
		
		
		/*통계정보생성을 위한 모바일 열람건수 합쿼리*/
		List<sttcInfoBatVO> sttcInfoBatList2 = new ArrayList<sttcInfoBatVO>();
		sttcInfoBatList2.addAll( dao.sndnRdngSel(sttcInfoBatVO));
		for(int i = 0; i < sttcInfoBatList2.size() ; i++) {
			sttcInfoBat = new sttcInfoBatVO();
			sttcInfoBat.setTransDt(sttcInfoBatList2.get(i).getTransDt());
			sttcInfoBat.setOganCd(sttcInfoBatList2.get(i).getOganCd());
			sttcInfoBat.setTmpltCd(sttcInfoBatList2.get(i).getTmpltCd());
			sttcInfoBat.setSuccCnt(sttcInfoBatList.get(i).getSndnCd());
			sttcInfoBat.setSndnCd(sttcInfoBatList.get(i).getSndnCd());
			
			dao.sndnRdngIns(sttcInfoBat);
		}
		
		
	}
	
	
	
	
}
