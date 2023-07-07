/**
 * 
 */
package com.polaris.lesscode.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Bomb
 *
 */
public class UsefulRegexUtils {

	/**
	 * 正则：手机号（精确）
	 * <p>
	 * 移动：134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188
	 * </p>
	 * <p>
	 * 联通：130、131、132、145、155、156、175、176、185、186
	 * </p>
	 * <p>
	 * 电信：133、153、173、177、180、181、189
	 * </p>
	 * <p>
	 * 全球星：1349
	 * </p>
	 * <p>
	 * 虚拟运营商：170
	 * </p>
	 */
	public static final String REGEX_MOBILE_EXACT = "^(?:\\+?86)?1(?:3\\d{3}|5[^4\\D]\\d{2}|8\\d{3}|7[^29\\D](?(?<=4)(?:0\\d|1[0-2]|9\\d)|\\d{2})|9[189]\\d{2}|6[567]\\d{2}|4(?:[14]0\\d{3}|[68]\\d{4}|[579]\\d{2}))\\d{6}$";

	/**
	 * 正则：身份证号码15位
	 */
	public static final String REGEX_ID_CARD15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
	/**
	 * 正则：身份证号码18位
	 */
	public static final String REGEX_ID_CARD18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9Xx])$";
	/**
	 * 正则：邮箱
	 */
	public static final String REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

	/**
	 * 正则：URL
	 */
	public static final String REGEX_URL = "[a-zA-z]+://[^\\s]*";
	/**
	 * 正则：汉字
	 */
	public static final String REGEX_ZH = "^[\\u4e00-\\u9fa5]+$";
	/**
	 * 正则：用户名，取值范围为a-z,A-Z,0-9,"_",汉字，不能以"_"结尾,用户名必须是6-20位
	 */
	public static final String REGEX_USERNAME = "^[\\w\\u4e00-\\u9fa5]{6,20}(?<!_)$";
	/**
	 * 正则：yyyy-MM-dd格式的日期校验，已考虑平闰年
	 */
	public static final String REGEX_DATE = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$";
	/**
	 * 正则：IP地址
	 */
	public static final String REGEX_IP = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";

	/**
	 * 正则：双字节字符(包括汉字在内)
	 */
	public static final String REGEX_DOUBLE_BYTE_CHAR = "[^\\x00-\\xff]";
	/**
	 * 正则：空白行
	 */
	public static final String REGEX_BLANK_LINE = "\\n\\s*\\r";

	/**
	 * 正则：中国邮政编码
	 */
	public static final String REGEX_ZIP_CODE = "[1-9]\\d{5}(?!\\d)";
	/**
	 * 正则：正整数
	 */
	public static final String REGEX_POSITIVE_INTEGER = "^[1-9]\\d*$";
	/**
	 * 正则：负整数
	 */
	public static final String REGEX_NEGATIVE_INTEGER = "^-[1-9]\\d*$";
	/**
	 * 正则：整数
	 */
	public static final String REGEX_INTEGER = "^-?[1-9]\\d*$";
	/**
	 * 正则：非负整数(正整数 + 0)
	 */
	public static final String REGEX_NOT_NEGATIVE_INTEGER = "^[1-9]\\d*|0$";
	/**
	 * 正则：非正整数（负整数 + 0）
	 */
	public static final String REGEX_NOT_POSITIVE_INTEGER = "^-[1-9]\\d*|0$";
	/**
	 * 正则：正浮点数
	 */
	public static final String REGEX_POSITIVE_FLOAT = "^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*$";
	/**
	 * 正则：负浮点数
	 */
	public static final String REGEX_NEGATIVE_FLOAT = "^-[1-9]\\d*\\.\\d*|-0\\.\\d*[1-9]\\d*$";
	
	/**
	 * 正则: 时间
	 */
	public static final String REGEX_DATETIME = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29) (20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d$";

	/**
	 * millsecond long pattern.
	 */
	public static final String REGEX_TIME_MILLS = "^\\d{13}$";
	
	/**
	  * second long pattern.
	  */
	 public static final String SECOND_LONG_REGEX_PATTERN = "^\\d{10}$";
	 
	 /**
	  * date regex string.
	  */
	 public static final String DATE_REGEX_PATTERN = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$";
	 
	 /**
	  * time regex string.
	  */
	 public static final String TIME_REGEX_PATTERN = "^((((0?[0-9])|([1][0-9])|([2][0-4]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
	 
	 /**
	  * date time regex String.
	  */
	 public static final String DATETIME_REGEX_PATTERN = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)\\s+([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$";
	
	/**
	 * can't define constructor.
	 */
	private UsefulRegexUtils() {
		throw new UnsupportedOperationException("u can't instantiate me...");
	}

	

	/**
	 * 验证手机号（精确）
	 *
	 * @param input 待验证文本
	 * @return {@code true}: 匹配<br>
	 *         {@code false}: 不匹配
	 */
	public static boolean isMobileExact(CharSequence input) {
		return isMatch(REGEX_MOBILE_EXACT, input);
	}

	

	/**
	 * 验证身份证号码15位
	 *
	 * @param input 待验证文本
	 * @return {@code true}: 匹配<br>
	 *         {@code false}: 不匹配
	 */
	public static boolean isIDCard15(CharSequence input) {
		return isMatch(REGEX_ID_CARD15, input);
	}

	/**
	 * 验证身份证号码18位
	 *
	 * @param input 待验证文本
	 * @return {@code true}: 匹配<br>
	 *         {@code false}: 不匹配
	 */
	public static boolean isIDCard18(CharSequence input) {
		return isMatch(REGEX_ID_CARD18, input);
	}

	/**
	 * 验证邮箱
	 *
	 * @param input 待验证文本
	 * @return {@code true}: 匹配<br>
	 *         {@code false}: 不匹配
	 */
	public static boolean isEmail(CharSequence input) {
		return isMatch(REGEX_EMAIL, input);
	}

	/**
	 * 验证URL
	 *
	 * @param input 待验证文本
	 * @return {@code true}: 匹配<br>
	 *         {@code false}: 不匹配
	 */
	public static boolean isURL(CharSequence input) {
		return isMatch(REGEX_URL, input);
	}

	/**
	 * 验证汉字
	 *
	 * @param input 待验证文本
	 * @return {@code true}: 匹配<br>
	 *         {@code false}: 不匹配
	 */
	public static boolean isZh(CharSequence input) {
		return isMatch(REGEX_ZH, input);
	}

	/**
	 * 验证用户名
	 * <p>
	 * 取值范围为a-z,A-Z,0-9,"_",汉字，不能以"_"结尾,用户名必须是6-20位
	 * </p>
	 *
	 * @param input 待验证文本
	 * @return {@code true}: 匹配<br>
	 *         {@code false}: 不匹配
	 */
	public static boolean isUsername(CharSequence input) {
		return isMatch(REGEX_USERNAME, input);
	}

	/**
	 * 验证yyyy-MM-dd格式的日期校验，已考虑平闰年
	 *
	 * @param input 待验证文本
	 * @return {@code true}: 匹配<br>
	 *         {@code false}: 不匹配
	 */
	public static boolean isDate(CharSequence input) {
		return isMatch(REGEX_DATE, input);
	}

	/**
	 * 验证IP地址
	 *
	 * @param input 待验证文本
	 * @return {@code true}: 匹配<br>
	 *         {@code false}: 不匹配
	 */
	public static boolean isIP(CharSequence input) {
		return isMatch(REGEX_IP, input);
	}
	
	/**
	 * validate a string is a datetime string.
	 * @param input String
	 * @return boolean
	 */
	public static boolean isDateTime(String input) {
		return isMatch(REGEX_DATETIME, input);
	}

	/**
	 * 判断是否匹配正则
	 *
	 * @param regex 正则表达式
	 * @param input 要匹配的字符串
	 * @return {@code true}: 匹配<br>
	 *         {@code false}: 不匹配
	 */
	public static boolean isMatch(String regex, CharSequence input) {
		return input != null && input.length() > 0 && Pattern.matches(regex, input);
	}

	/**
	 * 获取正则匹配的部分
	 *
	 * @param regex 正则表达式
	 * @param input 要匹配的字符串
	 * @return 正则匹配的部分
	 */
	public static List<String> getMatches(String regex, CharSequence input) {
		if (input == null)
			return null;
		List<String> matches = new ArrayList<>();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			matches.add(matcher.group());
		}
		return matches;
	}

	/**
	 * 获取正则匹配分组
	 *
	 * @param input 要分组的字符串
	 * @param regex 正则表达式
	 * @return 正则匹配分组
	 */
	public static String[] getSplits(String input, String regex) {
		if (input == null)
			return null;
		return input.split(regex);
	}

	/**
	 * 替换正则匹配的第一部分
	 *
	 * @param input       要替换的字符串
	 * @param regex       正则表达式
	 * @param replacement 代替者
	 * @return 替换正则匹配的第一部分
	 */
	public static String getReplaceFirst(String input, String regex, String replacement) {
		if (input == null)
			return null;
		return Pattern.compile(regex).matcher(input).replaceFirst(replacement);
	}

	/**
	 * 替换所有正则匹配的部分
	 *
	 * @param input       要替换的字符串
	 * @param regex       正则表达式
	 * @param replacement 代替者
	 * @return 替换所有正则匹配的部分
	 */
	public static String getReplaceAll(String input, String regex, String replacement) {
		if (input == null)
			return null;
		return Pattern.compile(regex).matcher(input).replaceAll(replacement);
	}

	/**
	 * check is a timemills string.
	 * @param source long string
	 * @return Boolean
	 */
	public static boolean isTimeMills(String source) {
		if(!StringUtils.isEmpty(source)) {
			return false;
		}
		return isMatch(REGEX_TIME_MILLS,source);
	}
}
