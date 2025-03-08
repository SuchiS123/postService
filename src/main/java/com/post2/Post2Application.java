package com.post2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Post2Application {

	public static void main(String[] args) {
		SpringApplication.run(Post2Application.class, args);
	}

}
