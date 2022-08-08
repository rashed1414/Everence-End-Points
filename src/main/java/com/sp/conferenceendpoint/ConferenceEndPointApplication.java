package com.sp.conferenceendpoint;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ConferenceEndPointApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConferenceEndPointApplication.class, args);
    }

    @Bean
    public OpenAPI openApiConfig(){
        return new OpenAPI().info(apiInfo());
    }

    private Info apiInfo() {
        return new Info().title("Everence EndPoint").description("Everence EndPoint API").version("1.0.0");
    }


}
