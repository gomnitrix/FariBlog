package com.gomnitrix.fariweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.gomnitrix.fariweb", "com.gomnitrix.commons"})
public class FariWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(FariWebApplication.class, args);
    }

}
