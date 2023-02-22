package cn.gyw.individual.commons.utils;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 序列号生成工具
 *
 * @author yuewu.guan
 * @date 2022/1/18 16:56
 */
public final class SequenceUtil {

    // 需要格式化的流水号规则
    private static final String STR_FORMAT = "000";

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat(STR_FORMAT);

    /**
     * 获取流水号
     * 规则：年月日+三位流水号
     *
     * @return 流水号
     */
    public static final String getPipelineNumbers() {
        StringBuilder builder = new StringBuilder();
        String today = FORMATTER.format(LocalDateTime.now());
        builder.append(today);
        // 将获取到的数量按照所需的格式进行格式化
        String strNum = DECIMAL_FORMAT.format(0);
        return builder.append(strNum).toString();
    }

    private SequenceUtil() {
    }
}
