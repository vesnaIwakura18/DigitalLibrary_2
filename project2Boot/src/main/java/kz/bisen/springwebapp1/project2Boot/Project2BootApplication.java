package kz.bisen.springwebapp1.project2Boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class Project2BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(Project2BootApplication.class, args);
    }
}