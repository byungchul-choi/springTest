package tpost.SimpleTest.vo;

import tpost.SimpleTest.common.DataField;
import tpost.SimpleTest.common.DataType;

/**
 * MessageIdReq 에 대한 응답 전문<br>
 * Last Message ID : Server 에서 마지막으로 처리한 Message ID (Transaction ID) 값이며, Server 는 12자리의 값을 Client 에 전달
 * 이후 Client 에서 전달하는 Push 명령어 처리 요구 메시지 Header 의 Message ID 는 Last Message ID 값보다 큰 값으로 Client 에서 Servier 로 전송
 */
public class ChannelCheckAckVO {
	
	public static final long MESSAGE_CODE = 0x000000e; // 참고 : unsigned int 표현을 위해 long 으로 처리
	
	@DataField(order=1, type=DataType.CHAR, length=2)
	private String result;
	
	@DataField(order=2, type=DataType.UNSIGNED_SHORT)
	private int reason;  // unsigned 를 고려햐여 int형으로 저장 
	
	@DataField(order=3, type=DataType.CHAR, length=8)
	private String lastMsgId1;
	
	@DataField(order=4, type=DataType.UNSIGNED_INT, length=4)
	private long   lastMsgId2;  //unsigned 를 고려하여 unsignedInt는 long으로 처리


	/************************************************/
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getReason() {
		return reason;
	}

	public void setReason(int reason) {
		this.reason = reason;
	}

	public String getLastMsgId1() {
		return lastMsgId1;
	}

	public void setLastMsgId1(String lastMsgId1) {
		this.lastMsgId1 = lastMsgId1;
	}

	public long getLastMsgId2() {
		return lastMsgId2;
	}

	public void setLastMsgId2(long lastMsgId2) {
		this.lastMsgId2 = lastMsgId2;
	}
}
