package com.alibaba.cloud.youxia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})

public class Example14GuavaApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Example14GuavaApplication.class, args);
	}
}
