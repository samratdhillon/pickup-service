package com.cpc.pickup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class PickupApplication {

    public static void main(String[] args) {
        SpringApplication.run(PickupApplication.class, args);
    }
}
