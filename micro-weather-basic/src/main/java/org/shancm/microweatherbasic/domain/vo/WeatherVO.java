package org.shancm.microweatherbasic.domain.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author shancm
 * @package org.shancm.microweatherbasic.domain.vo
 * @description:
 * @date 2018/11/8
 */
@Getter
@Setter
@ToString
public class WeatherVO implements Serializable {

	private static final long serialVersionUID = -2146234778201814517L;
	private WeatherBeforeToday yesterday;

	private String city;
	private String aqi;
	private String ganmao;
	private String wendu;

	private List<WeatherAfterToday> forecast;


}
