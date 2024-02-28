package com.mdina.location;

import com.mdina.location.config.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtConfig.class)
public class LocationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocationServiceApplication.class, args);
    }

}
