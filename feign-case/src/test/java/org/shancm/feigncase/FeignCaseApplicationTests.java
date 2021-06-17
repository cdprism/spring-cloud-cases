package org.shancm.feigncase;

import org.junit.jupiter.api.Test;
import org.shancm.feigncase.ilocal.Planet;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FeignCaseApplicationTests {

	@Test
	public void contextLoads() {
		Planet planet = new Planet();
		System.out.println(planet.getMap());
	}

}

