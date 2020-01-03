package com.jt;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
@MapperScan("com.jt.mapper")
public class SpringBootRun {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRun.class, args);
	}

}

