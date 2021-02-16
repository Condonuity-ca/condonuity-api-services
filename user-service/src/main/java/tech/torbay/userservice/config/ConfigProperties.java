package tech.torbay.userservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value={"classpath:application.properties"})
@ConfigurationProperties()
public class ConfigProperties {
	 
	 private String baseURL;

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}
	 
}