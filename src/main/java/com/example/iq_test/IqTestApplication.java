package com.example.iq_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// основной класс для запуска приложения
@SpringBootApplication
public class IqTestApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(IqTestApplication.class, args);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry)
	{
		resourceHandlerRegistry.addResourceHandler("/static/**").addResourceLocations("classpath:/static");
	}
}
