package org.shancm.common.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.shancm.common.domain.enums.ResultCode;

import java.time.Instant;

/**
 * @author by shancm
 * @description Result<T>
 * @since 2026-02-21 22:41
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)  // 序列化时忽略 null 字段
public class Result<T> {
    private T data;

    private int code;
    private String msg;

    private Instant timestamp;
    // 私有构造方法，只能通过静态方法创建
    /*private Result() {
        this.timestamp = Instant.now();
    }*/

    // 全参构造（供内部使用）
    private Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

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

    // ========== 其他快捷方法 ==========
    public static <T> Result<T> error(ResultCode resultCode) {
        return failed(resultCode);
    }

    public static <T> Result<T> error(String msg) {
        return failed(msg);
    }

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
