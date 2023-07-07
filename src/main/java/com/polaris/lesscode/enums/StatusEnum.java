package com.polaris.lesscode.enums;

public enum StatusEnum {

    ACTIVE(1, "可用"),
    NO_ACTIVE(2, "不可用");

    private Integer code;
    private String desc;

    StatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }


    public static StatusEnum formatOrNull(Integer code) {
        if (null == code) {
            return null;
        }
        StatusEnum[] enums = values();
        for (StatusEnum _enu : enums) {
            if (_enu.getCode().equals(code)) {
                return _enu;
            }
        }

        return null;
    }


    public static StatusEnum format(Integer code) {
        StatusEnum se = formatOrNull(code);
        return null == se ? ACTIVE : se;
    }

}