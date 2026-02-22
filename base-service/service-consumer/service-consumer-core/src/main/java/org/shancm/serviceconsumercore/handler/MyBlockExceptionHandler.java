package org.shancm.serviceconsumercore.handler;

import com.alibaba.csp.sentinel.adapter.spring.webmvc_v6x.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.shancm.common.domain.Result;
import org.shancm.common.util.JsonUtils;
import org.springframework.stereotype.Component;

/**
 * @author by shancm
 * @description MyBlockExceptionHandler
 * @since 2026-02-22 16:58
 */
@Component
public class MyBlockExceptionHandler implements BlockExceptionHandler {

    /*
     * @param s 为资源名称resourceName
     */
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                       String s, BlockException e) throws Exception {


        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.setStatus(500);
        Result<String> result = Result.failed("resourceName is "+s+". 接口被限流了. 流量控制接口为:"+e.getClass().toString());

        httpServletResponse.getWriter().write(JsonUtils.toJsonString(result));

    }
}
