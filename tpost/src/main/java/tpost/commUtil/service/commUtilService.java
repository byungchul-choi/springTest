package tpost.commUtil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpost.commUtil.dao.commUtilDao;
import tpost.commUtil.vo.commUtilVO;

@Service
public class commUtilService{
	
	@Autowired
	commUtilDao dao;
	
	public List<commUtilVO> getBascdList(commUtilVO commUtilVO){
		return dao.getBascdList(commUtilVO);	
	}
	public List<commUtilVO> getDtcdList(commUtilVO commUtilVO){
		return dao.getDtcdList(commUtilVO);	
	}
	
	public commUtilVO getBascdNmOne(commUtilVO commUtilVO){
		return dao.getBascdNmOne(commUtilVO);	
	}
	public commUtilVO getDtcdNmOne(commUtilVO commUtilVO){
		return dao.getDtcdNmOne(commUtilVO);	
	}
	
	public List<commUtilVO> getOganTmpltList(commUtilVO commUtilVO){
		return dao.getOganTmpltList(commUtilVO);	
	}
	
	public String getHolyYn(String date) {
		return dao.getHolyYn(date);
	}
}