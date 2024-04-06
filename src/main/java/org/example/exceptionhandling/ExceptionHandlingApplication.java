package org.example.exceptionhandling;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Event Ticketing System",
        version = "1.0",
        description = "Efficiently manage events with our streamlined ticketing system."))
public class ExceptionHandlingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExceptionHandlingApplication.class, args);
    }

}
