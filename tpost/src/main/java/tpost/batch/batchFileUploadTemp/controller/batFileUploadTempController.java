package tpost.batch.batchFileUploadTemp.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tpost.batch.batchFileUploadTemp.service.batFileUploadTempService;
import tpost.batch.batchFileUploadTemp.vo.batFileUploadTempVO;
import tpost.commCode.controller.commCdMgntController;



@Component
@Controller
@RequestMapping(value = "/batch/batchFileUploadTemp")
public class batFileUploadTempController {

	@Autowired
	batFileUploadTempService batFileUploadTempService;
	
	/*로그설정 입니다. */
	Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);

	String osName = System.getProperty("os.name");
	
	
	 /*파일 배치 테스트 입니다.  */
	 @ResponseBody
	 @RequestMapping(value = "/batchFileUploadTemp", method = RequestMethod.POST)
	 public Map batchFileUploadTemp(Model model , @ModelAttribute batFileUploadTempVO batFileUploadTempVO ) {
		 
		 log.debug("batchFileUploadTemp");
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		
		 String FileNm = batFileUploadTempVO.getFileNm();
		 String exeDt = batFileUploadTempVO.getExeDt();
		 String oganCd = batFileUploadTempVO.getOganCd();
		 String tmpltCd = batFileUploadTempVO.getTmpltCd();
		 
		 log.debug("FileNm ==> " + FileNm);
		 
		 /*파일 업로드 호출*/
		 batchFileUploadTempSt(FileNm, exeDt, oganCd, tmpltCd);
		 
		 /*test */
		 resultMap.put("result", "Y");
		 resultMap.put("status", "delete");
		 resultMap.put("errorMessage", "");
		 
		 return resultMap;
	 }
	 
	 
	 

	 
//	@Scheduled(fixedRate=1000000)
	public void batchFileUploadTempSt(String file_nm, String exeDt, String oganCd, String tmpltCd){
		  
		 log.debug("파일 업로드 배치 테스트 입니다.");
		 
		 /*파일 갯수 가져오기  */
		 /* 파일리드 카운트와 헤더에 있는 카운트를 비교하여 파일 업로드 할지 말지 정함 */
		 int file_cnt = temp_file_chk(oganCd, file_nm);
		 
		/*파일 업로드 처리 */
		 log.debug("file_cnt => ( " + file_cnt + " ) " );
		 
		 String oganNm = oganCd.toLowerCase();  /*코드를 수문자로 바꾼것을 사용한다.*/
		 
		 
		 if(osName.toLowerCase().startsWith("window"))  {
			 file_nm = "C:\\nfsdata01\\tpost\\file\\" + oganNm+"\\samFile\\" + file_nm;
		 }else {
			 file_nm = "/nfsdata01/tpost/file/"+oganNm+"/samFile/" + file_nm;
		 }

		 temp_file_upload(file_nm, file_cnt , exeDt, oganCd, tmpltCd );
	}
	
	 
	private int temp_file_chk(String oganCd, String file_nm) {
		// TODO Auto-generated method stub
		log.debug("=================temp_file_chk=============================");
		String command = "";
		StringBuffer buffer = new StringBuffer();

		String dirOganCd = "";
		dirOganCd = oganCd.toLowerCase();
		
		try {
			
			if(osName.toLowerCase().startsWith("window"))  {   /*windows */
				command = "findstr /R /N \"^\" " + "C:\\nfsdata01\\tpost\\file\\" +dirOganCd+"\\samFile\\" + file_nm + " | find /C \":\" " ;
				buffer.append("cmd.exe ");
				buffer.append("/c ");
				buffer.append(command);
			}
			else  	//linux 환경 
			{	
			
				log.debug("===========command=============================");
				
				command = "wc -l " + "/nfsdata01/tpost/file/"+dirOganCd+"/samFile/" +file_nm  ;
				
				buffer.append(command);
				
				log.debug("command  ===> " + buffer);
			}
			Process process = Runtime.getRuntime().exec(buffer.toString());
			
			BufferedReader 	bufferReader1 = new BufferedReader(new InputStreamReader(process.getErrorStream(),"EUC-KR"));

			String line = null;

			while ((line = bufferReader1.readLine())!= null) {
				System.out.println(line);
			}
			
			/*파일건수 가져오기 header건도 가져오기때문에 전체 건수대비 1이 큼*/
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			line = null;
			StringBuffer readBuffer = new StringBuffer();
			
			while((line = bufferReader.readLine()) != null){
				
				String[] array = line.split(" ");
				readBuffer.append(array[0]);
				
			}
			
			return Integer.parseInt(readBuffer.toString());
			
		} catch (IOException e) {
			log.debug("=================IOException=============================" + e);
			e.printStackTrace();
		}
		
		return 0;
		
	}
	

	/*파일 업로드시 정합성 체크후 템프 테이블에 적제하는 부분*/
	private void temp_file_upload(String file_nm, int file_cnt , String exeDt, String oganCd, String tmpltCd) {
		try{
			
			log.debug("=================temp_file_upload=============================" );
	        //파일 객체 생성

			/*현재는 하드코드 , 디렉토리 구조로해서 가져오는 코딩하기 */
	        File file = new File(file_nm);                  /*원본파일 */
	        File file_temp = new File(file_nm+"_temp");     /*오류결과저장파일*/
	        
	        
           //입력 스트림 생성
           FileReader filereader = new FileReader(file);
           //입력 버퍼 생성
           BufferedReader bufReader = new BufferedReader(filereader);
           String line = "";
           
           /*array list 에 파일 저장*/
           List<batFileUploadTempVO> retStr = new ArrayList<batFileUploadTempVO>();
           
           /*템플릿 정보 가져오기 */
           batFileUploadTempVO fileTestVO_tempSel = new batFileUploadTempVO();
           fileTestVO_tempSel.setOganCd(oganCd);
           fileTestVO_tempSel.setTmpltCd(tmpltCd);
           List<batFileUploadTempVO> fileTestVO_temp  = batFileUploadTempService.fileTestTempSel(fileTestVO_tempSel);
           
           int tempSize = 0;
           for(int i = 0 ; i < fileTestVO_temp.size() ; i++ ) {
        	   tempSize += fileTestVO_temp.get(i).getItemLen();
           }
           
           int cnt =0 ;
           int failChk = 0;
           batFileUploadTempVO batFileUploadTempVO = null;
           FileWriter fw_temp = null;
           
           try {
        	   /*에러난 파일을 임시파일에 담는다.*/
        	   log.debug("=================tbBatchFileUpldTempTruncate=============================" );
        	   fw_temp = new FileWriter(file_temp);

        	   /*기존 업로드에 있던 파일을 truncate*/
        	   /*배치 로직 =  
        	    * 임시테이블 truncate > 임시 테이블 업로드 > MMS발송 테이블로 insert select
        	    * 중간에 에러 발생시 파일러 처리  
        	    * 
        	    * */
        	   batFileUploadTempService.tbBatchFileUpldTempTruncate(batFileUploadTempVO);
	           
        	   while((line = bufReader.readLine()) != null){
	        	   
	        	   /************************************************/
	        	   /*파일 실행일과 기관코드 템플릿 코드 가져오는거 셋팅*/
        		   batFileUploadTempVO = new batFileUploadTempVO();
        		   batFileUploadTempVO.setExeDt(exeDt);
        		   batFileUploadTempVO.setOganCd(oganCd);
        		   batFileUploadTempVO.setTmpltCd(tmpltCd);
	               /************************************************/
	               
	               log.debug("=================while 1 =============================" );
	               /*헤더와 파일갯수 체크 */
	               if(cnt == 0 ) {
	            	   byte[] bytes1 = line.getBytes();
	            	   
	            	   /* String(bytes1, 6, 10 ) 
	            	    * bytes1 은무자열
	            	    * 6은 자를시작위치
	            	    * 10은 몇개자를지
	            	    * */
	            	   int header_cnt = Integer.parseInt(new String(bytes1, 6, 10 ));
	            	   log.debug("=================header_cnt =============================" + header_cnt );
	            	   
	            	   if(header_cnt == file_cnt-1 ) {
	            		   log.debug("=================헤더와파일갯수 일치=============================" );
	            		   cnt = cnt +1;
	            		   continue;
	            	   }else {
	            		   failChk = failChk +1;
	            		   log.debug("=================헤더갯수와 파일 갯수가 맞지 않습니다.=============================" );
	            		   batFileUploadTempVO.setFailCnts("헤더갯수와 파일 갯수가 맞지 않습니다. ");
	            		   fw_temp.write("헤더갯수와 파일 갯수가 맞지 않습니다. ");

	            		   batFileUploadTempService.fileTestInsert_fail(batFileUploadTempVO);
	            		   break;
	            	   }
	            	 
	               }
	               
	               
	               byte[] bytes = line.getBytes();
	               
	               /**********************************************/
	               /*파일 압에 자리수를 비교하여 이름과 CI번호를 가져온다. */
	               batFileUploadTempVO.setName(new String(bytes, 0, 30 ));   /*이름*/
	               batFileUploadTempVO.setCiNum(new String(bytes, 30, 88 )); /*CI번호*/
	               batFileUploadTempVO.setSsnoFronBrdt(new String(bytes, 30+88, 6 )); /*생년월일*/
	               batFileUploadTempVO.setSexClcd(new String(bytes, 30+88+6, 1 )); /*성별구분코드*/
	               batFileUploadTempVO.setAnocInfo(new String(bytes, 30+88+6+1 , line.getBytes().length -  (30+88+6+1))); /*고지정보*/
	               /***************************************************/
	              
	               log.debug("================이름=============================" + new String(bytes, 0, 30 ));
	               log.debug("=================CI번호 =============================" + new String(bytes, 30, 88 ) );
	               log.debug("=================생년월일 ============================="  + new String(bytes, 30+88, 6 ) );
	               log.debug("=================성별구분코드 =============================" + new String(bytes, 30+88+6, 1 ) );
	               
	               if( line.getBytes().length == tempSize) {
	               
		               retStr.add(batFileUploadTempVO);
		               
		               /*1000 건씩 인설트*/
		               if(cnt % 1000  == 0 ) {
		            	   
		            	   batFileUploadTempService.fileTestInsert(retStr);
		            	   retStr.clear();
		               }
		               cnt++;
	               }else {
	            	   
	            	   log.debug("line.getBytes().length ==> " + line.getBytes().length);
	            	   log.debug("tempSize ==> " + tempSize);
	            	   failChk = failChk +1;
	            	   fw_temp.write("cnt("+cnt +") "+line);
	            	   batFileUploadTempService.fileTestInsert_fail(batFileUploadTempVO);
	            	   
	            	   break;
	               }
	           }
	           
	           
	           /*1000 으로 나누어지지 않는것 인서트*/
        	   batFileUploadTempService.fileTestInsert(retStr);
	           
           }catch (ArrayIndexOutOfBoundsException  ex) {
        	   failChk = failChk +1;
        	   batFileUploadTempVO.setExeDt(exeDt);
        	   batFileUploadTempVO.setOganCd(oganCd);
        	   batFileUploadTempVO.setTmpltCd(tmpltCd);
               
        	   batFileUploadTempVO.setName("error");
        	   batFileUploadTempVO.setCiNum("error");
        	   batFileUploadTempVO.setFileCnts(line);
               
        	   batFileUploadTempVO.setSuccYn("N");
        	   batFileUploadTempVO.setFailCnts("자릿수 오류발생");
              
               /*파일에 쓰기 */
               fw_temp.write("자릿수 에러발생 cnt("+cnt +") "+line);
               
               batFileUploadTempService.fileTestInsert_fail(batFileUploadTempVO);
		   }
           
           catch (Exception e) {
        	   failChk = failChk +1;
        	   batFileUploadTempVO.setExeDt(exeDt);
        	   batFileUploadTempVO.setOganCd(oganCd);
        	   batFileUploadTempVO.setTmpltCd(tmpltCd);
               
        	   batFileUploadTempVO.setName("error");
        	   batFileUploadTempVO.setCiNum("error");
        	   batFileUploadTempVO.setFileCnts(line);
               
        	   batFileUploadTempVO.setSuccYn("N");
        	   batFileUploadTempVO.setFailCnts("파일 읽중 에러발생");
               
               /*파일에 쓰기 */
               fw_temp.write("파일 읽는중 에러발생 cnt("+cnt +") "+line);
               
               batFileUploadTempService.fileTestInsert_fail(batFileUploadTempVO);
           }finally{
        	   //.readLine()은 끝에 개행문자를 읽지 않는다.            
               filereader.close();
               
               /*임시 테이블 파일 처리 완료*/
               fw_temp.close();
	        }
           
	        }catch (FileNotFoundException e) {
	        	log.debug("-----------FileNotFoundException-----------------"+ e);
	            e.getStackTrace();
	        }catch(IOException e){
	        	
	        	log.debug("-----------IOException-----------------"+ e);
	            e.getStackTrace();
	        }catch(Exception e) {
	        	log.debug("-----------Exception-----------------" + e);
	        	e.getStackTrace();
	        }
		 	finally{
	        	
	        }
		
	}
	
	
	 
}
	
