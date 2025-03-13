package com.history.gishistorymap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.history.gishistorymap.dao")

public class GishistorymapApplication {

	public static void main(String[] args) {
		SpringApplication.run( GishistorymapApplication.class, args);
	}

}


