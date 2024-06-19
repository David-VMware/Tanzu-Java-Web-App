package com.example.springboot;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;

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
        		try {
            			String url = "https://support-lab-status.cfapps-01.slot-34.tanzu-gss-labs.vmware.com/";
            			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.HEAD, null, String.class);
            			System.out.println("Response Headers: " + response.getHeaders());
        		} catch (HttpClientErrorException | HttpServerErrorException e) {
            			System.out.println("Error Code: " + e.getStatusCode());
            			System.out.println("Error Response Body: " + e.getResponseBodyAsString());
        		} catch (Exception e) {
           			 System.out.println("An unexpected error occurred: " + e.getMessage());
        		}
    		};
	}
}
