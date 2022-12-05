package kz.bisen.springwebapp1.project2Boot.controller;

import kz.bisen.springwebapp1.project2Boot.dto.book.BookDTO;
import kz.bisen.springwebapp1.project2Boot.dto.book.impl.DefaultBookDTOBuilder;
import kz.bisen.springwebapp1.project2Boot.dto.publisher_book.PublisherBookDTO;
import kz.bisen.springwebapp1.project2Boot.dto.publisher_book.impl.PublisherBookDTOBuilder;
import kz.bisen.springwebapp1.project2Boot.rest_template.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publisher-book")
public class PublisherBookController {
    private final PublisherService service;

    private final DefaultBookDTOBuilder bookDtoBuilder;

    private final PublisherBookDTOBuilder dtoBuilder;

    @Autowired
    public PublisherBookController(
            PublisherService service,
            DefaultBookDTOBuilder bookDtoBuilder,
            PublisherBookDTOBuilder dtoBuilder
    ) {
        this.service = service;
        this.bookDtoBuilder = bookDtoBuilder;
        this.dtoBuilder = dtoBuilder;
    }

    @PostMapping("/isbn")
    public List<BookDTO> getByIsbns(@RequestBody List<String> isbns) {
        return service.getAllByIsbns(isbns);
    }

    @PutMapping("/save")
    public void save(@RequestBody List<BookDTO> bookDTOS) {
        service.saveBooks(
                bookDTOS
                        .stream()
                        .map(bookDtoBuilder::fromBookDTO)
                        .toList()
        );
    }

    public List<PublisherBookDTO> getAll() {
        return service
                .getAllNew()
                .stream()
                .map(dtoBuilder::fromPublisherBook)
                .toList();
    }
}
