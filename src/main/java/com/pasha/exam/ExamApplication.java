package com.pasha.exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.pasha.exam.Service.XmlCreatorService;

@SpringBootApplication
public class ExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamApplication.class, args).getBean(XmlCreatorService.class).process();
	}

}
