package cn.ch1tanda.event.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.support.config.FastJsonConfig;
import com.alibaba.fastjson2.support.spring.http.converter.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    }

    @Bean
    FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {

        FastJsonHttpMessageConverter messageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //数据编码-UTF-8
        fastJsonConfig.setCharset(StandardCharsets.UTF_8);
        //日期格式
        fastJsonConfig.setDateFormat("yyyy-MM-dd");
        fastJsonConfig.setWriterFeatures(
                //输出value为null的数据
                JSONWriter.Feature.WriteMapNullValue,
                //空集合输出[]而不是null
                JSONWriter.Feature.WriteNullListAsEmpty,
                //空字符串输出""而不是null
                JSONWriter.Feature.WriteNullStringAsEmpty,
                //生成的json格式话
                JSONWriter.Feature.PrettyFormat
        );

        messageConverter.setFastJsonConfig(fastJsonConfig);
        return messageConverter;
    }
}
