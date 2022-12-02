package kz.bisen.springwebapp1.project2Boot.dto.book.impl;

import kz.bisen.springwebapp1.project2Boot.dto.author.impl.DefaultAuthorDTOBuilder;
import kz.bisen.springwebapp1.project2Boot.dto.book.BookDTO;
import kz.bisen.springwebapp1.project2Boot.dto.book.BookDTOBuilder;
import kz.bisen.springwebapp1.project2Boot.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultBookDTOBuilder implements BookDTOBuilder {
    private final DefaultAuthorDTOBuilder authorDTOBuilder;

    @Autowired
    public DefaultBookDTOBuilder(DefaultAuthorDTOBuilder authorDTOBuilder) {
        this.authorDTOBuilder = authorDTOBuilder;
    }

    @Override
    public BookDTO fromBook(Book book) {
        return new BookDTO(book.getId(), book.getTitle(), book.getIssueDateTime(), book.getAmount(), book.getIsbn(), authorDTOBuilder.fromAuthor(book.getAuthor()));
    }

    @Override
    public Book fromBookDTO(BookDTO bookDTO) {
        return new Book(bookDTO.getTitle(), bookDTO.getIssueDateTime(), 0, 10, bookDTO.getIsbn(), authorDTOBuilder.fromAuthorDTO(bookDTO.getAuthorDTO()));
    }
}