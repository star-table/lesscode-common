/**
 * 
 */
package com.polaris.lesscode.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

/**
 * @author Bomb.
 *
 */
public class ApplicationContextUtil {

	private static ApplicationContext applicationContext;
	
	public static void setApplicationContext(ApplicationContext context) throws BeansException {
		applicationContext = context;
	}
	
	public static  <T> T  getBeanObjectByType(Class<T> clazz) {
		return applicationContext.getBean(clazz);
	}
	
	/**
	 * get bean from spring context by name and type.
	 * @param beanName
	 * @param requiredType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBeanObjectByName(String beanName) {
		return (T)applicationContext.getBean(beanName);
	}
}
