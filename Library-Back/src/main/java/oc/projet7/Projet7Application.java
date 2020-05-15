package oc.projet7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class Projet7Application {

    public static void main(String[] args) {

        SpringApplication.run(Projet7Application.class, args);
    }
}
