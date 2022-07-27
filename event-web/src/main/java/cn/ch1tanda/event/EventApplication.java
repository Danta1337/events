package cn.ch1tanda.event;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@EnableAspectJAutoProxy
@SpringBootApplication(exclude = {ValidationAutoConfiguration.class})
@PropertySource(value = "file:${spring.config.location}")
@MapperScan(basePackages = "cn.ch1tanda.event.mapper")
public class EventApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventApplication.class, args);
    }
}
