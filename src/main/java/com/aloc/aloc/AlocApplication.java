package com.aloc.aloc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class AlocApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlocApplication.class, args);
	}

}
