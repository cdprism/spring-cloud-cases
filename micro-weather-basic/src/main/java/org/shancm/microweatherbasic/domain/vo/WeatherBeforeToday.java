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
public class WeatherBeforeToday implements Serializable {

	private static final long serialVersionUID = 958376234121783274L;
	private String date;
	private String high;
	private String fx;
	private String low;
	private String fl;
	private String type;
}
