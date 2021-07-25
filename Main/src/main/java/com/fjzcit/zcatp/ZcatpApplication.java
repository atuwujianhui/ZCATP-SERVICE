package com.fjzcit.zcatp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
@MapperScan(basePackages = "com.fjzcit.zcatp.mapper")
public class ZcatpApplication {

    public static void main(String[] args) {
        // SpringApplication.run(ZcatpApplication.class, args);
        SpringApplication app = new SpringApplication(Application.class);
        Environment env = app.run(args).getEnvironment();
        System.out.println("启动成功！！");
        System.out.println("服务地址: \t\thttp://localhost:" + env.getProperty("server.port"));
        System.out.println("接口调用地址：\thttp://localhost:" + env.getProperty("server.port") + "/swagger-ui.html#/");
    }

}
