package tpost.elctDoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpost.elctDoc.dao.elctDocDao;
import tpost.elctDoc.vo.elctDocVO;

@Service
public class elctDocService {
	@Autowired
	elctDocDao dao;
	
	public List<elctDocVO> elctDocListSelect(elctDocVO elctDocVO){
		return dao.elctDocListSelect(elctDocVO);
	}
	
	public int elctDocListCountSelect(elctDocVO elctDocVO){
		return dao.elctDocListCountSelect(elctDocVO);
	}
	
	public elctDocVO elctDocDetlTitleSelect(elctDocVO elctDocVO){
		return dao.elctDocDetlTitleSelect(elctDocVO);
	}
	
	public List<elctDocVO> elctDocDetlListALLSelect(elctDocVO elctDocVO){
		return dao.elctDocDetlListALLSelect(elctDocVO);
	}
	
	public List<elctDocVO> elctDocDetlListSelect(elctDocVO elctDocVO){
		return dao.elctDocDetlListSelect(elctDocVO);
	}
	
	public int elctDocDetlListCountSelect(elctDocVO elctDocVO){
		return dao.elctDocDetlListCountSelect(elctDocVO);
	}
	
	public List<elctDocVO> elctDocFailListSelect(elctDocVO elctDocVO){
		return dao.elctDocFailListSelect(elctDocVO);
	}
}