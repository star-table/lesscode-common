/**
 * 
 */
package com.polaris.lesscode.exception;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import feign.codec.DecodeException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import io.sentry.Sentry;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.polaris.lesscode.vo.BaseResultCode;
import com.polaris.lesscode.vo.Result;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Bomb
 *
 */
@Slf4j
public class CommonsExceptionHandler {

	/**
	 * handleing exception for ConstraintViolationException.
	 * 
	 * @param ex ConstraintViolationException
	 * @return Response failed
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	public Result<?> handleResourceNotFoundException(ConstraintViolationException ex) {
		Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
		StringBuilder strBuilder = new StringBuilder();
		for (ConstraintViolation<?> violation : violations) {
			strBuilder.append(violation.getMessage() + "\n");
		}
		strBuilder.deleteCharAt(strBuilder.length() - 1);
		log.info(String.format("request error, parameters invalid:%s", strBuilder.toString()), ex);
		return Result.error(BaseResultCode.PARAM_ERROR.getCode(), strBuilder.toString());
	}

	/**
	 * handling exception for BindException.
	 * 
	 * @param ex BindException
	 * @return Response failed
	 */
	@ExceptionHandler(BindException.class)
	@ResponseBody
	public Result<?> bindException(BindException ex) {
		StringBuilder strBuilder = new StringBuilder();
		List<FieldError> errors = ex.getBindingResult().getFieldErrors();
		for (FieldError fieldError : errors) {
			strBuilder.append(fieldError.getDefaultMessage() + ",");
		}
		strBuilder.deleteCharAt(strBuilder.length() - 1);
		log.info(String.format("request error, parameters invalid:%s", strBuilder.toString()), ex);
		return Result.error(BaseResultCode.PARAM_ERROR.getCode(), strBuilder.toString());
	}

	/**
	 * handing MethodArgumentNotValidException.
	 * 
	 * @param ex MethodArgumentNotValidException
	 * @return Response
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	public Result<?> bindMethodIllegalArgumentException(IllegalArgumentException ex) {
		log.info(String.format("request error, parameters invalid:%s", ex.getMessage()), ex);
		return Result.error(BaseResultCode.PARAM_ERROR.getCode(), ex.toString());
	}

	/**
	 * handing MethodArgumentNotValidException.
	 * 
	 * @param ex MethodArgumentNotValidException
	 * @return Response
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public Result<?> bindMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		String traceId = MDC.get("traceId");
		StringBuilder strBuilder = new StringBuilder();
		List<FieldError> errors = ex.getBindingResult().getFieldErrors();
		for (FieldError fieldError : errors) {
			strBuilder.append(fieldError.getDefaultMessage() + ",");
		}
		strBuilder.deleteCharAt(strBuilder.length() - 1);
		log.error(String.format("捕获到的错误: traceId: %s, Exception: [%s] %s, request error, parameters invalid:%s", traceId, ex.getParameter().getParameterName(), ex, strBuilder));
		Sentry.capture(ex);
		return Result.error(BaseResultCode.PARAM_ERROR.getCode(), "系统开了个小差，程序员介入中...");
	}

	/**
	 * handing MethodArgumentTypeMismatchException.
	 *
	 * @param ex MethodArgumentTypeMismatchException
	 * @return Response
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseBody
	public Result<?> bindMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
		String traceId = MDC.get("traceId");
		log.error(String.format("捕获到的错误: traceId: %s, Exception: [%s] %s", traceId, ex.getParameter().getParameterName(), ex));
		Sentry.capture(ex);
		return Result.error(BaseResultCode.PARAM_ERROR.getCode(), "系统开了个小差，程序员介入中...");
	}

	/**
	 * handing NoHandlerFoundException.
	 * 
	 * @param ex NoHandlerFoundException
	 * @return Response
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseBody
	public Result<?> bindNoHandlerFoundException(NoHandlerFoundException ex) {
		String errorMsg = String.format("request error, %s:%s can't be found", ex.getHttpMethod(), ex.getRequestURL());
		log.debug(errorMsg);
		return Result.error(BaseResultCode.PATH_NOT_FOUND.getCode(), errorMsg);
	}
	
	@ExceptionHandler(HystrixRuntimeException.class)
	@ResponseBody
	public Result<?> bindHystrixRuntimeException(HystrixRuntimeException ex){
		if(ex.getCause() instanceof DecodeException){
			if(ex.getCause().getCause() instanceof BusinessException){
				return bindBizException((BusinessException) ex.getCause().getCause());
			}else if(ex.getCause().getCause() instanceof SystemException){
				return bindSysException((SystemException) ex.getCause().getCause());
			}
		}

		String traceId = MDC.get("traceId");
		String errStr = String.format("捕获到的错误: traceId: %s, HystrixRuntimeException: %s", traceId, ex.toString());
		log.error(errStr);
		Sentry.capture(ex);
		return Result.error(BaseResultCode.FALLBACK_ERROR);
	}

	/**
	 * handing BizException.
	 * 
	 * @param ex BizException
	 * @return Response
	 */
	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	public Result<?> bindBizException(BusinessException ex) {
		String traceId = MDC.get("traceId");
		String errStr = String.format("捕获到的错误: traceId: %s, BusinessException: %s", traceId, ex.toString());
		log.error(errStr);
//		Sentry.capture(ex);
		return Result.error(ex.getCode(), ex.getMessage());
	}

	/**
	 * handing System Exception.
	 * 
	 * @param ex SysException
	 * @return Response
	 */
	@ExceptionHandler(SystemException.class)
	@ResponseBody
	public Result<?> bindSysException(SystemException ex) {
		String traceId = MDC.get("traceId");
		String errStr = String.format("捕获到的错误: traceId: %s, SystemException: %s", traceId, ex.toString());
		log.error(errStr);
		Sentry.capture(ex);
		return Result.error(BaseResultCode.SYS_ERROR_MSG.getCode(), ex.getMessage());
	}



	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Result<?> bindException(Exception ex) {
		String traceId = MDC.get("traceId");
		String errStr = String.format("捕获到的错误: traceId: %s, Exception: %s", traceId, ex.toString());
		log.error(errStr);
		Sentry.capture(ex);
//		return Result.error(BaseResultCode.INTERNAL_SERVER_ERROR.getCode(), ex.getMessage());
		return Result.error(BaseResultCode.INTERNAL_SERVER_ERROR.getCode(), "系统开了个小差，程序员介入中...");
	}
}
