package com.seproject.healthqa;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
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

        Instant nowUtc = Instant.now();
        ZoneId asiaBangkok = ZoneId.of("Asia/Bangkok");

        ZonedDateTime nowAsiaSingapore = ZonedDateTime.ofInstant(nowUtc, asiaBangkok);

    }

    public static void main(String[] args) {
        SpringApplication.run(HealthQaApiApplication.class, args);
//        System.err.println("\n\n\n\n---------------------------------------------------------->\n"
//                + "Time Zone : " + TimeZone.getDefault() + "\n"
//                + "" + new Date()
//                + "\n<------------------------------------------------------------\n\n\n\n\n\n");
    }
}
