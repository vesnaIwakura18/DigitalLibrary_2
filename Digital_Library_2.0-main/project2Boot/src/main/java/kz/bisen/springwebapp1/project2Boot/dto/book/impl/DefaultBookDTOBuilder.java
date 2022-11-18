package kz.bisen.springwebapp1.project2Boot.dto.book.impl;

import kz.bisen.springwebapp1.project2Boot.dto.book.BookDTO;
import kz.bisen.springwebapp1.project2Boot.dto.book.BookDTOBuilder;
import kz.bisen.springwebapp1.project2Boot.model.Book;
import org.springframework.stereotype.Component;

@Component
public class DefaultBookDTOBuilder implements BookDTOBuilder {

    @Override
    public BookDTO fromBook(Book book) {
        return new BookDTO(book.getId(),
                book.getTitle(),
                book.getIssueYear(),
                book.getAmount(),
                book.getIsbn());
    }

    @Override
    public Book fromBookDTO(BookDTO bookDTO) {
        return new Book(bookDTO.getId(),
                bookDTO.getTitle(),
                bookDTO.getIssueYear(),
                bookDTO.getAmount(),
                10,
                bookDTO.getIsbn());
    }
}