package cn.ch1tanda.event.utils.http;

import java.util.Objects;

public class StringUtils {

    public static boolean isNotBlank (String str) {
        if (Objects.isNull(str)) {
            return false;
        }
        String trim = str.trim();
        if (trim.equals("")) {
            return false;
        }
        return true;
    }

    public static boolean isBlank (String str) {
        return !isNotBlank(str);
    }

}
