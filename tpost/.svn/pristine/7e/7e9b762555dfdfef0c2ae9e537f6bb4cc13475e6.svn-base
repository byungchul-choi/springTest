package tpost.SimpleTest.util;

public class BytesUtils {
	
	public static byte[] adjustBytes(byte[] src, int len) {
		if(src.length == len ) {
			return src;
		} else if(src.length > len) {
			return split(src, 0, len);
		} else if(src.length < len){ 
			return fillRight(src, len, ' ');
		}
		
		return src;
	}

	public static byte[] split(byte[] src, int start, int len) {
		if (src.length < len) {
			return src;
		}

		byte[] z = new byte[len];
		System.arraycopy(src, start, z, 0, len);
		return z;
	}
	
	public static String byteArrayToHex(byte[] data) {
		
		if(data == null ) {
			return "";
		} else {
			StringBuilder sb = new StringBuilder();
			for(byte curByte : data) {
				sb.append(String.format("%02x ", curByte&0xff));
			}
			return sb.toString();
		}
	}
	
	public static byte[] emptyValueWithSpace(int length) {

		byte[] bytes = new byte[length];
		for (int i = 0 ; i < length ; i++) {
			bytes[i] = ' ';
		}
		return bytes;
	}
	
	/**
	 * 오른쪽에 filler를 추가한다.
	 * 
	 * @param src
	 * @param length
	 * @param filler
	 * @return
	 */
	public static byte[] fillRight(byte[] src, int length, char filler) {
		if (src.length >= length) {
			return src;
		}

		byte[] fillByte = new byte[length - src.length];
		for (int i = 0; i < fillByte.length; i++) {
			fillByte[i] = (byte) filler;
		}

		return append(src, fillByte);
	}

	/**
	 * 왼쪽에 filler를 추가한다.
	 * @param src
	 * @param length
	 * @param filler
	 * @return
	 */
	public static byte[] fillLeft(byte[] src, int length, char filler) {
		if (src.length >= length) {
			return src;
		}

		byte[] fillByte = new byte[length - src.length];
		for (int i = 0; i < fillByte.length; i++) {
			fillByte[i] = (byte) filler;
		}

		return append(fillByte, src);
	}
	
	/**
	 * Appends two bytes array into one.
	 * 
	 * @param a
	 *            A byte[].
	 * @param b
	 *            A byte[].
	 * @return A byte[].
	 */
	public static byte[] append(byte[] a, byte[] b) {
		if (a == null) {
			return b;
		}

		if (b == null) {
			return a;
		}

		byte[] z = new byte[a.length + b.length];
		System.arraycopy(a, 0, z, 0, a.length);
		System.arraycopy(b, 0, z, a.length, b.length);
		return z;
	}

}
