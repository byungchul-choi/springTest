package tpost.batch.controller;



/**
 * Receive Data
 *
 */
public class RecvData {
	private Object  data    ;
	private String  errcode = "";
	private String  errmsg  = "";
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getErrcode() {
		return errcode;
	}
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
}
