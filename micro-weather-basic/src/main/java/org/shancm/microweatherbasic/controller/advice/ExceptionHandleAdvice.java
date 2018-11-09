package org.shancm.microweatherbasic.controller.advice;

import org.shancm.microweatherbasic.domain.BaseRes;
import org.shancm.microweatherbasic.domain.RetCodeEnum;
import org.shancm.microweatherbasic.exception.BusinessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author shancm
 * @package org.shancm.microweatherbasic.controller.advice
 * @description:
 * @date 2018/11/8
 */
@ControllerAdvice
public class ExceptionHandleAdvice {

	@ExceptionHandler(value = {Exception.class, BusinessException.class})
	public BaseRes exceptionHandle(Exception e){
		if(e instanceof BusinessException){
			BusinessException be = (BusinessException) e;
			return BaseRes.error(be.getRetCode(), be.getRetMsg());
		}

		return BaseRes.error(RetCodeEnum.FAIL);

	}

}
