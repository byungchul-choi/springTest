package tpost.elctDoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpost.elctDoc.dao.elctDocSndnDao;
import tpost.elctDoc.vo.elctDocSndnVO;

@Service
public class elctDocSndnService {
	
	@Autowired
	elctDocSndnDao dao;
	
	public List<elctDocSndnVO> elctDocSndnListSelect(elctDocSndnVO elctDocSndnVO){
		return dao.elctDocSndnListSelect(elctDocSndnVO);
	}
	
	public elctDocSndnVO elctDocSndnDetlSelect(elctDocSndnVO elctDocSndnVO){
		return dao.elctDocSndnDetlSelect(elctDocSndnVO);
	}
	
	public void elctDocSndnAprYnUpdate(elctDocSndnVO elctDocSndnVO) {
		dao.elctDocSndnAprYnUpdate(elctDocSndnVO);
	}
	
	public int elctDocSndnDupCheck(elctDocSndnVO elctDocSndnVO){
		return dao.elctDocSndnDupCheck(elctDocSndnVO);
	}
	
	public void elctDocSndnInsert(elctDocSndnVO elctDocSndnVO){
		dao.elctDocSndnInsert(elctDocSndnVO);
	}

	public void elctDocSndnUpdate(elctDocSndnVO elctDocSndnVO) {
		dao.elctDocSndnUpdate(elctDocSndnVO);
	}
}