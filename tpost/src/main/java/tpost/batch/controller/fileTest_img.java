//package tpost.batch.controller;
//
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Graphics2D;
//import java.awt.font.FontRenderContext;
//import java.awt.geom.Rectangle2D;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.imageio.ImageIO;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import tpost.batch.vo.touchImgVO;
//import tpost.commCode.controller.commCdMgntController;
//
//
//@Component
//@Controller
//@RequestMapping(value = "/batch/fileTest")
//public class fileTest_img {
//
//	@Autowired
//	fileTestService fileTestService;
//	
//	/*로그설정 입니다. */
//	Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);
//
//	String osName = System.getProperty("os.name");
//	
//	
//	 /*파일 배치 테스트 입니다.  */
//	 @ResponseBody
//	 @RequestMapping(value = "/image", method = RequestMethod.POST)
//	 public Map batchTestMain1(Model model , @ModelAttribute touchImgVO touchImgVO) {
//		 Map<String, Object> resultMap = new HashMap<String, Object>();
//		 
//		 touchImg (touchImgVO);
//		 
//		 return resultMap;
//	 }
//	 
//	 public void touchImg (touchImgVO touchImgVO) {
//		 // 입력하는 문자의 가용 넓이
//		 int boxWidth = touchImgVO.getBoxWidth();
//		 int boxHeight = touchImgVO.getBoxHeight();
//		  
//		 int size = 1;
//		  
//		 Date date = new Date();
//		 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//		 File makeImage = new File("C://image/myFeedImage_" + sdf.format(date) + ".jpg");
//
//		 File loadImage = new File("c://login-box.png");
//
//		 BufferedImage bi = null;
//
//		 try {
//			 bi = ImageIO.read(loadImage);
//		 } catch (IOException e) {
//			 log.debug("Image Load Error");
//			 e.printStackTrace();
//		 }
//		  
//		 Graphics2D g2 = null;
//		 g2 = bi.createGraphics();
//
//
//		 g2.setPaint(Color.red);
//		 g2.fill(new Rectangle2D.Double(touchImgVO.getPointX(), touchImgVO.getPointY(), boxWidth, boxHeight));
//		 
//		 // text에 적용할 폰트 생성, 아래 폰트는 시스템에 설치 되어 있어야 사용할 수 있음
//		 Font font = new Font("Gungsuh", Font.BOLD, 1);
//		 
//		  
//		 // 가운데 정렬하기 위해, text의 width구하기
//		 FontRenderContext frc = new FontRenderContext(null, true, true);
//		 Rectangle2D r2 = font.getStringBounds(touchImgVO.getSubject(), frc);
//		 int textWidth = (int) r2.getWidth();
//		 int textHeight = (int) r2.getHeight();
//		 float paddingLeft = 0;
//		 float paddingUp = 0; 
//
//		 
//		 while(textWidth + (boxWidth * 0.2) < boxWidth) {
//			 if(textHeight + (boxHeight * 0.2) >= boxHeight) break;
//			  
//			 font = new Font("Gungsuh", Font.BOLD, size);
//			  
//			 r2.isEmpty();
//			 r2 = font.getStringBounds(touchImgVO.getSubject(), frc);
//			  
//			 textWidth = (int)r2.getWidth();
//			 textHeight = (int)r2.getHeight();
//			  
//			 size++;
//		 }
//
//		 paddingLeft = ((boxWidth - textWidth) / 2);
//		 paddingUp = ((boxHeight - textHeight) / 2);
//
//		 // 폰트 색상 설정
//		 g2.setColor(Color.black);
//
//		 // 폰트 종류 설정
//		 g2.setFont(font);
//		  
//		 g2.drawString(touchImgVO.getSubject(), (float)(touchImgVO.getPointX() + paddingLeft), (float)(touchImgVO.getPointY() + boxHeight - paddingUp));
//		 g2.dispose();
//
//		 try {
//			 ImageIO.write(bi, "jpg", makeImage);
//		 } catch (IOException e) {	
//			 log.debug("New Image Not Upload Error");
//			 e.printStackTrace();
//		 }
//
//	 }
//}
//
//	
