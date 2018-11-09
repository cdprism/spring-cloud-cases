package org.shancm.microweatherbasic.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author shancm
 * @package org.shancm.microweatherbasic.domain.vo
 * @description:
 * @date 2018/11/8
 */
@Getter
@Setter
@ToString
public class BaseRes<T> implements Serializable {

	private static final long serialVersionUID = 4196082058092684897L;

	private String status;
	private String desc;

	private T retObj;

	public BaseRes(String status, String desc) {
		this.status = status;
		this.desc = desc;
	}

	public static BaseRes success(){
		return new BaseRes("0000", "");
	}

	public static BaseRes error(RetCodeEnum retCodeEnum){
		return new BaseRes(retCodeEnum.getRetCode(), retCodeEnum.getRetMsg());
	}

	public static BaseRes error(String status, String desc){
		return new BaseRes(status, desc);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public T getRetObj() {
		return retObj;
	}

	public void setRetObj(T retObj) {
		this.retObj = retObj;
	}
}
