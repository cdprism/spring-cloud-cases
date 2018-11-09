package org.shancm.microweatherbasic.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.shancm.microweatherbasic.domain.BaseRes;
import org.shancm.microweatherbasic.domain.RetCodeEnum;
import org.shancm.microweatherbasic.domain.vo.WeatherVO;
import org.shancm.microweatherbasic.service.WeatherQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Objects;

/**
 * @author shancm
 * @package org.shancm.microweatherbasic.service.impl
 * @description:
 * @date 2018/11/8
 */
@Service("weatherQueryService")
public class WeatherQueryServiceImpl implements WeatherQueryService {

	private RestTemplate restTemplate;

	@Autowired
	public WeatherQueryServiceImpl(){
		this.restTemplate = new RestTemplate();
	}

	@Override
	public BaseRes<WeatherVO> getWeatherByCityKey(int cityKey) {

		String url = "http://wthrcdn.etouch.cn/weather_mini?citykey="+cityKey;

		ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);

		if(Objects.equals(result.getStatusCodeValue(), 200)){
			return  BaseRes.error(RetCodeEnum.FAIL);
		}

		BaseRes res = JSONObject.parseObject(result.getBody(), BaseRes.class);

		WeatherVO weather = (WeatherVO) res.getRetObj();

		System.out.println(weather);

		return null;
	}
}
