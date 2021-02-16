package tech.torbay.clientsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

import tech.torbay.clientsservice.config.ConfigProperties;

@SpringBootApplication(scanBasePackages = {"tech.torbay.clientsservice"})
@EnableEurekaClient
@EnableScheduling
@EnableConfigurationProperties(ConfigProperties.class)
public class ClientsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientsServiceApplication.class, args);
	}

}
