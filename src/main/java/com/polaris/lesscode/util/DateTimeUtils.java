/**
 * 
 */
package com.polaris.lesscode.util;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @author Bomb
 * Instant时间处理工具类
 */
public class DateTimeUtils {
	
	/**
	 * get current DateTime.
	 * 
	 * @return LocalDateTime
	 */
	public static Instant getCurrentDateTime() {
		return Instant.now();
	}

	/**
	 * get start DateTime of date.
	 * 
	 * @param instant Instant
	 * @return Instant
	 */
	public static Instant getStartTimeOfDatetime(Instant instant) {
		LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		ldt = LocalDateTime.of(ldt.toLocalDate(), LocalTime.MIN);
		ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
		return zdt.toInstant();
	}

	/**
	 * get end DateTime of date.
	 * 
	 * @param instant Instant
	 * @return Instant
	 */
	public static Instant getEndTimeOfDatetime(Instant instant) {
		LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		ldt = LocalDateTime.of(ldt.toLocalDate(), LocalTime.MAX);
		ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
		return zdt.toInstant();
	}
	
	/**
	 * get start DateTime of week by the date.
	 * @param instant Instant
	 * @return Instant
	 */
	public static Instant getStartTimeOfWeek(Instant instant) {
		LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		TemporalAdjuster adjuster = TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY);
		ldt = LocalDateTime.of(ldt.with(adjuster).toLocalDate(), LocalTime.MIN);
		ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
		return zdt.toInstant();
	}

	/**
	 * get end DateTime of week by the date.
	 * @param instant Instant
	 * @return Instant
	 */
	public static Instant getEndTimeOfWeek(Instant instant) {
		LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		TemporalAdjuster adjuster = TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY);
		ldt = LocalDateTime.of(ldt.with(adjuster).toLocalDate(), LocalTime.MAX);
		ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
		return zdt.toInstant();
	}
	
	/**
	 * get start DateTime of month by the date.
	 * @param instant Instant
	 * @return Instant
	 */
	public static Instant getStartTimeOfMonth(Instant instant) {
		LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		TemporalAdjuster adjuster = TemporalAdjusters.firstDayOfMonth();
		ldt = LocalDateTime.of(ldt.with(adjuster).toLocalDate(), LocalTime.MIN);
		ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
		return zdt.toInstant();
	}

	/**
	 * get end DateTime of month by the date.
	 * @param instant Instant
	 * @return Instant
	 */
	public static Instant getEndTimeOfMonth(Instant instant) {
		LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		TemporalAdjuster adjuster = TemporalAdjusters.lastDayOfMonth();
		ldt = LocalDateTime.of(ldt.with(adjuster).toLocalDate(), LocalTime.MAX);
		ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
		return zdt.toInstant();
	}
	
	/**
	 * get start DateTime of year by the date.
	 * @param instant Instant
	 * @return Instant
	 */
	public static Instant getStartTimeOfYear(Instant instant) {
		LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		TemporalAdjuster adjuster = TemporalAdjusters.firstDayOfYear();
		ldt = LocalDateTime.of(ldt.with(adjuster).toLocalDate(), LocalTime.MIN);
		ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
		return zdt.toInstant();
	}

	/**
	 * get end DateTime of year by the date.
	 * @param instant Instant
	 * @return Instant
	 */
	public static Instant getEndTimeOfYear(Instant instant) {
		LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		TemporalAdjuster adjuster = TemporalAdjusters.lastDayOfYear();
		ldt = LocalDateTime.of(ldt.with(adjuster).toLocalDate(), LocalTime.MAX);
		ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
		return zdt.toInstant();
	}
	
	/**
	 * date convert to LocalDateTime.
	 * @param date Date
	 * @return Instant
	 */
	public static Instant date2LocalDateTime(Date date) {
		return date.toInstant();
	}
	
	/**
	 * LocalDateTime convert to date. 
	 * @param instant Instant
	 * @return Date
	 */
	public static Date localDateTime2Date(Instant instant) {
		return Date.from(instant);
	}
	
	/**
	 * get start time of yesterday
	 * @param instant
	 * @return
	 */
	public static Instant getStartTimeOfYesterday(Instant instant) {
		LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		ldt = ldt.minusDays(1);
		ldt = LocalDateTime.of(ldt.toLocalDate(), LocalTime.MIN);
		ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
		return zdt.toInstant();
	}
	
	/**
	 * get end time of yesterday
	 * @param instant
	 * @return
	 */
	public static Instant getEndTimeOfYesterday(Instant instant) {
		LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		ldt = ldt.minusDays(1);
		ldt = LocalDateTime.of(ldt.toLocalDate(), LocalTime.MAX);
		ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
		return zdt.toInstant();
	}
	
	/**
	 * get day of month.
	 * @param instant Instant
	 * @return Integer
	 */
	public static Integer getDayOfMonth(Instant instant) {
		LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		return ldt.getDayOfMonth();
	}
	
	/**
	 * get start time of last month.
	 * @param instant Instant
	 * @return Instant
	 */
	public static Instant getStartTimeOfLastMonth(Instant instant) {
		LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		TemporalAdjuster adjuster = TemporalAdjusters.firstDayOfMonth();
		ldt = LocalDateTime.of(ldt.minusMonths(1).with(adjuster).toLocalDate(), LocalTime.MIN);
		ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
		return zdt.toInstant();
	}
	
	/**
	 * get end time of last month.
	 * @param instant Instant
	 * @return Instant
	 */
	public static Instant getEndTimeOfLastMonth(Instant instant) {
		LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		TemporalAdjuster adjuster = TemporalAdjusters.lastDayOfMonth();
		ldt = LocalDateTime.of(ldt.plusMonths(-1).with(adjuster).toLocalDate(), LocalTime.MAX);
		ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
		return zdt.toInstant();
	}
}
