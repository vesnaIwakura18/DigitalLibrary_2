package kz.bisen.springwebapp1.project2Boot.dto.book.impl;

import kz.bisen.springwebapp1.project2Boot.dto.author.impl.DefaultAuthorDtoBuilder;
import kz.bisen.springwebapp1.project2Boot.dto.book.BookDTO;
import kz.bisen.springwebapp1.project2Boot.dto.book.BookDTOBuilder;
import kz.bisen.springwebapp1.project2Boot.model.Book;
import kz.bisen.springwebapp1.project2Boot.model.PublisherBook;
import kz.bisen.springwebapp1.project2Boot.repository.datajpa.CrudAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultBookDTOBuilder implements BookDTOBuilder {
    private final CrudAuthorRepository authorRepository;

    @Autowired
    public DefaultBookDTOBuilder(CrudAuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public BookDTO fromBook(Book book) {
        return new BookDTO(
                book.getTitle(),
                book.getIssueDateTime(),
                book.getAmount(),
                book.getMinAmount(),
                book.getIsbn(),
                book.getAuthor().getFirstName(),
                book.getAuthor().getLastName()
        );
    }

    @Override
    public Book fromBookDTO(BookDTO bookDTO) {
        return new Book(bookDTO.getTitle(),
                bookDTO.getIssueDateTime(),
                0,
                bookDTO.getMinAmount(),
                bookDTO.getIsbn(),
                authorRepository.findAuthorByFirstNameAndLastName(
                                bookDTO.getAuthorFirstName(),
                                bookDTO.getAuthorLastName()
                        ).orElse(null));
    }
}