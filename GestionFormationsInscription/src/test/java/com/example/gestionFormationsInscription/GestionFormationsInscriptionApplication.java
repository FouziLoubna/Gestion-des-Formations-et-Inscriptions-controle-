package com.example.gestionFormationsInscription;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.example.gestionFormationsInscription.controller")
@SpringBootApplication
public class GestionFormationsInscriptionApplication {
    public static void main(String[] args) {
        SpringApplication.run(GestionFormationsInscriptionApplication.class, args);
    }

}
