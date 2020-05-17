package com.family.fmlgatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class FmlGatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FmlGatewayServerApplication.class, args);
    }

}
