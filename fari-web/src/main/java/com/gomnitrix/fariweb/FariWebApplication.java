package com.gomnitrix.fariweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "com.gomnitrix.commons.mapper")
@ComponentScan(basePackages = {"com.gomnitrix.fariweb", "com.gomnitrix.commons"})
public class FariWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(FariWebApplication.class, args);
    }

}
