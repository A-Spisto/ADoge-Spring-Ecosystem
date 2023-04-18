package it.kaiser.galileo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class GalileoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GalileoApplication.class, args);
	}

}
