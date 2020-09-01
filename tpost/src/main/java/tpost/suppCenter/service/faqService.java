package tpost.suppCenter.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpost.egovcomm.EgovUserDetailsHelper;
import tpost.suppCenter.dao.faqDao;
import tpost.suppCenter.vo.faqVO;

@Service
public class faqService {

	@Autowired
	faqDao dao;
	
	public List<faqVO> faqListSelect(faqVO faqVO){
		return dao.faqListSelect(faqVO);
	}
	
	public int faqListCountSelect(faqVO faqVO) {
		return dao.faqListCountSelect(faqVO);
	}
	
	public faqVO faqDetlSelect(faqVO faqVO) {
		return dao.faqDetlSelect(faqVO);
	}

	@Transactional
	public void faqInsert(faqVO faqVO) {
		int wrtgNum = dao.getMaxWrtgNum(faqVO);
		faqVO.setWrtgNum(Integer.toString(wrtgNum));
		faqVO.setAmdr(EgovUserDetailsHelper.getUserId());
		faqVO.setCrtr(EgovUserDetailsHelper.getUserId());
		
		dao.faqInsert(faqVO);
	}
	
	public void faqUpdate(faqVO faqVO) {
		faqVO.setAmdr(EgovUserDetailsHelper.getUserId());
		dao.faqUpdate(faqVO);
	}
}
