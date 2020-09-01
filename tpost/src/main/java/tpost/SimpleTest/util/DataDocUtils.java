package tpost.SimpleTest.util;

public class DataDocUtils {
	
	public final static String KEY_GBN_CD = "DATA_GBN_CD";
	public final static String KEY_MV_TSCO_CLCD = "MV_TSCO_CLCD";
	
	
	/**
	 * 데이터 구본 코드를 추출하는 함수<br>
	 * @param msg
	 * @return
	 */
	public static String findGbnCd(String msg) {
		int startIdx = msg.indexOf(KEY_GBN_CD);
		int sepIdx = msg.indexOf(",", startIdx);
		String[] tempStr = msg.substring(startIdx, sepIdx).split(":");
		String gbnText = tempStr[1].replaceAll("\"|\'", "").trim();
		return gbnText;
	}
	
	/**
	 * 통신사 구분 코드를 추출하는 함수<br>
	 * 01:SKT,02:KT,03:LGU+
	 * @param msg
	 * @return
	 */
	public static String findTscoClcd(String msg) {
		int startIdx = msg.indexOf(KEY_MV_TSCO_CLCD);
		int sepIdx = msg.indexOf(",", startIdx);
		String[] tempStr = msg.substring(startIdx, sepIdx).split(":");
		String gbnText = tempStr[1].replaceAll("\"|\'", "").trim();
		return gbnText;
	}
}
