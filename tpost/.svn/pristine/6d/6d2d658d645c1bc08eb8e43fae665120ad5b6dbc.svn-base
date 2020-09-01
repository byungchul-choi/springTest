package tpost.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpost.common.dao.athGrpMgntDao;
import tpost.common.vo.aoMgntVO;
import tpost.common.vo.athGrpMgntAOVO;
import tpost.common.vo.athGrpMgntCustVO;
import tpost.common.vo.athGrpMgntVO;

@Service
public class athGrpMgntService {

  @Autowired
  athGrpMgntDao	 dao;
  
  /**
   * 매핑권한 그룹정보 
   * */
  
  /*권한그룹 조회 . */
	public  List<athGrpMgntVO> athGrpMgntSelect(athGrpMgntVO athGrpMgntVO) {
		return dao.athGrpMgntSelect(athGrpMgntVO);
	}
  
	/*권한그룹 조회 카운트*/
	public  int athGrpMgntSelectCnt(athGrpMgntVO athGrpMgntVO) {
		  return dao.athGrpMgntSelectCnt(athGrpMgntVO);
	}
	
	/*권한 그룹 정보 insert */
	public void athGrpMgntInsert(athGrpMgntVO athGrpMgntVO){
		dao.athGrpMgntInsert(athGrpMgntVO);
	}

/*권한 그룹 정보 update . */
	public void athGrpMgntUpdate(athGrpMgntVO athGrpMgntVO){
		dao.athGrpMgntUpdate(athGrpMgntVO);
	}
	
	/*권한 그룹 정보 delete . */
	public void athGrpMgntDelete(athGrpMgntVO athGrpMgntVO){
		dao.athGrpMgntDelete(athGrpMgntVO);
	}

  
  /**
   * 매핑권한 고객정보 
   * */
  /*매핑 고객정보 조회 . */
	public  List<athGrpMgntCustVO> athGrpMgntCustSelect(athGrpMgntCustVO athGrpMgntCustVO) {
		return dao.athGrpMgntCustSelect(athGrpMgntCustVO);
	}
	
	/*권한그룹 조회 카운트*/
	public  int athGrpMgntCustSelectCnt(athGrpMgntCustVO athGrpMgntCustVO) {
		  return dao.athGrpMgntCustSelectCnt(athGrpMgntCustVO);
	}
	
	/*매핑 고객정보 Insert . */
	public void athMgntCustInsert(athGrpMgntCustVO athGrpMgntCustVO){
		dao.athMgntCustInsert(athGrpMgntCustVO);
	}

	/*매핑 고객정보update. */
	public void athMgntCustUpdate(athGrpMgntCustVO athGrpMgntCustVO){
		dao.athMgntCustUpdate(athGrpMgntCustVO);
	}

  
  /**
   * 매핑권한 AO정보 
   * */
  
  /*매핑 AO정보 조회 . */
	public  List<athGrpMgntAOVO> athGrpMgntAOSelect(athGrpMgntAOVO athGrpMgntAOVO) {
		return dao.athGrpMgntAOSelect(athGrpMgntAOVO);
	}
  
	/*권한그룹 조회 카운트*/
	            
	public  int athGrpMgntAOSelectCnt(athGrpMgntAOVO athGrpMgntAOVO) {
		  return dao.athGrpMgntAOSelectCnt(athGrpMgntAOVO);
	}
  
  /*매핑 AO정보 Insert . */
	public void athMgntAOInsert(athGrpMgntAOVO athGrpMgntAOVO){
		dao.athMgntAOInsert(athGrpMgntAOVO);
	}
  
	/*매핑 AO정보 update . */
	public void athMgntAOUpdate(athGrpMgntAOVO athGrpMgntAOVO){
		dao.athMgntAOUpdate(athGrpMgntAOVO);
	}
	
}
