package cn.ch1tanda.event.manager.tools.file.constant;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FileAccessConstant {

    public static final String SECRET_ID_CONFIG_KEY = "file.access.secret.id";

    public static final String SECRET_KEY_CONFIG_KEY = "file.access.secret.key";

    public static final String REGION_CONFIG_KEY = "file.access.region";

    public static final String BUCKET_NAME_CONFIG_KEY = "file.access.bucket.name";

    public static String REGION;

    public static String BUCKET_NAME;

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("String", "String");
        map.put("String1", "String");
        map.put("String2", "String");
        map.put("String3", "String");
        System.out.println(JSONObject.toJSONString(map));
    }
}
