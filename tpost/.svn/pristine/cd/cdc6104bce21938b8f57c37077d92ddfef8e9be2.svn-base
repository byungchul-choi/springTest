package tpost.commUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tpost.commCode.controller.commCdMgntController;

public class commUtil{
	
	static Logger commlog = (Logger) LogManager.getLogger(commCdMgntController.class);
	
	//20200220 => 2020-02-20
	public static String textToDate(String text) {
		String y = text.substring(0, 4);
		String m = text.substring(4, 6);
		String d = text.substring(6, 8);

		return y + "-" + m + "-" + d;
	}
	
	//2020-02-20 => 20200220  
	public static String dateToText(String date) {
		String[] temp = date.split("-");
		
		String text = "";
		for(int i = 0; i < 3; i++) {
			text += temp[i];
		}
		
		return text;
	}
	
	//해당년월일의 처음 날짜
	public static String YYYYMMFirstDate() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String[] temp = dateFormat.format(date).split("-");
		
		String result = "";
		for(int i = 0; i < 2; i++) {
			result += temp[i] + "-";
		}
		
		result += "01";
		
		return result;
	}
	
	//해당년월일의 마지막날짜
	public static String YYYYMMLastDate() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String[] temp = dateFormat.format(date).split("-");
		
		String result = "";
		for(int i = 0; i < 2; i++) {
			result += temp[i] + "-";
		}
		
		result += cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		return result;
	}
	
	//오늘날짜
	public static String getToday() {
		String result = "";
	
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		result = dateFormat.format(date);
		
		return result;
	}
	
	//어제날짜
	public static String getYesterday() {
		String result = "";
	
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(date);
		cal.add(Calendar.DATE, -1);
		
		result = dateFormat.format(cal.getTime());
		
		return result;
	}
	
	/**
	 * 왼쪽에 자리수만큼 특정 문자열 채우는 함수추가 
	 * strContext :  원 문자열
	 * iLen : 전체자리수
	 * strChar :  공백을 채울 문자열
	 * */
	public static String setLPad( String strContext, int iLen, String strChar ){
		String strResult = "";
		StringBuilder sbAddChar = new StringBuilder(); 
		
		for( int i = strContext.length(); i < iLen; i++ ) { 
			// iLen길이 만큼 strChar문자로 채운다. 
			sbAddChar.append( strChar );
		}
		strResult = sbAddChar + strContext; // LPAD이므로, 채울문자열 + 원래문자열로 Concate한다. 
		return strResult;
		
	}
	
	/*바이너리 관련된함수 StringToBinary */
	public static String StringToBinary(String st) {
		String Binary = "";
		/*바이트로 바꾸기*/
		commlog.debug("변환전 Binary  ==> " + st);
		byte[] buffers = st.getBytes();
		String by = byteArrayToBinaryString(buffers); 
		
		
		commlog.debug("변환된 Binary  ==> " + by);
		return by;
		
	}
	
	/*바이너리 관련된함수 BinaryToString */
	public static String BinaryToString(String binary) {
		
		commlog.debug("변환된 전 Binary  ==> " + binary);
		byte[] byte1 = binaryStringToByteArray(binary);
		
		String str2 = new String(byte1);
		
		commlog.debug("변환후  Binary  ==> " + binary);
		return str2 ;
		
	}
	
	public static String byteArrayToBinaryString(byte[] b){
	    StringBuilder sb=new StringBuilder();
	    for(int i=0; i<b.length; ++i){
	        sb.append(byteToBinaryString(b[i]));
	    }
	    return sb.toString();
	}

	public static String byteToBinaryString(byte n) {
	    StringBuilder sb = new StringBuilder("00000000");
	    for (int bit = 0; bit < 8; bit++) {
	        if (((n >> bit) & 1) > 0) {
	            sb.setCharAt(7 - bit, '1');
	        }
	    }
	    return sb.toString();
	}

	public static byte[] binaryStringToByteArray(String s){
	    int count=s.length()/8;
	    byte[] b=new byte[count];
	    for(int i=1; i<count; ++i){
	        String t=s.substring((i-1)*8, i*8);
	        b[i-1]=binaryStringToByte(t);
	    }
	    return b;
	}

	public static byte binaryStringToByte(String s){
	    byte ret=0, total=0;
	    for(int i=0; i<8; ++i){        
	        ret = (s.charAt(7-i)=='1') ? (byte)(1 << i) : 0;
	        total = (byte) (ret|total);
	    }
	    return total;
	}
	
	/*바이너리 관련된함수 ed*/
	
	
	//decode html tag
	public static String replaceTag(String html) {
		html = html.replaceAll("&lt;", "<").replaceAll("&gt;", ">");
		html = html.replaceAll("&#40;", "\\(").replaceAll("&#41;", "\\)");
		html = html.replaceAll("&#35;", "#");
		html = html.replaceAll("&amp;", "&");
		html = html.replaceAll("&#39;", "'");
		html = html.replaceAll("&quot;", "\"");
		
		return html;
	}
	
//	/*
//	 * getOganTmpltList
//	 * date(YYYYMMDD) 넘기면 휴일인지 평일인지 가져오는 함수
//	 * param : date (날짜)
//	 * return : holyYn(휴일 여부 - 0 : 영업일 / 1 : 휴일)
//	 */
//	public String getHolyYn(String date) {
//		log.debug(date);
//		return commUtilDao.getHolyYn(date);
//	}
//	
//	/*
//	 * getHolyYn
//	 * date(YYYYMMDD) 넘기면 휴일인지 평일인지 가져오는 함수
//	 * param : date (날짜)
//	 * return : holyYn(휴일 여부 - 0 : 영업일 / 1 : 휴일)
//	 */
//	public String getHolyYn() {
//		return commUtilDao.getHolyYn("");
//	}
	
	
	public static String StringReplace(String str){
        String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
        str =str.replaceAll(match, "");
        return str;
	}
	
}