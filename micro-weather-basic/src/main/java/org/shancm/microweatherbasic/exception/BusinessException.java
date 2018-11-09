package org.shancm.microweatherbasic.exception;

/**
 * @author shancm
 * @package org.shancm.microweatherbasic.exception
 * @description:
 * @date 2018/11/8
 */
public class BusinessException extends Exception {

	private String retCode;
	private String retMsg;

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
}
