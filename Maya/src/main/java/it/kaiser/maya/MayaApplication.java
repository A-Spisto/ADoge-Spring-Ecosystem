package it.kaiser.maya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MayaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MayaApplication.class, args);
	}

}
