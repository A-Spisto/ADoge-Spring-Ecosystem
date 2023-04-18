package it.kaiser.kylo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class KyloApplication {

	public static void main(String[] args) {
		SpringApplication.run(KyloApplication.class, args);
	}

}
