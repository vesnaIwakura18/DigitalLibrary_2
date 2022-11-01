package kz.bisen.springwebapp1.project2Boot.dtos.Book.impl;

import kz.bisen.springwebapp1.project2Boot.dtos.Book.BookDTO;
import kz.bisen.springwebapp1.project2Boot.dtos.Book.BookDTOBuilder;
import kz.bisen.springwebapp1.project2Boot.models.Book;
import org.springframework.stereotype.Component;

@Component
public class DefaultBookDTOBuilder implements BookDTOBuilder {

    @Override
    public BookDTO fromBook(Book book) {
        return new BookDTO(book.getTitle(),
                book.getAuthor(),
                book.getIssueYear());
    }

    @Override
    public Book fromBookDTO(BookDTO bookDTO) {
        return new Book(bookDTO.getTitle(),
                bookDTO.getAuthor(),
                bookDTO.getIssueYear());
    }
}