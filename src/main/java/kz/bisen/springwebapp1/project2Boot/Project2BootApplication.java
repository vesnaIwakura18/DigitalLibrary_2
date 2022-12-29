package kz.bisen.springwebapp1.project2Boot;

import kz.bisen.springwebapp1.project2Boot.soap.builder.SoapBookBuilder;
import kz.bisen.springwebapp1.project2Boot.soap.generated.SoapBookService;
import kz.bisen.springwebapp1.project2Boot.soap.generated.SoapBookService_Service;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.Arrays;


@SpringBootApplication
@EnableScheduling
public class Project2BootApplication {

    public static void main(String[] args) {

        SpringApplication.run(Project2BootApplication.class, args);

    }

//    @Bean
//    public CommandLineRunner runOnStartup(SoapBookBuilder builder) {
//        return args -> {
//            SoapBookService_Service serviceImpl = new SoapBookService_Service();
//            SoapBookService service = serviceImpl.getSoapBookServicePort();
//
//            service.getBooks(new ArrayList<>(Arrays.asList("1", "2")))
//                    .stream()
//                    .map(builder::fromSoapBook)
//                    .forEach(System.out::println);
//        };
//    }

}