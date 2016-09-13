package cn.inovance.iotgp.common.msg.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.MissingResourceException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * Date Utility Class used to convert Strings to Dates and Timestamps
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Modified by
 *         <a href="mailto:dan@getrolling.com">Dan Kibler </a> to correct time
 *         pattern. Minutes should be mm not MM (MM is month).
 */
public class DateUtil {
	private static Log log = LogFactory.getLog(DateUtil.class);
	private static final String TIME_PATTERN = "HH:mm";

	/**
	 * Checkstyle rule: utility classes should not have public constructor
	 */
	private DateUtil() {
	}

	/**
	 * Return default datePattern (MM/dd/yyyy)
	 * 
	 * @return a string representing the date pattern on the UI
	 */
	public static String getDatePattern() {
		Locale locale = LocaleContextHolder.getLocale();
		String defaultDatePattern;
		try {
			// defaultDatePattern =
			// ResourceBundle.getBundle(Constants.BUNDLE_KEY, locale)
			// .getString("date.format");
			defaultDatePattern = "yyyy-MM-dd HH:mm:ss";
		} catch (MissingResourceException mse) {
			defaultDatePattern = "yyyy-MM-dd HH:mm:ss";
		}

		return defaultDatePattern;
	}

	public static String getDateTimePattern() {
		return DateUtil.getDatePattern() + " HH:mm:ss";
	}

	/**
	 * This method attempts to convert an Oracle-formatted date in the form
	 * dd-MMM-yyyy to mm/dd/yyyy.
	 * 
	 * @param aDate
	 *            date from database as a string
	 * @return formatted string for the ui
	 */
	public static String getDate(Date aDate) {
		SimpleDateFormat df;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(getDatePattern());
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * This method generates a string representation of a date/time in the
	 * format you specify on input
	 * 
	 * @param aMask
	 *            the date pattern the string is in
	 * @param strDate
	 *            a string representation of a date
	 * @return a converted Date object
	 * @see java.text.SimpleDateFormat
	 * @throws ParseException
	 *             when String doesn't match the expected format
	 */
	public static Date convertStringToDate(String aMask, String strDate)
			throws ParseException {
		SimpleDateFormat df;
		Date date;
		df = new SimpleDateFormat(aMask);

		if (log.isDebugEnabled()) {
			log.debug("converting '" + strDate + "' to date with mask '"
					+ aMask + "'");
		}

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			// log.error("ParseException: " + pe);
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return (date);
	}

	/**
	 * This method returns the current date time in the format: MM/dd/yyyy HH:MM
	 * a
	 * 
	 * @param theTime
	 *            the current time
	 * @return the current date/time
	 */
	public static String getTimeNow(Date theTime) {
		return getDateTime(TIME_PATTERN, theTime);
	}

	/**
	 * This method returns the current date in the format: MM/dd/yyyy
	 * 
	 * @return the current date
	 * @throws ParseException
	 *             when String doesn't match the expected format
	 */
	public static Calendar getToday() throws ParseException {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

		// This seems like quite a hack (date -> string -> date),
		// but it works ;-)
		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));

		return cal;
	}

	/**
	 * This method generates a string representation of a date's date/time in
	 * the format you specify on input
	 * 
	 * @param aMask
	 *            the date pattern the string is in
	 * @param aDate
	 *            a date object
	 * @return a formatted string representation of the date
	 * 
	 * @see java.text.SimpleDateFormat
	 */
	public static String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate == null) {
			log.error("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * This method generates a string representation of a date based on the
	 * System Property 'dateFormat' in the format you specify on input
	 * 
	 * @param aDate
	 *            A date to convert
	 * @return a string representation of the date
	 */
	public static String convertDateToString(Date aDate) {
		return getDateTime(getDatePattern(), aDate);
	}

	public static String convertDateToNormalString(Date aDate) {
		return getDateTime("yyMMdd", aDate);
	}

	public static String convertDateToMonthString(Date aDate) {
		return getDateTime("yyMM", aDate);
	}

	
	/**
	 * This method converts a String to a date using the datePattern
	 * 
	 * @param strDate
	 *            the date to convert (in format MM/dd/yyyy)
	 * @return a date object
	 * @throws ParseException
	 *             when String doesn't match the expected format
	 */
	public static Date convertStringToDate(String strDate)
			throws ParseException {
		Date aDate = null;

		try {
			if (log.isDebugEnabled()) {
				log.debug("converting date with pattern: " + getDatePattern());
			}

			aDate = convertStringToDate(getDatePattern(), strDate);
		} catch (ParseException pe) {
			log.error("Could not convert '" + strDate
					+ "' to a date, throwing exception");
			pe.printStackTrace();
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return aDate;
	}
	/**
	 * 判断是否是同一天
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean isTheSameDay(Date d1, Date d2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		return (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR))
				&& (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH))
				&& (c1.get(Calendar.DAY_OF_MONTH) == c2
						.get(Calendar.DAY_OF_MONTH));
	}
	/**
	 * 判断是否是同一月
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean isTheSameMonth(Date d1, Date d2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		return (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR))
				&& (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH));
	}
	/**
	 * 获取当前月份第一天日期时间
	 * 
	 * @return
	 */
	public static String getCurrentMonthFirstDate() {
		Calendar curCal = Calendar.getInstance();
		SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
		// 当前月的第一天
		curCal.set(Calendar.DAY_OF_MONTH, 1);
		Date beginTime = curCal.getTime();
		String beginTimeStr = datef.format(beginTime) + " 00:00:00";
		return beginTimeStr;
	}

	/**
	 * 获取当前月份最后一天日期时间
	 * 
	 * @return
	 */
	public static String getCurrentMonthLastDate() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
		// 当前月的最后一天
		cal.set(Calendar.DATE, 1);
		cal.roll(Calendar.DATE, -1);
		Date endTime = cal.getTime();
		String endTimeStr = datef.format(endTime) + " 23:59:59";
		return endTimeStr;
	}
	/**
	 * 根据给定年月按指定格式获取第一天日期
	 * 
	 * @return
	 */
	public static String getCurrentMonthFirstDate(String format) {
		Calendar curCal = Calendar.getInstance();
		SimpleDateFormat datef = new SimpleDateFormat(format);
		// 当前月的第一天
		curCal.set(Calendar.DAY_OF_MONTH, 1);
		Date beginTime = curCal.getTime();
		String beginTimeStr = datef.format(beginTime);
		return beginTimeStr;
	}
	/**
	 * 根据给定年月获取第一天日期时间
	 * 
	 * @return
	 */
	public static String getFirstDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		// 月份从0开始,故需要减一
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
		return new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime())
				+ " 00:00:00";
	}

	/**
	 * 根据给定年月获取最后一天日期时间
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		// 月份从0开始,故需要减一
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
		return new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime())
				+ " 23:59:59";
	}
    public static Date getDayOfBeforeMonth(Date date){
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.MONTH,  - 1);
    	return new Date(cal.getTimeInMillis());
    }
    /**
	 * 根据给定日期按照指定格式
	 * 
	 * @return
	 */
	public static String getFormarMonth(Date date,String format){
		SimpleDateFormat datef = new SimpleDateFormat(format);
		String formatTimeStr = datef.format(date);
		return formatTimeStr;
	}
	/**
	 * 获取指定日期的下一个月的第一天
	 * 
	 * @return
	 */
	public static Date getFristDayOfNextMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);  
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.MINUTE, 0);  
		cal.set(Calendar.SECOND, 0);  
		cal.set(Calendar.MILLISECOND, 0);
		// 月份从0开始,故需要减一
		cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
		return new Date(cal.getTimeInMillis());
	}
	public static void main(String[] args) {
		try {
			System.out.println("DateUtil:"
					+ convertStringToDate("yyyy-MM-dd HH:mm:ss","2015-10-12 10:5:02"));
		} catch (Exception e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}
}
