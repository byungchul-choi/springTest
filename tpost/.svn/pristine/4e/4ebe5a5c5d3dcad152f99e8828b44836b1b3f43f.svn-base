package tpost.restComu.restSuppCenter.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tpost.restComu.restSuppCenter.vo.restNoticeVO;

@Controller
public class restNoticeDao {

	@Autowired
	SqlSession sqlSessionTemplate;
	 
	public List<restNoticeVO> noticeListSelect(restNoticeVO restNoticeVO){
		return sqlSessionTemplate.selectList("tpost.restComu.restSuppCenter.dao.restNoticeDao.noticeListSelect", restNoticeVO);
	}
	
	public int noticeListCountSelect(restNoticeVO restNoticeVO) {
		return sqlSessionTemplate.selectOne("tpost.restComu.restSuppCenter.dao.restNoticeDao.noticeListCountSelect", restNoticeVO);
	}
	
	public restNoticeVO noticeDetlSelect(restNoticeVO restNoticeVO) {
		 return sqlSessionTemplate.selectOne("tpost.restComu.restSuppCenter.dao.restNoticeDao.noticeDetlSelect", restNoticeVO);
	}
}
