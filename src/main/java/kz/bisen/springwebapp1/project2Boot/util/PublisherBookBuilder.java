package kz.bisen.springwebapp1.project2Boot.util;

import kz.bisen.springwebapp1.project2Boot.dto.author.impl.DefaultAuthorDtoBuilder;
import kz.bisen.springwebapp1.project2Boot.dto.book.BookDTO;
import kz.bisen.springwebapp1.project2Boot.dto.book.impl.DefaultBookDTOBuilder;
import kz.bisen.springwebapp1.project2Boot.model.Author;
import kz.bisen.springwebapp1.project2Boot.model.Book;
import kz.bisen.springwebapp1.project2Boot.model.PublisherBook;
import kz.bisen.springwebapp1.project2Boot.model.PublisherBookStatus;
import kz.bisen.springwebapp1.project2Boot.repository.datajpa.CrudAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PublisherBookBuilder {
    private final CrudAuthorRepository authorRepository;

    private final DefaultAuthorDtoBuilder bookDTOBuilder;

    @Autowired
    public PublisherBookBuilder(CrudAuthorRepository authorRepository, DefaultAuthorDtoBuilder bookDTOBuilder) {
        this.authorRepository = authorRepository;
        this.bookDTOBuilder = bookDTOBuilder;
    }

//    public BookDTO fromPublisherBook(PublisherBook publisherBook) {
//        Author author = authorRepository.findAuthorByFirstNameAndLastName(publisherBook.getAuthorFirstName(), publisherBook.getAuthorLastName()).orElse(null);
//        return new BookDTO(
//                publisherBook.getTitle(),
//                publisherBook.getIssueDateTime(),
//                0,
//                publisherBook.getIsbn(),
//                0,
//                author.getFirstName(),
//                author.getLastName()
//        );
//    }

    public PublisherBook fromBook(BookDTO book) {
        return new PublisherBook(
                book.getTitle(),
                book.getAuthorFirstName(),
                book.getAuthorLastName(),
                LocalDateTime.now(),
                PublisherBookStatus.NEW,
                book.getIsbn(),
                book.getIssueDateTime()
        );
    }
}
