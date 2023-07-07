package com.polaris.lesscode.util;


import com.polaris.lesscode.enums.DescEnum;

import java.io.Serializable;
import java.util.Objects;

/**
 * 枚举工具类
 *
 * @author roamer
 * @version v1.0
 * @date 2020-09-01 14:56
 */
public final class DescEnumUtils {

    private DescEnumUtils() {
    }
    
    public static <K extends Serializable, E extends Enum<E> & DescEnum<K>> String getDesc(Class<E> descEnumClass,
                                                                                           K code) {
        if (null == code) {
            return null;
        }
        for (DescEnum<K> enumConstant : descEnumClass.getEnumConstants()) {
            if (Objects.equals(enumConstant.getCode(), code)) {
                return enumConstant.getDesc();
            }
        }
        return null;
    }

    public static <K extends Serializable, E extends Enum<E> & DescEnum<K>> E getEnum(Class<E> descEnumClass, K code) {
        if (null == code) {
            return null;
        }
        for (DescEnum<K> enumConstant : descEnumClass.getEnumConstants()) {
            if (Objects.equals(enumConstant.getCode(), code)) {
                return (E) enumConstant;
            }
        }
        return null;
    }
}
