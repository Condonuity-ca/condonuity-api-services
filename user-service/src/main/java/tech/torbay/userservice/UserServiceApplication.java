package tech.torbay.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

import tech.torbay.userservice.config.ConfigProperties;

@SpringBootApplication(scanBasePackages = {"tech.torbay.userservice"})
@EnableEurekaClient
@EnableScheduling
@EnableConfigurationProperties(ConfigProperties.class)
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
