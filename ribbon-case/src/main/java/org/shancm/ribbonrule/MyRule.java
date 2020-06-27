package org.shancm.ribbonrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import com.netflix.loadbalancer.ZoneAvoidanceRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shancm
 * @date 2020/06/22
 */
@Configuration
public class MyRule {

    @Bean
    public IRule rule(){
//        return new ZoneAvoidanceRule();
        return new RandomRule();
    }
}
