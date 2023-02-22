package cn.gyw.individual.commons.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * 日期工具
 *
 * @date 2021/11/9 18:44
 */
public final class DateUtil {

    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String HHMMSS = "hhmmss";

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    /**
     * 获取前一天的日期
     *
     * @return 字符串
     */
    public static LocalDate getDateOfYesterday() {
        return LocalDate.now().minusDays(1);
    }

    /**
     * 获取前一天的日期
     *
     * @return 字符串
     */
    public static String formatDate(LocalDate localDate) {
        Objects.requireNonNull(localDate);
        return localDate.format(dateFormatter);
    }

    /**
     * 字符串转日期
     * @param dateStr 日期字符串
     * @param format 日期格式
     * @return 日期
     */
    public static LocalDate parse(String dateStr, String format) {
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(format));
    }

    private DateUtil() {
    }
}
