package tpost.batch.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tpost.batch.vo.moblSndnBulkInsVO;
import tpost.commCode.controller.commCdMgntController;

@Repository
public class moblSndnBulkInsDao {
	
  @Autowired
  SqlSession sqlSessionTemplate;

  /*로그설정 입니다. */
	Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);

	public void moblSndnBulkIns(moblSndnBulkInsVO moblSndnBulkInsVO) {
		// TODO Auto-generated method stub
		log.debug("moblSndnBulkIns ==> " );
		
		
		  /*step 3 발송목록 테이블에 insert */
		 List<Map<String, Object>> tempList = new ArrayList<Map<String, Object>>();
		 Map<String, Object> paramMap = new HashMap<String, Object>();
/*			
		  for (int i =0 ; i < moblSndnBulkInsVO.getCnt() ; i++ ) {
			  
			  Map<String, Object> temp = new HashMap<String, Object>();
			  temp.put("transDt"      	, moblSndnBulkInsVO.getTransDt      ());
			  temp.put("sndnDtm"		, moblSndnBulkInsVO.getSndnDtm        ());
			  temp.put("sndnVldDtm"		, moblSndnBulkInsVO.getSndnVldDtm     ());
				
			  
			  tempList.add(temp); 

			  if( i >0 && i% 500 == 0) {
				  log.debug("moblSndnBulkIns set==> " + i );
				  log.debug("tempList.size() set==> " + tempList.size() );
				  paramMap.put("tempList", tempList);
				  sqlSessionTemplate.insert("tpost.batch.dao.moblSndnBulkIns", paramMap);
				  
				  paramMap.clear();
				  temp.clear();
				  tempList.clear();
			  }
			 
		  }
		  if(tempList.size() > 0) {
			  log.debug("tempList.size() set==> " + tempList.size() );
				paramMap.put("tempList", tempList);
				sqlSessionTemplate.insert("tpost.batch.dao.moblSndnBulkIns", paramMap);
			}
			
*/		for (int i= 0 ; i<moblSndnBulkInsVO.getCnt(); i++ ) {	
		 sqlSessionTemplate.insert("tpost.batch.dao.moblSndnBulkIns", moblSndnBulkInsVO);
		}
		
	}
	
	
	

}
