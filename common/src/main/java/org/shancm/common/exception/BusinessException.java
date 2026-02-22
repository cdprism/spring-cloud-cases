package org.shancm.common.exception;

import lombok.Getter;
import org.shancm.common.domain.enums.ResultCode;

import java.io.Serial;

/**
 * @author by asheng
 * @description ServiceException
 * @since 2018/7/18
 */
@Getter
public class BusinessException extends Exception {

    @Serial
    private static final long serialVersionUID = 2322414292460262925L;

    private final int code;
    private String msg;

    public BusinessException() {
        this(ResultCode.FAILED);
    }

    public BusinessException(ResultCode resultCode) {
        this(resultCode.getCode(), resultCode.getMsg());
    }

    public BusinessException(ResultCode resultCode, String retMsg) {
        this(resultCode);
        this.msg = retMsg;
    }

    public BusinessException(int errorCode, String errorMsg) {
        this(errorCode, errorMsg, null);
    }

    public BusinessException(ResultCode resultCode, Throwable cause) {
        this(resultCode.getCode(), resultCode.getMsg(), cause);
    }

    public BusinessException(int retCode, String retMsg, Throwable cause) {
        super(null, cause);
        this.code = retCode;
        this.msg = retMsg;
    }

    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder()
                .append("[ResCode]:")
                .append(this.getCode())
                .append(",")
                .append("[ResMsg]:")
                .append(this.getMsg());

        if (this.getCause() != null) {
            sb.append(",");
            sb.append("[Cause]:")
                    .append(this.getCause());
        }
        return sb.toString();
    }
}
