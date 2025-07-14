package sakhno.psup.manufacture_service;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Hooks;

@SpringBootApplication
public class ManufactureServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManufactureServiceApplication.class, args);
    }

    @PostConstruct
    public  void  init () {
        Hooks.enableAutomaticContextPropagation();
    }

}
