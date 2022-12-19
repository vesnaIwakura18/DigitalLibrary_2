//package kz.bisen.springwebapp1.project2Boot.util;
//
//import kz.bisen.springwebapp1.project2Boot.soap.builder.SoapBookBuilder;
//import kz.bisen.springwebapp1.project2Boot.soap.generated.SoapBookService;
//import kz.bisen.springwebapp1.project2Boot.soap.generated.SoapBookService_Service;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//
//@Component
//public class Tester implements ApplicationRunner {
//    private final SoapBookBuilder builder;
//
//    public Tester(SoapBookBuilder builder) {
//        this.builder = builder;
//    }
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        SoapBookService_Service serviceImpl = new SoapBookService_Service();
//        SoapBookService service = serviceImpl.getSoapBookServicePort();
//
//        service.getBooks(new ArrayList<>(Arrays.asList("1", "2"))).stream().map(builder::fromSoapBook).forEach(System.out::println);
//    }
//}
