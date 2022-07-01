package cn.ch1tanda.event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@EnableAspectJAutoProxy
@SpringBootApplication
@PropertySource(value = "file:${spring.config.location}")
public class EventApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventApplication.class, args);
    }
}
