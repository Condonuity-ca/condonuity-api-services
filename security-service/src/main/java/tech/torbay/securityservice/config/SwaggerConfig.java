package tech.torbay.securityservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {


    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("tech.torbay.securityservice"))
                .paths(regex("/api.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "Condonuity Service Application",
                "Condonuity Service Application for User",
                "1.0",
                "Terms of Service",
                new Contact("Condonuity", "https://www.torbay.condonuity/",
                        "support@condonuity.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html"
        );

        return apiInfo;
    }
    
	
	/*
	 * @Bean public CorsFilter corsFilter() { UrlBasedCorsConfigurationSource source
	 * = new UrlBasedCorsConfigurationSource();
	 * 
	 * // Allow anyone and anything access. Probably ok for Swagger spec
	 * CorsConfiguration config = new CorsConfiguration();
	 * config.setAllowCredentials(true); config.addAllowedOrigin("*");
	 * config.addAllowedHeader("*"); config.addAllowedMethod("*");
	 * 
	 * source.registerCorsConfiguration("*", config); return new CorsFilter(source);
	 * }
	 */
	 
}
