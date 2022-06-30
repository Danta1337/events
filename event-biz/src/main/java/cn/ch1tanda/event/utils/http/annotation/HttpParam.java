package cn.ch1tanda.event.utils.http.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HttpParam {

    String value() default "";
}
