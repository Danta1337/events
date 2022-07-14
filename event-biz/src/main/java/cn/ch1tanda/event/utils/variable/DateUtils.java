package cn.ch1tanda.event.utils.variable;

import java.math.BigDecimal;

public class DateUtils {

    public static long secTimestampToMSTimestamp (long timestamp) {
        return Long.parseLong(new BigDecimal(String.valueOf(timestamp)).multiply(new BigDecimal("1000")).toString());
    }

}
