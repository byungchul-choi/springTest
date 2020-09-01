package tpost.common.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpost.common.vo.tmpltMgntVO;
import tpost.egovcomm.EgovUserDetailsHelper;
import tpost.common.dao.tmpltMgntDao;

@Service
public class tmpltMgntService {
	@Autowired
	tmpltMgntDao dao;
	
	public List<tmpltMgntVO> tmpltMgntSelect(tmpltMgntVO tmpltMgntVO){
		return dao.tmpltMgntSelect(tmpltMgntVO);
	}
	
	public tmpltMgntVO tmpltInfoSelect(tmpltMgntVO tmpltMgntVO) {
		return dao.tmpltInfoSelect(tmpltMgntVO);
	}
	
	public void tmpltInfoUpdate(tmpltMgntVO tmpltMgntVO) {
		dao.tmpltInfoUpdate(tmpltMgntVO);
	}
	
	public void tmpltInfoInsert(tmpltMgntVO tmpltMgntVO) {
		dao.tmpltInfoInsert(tmpltMgntVO);
	}
	
	public List<tmpltMgntVO> tmpltSchemaSelect(tmpltMgntVO tmpltMgntVO){
		return dao.tmpltSchemaSelect(tmpltMgntVO);
	}
	
	@Transactional
	public void tmpltSchemaInsert(tmpltMgntVO tmpltMgntVO){
		dao.tmpltSchemaDelete(tmpltMgntVO); //현재 저장되어있는 스키마 삭제
		
		String sndnOganCd = tmpltMgntVO.getSndnOganCd();
		String tmpltCd = tmpltMgntVO.getTmpltCd();
		String[] inputChk = tmpltMgntVO.getInputChk().split(",");	
		String[] itemNm = tmpltMgntVO.getItemNm().split(",");
		String[] itemLen = tmpltMgntVO.getItemLen().split(",");
		String[] itemLocSeqn = tmpltMgntVO.getItemLocSeqn().split(",");
		String amdr = EgovUserDetailsHelper.getUserId();
		String crtr = EgovUserDetailsHelper.getUserId();
		
		for(int i = 0; i < itemNm.length; i++) {
			if(inputChk[i].equals("true")) {
				tmpltMgntVO tempVO = new tmpltMgntVO();

				tempVO.setSndnOganCd(sndnOganCd);
				tempVO.setTmpltCd(tmpltCd);
				tempVO.setItemSno(String.valueOf(i));
				tempVO.setItemNm(itemNm[i]);
				tempVO.setItemLen(itemLen[i]);
				tempVO.setItemLocSeqn(itemLocSeqn[i]);
				tempVO.setAmdr(amdr);
				tempVO.setCrtr(crtr);
				
				dao.tmpltSchemaInsert(tempVO);
			}
		}
	}
	
	public tmpltMgntVO tmpltEditSelect(tmpltMgntVO tmpltMgntVO) {
		return dao.tmpltEditSelect(tmpltMgntVO);
	}
	
	@Transactional
	public void tmpltEditInsert(tmpltMgntVO tmpltMgntVO){
		tmpltMgntVO.setAmdr(EgovUserDetailsHelper.getUserId());
		tmpltMgntVO.setCrtr(EgovUserDetailsHelper.getUserId());
		
		dao.tmpltEditDelete(tmpltMgntVO);
		dao.tmpltEditInsert(tmpltMgntVO);
	}
	
	public String oganMaxTmpltIdSelect(tmpltMgntVO tmpltMgntVO) {
		return dao.oganMaxTmpltIdSelect(tmpltMgntVO);
	}
}