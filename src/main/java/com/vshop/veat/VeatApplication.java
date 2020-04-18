package com.vshop.veat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.vshop.veat.mapper")
@SpringBootApplication
public class VeatApplication {

    public static void main(String[] args) {
        SpringApplication.run(VeatApplication.class, args);
    }

}
