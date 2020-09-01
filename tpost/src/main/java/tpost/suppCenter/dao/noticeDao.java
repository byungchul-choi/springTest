package tpost.suppCenter.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tpost.suppCenter.vo.noticeVO;


@Controller
public class noticeDao {

	 @Autowired
	 SqlSession sqlSessionTemplate;
	 
	 public List<noticeVO> noticeListSelect(noticeVO noticeVO){
		 return sqlSessionTemplate.selectList("tpost.suppCenter.dao.noticeDao.noticeListSelect", noticeVO);
	 }
	 
	 public int noticeListCountSelect(noticeVO noticeVO){
		 return sqlSessionTemplate.selectOne("tpost.suppCenter.dao.noticeDao.noticeListCountSelect", noticeVO);
	 }
	 
	 public noticeVO noticeDetlSelect(noticeVO noticeVO) {
		 return sqlSessionTemplate.selectOne("tpost.suppCenter.dao.noticeDao.noticeDetlSelect", noticeVO);
	 }
	 
	 public void noticeInsert(noticeVO noticeVO) {
		 sqlSessionTemplate.insert("tpost.suppCenter.dao.noticeDao.noticeInsert", noticeVO);
	 }
	 
	 public void noticeUpdate(noticeVO noticeVO) {
		 sqlSessionTemplate.update("tpost.suppCenter.dao.noticeDao.noticeUpdate", noticeVO);
	 }
	 
	 public int getMaxWrtgNum(noticeVO noticeVO) {
		 return sqlSessionTemplate.selectOne("tpost.suppCenter.dao.noticeDao.getMaxWrtgNum", noticeVO);
	 }
}
