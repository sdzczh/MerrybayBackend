package cn.stylefeng.guns.core.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @描述 日期时间工具<br>
 * @author administrator
 * @版本 v1.0.0
 * @日期 2017-6-8
 */
public class DateUtils {

	private static final String CURRENT_DATE_STR = "yyyy-MM-dd";
	private static final String CURRENT_TIME_STR = "yyyy-MM-dd HH:mm:ss";

	public static Date getCurrentDate() {
		Date date = new Date();
		return date;
	}
	public static String timeStamp2Date(String time) {
		Long timeLong = Long.parseLong(time);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//要转换的时间格式
		Date date;
		try {
			date = sdf.parse(sdf.format(timeLong));
			return sdf.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 将时间戳转换为时间
	 */
	public static String stampToDate(String s){
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long lt = new Long(s)*100L;
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;
	}
	/**
	 * 将时间戳转换为时间
	 */
	public static String stampToDate3(String s){
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long lt = new Long(s)*1000L;
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;
	}
	/**
	 * 将时间戳转换为时间
	 */
	public static String stampToDate4(String s){
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long lt = new Long(s);
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;
	}
	/**
	 * 将时间戳转换为时间
	 * mm:ss dd/MM/yyyy
	 */
	public static String stampToDate2(String s){
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
		long lt = new Long(s)*1000L;
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;
	}
	/**
	 * 将UTC转换为时间
	 * mm:ss dd/MM/yyyy
	 */
	public static String UTCToDate(String s){
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm dd/MM/yyyy");
		return format(s, sdf1, sdf2);
	}
	/**
	 * 将UTC转换为时间
	 * mm:ss dd/MM/yyyy
	 */
	public static String DateToTimeStamp(String s) throws ParseException {
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		Date date = simpleDateFormat.parse(s);
		long ts = date.getTime();
		res = String.valueOf(ts);
		return res;
	}
	/**
	 * 将UTC转换为时间
	 * yyyy-MM-dd
	 */
	public static String UTCToDate2(String s){
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		return format(s, sdf1, sdf2);
	}
	/**
	 * 将UTC转换为时间
	 * yyyy-MM-dd
	 */
	public static String UTCToDate3(String s){
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format(s, sdf1, sdf2);
	}
	/*
	 * 将时间转换为时间戳
	 */
	public static String dateToStamp(String s) throws ParseException{
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = simpleDateFormat.parse(s);
		long ts = date.getTime();
		res = String.valueOf(ts);
		return res;
	}


	public static String getDateFormate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(CURRENT_TIME_STR);
		return format.format(date);
	}

	/**
	 * @描述 获取字符串格式的当前<br>
	 * @return XXXX-XX-XX XX:XX:XX
	 * @author administrator
	 * @版本 v1.0.0
	 * @日期 2017-6-13
	 */
	public static String getCurrentTimeStr() {
		SimpleDateFormat format = new SimpleDateFormat(CURRENT_TIME_STR);
		return format.format(new Date());
	}

	/**
	 * @描述 获取字符串格式的当前<br>
	 * @return XXXX-XX-XX
	 * @author 李娜
	 * @版本 v1.0.0
	 * @日期 2017-6-17
	 */
	public static String getCurrentDateStr() {
		SimpleDateFormat format = new SimpleDateFormat(CURRENT_DATE_STR);
		return format.format(new Date());
	}
	/**
	 * @描述 获取字符串格式的当前<br>
	 * @return XX-XX-XX 月-日-小时
	 * @author 李娜
	 * @版本 v1.0.0
	 * @日期 2017-6-17
	 */
	public static String getMd5CurrentDateStr() {
		SimpleDateFormat format = new SimpleDateFormat("MM-dd-HH");
		return format.format(new Date());
	}

	/**
	 * @描述 字符串转时间 例：XXXX-XX-XX XX:XX:XX<br>
	 * @param str
	 * @return
	 * @author administrator
	 * @版本 v1.0.0
	 * @日期 2017-6-17
	 */
	public static Date strToDate(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 时间字符串格式化
	 * @param time  yyyyMMddHHmmss格式字符串
	 * @return yyyy-MM-dd HH:mm:ss 格式字符串
	 * @return String
	 * @date 2017-9-5
	 * @author lina
	 */
	public static String converTimeStr(String time){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat newformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = format.parse("20141030133525");
			return newformat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * @描述 计算机两个时间相差天数<br>
	 *            字符串格式日期时间,例：XXXX-XX-XX XX:XX:XX
	 * @return
	 * @author administrator
	 * @版本 v1.0.0
	 * @日期 2017-6-17
	 */
	public static int daysBetween(String dateStr) {
		Date after = strToDate(dateStr);
		Calendar cal = Calendar.getInstance();
		Date before = cal.getTime();
		long afterMillis = after.getTime();
		long beforeMillis = before.getTime();
		long between_days = (beforeMillis - afterMillis) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * @描述 计算机两个时间相差小时数<br>
	 *            字符串格式日期时间,例：XXXX-XX-XX XX:XX:XX
	 * @return
	 * @author administrator
	 * @版本 v1.0.0
	 * @日期 2017-6-17
	 */
	public static int hoursBetween(String dateStr) {
		Date after = strToDate(dateStr);
		Calendar cal = Calendar.getInstance();
		Date before = cal.getTime();
		long afterMillis = after.getTime();
		long beforeMillis = before.getTime();
		long between_days = (beforeMillis - afterMillis) / (1000 * 3600);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * @描述 获取当前月份第一天<br>
	 * @return
	 * @author administrator
	 * @版本 v1.0.0
	 * @日期 2017-7-5
	 */
	public static String getCurrentMonthStartDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal_1 = Calendar.getInstance();// 获取当前日期
		cal_1.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		 String startDay = format.format(cal_1.getTime());
		return startDay+" 00:00:00";
	}

	/**
	 * @描述 获取当前月份第一天<br>
	 * @return
	 * @author administrator
	 * @版本 v1.0.0
	 * @日期 2017-7-5
	 */
	public static String getCurrentMonthEndDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cale = Calendar.getInstance();
		cale.add(Calendar.MONTH, 1);
		cale.set(Calendar.DAY_OF_MONTH, 0);// 设置为1号,当前日期既为本月第一天
		String endDay = format.format(cale.getTime());
		return endDay+" 23:59:59";
	}

	/**
	 * @描述 获取N天前/后的日期时间<br>
	 * @param days 天数(负数为当前时间的前段时间)
	 * @return 日期时间
	 * @author administrator
	 * @版本 v1.0.0
	 * @日期 2017-8-31
	 */
	public static String getSomeDay(int days){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, days);
		String date = format.format(calendar.getTime());
		return date;
	}
	
	/**
	 * 根据出生日期计算年龄
	 * @param birth 'yyyyMMdd' 字符串
	 * @return Integer 年龄
	 * @date 2017-11-3
	 * @author lina
	 */
	public static Integer idCardToAge(String birth) { 
        Integer selectYear = Integer.valueOf(birth.substring(0, 4));         //出生的年份
        Integer selectMonth = Integer.valueOf(birth.substring(4, 6));       //出生的月份
        Integer selectDay = Integer.valueOf(birth.substring(6, 8));         //出生的日期
        Calendar cal = Calendar.getInstance();
        Integer yearMinus = cal.get(Calendar.YEAR) - selectYear;
        Integer monthMinus = cal.get(Calendar.MONTH) + 1 - selectMonth;
        Integer dayMinus = cal.get(Calendar.DATE) - selectDay;
        Integer age = yearMinus;
        if (yearMinus < 0) {
            age = 0;
        } else if (yearMinus == 0) {
            age = 0;
        } else if (yearMinus > 0) {
            if (monthMinus == 0) {
                if (dayMinus < 0) {
                    age = age - 1;
                }
            } else if (monthMinus < 0) {
                age = age - 1;
            }
        }
        return age;
    }
	
	/**
	 * 返回K线数据时间戳
	 * @param timeInteval
	 * @return
	 */
	public static Long getKLineTimestamp(Long timeInteval){
		if(timeInteval == null || timeInteval == 0)timeInteval = 60000l;//默认取一分钟
		Date date = new Date();
		//一天间隔
		if(timeInteval == 86400000)return date.getTime()-date.getTime()%timeInteval - 28800000;
		//其他时间间隔
		return date.getTime()-date.getTime()%timeInteval;
	}


	/**
	 * 获取星期几
	 * @param dt
	 * @return
     */
	public static String getWeekOfDate(Date dt) {
		String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	/**
	 * 返回 yyyy-MM-dd 格式日期
	 * @param time
	 * @return
	 */
	public static String getYMDStr(String time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sdf.format(date);
	}
	public static String format(String time, SimpleDateFormat fmt1, SimpleDateFormat fmt2){
		try {
			Date date = fmt1.parse(time);
			String str = fmt2.format(date);
			System.out.println(str);
			return str;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
