package cn.ch1tanda.event.utils.variable;

import java.util.Objects;

public class StringUtils {

    public static boolean isNotBlank (String str) {
        if (Objects.isNull(str)) {
            return false;
        }
        String trim = str.trim();
        return !trim.equals("");
    }

    public static boolean isBlank (String str) {
        return !isNotBlank(str);
    }

}
