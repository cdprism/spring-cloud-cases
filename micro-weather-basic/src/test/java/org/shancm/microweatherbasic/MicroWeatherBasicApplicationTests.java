package org.shancm.microweatherbasic;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.shancm.microweatherbasic.domain.BaseRes;
import org.shancm.microweatherbasic.domain.vo.WeatherVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MicroWeatherBasicApplicationTests {


	@Test
	public void run() {

		String url = "wthrcdn.etouch.cn/weather_mini?citykey="+"101280601";

		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);

		CloseableHttpResponse response = null;
		try {
			response = client.execute(get);
			System.out.println(EntityUtils.toString(response.getEntity()));
		} catch (IOException e) {
			HttpClientUtils.closeQuietly(response);
		}


	}

}
