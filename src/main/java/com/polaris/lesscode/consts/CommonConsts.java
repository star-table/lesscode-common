package com.polaris.lesscode.consts;

import com.polaris.lesscode.enums.DeleteEnum;
import com.polaris.lesscode.enums.StatusEnum;

public interface CommonConsts {

	
	/** {@code 500 Server Error} (HTTP/1.0 - RFC 1945) */
    public static final Integer SC_INTERNAL_SERVER_ERROR_500 = 500;
    /** {@code 200 OK} (HTTP/1.0 - RFC 1945) */
    public static final Integer SC_OK_200 = 200;
    
    /**访问权限认证未通过 510*/
    public static final Integer SC_JEECG_NO_AUTHZ=510;

    public final static String LOCK_ROOT_KEY = "lesscode:";

    public final static String NULL = "NULL";

    /**
     *  date time format pattern.
     */
    public final static String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    
    /**
     *  date format pattern.
     */
    public final static String DEFAULT_SIMPLE_DATE_PATTERN = "yyyy-MM-dd";

    public final static Integer NO_DELETE = DeleteEnum.No_Deleted.getCode();
    public final static Integer DELETED = DeleteEnum.Deleted.getCode();

    public final static Integer ENABLE = StatusEnum.ACTIVE.getCode();
    public final static Integer DISABLE = StatusEnum.NO_ACTIVE.getCode();

    public final static Integer TRUE = 1;
    public final static Integer FALSE = 2;

    public static final String DEFAULT_HEADER_CONFIG = "{\"allowBlank\":true,\"visible\":true,\"enable\":true}";

    public static final String KEY_NAME_PREFIX = "_field_";
    
    /**
     * fallback mocked map bean name, used for global.
     */
    public static final String FALLBACK_MOCKED_MAP_BEAN_NAME = "fallbackMockedFlgBean";
    
    /**
     * .默认查询数据的最大记录数.
     */
    public static final Integer DEFAULT_MAX_RECORDS_SIZE = 200;

}
