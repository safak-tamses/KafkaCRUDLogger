package com.api;

import com.api.config.db.JpaConfig;
import com.api.config.db.MongoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ MongoConfig.class, JpaConfig.class })
public class KafkaCrudLoggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaCrudLoggerApplication.class, args);
    }

}
