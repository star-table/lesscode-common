package com.polaris.lesscode.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegxUtils {

	private static String[] parsePatterns = {
			"yyyy-MM-dd HH:mm:ss",
			"yyyy-MM-dd",
			"yyyy年MM月dd日",
			"yyyy-MM-dd HH:mm", 
			"yyyy/MM/dd",
			"yyyy/MM/dd HH:mm:ss", 
			"yyyy/MM/dd HH:mm", 
			"yyyyMMdd"
	};
	private static Pattern emailPattern = Pattern.compile("^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$");
	private static Pattern numericPattern = Pattern.compile("[0-9]*");

	public static boolean isNumeric(String str){
		return numericPattern.matcher(str).matches();
	}

	public static boolean isBoolean(String str) {
		if (StringUtils.isEmpty(str)) {
			return false;
		}
		if(		str.equals("是") 
				|| str.equals("否")
				|| str.equalsIgnoreCase("true") 
				|| str.equalsIgnoreCase("false")
				|| str.equals("1") 
				|| str.equals("2") 
				|| str.equals("对") 
				|| str.equals("错")
				) {
			return true;
		}
		return false;
	}

	public static boolean isFloat(String str) {
		try {
			Double.valueOf(str);
		}catch(Throwable e) {
			return false;
		}
		return true;
	}

	public static boolean isDate(String str) {
		if (StringUtils.isEmpty(str)) {
			return false;
		}
		try {
			DateUtils.parseDate(str, parsePatterns);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public static boolean isEmail(String str) {
		Matcher matcher = emailPattern.matcher(str);
		return matcher.matches();
	}

}
