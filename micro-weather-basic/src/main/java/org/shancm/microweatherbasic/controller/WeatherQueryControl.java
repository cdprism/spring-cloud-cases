package org.shancm.microweatherbasic.controller;

import org.shancm.microweatherbasic.domain.BaseRes;
import org.shancm.microweatherbasic.domain.vo.WeatherVO;
import org.shancm.microweatherbasic.service.WeatherQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shancm
 * @package org.shancm.microweatherbasic.controller
 * @description:
 * @date 2018/11/8
 */
@RestController
public class WeatherQueryControl {

	private WeatherQueryService weatherQueryService;

	@Autowired
	public WeatherQueryControl(WeatherQueryService weatherQueryService){
		this.weatherQueryService = weatherQueryService;
	}

	@GetMapping("/weather")
	public BaseRes<WeatherVO> getWeatherByCityKey(@RequestParam("cityKey") int cityKey){
		return weatherQueryService.getWeatherByCityKey(cityKey);
	}
}
