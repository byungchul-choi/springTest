package tpost.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tpost.commCode.controller.commCdMgntController;
import tpost.commUtil.commUtil;
import tpost.common.service.codeMgntService;
import tpost.common.vo.codeMgntVO;
import tpost.common.vo.tmpltMgntVO;
import tpost.egovcomm.EgovUserDetailsHelper;

@Controller
@RequestMapping(value = "/common")
public class codeMgntController {
	Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);
	
	@Autowired
	codeMgntService codeMgntService;
	
	@RequestMapping(value = "/codeMgnt", method = RequestMethod.POST)
	public String codeMgntInit(Model model) {
		log.debug("/common/codeMgnt - codeMgntInit");
		
		codeMgntVO codeMgntVO = new codeMgntVO();
		codeMgntVO.setInputCfcd("");
		codeMgntVO.setInputCfcdNm("");
		codeMgntVO.setInputBasc("");
		codeMgntVO.setInputBascNm("");
	
		codeMgntSelect(model, codeMgntVO);
		
		return "common/codeMgnt";
	}
	
	//상단 조회 버튼 클릭 시
	@RequestMapping(value = "/codeMgntSelect", method = RequestMethod.POST)
	public String codeMgntSelect(Model model, @ModelAttribute codeMgntVO codeMgntVO) {
		log.debug("/common/codeMgntSelect - codeMgntSelect");
		
		model.addAttribute("inputCfcd", codeMgntVO.getInputCfcd());
		model.addAttribute("inputCfcdNm",codeMgntVO.getInputCfcdNm());
		model.addAttribute("inputBasc",codeMgntVO.getInputBasc());
		model.addAttribute("inputBascNm",codeMgntVO.getInputBascNm());
		
		List<codeMgntVO> cfcdLst = new ArrayList<codeMgntVO>();
		List<codeMgntVO> bascdLst = new ArrayList<codeMgntVO>();
		List<codeMgntVO> dtcdLst = new ArrayList<codeMgntVO>();
		
		if(!codeMgntVO.getInputBasc().equals("") || !codeMgntVO.getInputBascNm().equals("")){	
			//기본코드 셋팅 (분류코드도 같이 가져옴)
			bascdLst = codeMgntService.codeMgntSelect(codeMgntVO);
			
			//분류코드 셋팅
			if(bascdLst.size() > 0) {
				for(int i = 0; i < bascdLst.size(); i++) {
					codeMgntVO tempVO = new codeMgntVO();
					if(i == 0) {
						tempVO.setCfcd(bascdLst.get(i).getCfcd());
						tempVO.setCfcdNm(bascdLst.get(i).getCfcdNm());
						tempVO.setCfcdDesc(bascdLst.get(i).getCfcdDesc());

						cfcdLst.add(tempVO);
					}
					else {
						if(cfcdLst.get(cfcdLst.size() - 1).getCfcd().equals(bascdLst.get(i).getCfcd())) {
							;
						}
						else {
							tempVO.setCfcd(bascdLst.get(i).getCfcd());
							tempVO.setCfcdNm(bascdLst.get(i).getCfcdNm());
							tempVO.setCfcdDesc(bascdLst.get(i).getCfcdDesc());

							cfcdLst.add(tempVO);
						}
					}
				}
			}
			
			//상세코드 셋팅
			if(bascdLst.size() > 0 && !bascdLst.get(0).getBasc().equals("")) {
				if(codeMgntVO.getInputCfcd().equals("")) codeMgntVO.setInputCfcd(cfcdLst.get(0).getCfcd());
				codeMgntVO.setInputBasc(bascdLst.get(0).getBasc());
				codeMgntVO.setInputDtcd("");
				dtcdLst = codeMgntService.dtcdSelect(codeMgntVO);
			}
		}else {
			//분류코드 셋팅
			cfcdLst = codeMgntService.cfcdSelect(codeMgntVO);
			
			//기본코드 셋팅
			if(cfcdLst.size() > 0 && !cfcdLst.get(0).getCfcd().equals("")) {
				codeMgntVO.setInputCfcd(cfcdLst.get(0).getCfcd());
				bascdLst = codeMgntService.bascdSelect(codeMgntVO);
				
				//상세코드 셋팅
				if(bascdLst.size() > 0 && !bascdLst.get(0).getBasc().equals("")) {
					codeMgntVO.setInputBasc(bascdLst.get(0).getBasc());
					codeMgntVO.setInputDtcd("");
					dtcdLst = codeMgntService.dtcdSelect(codeMgntVO);
				}
			}
		}
		
		model.addAttribute("cfcdLst", cfcdLst);
		model.addAttribute("bascdLst", bascdLst);
		model.addAttribute("dtcdLst", dtcdLst);
		
		return "common/codeMgnt";
	}
	
	//분류코드 명 클릭 시 기본코드 조회
	@ResponseBody
	@RequestMapping(value = "/cfcdToBascSelect.ajax")
	public Map cfcdToBascSelect(@ModelAttribute("codeMgntVO") codeMgntVO codeMgntVO) {
		log.debug("/common/codeMgnt - cfcdToBascSelect.ajax  " + codeMgntVO.getInputCfcd());

		Map<String, Object> resultMap = new HashMap<String, Object>();

		List<codeMgntVO> bascdLst = new ArrayList<codeMgntVO>();
		bascdLst = codeMgntService.bascdSelect(codeMgntVO);

		List<codeMgntVO> dtcdLst = new ArrayList<codeMgntVO>();
		if(bascdLst.size() > 0 && !bascdLst.get(0).getBasc().equals("")) {
			codeMgntVO.setInputBasc(bascdLst.get(0).getBasc());
			dtcdLst = codeMgntService.dtcdSelect(codeMgntVO);
		}
		
		resultMap.put("bascdLst", bascdLst);
		resultMap.put("dtcdLst", dtcdLst);
		
		return resultMap;
	}
	
	//기본코드 명 클릭 시 세부코드 조회
	@ResponseBody
	@RequestMapping(value = "/bascToDtcdSelect.ajax")
	public Map bascToDtcdSelect(@ModelAttribute("codeMgntVO") codeMgntVO codeMgntVO) {
		log.debug("/common/codeMgnt - bascToDtcdSelect.ajax");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<codeMgntVO> dtcdLst = new ArrayList<codeMgntVO>();
		dtcdLst = codeMgntService.dtcdSelect(codeMgntVO);
		
		resultMap.put("dtcdLst", dtcdLst);
		
		return resultMap;
	}
	
	//하단의 상세 내역 조회
	@ResponseBody
	@RequestMapping(value = "/brkdSelect.ajax")
	public Map brkdSelect(@ModelAttribute("codeMgntVO") codeMgntVO codeMgntVO) {
		log.debug("/common/codeMgnt - brkdSelect.ajax");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("brkd", codeMgntService.brkdSelect(codeMgntVO));
		
		return resultMap;
	}
	
	//분류코드 등록
	@ResponseBody
	@RequestMapping(value = "/cfcdInsert.ajax")
	public Map cfcdInsert(@ModelAttribute("codeMgntVO") codeMgntVO codeMgntVO) {
		log.debug("/common/codeMgnt - cfcdInsert.ajax");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String errorMessage = "";
		
		codeMgntVO tempVO = new codeMgntVO();
		tempVO.setInputCfcd(codeMgntVO.getCfcd());
		tempVO.setInputCfcdNm("");
		
		List<codeMgntVO> cfcdLst = codeMgntService.cfcdSelect(tempVO);
	
		if(cfcdLst.size() > 0) {
			errorMessage = "해당 분류코드는 이미 존재하는 값입니다.";
		}else {
			codeMgntVO.setAmdr(EgovUserDetailsHelper.getUserId());
			codeMgntVO.setCrtr(EgovUserDetailsHelper.getUserId());
			
			codeMgntService.cfcdInsert(codeMgntVO);
		}
		
		resultMap.put("errorMessage", errorMessage);
		
		return resultMap;
	}
	
	//기본코드 등록
	@ResponseBody
	@RequestMapping(value = "/bascInsert.ajax")
	public Map bascInsert(@ModelAttribute("codeMgntVO") codeMgntVO codeMgntVO) {
		log.debug("/common/codeMgnt - bascInsert.ajax");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String errorMessage = "";
		
		codeMgntVO tempVO = new codeMgntVO();
		tempVO.setInputCfcd(codeMgntVO.getCfcd());
		tempVO.setInputBasc(codeMgntVO.getBasc());
		tempVO.setInputBascNm("");
		
		List<codeMgntVO> bascLst = codeMgntService.bascdSelect(tempVO);
	
		if(bascLst.size() > 0) {
			errorMessage = "해당 분류코드의 기본코드는 이미 존재하는 값입니다.";
		}else {
			codeMgntVO.setAmdr(EgovUserDetailsHelper.getUserId());
			codeMgntVO.setCrtr(EgovUserDetailsHelper.getUserId());
			
			codeMgntVO.setBascApplStDt(commUtil.dateToText(codeMgntVO.getBascApplStDt()));
			codeMgntVO.setBascApplClosDt(commUtil.dateToText(codeMgntVO.getBascApplClosDt()));
			
			codeMgntService.bascInsert(codeMgntVO);
		}
		
		resultMap.put("errorMessage", errorMessage);
		
		return resultMap;
	}
	
	//상세코드 등록
	@ResponseBody
	@RequestMapping(value = "/dtcdInsert.ajax")
	public Map dtcdInsert(@ModelAttribute("codeMgntVO") codeMgntVO codeMgntVO) {
		log.debug("/common/codeMgnt - dtcdInsert.ajax");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String errorMessage = "";
		
		codeMgntVO tempVO = new codeMgntVO();
		tempVO.setInputCfcd(codeMgntVO.getCfcd());
		tempVO.setInputBasc(codeMgntVO.getBasc());
		tempVO.setInputDtcd(codeMgntVO.getDtcd());
		
		List<codeMgntVO> dtcdLst = codeMgntService.dtcdSelect(tempVO);
	
		if(dtcdLst.size() > 0) {
			errorMessage = "해당 분류코드의 기본코드의 상세코드는 이미 존재하는 값입니다.";
		}else {
			codeMgntVO.setAmdr(EgovUserDetailsHelper.getUserId());
			codeMgntVO.setCrtr(EgovUserDetailsHelper.getUserId());
			
			codeMgntVO.setDtcdApplStDt(commUtil.dateToText(codeMgntVO.getDtcdApplStDt()));
			codeMgntVO.setDtcdApplClosDt(commUtil.dateToText(codeMgntVO.getDtcdApplClosDt()));
			
			codeMgntService.dtcdInsert(codeMgntVO);
		}
		
		resultMap.put("errorMessage", errorMessage);
		
		return resultMap;
	}
	
	//기본코드 수정
	@ResponseBody
	@RequestMapping(value = "/bascUpdate.ajax")
	public Map bascUpdate(@ModelAttribute("codeMgntVO") codeMgntVO codeMgntVO) {
		log.debug("/common/codeMgnt - bascUpdate.ajax");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		codeMgntVO.setAmdr(EgovUserDetailsHelper.getUserId());
		codeMgntVO.setCrtr(EgovUserDetailsHelper.getUserId());
		
		codeMgntVO.setBascApplStDt(commUtil.dateToText(codeMgntVO.getBascApplStDt()));
		codeMgntVO.setBascApplClosDt(commUtil.dateToText(codeMgntVO.getBascApplClosDt()));
		
		codeMgntService.bascUpdate(codeMgntVO);
		
		return resultMap;
	}
	
	//상세코드 수정
	@ResponseBody
	@RequestMapping(value = "/dtcdUpdate.ajax")
	public Map dtcdUpdate(@ModelAttribute("codeMgntVO") codeMgntVO codeMgntVO) {
		log.debug("/common/codeMgnt - dtcdUpdate.ajax");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		codeMgntVO.setAmdr(EgovUserDetailsHelper.getUserId());
		codeMgntVO.setCrtr(EgovUserDetailsHelper.getUserId());
		
		codeMgntVO.setDtcdApplStDt(commUtil.dateToText(codeMgntVO.getDtcdApplStDt()));
		codeMgntVO.setDtcdApplClosDt(commUtil.dateToText(codeMgntVO.getDtcdApplClosDt()));
		
		codeMgntService.dtcdUpdate(codeMgntVO);
		
		return resultMap;
	}
}