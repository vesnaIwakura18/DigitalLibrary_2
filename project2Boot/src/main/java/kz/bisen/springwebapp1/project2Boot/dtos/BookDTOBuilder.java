package kz.bisen.springwebapp1.project2Boot.dtos;

import kz.bisen.springwebapp1.project2Boot.models.Book;

public interface BookDTOBuilder {
    BookDTO fromBook(Book book);

    Book fromBookDTO(BookDTO bookDTO);
}
