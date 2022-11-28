package kz.bisen.springwebapp1.project2Boot;

import kz.bisen.springwebapp1.project2Boot.model.Book;
import kz.bisen.springwebapp1.project2Boot.repository.jpa.JpaBookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootApplication
//@EnableCaching
@EnableScheduling
public class Project2BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(Project2BootApplication.class, args);
    }
}