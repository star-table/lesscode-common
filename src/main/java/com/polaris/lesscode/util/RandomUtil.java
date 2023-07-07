package com.polaris.lesscode.util;

import java.util.Random;

public class RandomUtil {

	private static final String STR = "abcdef0123456789";

	public static String randomString(int length){
		Random random=new Random();
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<length;i++){
			int number=random.nextInt(STR.length());
			sb.append(STR.charAt(number));
		}
		return sb.toString();
	}
}
