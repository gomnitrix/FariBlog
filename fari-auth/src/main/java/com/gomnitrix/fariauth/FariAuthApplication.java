package com.gomnitrix.fariauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.gomnitrix.commons.mapper")
@ComponentScan(basePackages = {"com.gomnitrix.fariauth", "com.gomnitrix.commons"})
public class FariAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(FariAuthApplication.class, args);
    }

}
