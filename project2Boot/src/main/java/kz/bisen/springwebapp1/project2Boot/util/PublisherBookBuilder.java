package kz.bisen.springwebapp1.project2Boot.util;

import kz.bisen.springwebapp1.project2Boot.model.Book;
import kz.bisen.springwebapp1.project2Boot.model.PublisherBook;
import kz.bisen.springwebapp1.project2Boot.repository.datajpa.CrudAuthorRepository;
import kz.bisen.springwebapp1.project2Boot.repository.datajpa.CrudBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PublisherBookBuilder {
    private final CrudAuthorRepository authorRepository;

    @Autowired
    public PublisherBookBuilder(CrudAuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Book fromPublisherBook(PublisherBook publisherBook) {
        return new Book(publisherBook.getTitle(),
                publisherBook.getIssueDateTime(),
                0,
                10,
                publisherBook.getIsbn(),
                authorRepository.findAuthorByFirstNameAndLastName(publisherBook.getAuthorFirstName(), publisherBook.getAuthorLastName()).orElse(null));
    }
}
