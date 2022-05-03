package com.craig.keniprojectwefbluxproduct;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Spring webflux api client",
        version = "1.0",
        description = "End points Client Microservice"
))
public class KeniProjectWefbluxProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeniProjectWefbluxProductApplication.class, args);
    }

}
