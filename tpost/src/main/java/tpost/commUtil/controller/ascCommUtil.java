package tpost.commUtil.controller;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tpost.commCode.controller.commCdMgntController;
import tpost.commUtil.commUtil;

@Controller
@RequestMapping(value = "commUtil")
public class ascCommUtil{
	static Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);
	
//	@Value("#{prop['sample.prop1'].concat(' abc')}") private String value1;

	@Value("#{properties['acsKey']}") 
	private String ascKey;
	
	@Value("#{properties['server']}") 
	private String server;
	
	@ResponseBody
	@RequestMapping(value = "/ascCommUtil")
	public Map ascCommUtil() {

		System.out.println("ascKey ===> " + ascKey);
		System.out.println("server ===> " + server);
		String text = "devTpost"; 
		byte[] targetBytes = text.getBytes(); // Base64 인코딩 /////////////////////////////////////////////////// 
		Encoder encoder = Base64.getEncoder(); 
		byte[] encodedBytes = encoder.encode(targetBytes); // Base64 디코딩 /////////////////////////////////////////////////// 
		
		Decoder decoder = Base64.getDecoder(); 
		byte[] decodedBytes = decoder.decode(encodedBytes); 
		System.out.println("인코딩 전 : " + text); 
		System.out.println("인코딩 text : " + new String(encodedBytes)); 
		System.out.println("디코딩 text : " + new String(decodedBytes)); 

		
		
		
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		return resultMap;
		
	}
	
	/* 암호화 처리 base64로 인코딩*/
	public static String ascKeyEncode(String str) {
		
		log.debug("acsKey ===> " + str ); 
		String strKey = str.trim(); 
		byte[] targetBytes = strKey.getBytes();  
		Encoder encoder = Base64.getEncoder(); 
		byte[] encodedBytes = encoder.encode(targetBytes);  
		
		log.debug("인코딩 전 : " + strKey ); 
		log.debug("인코딩 text : " + new String(encodedBytes)); 
		
		return new String(encodedBytes);
		
	}
	
	
}