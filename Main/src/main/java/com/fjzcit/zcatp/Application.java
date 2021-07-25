package com.fjzcit.zcatp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PreDestroy;

/**
 * 另一种启动方式
 */
@EnableScheduling
@SpringBootApplication
public class Application {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        // Application.context = SpringApplication.run(Application.class, args);
        SpringApplication app = new SpringApplication(Application.class);
        Environment env = app.run(args).getEnvironment();
        System.out.println("启动成功！！！");
        System.out.println("接口服务根地址: \thttp://localhost:" + env.getProperty("server.port"));
        System.out.println("Swagger UI：\thttp://localhost:" + env.getProperty("server.port") + "/swagger-ui.html#/");
    }

    @PreDestroy
    public void close() {
        Application.context.close();
    }
}
