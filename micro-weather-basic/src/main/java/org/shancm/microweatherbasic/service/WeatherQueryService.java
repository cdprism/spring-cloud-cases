package org.shancm.microweatherbasic.service;

import org.shancm.microweatherbasic.domain.BaseRes;
import org.shancm.microweatherbasic.domain.vo.WeatherVO;

/**
 * @author shancm
 * @package org.shancm.microweatherbasic.service
 * @description:
 * @date 2018/11/8
 */
public interface WeatherQueryService {

	/**
	 * @param cityKey 城市key
	 * @return 天气
	 */
	BaseRes<WeatherVO> getWeatherByCityKey(int cityKey);
}
