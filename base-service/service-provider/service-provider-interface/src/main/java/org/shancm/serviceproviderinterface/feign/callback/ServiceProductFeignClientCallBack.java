package org.shancm.serviceproviderinterface.feign.callback;

import com.mybatisflex.core.paginate.Page;
import org.shancm.common.domain.Result;
import org.shancm.serviceproviderinterface.domain.req.ProductReq;
import org.shancm.serviceproviderinterface.domain.res.ProductRes;
import org.shancm.serviceproviderinterface.feign.ServiceProductFeignClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author by shancm
 * @description ServiceProductFeignClientCallBack
 * @since 2026-02-22 18:39
 */

@Component
public class ServiceProductFeignClientCallBack implements ServiceProductFeignClient {
    @Override
    public String postTest(ProductReq productReq) {
        return "empty";
    }

    @Override
    public String checkConfig() {
        return "empty";
    }

    @Override
    public Result<List<ProductRes>> list() {
        return Result.failed();
    }

    @Override
    public Result<ProductRes> getInfo(Long id) {
        return Result.failed();
    }
}
