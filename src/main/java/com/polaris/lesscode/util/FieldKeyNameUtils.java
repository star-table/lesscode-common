package com.polaris.lesscode.util;

import static com.polaris.lesscode.consts.CommonConsts.KEY_NAME_PREFIX;

/**
 * @author wanglei
 * @version 1.0
 * @date 2020-08-04 11:39 上午
 */
public class FieldKeyNameUtils {

    public static String generateKeyName(){
        long currentTime = System.currentTimeMillis();
        try {
            Thread.sleep(1);
            return KEY_NAME_PREFIX + currentTime;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
