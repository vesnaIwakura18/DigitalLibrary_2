package kz.bisen.springwebapp1.project2Boot.service.impl;

import kz.bisen.springwebapp1.project2Boot.dto.book.BookDTO;
import kz.bisen.springwebapp1.project2Boot.exception.BookNotFoundException;
import kz.bisen.springwebapp1.project2Boot.model.Book;
import kz.bisen.springwebapp1.project2Boot.model.PublisherBook;
import kz.bisen.springwebapp1.project2Boot.model.PublisherBookStatus;
import kz.bisen.springwebapp1.project2Boot.repository.datajpa.CrudBookRepository;
import kz.bisen.springwebapp1.project2Boot.repository.datajpa.CrudPublisherBookRepository;
import kz.bisen.springwebapp1.project2Boot.util.PublisherBookBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DefaultPublisherBookService {
    private final CrudPublisherBookRepository repository;

    private final CrudBookRepository bookRepository;

    private final PublisherBookBuilder publisherBookBuilder;

    @Autowired
    public DefaultPublisherBookService(CrudPublisherBookRepository repository,
                                       CrudBookRepository bookRepository,
                                       PublisherBookBuilder publisherBookBuilder) {
        this.repository = repository;
        this.bookRepository = bookRepository;
        this.publisherBookBuilder = publisherBookBuilder;
    }

}
