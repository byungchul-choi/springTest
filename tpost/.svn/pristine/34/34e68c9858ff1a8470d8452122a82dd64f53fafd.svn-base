package tpost.batch.batchElctAddrInfo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tpost.batch.batchElctAddrInfo.vo.batElctAddrInfoVO;
import tpost.commCode.controller.commCdMgntController;

@Repository
public class batElctAddrInfoDao {
	
  @Autowired
  SqlSession sqlSessionTemplate;

  /*로그설정 입니다. */
	Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);
	
	public List<batElctAddrInfoVO> elecAddrSel(batElctAddrInfoVO batElctAddrInfoVO) {
//		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("tpost.batch.batchElctAddrInfo.dao.batFileUploadTempDao.elecAddrSel", batElctAddrInfoVO);
	}
	
	public batElctAddrInfoVO elecAddrInputSeq(batElctAddrInfoVO batElctAddrInfoVO) {
	// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("tpost.batch.batchElctAddrInfo.dao.batFileUploadTempDao.elecAddrInputSeq", batElctAddrInfoVO);
	}
	
	public void ofapElctAddrListInsert_bat(Map<String, Object> paramMap) {
	// TODO Auto-generated method stub
		sqlSessionTemplate.insert("tpost.batch.batchElctAddrInfo.dao.batFileUploadTempDao.ofapElctAddrListInsert_bat", paramMap);
	}
	
	public void ofapElctAddrHisListInsert_bat(batElctAddrInfoVO batElctAddrInfoVO) {
	// TODO Auto-generated method stub
		sqlSessionTemplate.insert("tpost.batch.batchElctAddrInfo.dao.batFileUploadTempDao.ofapElctAddrHisListInsert_bat", batElctAddrInfoVO);
	}
	
	public void tbRcveCiInfoInsert_bat(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
			sqlSessionTemplate.insert("tpost.batch.batchElctAddrInfo.dao.batFileUploadTempDao.tbRcveCiInfoInsert_bat", paramMap);
		}


}
