package it.kaiser.maple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MapleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MapleApplication.class, args);
	}

}
