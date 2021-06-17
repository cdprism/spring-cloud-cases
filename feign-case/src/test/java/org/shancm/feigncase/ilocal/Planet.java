package org.shancm.feigncase.ilocal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shancm
 * @date 2020/7/1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Planet {
    private String name;
    private Map<String, String> map = new HashMap<>();

}
