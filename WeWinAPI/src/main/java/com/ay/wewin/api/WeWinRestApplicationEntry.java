package com.ay.wewin.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WeWinRestApplicationEntry extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(WeWinRestApplicationEntry.class, args);
	}

}
