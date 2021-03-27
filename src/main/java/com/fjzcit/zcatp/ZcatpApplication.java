package com.fjzcit.zcatp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.fjzcit.zcatp.model.*")
@EnableJpaRepositories("com.fjzcit.zcatp.repository.*")
public class ZcatpApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZcatpApplication.class, args);
    }

}
