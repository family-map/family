package com.family.fmleurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaServer
@SpringBootApplication
@ComponentScan("com.family")
public class FmlEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FmlEurekaServerApplication.class, args);
    }

}
