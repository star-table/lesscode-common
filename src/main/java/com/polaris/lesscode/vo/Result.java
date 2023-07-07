package com.polaris.lesscode.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

import com.polaris.lesscode.consts.CommonConsts;

@Data
@ApiModel(value="接口返回对象", description="接口返回对象")
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 成功标志
	 */
	@ApiModelProperty(value = "成功标志")
	@Deprecated
	private boolean success = true;

	/**
	 * 返回处理消息
	 */
	@ApiModelProperty(value = "返回处理消息")
	private String message = "操作成功！";

	/**
	 * 返回代码
	 */
	@ApiModelProperty(value = "返回代码")
	private Integer code = BaseResultCode.OK.getCode();
	
	/**
	 * 返回数据对象 data
	 */
	@ApiModelProperty(value = "返回数据对象")
	private T data;
	
	/**
	 * 时间戳
	 */
	@ApiModelProperty(value = "时间戳")
	private long timestamp = System.currentTimeMillis();

	public Result() {
		
	}
	
	public Result<T> success(String message) {
		this.message = message;
		this.code = BaseResultCode.OK.getCode();
		this.success = true;
		return this;
	}
	
	
	public static Result<Object> ok() {
		Result<Object> r = new Result<Object>();
		r.setSuccess(true);
		r.setCode(BaseResultCode.OK.getCode());
		r.setMessage(BaseResultCode.OK.getMessage());
		r.setMessage("成功");
		return r;
	}
	
	public static <T> Result<T> ok(T data) {
		Result<T> r = new Result<T>();
		r.setSuccess(true);
		r.setCode(BaseResultCode.OK.getCode());
		r.setMessage(BaseResultCode.OK.getMessage());
		r.setData(data);
		return r;
	}
	
	public static Result<Object> error(String msg) {
		return error(BaseResultCode.INTERNAL_SERVER_ERROR.getCode(), msg);
	}
	
	public static Result<Object> error(AbstractResultCode rc) {
		return error(rc.getCode(), rc.getMessage());
	}
	
	public static Result<Object> error(int code, String msg) {
		Result<Object> r = new Result<Object>();
		r.setCode(code);
		r.setMessage(msg);
		r.setSuccess(false);
		return r;
	}

	public Result<T> error500(String message) {
		this.message = message;
		this.code = BaseResultCode.INTERNAL_SERVER_ERROR.getCode();
		this.success = false;
		return this;
	}
	/**
	 * 无权限访问返回结果
	 */
	public static Result<Object> noauth(String msg) {
		return error(CommonConsts.SC_JEECG_NO_AUTHZ, msg);
	}
}