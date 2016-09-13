package cn.inovance.iotgp.cdSimulator.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatetimeUtil {
	private static final String dateFormat1 = "yyyy-MM-dd HH:mm:ss";
    private static final String dateFormat2 = "yyyy-MM-dd";
    private static final String dateFormat3 = "yyyyMMddHHmmss";
    private static DateFormat format1 = new SimpleDateFormat(dateFormat1);
    private static DateFormat format2 = new SimpleDateFormat(dateFormat2);
    private static DateFormat format3 = new SimpleDateFormat(dateFormat3);

    /**
     * 日期格式化
     * @param date  时间
     * @return  yyyy-MM-dd HH:mm:ss类型字符串日期
     */
    public static String getDateBySecond(Date date) {

        String result = "";
        if (null != date) {
            result = format1.format(date);
        }
        return result;
    }

    /**
     * 根据时间获取年月日
     * @param date
     * @return yyyy-MM-dd类型字符串日期
     */
    public static String getDateByDay(Date date) {

        String result = "";
        if (null != date) {
            result = format2.format(date);
        }
        return result;
    }

    public static String getDateByNotStyle(Date date) {

        String result = "";
        if (null != date) {
            result = format3.format(date);
        }
        return result;
    }

    public static Date getDateByString(String dateString) {
        try {
            Date date = format2.parse(dateString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
