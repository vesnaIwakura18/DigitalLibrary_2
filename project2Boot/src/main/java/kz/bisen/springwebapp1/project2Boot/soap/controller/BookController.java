//package kz.bisen.springwebapp1.project2Boot.soap.controller;
//
//import kz.bisen.springwebapp1.project2Boot.dto.book.BookDTO;
//import kz.bisen.springwebapp1.project2Boot.soap.client.impl.BookServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/soap/book")
//public class BookController {
//    private final BookServiceImpl service;
//
//    @Autowired
//    public BookController(BookServiceImpl service) {
//        this.service = service;
//    }
//
//    public List<BookDTO> requestBook(List<String> isbns) {
//        service.request(isbns);
//    }
//}
