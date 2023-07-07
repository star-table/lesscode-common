package com.polaris.lesscode.enums;

import java.io.Serializable;

/**
 * 基础枚举接口
 *
 * @author roamer
 * @version v1.0
 * @date 2020-09-01 14:56
 */
public interface DescEnum<K extends Serializable> {

    /**
     * 获取枚举CODE
     *
     * @return 枚举CODE
     */
    K getCode();

    /**
     * 获取枚举说明
     *
     * @return 枚举说明
     */
    String getDesc();
}
