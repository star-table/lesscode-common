/**
 * 
 */
package com.polaris.lesscode.exception;

import com.polaris.lesscode.vo.AbstractResultCode;

/**
 * @author Bomb.
 *
 */
public class BusinessException extends AbstractBaseException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8484335764472368237L;


	/**
	 * construct with code & message.
	 * @param code code
	 * @param message message
	 */
	public BusinessException(Integer code, String message) {
		super(code,message);
	}
	
	/**
	 * absctract result code
	 * 
	 * @param resultCode {@link AbstractResultCode}
	 */
	public BusinessException(AbstractResultCode resultCode) {
		super(resultCode.getCode(),resultCode.getMessage());
	}

	/**
	 * construct with code & message & throwable.
	 * @param code
	 * @param message
	 * @param throwable
	 */
	public BusinessException(Integer code, String message, Throwable throwable) {
		super(code,message, throwable);
	}
	
	/**
	 * absctract result code
	 * 
	 * @param resultCode {@link AbstractResultCode}
	 * @param throwable 
	 */
	public BusinessException(AbstractResultCode resultCode, Throwable throwable) {
		super(resultCode.getCode(),resultCode.getMessage(), throwable);
	}
}
