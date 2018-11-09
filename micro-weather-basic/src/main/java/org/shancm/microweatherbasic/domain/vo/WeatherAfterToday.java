package org.shancm.microweatherbasic.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author shancm
 * @package org.shancm.microweatherbasic.domain.vo
 * @description:
 * @date 2018/11/8
 */
@Data
public class WeatherAfterToday implements Serializable {

	private static final long serialVersionUID = -5115294129259062293L;
	private String date;
	private String high;
	private String fengli;
	private String low;
	private String fengxiang;
	private String type;
}
