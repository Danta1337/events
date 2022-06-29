package cn.ch1tanda.event.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.Charsets;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.*;

/**
 * HTTP工具类
 */
public class HttpUtils {

    private static HttpClient httpClient;

    public static <T> List<T> GETArray (String URL, Class<T> responseType) {
        return GETArray(URL, new HashMap<String, String>(), responseType);
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
        return GET(URL, new HashMap<String, String>(), responseType);
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
        return null;
    }

    private static HttpClient getClient () {
        if (Objects.isNull(httpClient)) {
            httpClient = HttpClientBuilder.create().build();
        }
        return httpClient;
    }

    private static URI buildGetURI (String URL, List<NameValuePair> params) {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(URL);
        if (!params.isEmpty()) {
            strBuilder.append("?");
            params.forEach(item -> {
                try {
                    strBuilder.append(URLEncoder.encode(item.getName(), Charsets.UTF_8.name()))
                            .append("=")
                            .append(URLEncoder.encode(item.getValue(), Charsets.UTF_8.name()))
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
