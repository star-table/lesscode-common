/**
 * 
 */
package com.polaris.lesscode.exception;

import lombok.Getter;

/**
 * @author Bomb.
 *
 */
public abstract class AbstractBaseException extends RuntimeException {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -8088475456529581491L;
	
	/**
	 * return code.
	 */
	@Getter
	private Integer code;

	/**
	 * message.
	 */
	@Getter
	private String message;

	/**
	 * construct with code & message.
	 * @param code code
	 * @param message message
	 */
	public AbstractBaseException(Integer code, String message) {
		super(message);
		this.message = message;
		this.code = code;
	}
	
	/**
	 * construct with code . message * throwable.
	 * @param code code
	 * @param message message
	 * @param throwable throwable
	 */
	public AbstractBaseException(Integer code, String message,Throwable throwable) {
		super(message,throwable);
		this.message = message;
		this.code = code;
	}

}
