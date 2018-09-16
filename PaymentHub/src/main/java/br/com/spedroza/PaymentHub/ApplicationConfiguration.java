package br.com.spedroza.PaymentHub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = JPAConfiguration.class)
public class ApplicationConfiguration {

	public static void main(String[] args) {
		System.out.println("Application starting...");
		SpringApplication.run(ApplicationConfiguration.class, args);
		System.out.println("Application started...");
	}
	
}
