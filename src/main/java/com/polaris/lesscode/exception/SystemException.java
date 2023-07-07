/**
 * 
 */
package com.polaris.lesscode.exception;

import com.polaris.lesscode.vo.AbstractResultCode;

/**
 * @author admin
 *
 */
public class SystemException extends AbstractBaseException {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -7584135679375257111L;

	/**
	 * construct with code & message
	 * 
	 * @param code
	 * @param message
	 */
	public SystemException(Integer code,String message) {
		super(code,message);
	}
	
	/**
	 * absctract result code
	 * 
	 * @param resultCode {@link AbstractResultCode}
	 */
	public SystemException(AbstractResultCode resultCode) {
		super(resultCode.getCode(),resultCode.getMessage());
	}

	/**
	 * construct with code & message & throwable
	 * @param code
	 * @param message
	 * @param throwable
	 */
	public SystemException(Integer code,String message, Throwable throwable) {
		super(code, message, throwable);
	}
	
	/**
	 * absctract result code
	 * 
	 * @param resultCode {@link AbstractResultCode}
	 * @param throwable 
	 */
	public SystemException(AbstractResultCode resultCode, Throwable throwable) {
		super(resultCode.getCode(),resultCode.getMessage(), throwable);
	}
}
