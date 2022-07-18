package cn.ch1tanda.event.utils.http;

import cn.ch1tanda.event.manager.game.apex.req.ApexPlayerStatisticsQueryReq;
import cn.ch1tanda.event.utils.http.annotation.HttpParam;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.Charsets;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URLEncoder;
import java.util.*;

/**
 * HTTP工具类
 */
public class HttpUtils {

    private static HttpClient httpClient;

    public static <T> List<T> GETArray (String URL, Class<T> responseType) {
        return GETArray(URL, new HashMap<>(), responseType);
    }

    public static void main(String[] args) {
        ApexPlayerStatisticsQueryReq req = new ApexPlayerStatisticsQueryReq();
        req.setAuth("auth");
        req.setPlatform("PC");
        req.setPlayer("ch1tanda");
        String httpParamStr = HttpUtils.getHttpParamStr(req);
        System.out.println(httpParamStr);
    }

    public static <T> List<T> GETArray (String URL, Map<String, String> param, Class<T> responseType) {
        List<NameValuePair> params = new ArrayList<>();
        param.forEach((key, value) -> params.add(new BasicNameValuePair(key, value)));
        HttpGet get = new HttpGet(buildGetURI(URL, params));
        try {
            HttpResponse response = getClient().execute(get);
            String responseStr = EntityUtils.toString(response.getEntity());
            return JSONObject.parseArray(responseStr, responseType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T GET (String URL, Class<T> responseType) {
        return GET(URL, new HashMap<>(), responseType);
    }

    public static <T> T GET (String URL, Map<String, String> param, Class<T> responseType) {
        List<NameValuePair> params = new ArrayList<>();
        param.forEach((key, value) -> params.add(new BasicNameValuePair(key, value)));
        HttpGet get = new HttpGet(buildGetURI(URL, params));
        try {
            HttpResponse response = getClient().execute(get);
            String responseStr = EntityUtils.toString(response.getEntity());
            return JSONObject.parseObject(responseStr, responseType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T POST (String URL, Object request, Class<T> responseType) {
        HttpPost post = new HttpPost(URL);
        StringEntity se = new StringEntity(JSONObject.toJSONString(request), Charsets.toCharset("UTF-8"));
        post.setEntity(se);
        try {
            HttpResponse response = getClient().execute(post);
            String responseStr = EntityUtils.toString(response.getEntity());
            return JSONObject.parseObject(responseStr, responseType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static HttpClient getClient () {
        if (Objects.isNull(httpClient)) {
            httpClient = HttpClientBuilder.create().build();
        }
        return httpClient;
    }

    /**
     * @see cn.ch1tanda.event.utils.http.annotation.HttpParam
     * @param request 请求对象
     * @return 返回请求对象中使用@HttpParam的字段拼接而成的Http参数
     * @param <T> 对象类型
     */
    public static <T> String getHttpParamStr (T request) {
        StringBuilder httpParam = new StringBuilder();
        httpParam.append("?");
        Class<?> clazz = request.getClass();
        // 获取请求对象及其父类中的所有属性
        List<Field> fields = new ArrayList<>();
        while (!clazz.getName().equals(Object.class.getName())) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        // 收集被@HttpParam注解修饰的字段
        fields.stream()
                .filter(item -> Objects.nonNull(item.getAnnotation(HttpParam.class)))
                .filter(item -> {
                    try {
                        item.setAccessible(true);
                        return Objects.nonNull(item.get(request));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }).forEach(item -> {
                    String key;
                    String value;
                    try {
                        item.setAccessible(true);
                        value = item.get(request).toString();
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    if (!item.getAnnotation(HttpParam.class).value().equals("")) {
                        key = item.getAnnotation(HttpParam.class).value();
                    } else {
                        key = item.getName();
                    }
                    httpParam.append(key).append("=").append(value).append("&");
                });
        httpParam.delete(httpParam.length() - 1, httpParam.length());
        return httpParam.toString();
    }

    private static URI buildGetURI (String URL, List<NameValuePair> params) {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(URL);
        if (!params.isEmpty()) {
            strBuilder.append("?");
            params.forEach(item -> {
                try {
                    strBuilder.append(URLEncoder.encode(item.getName(), Charsets.toCharset("UTF-8").name()))
                            .append("=")
                            .append(URLEncoder.encode(item.getValue(), Charsets.toCharset("UTF-8").name()))
                            .append("&");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            });
            strBuilder.delete(strBuilder.length() - 1, strBuilder.length());
        }
        return URI.create(strBuilder.toString());
    }

    private static boolean isJSONArrayResponse (String response) {
        return response.startsWith("[") && response.endsWith("]");
    }
}
