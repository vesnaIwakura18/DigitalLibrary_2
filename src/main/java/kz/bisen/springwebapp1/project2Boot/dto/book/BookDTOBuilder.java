package kz.bisen.springwebapp1.project2Boot.dto.book;

import kz.bisen.springwebapp1.project2Boot.model.Book;

public interface BookDTOBuilder {
    BookDTO fromBook(Book book);

    Book fromBookDTO(BookDTO bookDTO);
}
