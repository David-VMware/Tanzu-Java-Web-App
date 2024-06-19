package com.example.springboot;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
        public RestTemplate restTemplate() {
        	return new RestTemplate();
        }
	
	@Bean
	public CommandLineRunner commandLineRunner(RestTemplate restTemplate) {
    		return args -> {
            		String url = "https://10.225.31.125.nip.io/";
            		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.HEAD, null, String.class);
            		System.out.println("Response Headers: " + response.getHeaders());
        	};
	}
}
