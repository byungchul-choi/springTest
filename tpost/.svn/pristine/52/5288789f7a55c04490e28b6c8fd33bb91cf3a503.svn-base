package tpost.batch.batchMoblSndnRsltArg.service;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpost.batch.batchMoblSndnRsltArg.dao.batMoblSndnRsltArgDao;
import tpost.batch.batchMoblSndnRsltArg.vo.batMoblSndnRsltArgVO;
import tpost.commCode.controller.commCdMgntController;

@Service
public class batMoblSndnRsltArgService {

	@Autowired
	batMoblSndnRsltArgDao dao;
	
	/* 로그설정 */
	Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);
	
	@Transactional
	public void moblSndnRsltArgSuccIns(batMoblSndnRsltArgVO batMoblSndnRsltArgVO) {
		
		
		dao.moblSndnRsltArgSuccIns(batMoblSndnRsltArgVO);
	}
	
	@Transactional
	public void moblSndnRsltArgFailIns(batMoblSndnRsltArgVO batMoblSndnRsltArgVO) {
		
		
		dao.moblSndnRsltArgFailIns(batMoblSndnRsltArgVO);
	}
	
	
}
