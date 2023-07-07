package com.polaris.lesscode.enums;

public enum DeleteEnum {


    No_Deleted(2, "未删除"),
    Deleted(1, "已删除");

    private Integer code;
    private String desc;

    DeleteEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }


    public static DeleteEnum formatOrNull(Integer code) {
        if (null == code) {
            return null;
        }
        DeleteEnum[] enums = values();
        for (DeleteEnum _enu : enums) {
            if (_enu.getCode().equals(code)) {
                return _enu;
            }
        }

        return null;
    }


    public static DeleteEnum format(Integer code) {
        DeleteEnum se = formatOrNull(code);
        return null == se ? No_Deleted : se;
    }

}
