package org.shancm.microweatherbasic.domain;

/**
 * @author shancm
 * @package org.shancm.microweatherbasic.domain
 * @description:
 * @date 2018/11/8
 */
public enum  RetCodeEnum {

	FAIL("1001", "INVOKE FAIL"),
	;
	private String retCode;
	private String retMsg;

	RetCodeEnum(String retCode, String retMsg) {
		this.retCode = retCode;
		this.retMsg = retMsg;
	}

	public String getRetCode() {
		return retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}
}
