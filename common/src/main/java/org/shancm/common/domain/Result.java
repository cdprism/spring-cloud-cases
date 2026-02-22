package org.shancm.common.domain;

import lombok.Data;
import org.shancm.common.domain.enums.BusinessCode;

/**
 * @author by shancm
 * @description TODO
 * @since 2026-02-21 22:41
 */

@Data
public class Result<T> {
    private T data;

    private String code;
    private String msg;


    public static <T> Result<T> success(T t) {
        Result<T> result = new Result<>();
        result.setCode(BusinessCode.SUCCESS.getCode());
        result.setMsg(BusinessCode.SUCCESS.getMsg());
        result.setData(t);
        return result;
    }


}
