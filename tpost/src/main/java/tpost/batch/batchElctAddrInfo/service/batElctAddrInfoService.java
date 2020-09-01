package tpost.batch.batchElctAddrInfo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpost.acmdCerf.vo.ofapElctAddrVO;
import tpost.batch.batchElctAddrInfo.dao.batElctAddrInfoDao;
import tpost.batch.batchElctAddrInfo.vo.batElctAddrInfoVO;
import tpost.commUtil.commUtil;

@Service
public class batElctAddrInfoService {

  @Autowired
  batElctAddrInfoDao	 dao;
  
  Logger log = (Logger) LogManager.getLogger(batElctAddrInfoService.class);

	public void elctAddrInfoInsert(batElctAddrInfoVO batElctAddrInfoVO) {
		// TODO Auto-generated method stub
		/*공인전자 주소가 없는것 가져오기*/
		List<batElctAddrInfoVO>  ofapElctAddrList = dao.elecAddrSel(batElctAddrInfoVO);
		
		log.debug("2222222222=>" + ofapElctAddrList.size());
		
		java.util.Calendar cal = java.util.Calendar.getInstance();
		int nYear = cal.get(cal.YEAR);
		
		
		if( "5".equals(batElctAddrInfoVO.getSexClcd()) 
			||	"6".equals(batElctAddrInfoVO.getSexClcd())	
			||	"7".equals(batElctAddrInfoVO.getSexClcd())
			||	"8".equals(batElctAddrInfoVO.getSexClcd())
			) {
			batElctAddrInfoVO.setBsrpCls("200");  /*외국인 */
		} else {
			batElctAddrInfoVO.setBsrpCls("100");  /*내국인*/
		}
				
		/*공인전자 주소 max +1 가져오기*/
		String inputBsrpCls = batElctAddrInfoVO.getBsrpCls();
		String ye = (""+nYear).substring(2, 4);
		
		batElctAddrInfoVO.setInputBsrpCls(batElctAddrInfoVO.getBsrpCls());
		batElctAddrInfoVO.setYe(ye);
		
		batElctAddrInfoVO ofapElctAddr = new batElctAddrInfoVO();
		            ofapElctAddr = dao.elecAddrInputSeq(batElctAddrInfoVO);
		
//		 Integer pk = Integer.parseInt(ofapElctAddr.getInputPk());
//		 Integer inputpk = Integer.parseInt(ofapElctAddr.getInputPk());
		 int  inputOfapElctAddr = Integer.parseInt(ofapElctAddr.getInputOfapElctAddr());
		 
		 /*************************************************************/
		 /*입력데어터 생성*/
		 List<Map<String, Object>> tempList = new ArrayList<Map<String, Object>>();
			for(int i = 0; i < ofapElctAddrList.size(); i++) {
				
					
					 //중복된게 없을 경우
						Map<String, Object> temp = new HashMap<String, Object>();
						
//						temp.put("inputPk", pk);
						temp.put("name", ofapElctAddrList.get(i).getName());
						temp.put("inputOfapElctAddr",  inputBsrpCls+ye+ commUtil.setLPad(""+inputOfapElctAddr, 8,"0"));
						
						temp.put("ciNum", ofapElctAddrList.get(i).getCiNum());
						temp.put("ssnoFronBrdt", ofapElctAddrList.get(i).getSsnoFronBrdt());
						temp.put("sexClcd", ofapElctAddrList.get(i).getSexClcd());
						temp.put("celpNum", ofapElctAddrList.get(i).getCelpNum());
//						temp.put("bsrpCls", ofapElctAddrList.get(i).getBsrpCls()); /* 100 : 개인 200: 법인*/
						temp.put("crtr", "batch");
						temp.put("amdr", "batch");
						
						tempList.add(temp);
//						pk++;
						inputOfapElctAddr++;
				
			}

			if(tempList.size() > 0) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("tempList", tempList);
				
				/*공인전자주소 입력 */
				  dao.ofapElctAddrListInsert_bat(paramMap);

				  /*공인전자 주소 이력입력  pkVO.setInputPk(pk.toString()); */
				  dao.ofapElctAddrHisListInsert_bat(batElctAddrInfoVO);
				  
				  /*수신메세지 고객정보입력*/
				  dao.tbRcveCiInfoInsert_bat(paramMap);
			}
		
	}


	 
}
