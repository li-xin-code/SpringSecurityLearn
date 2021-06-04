package com.lixin.springsecuritylearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author lx
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringSecurityLearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityLearnApplication.class, args);
    }

}
