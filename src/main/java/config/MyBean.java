package config;

import org.springframework.context.annotation.Bean;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Bean @Retention(RetentionPolicy.RUNTIME)
public @interface MyBean {
}
