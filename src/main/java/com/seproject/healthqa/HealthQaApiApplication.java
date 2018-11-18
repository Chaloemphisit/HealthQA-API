package com.seproject.healthqa;

import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = {
    HealthQaApiApplication.class,
    Jsr310JpaConverters.class
})
public class HealthQaApiApplication {

    @PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Bangkok"));
    }

    public static void main(String[] args) {
        SpringApplication.run(HealthQaApiApplication.class, args);
    }
}
