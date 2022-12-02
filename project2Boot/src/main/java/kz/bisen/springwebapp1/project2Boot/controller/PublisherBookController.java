package kz.bisen.springwebapp1.project2Boot.controller;

import kz.bisen.springwebapp1.project2Boot.dto.publisher_book.PublisherBookDTO;
import kz.bisen.springwebapp1.project2Boot.dto.publisher_book.impl.PublisherBookDTOBuilder;
import kz.bisen.springwebapp1.project2Boot.service.impl.DefaultPublisherBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publisher-book")
public class PublisherBookController {
    private final DefaultPublisherBookService service;

    private final PublisherBookDTOBuilder dtoBuilder;

    @Autowired
    public PublisherBookController(DefaultPublisherBookService service, PublisherBookDTOBuilder dtoBuilder) {
        this.service = service;
        this.dtoBuilder = dtoBuilder;
    }

    @GetMapping("/new")
    public List<PublisherBookDTO> getAllByNew() {
        return service.findAllByNew().stream().map(dtoBuilder::fromPublisherBook).toList();
    }

    @GetMapping("/search")
    public PublisherBookDTO getByIsbn(@RequestParam("isbn") String isbn) throws Exception {
        return dtoBuilder.fromPublisherBook(service.findByIsbn(isbn));
    }

    @PutMapping("/search")
    public void saveByIsbn(@RequestBody String isbn) throws Exception {
        service.saveAsBookByIsbn(isbn);
    }

    @PutMapping("/save")
    public void saveAll() {
        service.saveAllAsBook();
    }

    @PutMapping("/reject/{id}")
    public void reject(@PathVariable("id") Long id) throws Exception {
        service.rejectPublisherBook(id);
    }
}
