package com.polaris.lesscode.util;

/**
 * @Author Liu.B.J
 */
public class StrUtil {

    public static String null2String(String paramString)
    {
        return paramString == null ? "" : paramString;
    }

    public static String null2String(Object paramObject)
    {
        return paramObject == null ? "" : paramObject.toString();
    }

}
