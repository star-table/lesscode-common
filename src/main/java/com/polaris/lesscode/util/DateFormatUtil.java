package com.polaris.lesscode.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author Liu.B.J
 */
@Deprecated
public class DateFormatUtil {

    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

    public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String EL_YYYY_MM_DD = "[0-9]{4}-[0-9]{2}-[0-9]{2}";

    public static final String EL_YYYY_MM_DD_HH_MM = "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}";

    public static final String EL_YYYY_MM_DD_HH_MM_SS = "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}";

    private static final DateTimeFormatter dtf_YYYY_MM_DD = DateTimeFormatter.ofPattern(DATE_FORMAT_YYYY_MM_DD);

    private static final DateTimeFormatter dtf_YYYY_MM_DD_HH_MM = DateTimeFormatter.ofPattern(DATE_FORMAT_YYYY_MM_DD_HH_MM);

    private static final DateTimeFormatter dtf_YYYY_MM_DD_HH_MM_SS = DateTimeFormatter.ofPattern(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);

    /**
     * 格式化String时间
     * @param time String类型时间
     * @param timeFromat String类型格式
     * @return 格式化后的Instant时间戳
     */
    public static Instant parseStrToDate(String time, String timeFromat) {
        if (time == null || time.equals("")) {
            return null;
        }
        Instant instant = null;
        LocalDateTime localDateTime = null;
        try{
            if(timeFromat.equals(DATE_FORMAT_YYYY_MM_DD)){
                localDateTime = LocalDateTime.parse(time.substring(0, 10), dtf_YYYY_MM_DD);
            }else if (timeFromat.equals(DATE_FORMAT_YYYY_MM_DD_HH_MM)){
                localDateTime = LocalDateTime.parse(time.substring(0, 16), dtf_YYYY_MM_DD_HH_MM);
            }else if (timeFromat.equals(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS)){
                localDateTime = LocalDateTime.parse(time, dtf_YYYY_MM_DD_HH_MM_SS);
            }
        }catch(Exception e){
        }
        if(localDateTime != null){
            instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant().plusMillis(TimeUnit.HOURS.toMillis(8));
        }
        return instant;
    }

    /**
     * 格式化Instant时间戳
     * @param timestamp Instant时间戳类型
     * @param timeFromat String类型格式
     * @return 格式化后的字符串
     */
    public static String parseDateToStr(Instant timestamp, String timeFromat){
        if(timestamp != null){
            try{
                LocalDateTime ldt = timestamp
                        .atZone( ZoneId.systemDefault() )
                        .toLocalDateTime();
                if(timeFromat.equals(DATE_FORMAT_YYYY_MM_DD)){
                    return ldt.format(dtf_YYYY_MM_DD);
                }else if (timeFromat.equals(DATE_FORMAT_YYYY_MM_DD_HH_MM)){
                    return ldt.format(dtf_YYYY_MM_DD_HH_MM);
                }else if (timeFromat.equals(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS)){
                    return ldt.format(dtf_YYYY_MM_DD_HH_MM_SS);
                }
            }catch(Exception e){
            }
        }
        return "";
    }

    public static Boolean checkDateFormat(String date, String el){
        Pattern p = Pattern.compile(el);
        Matcher m = p.matcher(date);
        boolean dateFlag = m.matches();
        if (!dateFlag) {
            return false;
        }
        return true;
    }

}
