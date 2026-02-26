package org.shancm.serviceprovidercore.advice;

import org.shancm.common.domain.Result;
import org.shancm.common.domain.enums.ResultCode;
import org.shancm.common.exception.BusinessException;
import org.shancm.common.exception.DataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * @author by shancm
 * @description ExceptionAdvice
 * @since 2026-02-22 11:44
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    public Result<Void> handleBusinessException(BusinessException e) {
        return Result.failed(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; "));
        return Result.failed(ResultCode.VALIDATE_FAILED, msg);
    }
    @ExceptionHandler(DataException.class)
    public Result<Void> handleDataException(Exception e) throws DataException {
        log.error("数据异常", e);
        throw new DataException(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        log.error("系统异常", e);
        return Result.failed(e.getCause().toString());
    }
}
