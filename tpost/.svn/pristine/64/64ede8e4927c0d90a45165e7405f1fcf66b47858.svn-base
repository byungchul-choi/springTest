package tpost.batch.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import tpost.commCode.controller.commCdMgntController;

@Service
public class makeImage {

	public makeImage() { 
	 }
	 public static String makeImage(String cstNm, String loadImageStr, String oranNm) {
		 
		 Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);

		 long startTime = System.currentTimeMillis();

		 String subject = cstNm.trim();
		 
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

		 Date date = new Date();

		 String osName = System.getProperty("os.name");
		 
		 log.debug("고객명 : " + cstNm);
		 
		 String makeImageStr ="";
	 	 
		 if(osName.toLowerCase().startsWith("window"))  { /*윈도우 환경*/
			 makeImageStr = "C://imgTest/"+subject + sdf.format(date) + ".jpg".trim() ;
			 loadImageStr = "c://"+loadImageStr;
	 	 }else { /*리눅스 환경*/
//	 			saveDir = "/DATA/tpost/nhis/acmdCert/";
//	 		makeImageStr = "C://imgTest/"+ subject  + sdf.format(date) + ".jpg".trim() ;
	 		 
	 		makeImageStr = "/nfsdata01/tpost/file/"+oranNm+"/mmsFile/tempImg/"+ subject  + sdf.format(date) + ".jpg".trim() ;
	 		loadImageStr = "/nfsdata01/tpost/file/"+oranNm+"/mmsFile/"+loadImageStr;
	 		 
	 	 }
	 	
		 
		 File makeImage = new File(makeImageStr);
		 File loadImage = new File(loadImageStr);
		 
		 BufferedImage bi = null;

		 try {
			 	bi = ImageIO.read(loadImage);
		  } catch (IOException e) {
			  e.printStackTrace();
		  }

//		  int imgWidth = bi.getWidth();
//		  int imgHeight = bi.getHeight();
		 
		  Graphics2D g2 = null;

		  g2 = bi.createGraphics();

		  // text에 적용할 폰트 생성, 아래 폰트는 시스템에 설치 되어 있어야 사용할 수 있음
		  Font font = new Font("Gungsuh", Font.BOLD, 15);

		  // 가운데 정렬하기 위해, text의 width구하기
		  FontRenderContext frc = new FontRenderContext(null, true, true);
		  Rectangle2D r2 = font.getStringBounds(subject, frc);

		  int textWidth = (int) r2.getWidth();
		  float paddingleft = 0;

		  // 입력하는 문자의 가용 넓이

		  int textWide = 439;
		  paddingleft = ((textWide - textWidth) / 2) + 20;

		 
		  // 폰트 색상 설정
		  g2.setColor(Color.black);

		  // 폰트 종류 설정
		  g2.setFont(font);

		  // 이미지에 텍스트 사입. (text,x축,y축)

		  /*g2.drawString(subject, paddingleft, (float) (imgHeight * 0.8247));*/
		  g2.drawString(subject, 20, 20);
		  g2.dispose();

		  try {
			  ImageIO.write(bi, "jpg", makeImage);
		  } catch (IOException e) {
			  e.printStackTrace();
			  return "FAIL";
		  }

		  long endTime = System.currentTimeMillis();
		  	
		  log.debug("시작시간 : [" + startTime + "] 종료시간 ["+endTime+"]");
		  log.debug("로드이미지 : [" + loadImageStr + "] 만든이미지 ["+makeImageStr+"]");
		  
		  return makeImageStr;
	 }

	 
}
