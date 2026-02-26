package org.shancm.common.domain;

import lombok.*;
import org.shancm.common.domain.enums.ResultCode;

/**
 * @author by shancm
 * @description Result<T>
 * @since 2026-02-21 22:41
 */
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Result<T> {

    private int code;
    private String msg;
    private T data;
//    private Instant timestamp;

    // ========== 成功静态方法 ==========
    public static <T> Result<T> success() {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), data);
    }

    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> Result<T> success(ResultCode resultCode, T data) {
        return new Result<>(resultCode.getCode(), resultCode.getMsg(), data);
    }

    // ========== 失败静态方法 ==========
    public static <T> Result<T> failed() {
        return new Result<>(ResultCode.FAILED.getCode(), ResultCode.FAILED.getMsg(), null);
    }

    public static <T> Result<T> failed(String msg) {
        return new Result<>(ResultCode.FAILED.getCode(), msg, null);
    }

    public static <T> Result<T> failed(ResultCode resultCode) {
        return new Result<>(resultCode.getCode(), resultCode.getMsg(), null);
    }

    public static <T> Result<T> failed(ResultCode resultCode, String msg) {
        return new Result<>(resultCode.getCode(), msg, null);
    }

    public static <T> Result<T> failed(int code, String msg) {
        return new Result<>(code, msg, null);
    }

    /*public static  Result<String> failed(String msg, Throwable throwable) {
        Result<String> failed = failed(msg);
        // throwable.getCause() 是否为空判断
        failed.setData(throwable.getCause() != null ? throwable.getCause().toString() : "");
        return failed;
    }*/

    // ========== 链式设置（可选） ==========
    public Result<T> code(int code) {
        this.code = code;
        return this;
    }

    public Result<T> msg(String msg) {
        this.msg = msg;
        return this;
    }

    public Result<T> data(T data) {
        this.data = data;
        return this;
    }

}
