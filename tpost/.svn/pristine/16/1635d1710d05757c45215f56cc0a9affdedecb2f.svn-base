package tpost.SimpleTest.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.FileRegion;
import tpost.SimpleTest.common.DataField;
import tpost.SimpleTest.common.DataType;
import tpost.SimpleTest.vo.ChannelCheckAckVO;

public class ConverterUtils {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ConverterUtils.class);
	
	public final static String DEFAULT_CHARSET = "UTF-8";
	
	/**
	 * 입력된 VO로 부터 전달받은 ByteBuf 에 전문을 생성하는 메소드<br>
	 * VO 의 DataField Annotation이 기술되어 있는 필드를 기준으로 생성함
	 * 
	 * @param obj 입력 VO
	 * @param buffer 저장될 ByteBuf
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static long toByteBuf(Object obj, ByteBuf buffer) throws UnsupportedEncodingException, FileNotFoundException, IllegalArgumentException, IllegalAccessException {
		long index = 0; // 생성된 데이터의 길이를 반환할 필드 (추후, ByteBuf 인덱스 활용 방법 고려해 볼 것)

		Field[] fields = obj.getClass().getDeclaredFields();
		
		
		SortUtils.fieldSort(fields);
		for(Field curfield : fields) {
			DataField annoField = curfield.getAnnotation(DataField.class); // 어노테이션 필드
			if (annoField == null ) { continue;} // 어노테이션 이 없으면  SKIP
			
			Object value = null;
			try {
				if(curfield.isAccessible()) { 
					value = curfield.get(obj);
				} else { // private 일 경우
					curfield.setAccessible(true);
					value = curfield.get(obj);
					curfield.setAccessible(false);
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (annoField.type() == DataType.CHAR) {  // 정의 : CHAR 타입은 반드시 String 으로 되어 있는 값을 처리하도록 정의
			// 1. 문자열 처리
				
				int fieldLen = annoField.length();
				
				if(fieldLen <= 0) {
					if (annoField.refLength() != null && !annoField.refLength().isEmpty()) {
						fieldLen = (int) findRefObjectFieldValue(annoField.refLength(), obj); // 주의사항 : 필드 의 길이값은 ,long 형으로 처리할 수 없음
						//LOGGER.info("*** CHECK => refLength ["+curfield.getName()+ "/"+fieldLen+"]");
					} else {
						continue;
					}
				} // CHAR 타입인 경우에만 length 체크

				if( value == null ) { //값이 NULL 이면 공백을 채움
					buffer.writeBytes(BytesUtils.emptyValueWithSpace(fieldLen));
					index = index + fieldLen;
				} else { // 값이 있는 경우 테이터를 채움
					if(!(value instanceof java.lang.String)) {continue;} // String 타입만 처리 토록 함. 아닌 경우 SKIP
					//buffer.writeBytes(BytesUtils.adjustBytes(((String)value).getBytes(GlobalEnv.DEFAULT_CHARSET), fieldLen));
					
					byte[] temp = BytesUtils.adjustBytes(((String)value).getBytes(DEFAULT_CHARSET), fieldLen);
					//LOGGER.info("*** CHECK => DATA ["+(new String(temp,"UTF-8"))+"]");
					
					buffer.writeBytes(temp);
					
					index = index + fieldLen;
				}
			} else if (annoField.type() == DataType.INT || annoField.type() == DataType.UNSIGNED_INT) {
			// 2. INT 형 (4 byte) 처리 .  INT 타입인 경우에만 length 값 무시 (4byte 고정)
				if( value == null ) { //값이 NULL 이면 0 을 넣음
					buffer.writeInt(0);
					index = index + 4;
				} else { // 값이 잇는 경우 테이터를 채움
					if(value instanceof java.lang.Long) { // UNSIGNED_INT 의 경우 LONG 형으로 내부적으로 처리하도록 사전 정의함 
						int tempValue = (int)((long)value); // DownCasting
						buffer.writeInt(tempValue);
						index = index + 4;
					} else if (value instanceof java.lang.Integer) { 
						buffer.writeInt((int)value);
						index = index + 4;
					} else { // 다른 타입의 경우 SKIP
						continue;
					}
				}				
			} else if (annoField.type() == DataType.SHORT || annoField.type() == DataType.UNSIGNED_SHORT) {
			// 3.  SHORT 형 (2 byte) 처리.  SHORT 타입인 경우에만 length 값 무시 (2byte 고정)
				if( value == null ) { //값이 NULL 이면 0 을 넣음
					buffer.writeShort(0);
					index = index + 2;
				} else { // 값이 잇는 경우 테이터를 채움
					if (value instanceof java.lang.Integer) { // UNSIGNED_SHORT 의 경우 INT 형으로 내부적으로 처리하도록 사전 정의함 
						short tempValue = (short)((int)value);
						buffer.writeShort(tempValue);
						index = index + 2;
					} else if (value instanceof java.lang.Short) {
						buffer.writeShort((short)value); 
						index = index + 2;
					} else { // 다른 타입의 경우 SKIP
						continue;
					}
				}
			} else if (annoField.type() == DataType.FILE) {
				if(!(value instanceof java.lang.String)) {continue;} // String 타입만 처리 토록 함. 아닌 경우 SKIP
				String valueStr = (String) value;
				
				if( valueStr != null) {
					if(valueStr.isEmpty()) {
						continue;
					} else {
						File file  = new File(valueStr);
						RandomAccessFile raf = new RandomAccessFile(file, "r");
						FileChannel fChnnal = null;
						try {
							long fileLen = raf.length();
							fChnnal = raf.getChannel();
							MappedByteBuffer byteBuffer = fChnnal.map(FileChannel.MapMode.READ_ONLY, 0, fileLen);
							buffer.writeBytes(byteBuffer);
							index = index + fileLen;  
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} finally {
							try {
								if (raf != null) {
									raf.close();
								}
								if (fChnnal != null) {
									fChnnal.close();
								}
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					}
				}
			}
		}
		
		return index;
	}
	
	
	/**
	 * VO 로 바인딩 하는 함수
	 * @param buffer 전문 데이터가 저장되어 있는 버퍼
	 * @param obj 바인딩 하고자 하는 객체
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws UnsupportedEncodingException
	 */
	public static void toObject(ByteBuf buffer, Object obj) throws IllegalArgumentException, IllegalAccessException, UnsupportedEncodingException {
		
		Field[] fields = obj.getClass().getDeclaredFields();
		SortUtils.fieldSort(fields);
		
		for(Field curfield : fields) {
			
			boolean isAccessibleFlag = curfield.isAccessible();
			if(isAccessibleFlag == false) {
				curfield.setAccessible(true);
			} 
			
			DataField annoField = curfield.getAnnotation(DataField.class); // 어노테이션 필드
			if (annoField == null ) { continue;} // 어노테이션 이 없으면  SKIP
			
			if (annoField.type() == DataType.CHAR) {  // 정의 : CHAR 타입은 반드시 String 으로 되어 있는 값을 처리하도록 정의
				// 1. 문자열 처리
				if(annoField.length() <= 0) {continue;} // CHAR 타입인 경우에만 length 체크
				byte[] curBytes = new byte[annoField.length()];	
				buffer.readBytes(curBytes);
				String tempStr = new String(curBytes, DEFAULT_CHARSET);
				curfield.set(obj, tempStr.trim());
			} else if (annoField.type() == DataType.INT) { // Int 형 저장
				curfield.set(obj, buffer.readInt());
			} else if (annoField.type() == DataType.UNSIGNED_INT) { // Unsigned Int 저장. 중요: Unsigned Int 형은 Long 형으로 저장
				curfield.set(obj, buffer.readUnsignedInt());
			} else if (annoField.type() == DataType.SHORT) { // Short 저장
				curfield.set(obj, buffer.readShort());
			} else if (annoField.type() == DataType.UNSIGNED_SHORT) { // Unsigned Short 저장. 중요:  
				curfield.set(obj, buffer.readUnsignedShort());
			}
			
			if(isAccessibleFlag == false) {
				curfield.setAccessible(false);
			} 
		}			
	}
	
	private static long findRefObjectFieldValue(String key, Object obj) throws IllegalArgumentException, IllegalAccessException {
		
		long retValue = 0;
		String findkey = key.replace("$", "");
		Field[] fields = obj.getClass().getDeclaredFields();
		SortUtils.fieldSort(fields);
		
		for(Field curfield : fields) {
			
			boolean isAccessibleFlag = curfield.isAccessible();
			if(isAccessibleFlag == false) {
				curfield.setAccessible(true);
			}
			
			if(curfield.getName().equals(findkey)) {
				Object value = curfield.get(obj);
				if (value instanceof java.lang.Long || value instanceof java.lang.Integer || value instanceof java.lang.Short) { 
					retValue = (long) value;
				}
				break;
			}
		}
		
		return retValue;
	}
	

}
