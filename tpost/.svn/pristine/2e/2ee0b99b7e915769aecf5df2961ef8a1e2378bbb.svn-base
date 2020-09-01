package tpost.SimpleTest.controller;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import tpost.SimpleTest.util.ConverterUtils;
import tpost.SimpleTest.util.TimeClient;
import tpost.SimpleTest.vo.ChannelCheckAckVO;
import tpost.batch.service.kakaoTestService;
import tpost.batch.vo.kakaoTestVO;

@Component
@Controller
@RequestMapping(value = "/batch/SampleTest")
public class SimpleTest{

	private Channel channel;
	
	@Autowired
	kakaoTestService kakaoTestService;
	
	/*로그설정 입니다. */
	Logger logger = (Logger) LogManager.getLogger(SimpleTest.class);

	 /*파일 배치 테스트 입니다.  */
	 @ResponseBody
	 @RequestMapping(value = "/sktHttp", method = RequestMethod.POST)
	 public Map kakaoSendTest(Model model , @ModelAttribute kakaoTestVO kakaoTestVO ) throws InterruptedException {
		 
		 System.out.println("/batch/SampleTest/sktHttp");
		
		 Charset utf8 = Charset.forName("UTF-8");
		 ByteBuf buffer = Unpooled.copiedBuffer("", utf8);
			
		 ChannelCheckAckVO channelCheckAckVO = new ChannelCheckAckVO();
	
		 channelCheckAckVO.setResult("bc");
		 channelCheckAckVO.setReason(11);
		 channelCheckAckVO.setLastMsgId1("Msg");
		 channelCheckAckVO.setLastMsgId2(222);
		 
		 System.out.println("2222  getLastMsgId2 ==> "+(long)channelCheckAckVO.getLastMsgId2());
		 
		 
		 System.out.println("===>"+ channelCheckAckVO.getLastMsgId2() );
		 try {
			 
			long dd = ConverterUtils.toByteBuf(channelCheckAckVO, buffer);
			buffer.writeBytes(buffer);
			
			TimeClient tc = new TimeClient();
			
			String host = "127.70.7.103";
			int port = 8888;
			
			tc.myClient.setSendMsg(buffer);
			
			tc.setHost(host);
			tc.setPort(port);
			
			try {
				tc.runClient();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		 
		 
		return null;
		 
	 }
	 
	 public void retMsg(Object msg) throws IllegalArgumentException, IllegalAccessException, UnsupportedEncodingException {
		 System.out.println("retMsg ==> " + msg);
		 
		 System.out.println("/batch/SampleTest/sktHttp");
			
		 Charset utf8 = Charset.forName("UTF-8");
		 ByteBuf buffer = Unpooled.copiedBuffer("", utf8);
			
		 ChannelCheckAckVO channelCheckAckVO = new ChannelCheckAckVO();
		 
		 ConverterUtils.toObject((ByteBuf)msg, channelCheckAckVO);
		 
		 System.out.println("channelCheckAckVO getLastMsgId1  ==> "+channelCheckAckVO.getLastMsgId1());
		 System.out.println("channelCheckAckVO getLastMsgId2 ==> "+(long)channelCheckAckVO.getLastMsgId2());
		 System.out.println("channelCheckAckVO getReason ==> "+channelCheckAckVO.getReason());
		 System.out.println("channelCheckAckVO getResult ==> "+channelCheckAckVO.getResult());
		 
	 }

}