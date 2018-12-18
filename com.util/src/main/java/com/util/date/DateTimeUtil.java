package com.util.date;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author : 王如雨
 * @date 创建时间：2017年11月8日 上午11:46:36
 * @version 1.0
 */
public class DateTimeUtil {
	/**
	 * yyyy-MM-dd HH:mm:ss格式
	 */
	public static final String LINE_FORMATTER = "yyyy-MM-dd HH:mm:ss";
	public static final String CN_DATE_TIME_FORMAT = "yyyy年MM月dd日 HH时mm分ss秒";
	public static final String CN_DATE_FORMAT = "yyyy年MM月dd日";

	/**
	 * 格式化时间
	 * 
	 * @param date
	 *            被格式化的LocalDateTime时间
	 * @param format
	 *            格式
	 * @return 格式化的字符串
	 */
	public static String format(LocalDateTime date, String format) {
		if (format == null) {
			format = LINE_FORMATTER;
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return date.format(formatter);
	}

	/**
	 * 格式化时间
	 * 
	 * @param date
	 *            被格式化的LocalDateTime时间,默认格式:yyyy-MM-dd HH:mm:ss
	 * @return 格式化的字符串
	 */
	public static String format(LocalDateTime date) {
		return format(date, null);
	}

	/**
	 * 格式化时间,时区是当前系统确定
	 * 
	 * @param date
	 *            被格式化的Date时间
	 * @param format
	 *            格式
	 * @return 格式化的字符串
	 */
	public static String format(Date date, String format) {
		Instant instant = date.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return localDateTime.format(formatter);
	}

	/**
	 * 转换Date为LocalDateTime,时区是当前系统确定
	 * 
	 * @param date
	 * @return
	 */
	public static LocalDateTime parse(Date date) {
		Instant instant = date.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		return LocalDateTime.ofInstant(instant, zone);
	}

	/**
	 * 转化日期字符串到对象，如果不包含time,默认为00:00:00
	 * 
	 * @param date
	 * @param format
	 * @return LocalDateTime对象
	 */
	public static LocalDateTime parse(String date, String format) {
		if (!date.trim().contains(" ")) {
			date += " 00:00:00";
		} else {
			// final String[] dt = date.split(" ");
			// final
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return LocalDateTime.parse(date, formatter);
	}

	/**
	 * 获取日期时间毫秒值
	 * 
	 * @param date
	 *            时间日期
	 * @param formats
	 *            时间日期格式, 默认格式=yyyy-MM-dd HH:mm:ss
	 * @return null=获取失败，否则获取成功
	 */
	public static Long getDateTimeMsec(LocalDateTime date) {
		Long msec = null;
		try {
			msec = date.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msec;
	}

	/**
	 * 替换当前时间的时分秒
	 * 
	 * @param hour
	 *            小时
	 * @param min
	 *            分钟
	 * @param sec
	 *            秒
	 * @return 时间秒数
	 */
	public static long getOperationMsecNearNow(int hour, int min, int sec) {
		LocalDateTime now = LocalDateTime.now();
		now.plusHours(hour);
		now.plusMinutes(min);
		now.plusSeconds(sec);
		return getDateTimeMsec(now);
	}

	/**
	 * 将时间戳转成时间,时区是当前系统确定
	 * 
	 * @param time
	 * @return LocalDateTime时间
	 */
	public static LocalDateTime parse(Long time) {
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
	}

	public static void main(String[] args) {
		System.out.println(format(new Date(), CN_DATE_TIME_FORMAT));
	}

}
