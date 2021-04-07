package com.fjzcit.zcatp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.fjzcit.zcatp.mapper")
public class ZcatpApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZcatpApplication.class, args);
    }

}
