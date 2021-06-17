package org.shancm.feigncase.ilocal;

import java.util.Map;

/**
 * @author Shancm
 * @date 2020/7/1
 */
public class Machine {
    public static void main(String[] args) {
        Planet planet = new Planet();
        Map<String, String> map = planet.getMap();
        map.put("key", "name");
        System.out.println(planet);
    }
}
