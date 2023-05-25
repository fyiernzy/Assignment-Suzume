package com.assignment.suzume;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@MapperScan("com.assignment.suzume.mapper")
public class SuzumeApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(SuzumeApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
