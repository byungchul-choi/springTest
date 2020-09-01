package tpost.suppCenter.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tpost.suppCenter.vo.faqVO;


@Controller
public class faqDao {

	 @Autowired
	  SqlSession sqlSessionTemplate;
	 
	 public List<faqVO> faqListSelect(faqVO faqVO){
		 return sqlSessionTemplate.selectList("tpost.suppCenter.dao.faqDao.faqListSelect", faqVO);
	 }
	 
	 public int faqListCountSelect(faqVO faqVO){
		 return sqlSessionTemplate.selectOne("tpost.suppCenter.dao.faqDao.faqListCountSelect", faqVO);
	 }
	 
	 public faqVO faqDetlSelect(faqVO faqVO) {
		 return sqlSessionTemplate.selectOne("tpost.suppCenter.dao.faqDao.faqDetlSelect", faqVO);
	 }
	 
	 public void faqInsert(faqVO faqVO) {
		 sqlSessionTemplate.insert("tpost.suppCenter.dao.faqDao.faqInsert", faqVO);
	 }
	 
	 public void faqUpdate(faqVO faqVO) {
		 sqlSessionTemplate.update("tpost.suppCenter.dao.faqDao.faqUpdate", faqVO);
	 }
	 
	 public int getMaxWrtgNum(faqVO faqVO) {
		 return sqlSessionTemplate.selectOne("tpost.suppCenter.dao.faqDao.getMaxWrtgNum", faqVO);
	 }

}
