package com.argentina.programa.jwt.test;

import org.springframework.boot.SpringApplication;
import static org.springframework.boot.SpringApplication.run;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class JwtTestApplication {

	public static void main(String[] args) {
		run(JwtTestApplication.class, args);
	}

}
