package kz.bisen.springwebapp1.project2Boot.service.impl;

import kz.bisen.springwebapp1.project2Boot.model.Book;
import kz.bisen.springwebapp1.project2Boot.model.PublisherBook;
import kz.bisen.springwebapp1.project2Boot.model.PublisherBookStatus;
import kz.bisen.springwebapp1.project2Boot.repository.datajpa.CrudBookRepository;
import kz.bisen.springwebapp1.project2Boot.repository.datajpa.CrudPublisherBookRepository;
import kz.bisen.springwebapp1.project2Boot.repository.datajpa.DataJpaBookRepository;
import kz.bisen.springwebapp1.project2Boot.util.PublisherBookBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DefaultPublisherBookService {
    private final CrudPublisherBookRepository repository;

    private final CrudBookRepository bookRepository;

    private final PublisherBookBuilder publisherBookBuilder;

    @Autowired
    public DefaultPublisherBookService(CrudPublisherBookRepository repository, CrudBookRepository bookRepository, PublisherBookBuilder publisherBookBuilder) {
        this.repository = repository;
        this.bookRepository = bookRepository;
        this.publisherBookBuilder = publisherBookBuilder;
    }

    public PublisherBook findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<PublisherBook> findAllByNew() {
        return repository.findByStatus(PublisherBookStatus.NEW);
    }

    public PublisherBook findByIsbn(String isbn) throws Exception {
        return repository.findByIsbn(isbn).orElseThrow(Exception::new);
    }

    @Transactional
    public void saveAsBookByIsbn(String isbn) throws Exception {
        PublisherBook publisherBook = repository.findByIsbn(isbn).orElseThrow(Exception::new);
        publisherBook.setStatus(PublisherBookStatus.ADDED);
        repository.save(publisherBook);
        bookRepository.save(publisherBookBuilder.fromPublisherBook(publisherBook));
    }

    @Transactional
    public void saveAllAsBook() {
        List<PublisherBook> publisherBooks = repository.findByStatus(PublisherBookStatus.NEW);
        publisherBooks.forEach(b -> b.setStatus(PublisherBookStatus.ADDED));
        List<Book> books = publisherBooks.stream().map(publisherBookBuilder::fromPublisherBook).toList();
        bookRepository.saveAll(books);
        repository.saveAll(publisherBooks);
    }

    @Transactional
    public void rejectPublisherBook(Long id) throws Exception {
        PublisherBook publisherBook = repository.findById(id).orElseThrow(Exception::new);
        publisherBook.setStatus(PublisherBookStatus.REJECTED);
        repository.save(publisherBook);
    }
}
