package com.project.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
@ServletComponentScan
public class SchedulerApplication {

	// 뷰 리졸버 설정 : 컨트롤러가 리턴한 문자열을 해석해주는 객체
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();

		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	public static void main(String[] args) {
		SpringApplication.run(SchedulerApplication.class, args);
	}

}
