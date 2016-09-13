package cn.inovance.iotgp.common.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtil {

	/*
	 * public static void main(String[] args) {
	 * 
	 * }
	 */
	/**
	 * 日期转字符串
	 * 
	 * @param date
	 *            日期
	 * @param pattern
	 *            格式
	 * @return
	 */
	public static String dateToString(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		}
		return "";
	}

	/**
	 * 日期转字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		return dateToString(date, "yyyy-MM-dd hh:mm:ss");
	}

	public static String getDateTime(String format, Date date) {
		return dateToString(date, format);
	}

	public static Date strToDateLong(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}
	public static Date strDayToDateLong(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}
	/**
	 * 东八区时间
	 * @param date
	 * @return
	 */
	public static Date getLocalTime(Date date){
		 Calendar cal = Calendar.getInstance();
	     cal.setTime(date);//date 换成已经已
	     cal.add(Calendar.HOUR_OF_DAY, -8);
	     return cal.getTime();
	}
}
