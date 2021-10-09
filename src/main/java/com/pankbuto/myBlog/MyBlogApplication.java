package com.pankbuto.myBlog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class MyBlogApplication {
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(MyBlogApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(MyBlogApplication.class, args);
		LOGGER.info("welcome to the blog REST Application created by Pankaj Sharma");
	}

}
