package tech.torbay.securityservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import tech.torbay.securityservice.config.ConfigProperties;

@SpringBootApplication
@EnableConfigurationProperties(ConfigProperties.class)
public class SecurityServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(SecurityServiceApplication.class, args);
  }
}
