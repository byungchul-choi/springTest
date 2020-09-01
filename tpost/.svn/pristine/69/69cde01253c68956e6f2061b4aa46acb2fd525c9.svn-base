package tpost.restComu.restSuppCenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpost.restComu.restSuppCenter.vo.restNoticeVO;
import tpost.restComu.restSuppCenter.dao.restNoticeDao;

@Service
public class restNoticeService {

	@Autowired
	restNoticeDao dao;
	
	public List<restNoticeVO> noticeListSelect(restNoticeVO restNoticeVO){
		return dao.noticeListSelect(restNoticeVO);
	}
	
	public int noticeListCountSelect(restNoticeVO restNoticeVO) {
		return dao.noticeListCountSelect(restNoticeVO);
	}
	
	public restNoticeVO noticeDetlSelect(restNoticeVO restNoticeVO) {
		return dao.noticeDetlSelect(restNoticeVO);
	}
}
