package com.psc.wktest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.psc.wktest.entities")
public class WkTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(WkTestApplication.class, args);
    }
}
