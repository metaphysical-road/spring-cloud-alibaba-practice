package com.alibaba.cloud.youxia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class Example14GuavaApplication{

	public static void main(String[] args) {
		SpringApplication.run(Example14GuavaApplication.class, args);
	}
}
