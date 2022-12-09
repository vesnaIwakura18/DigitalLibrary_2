package kz.bisen.springwebapp1.project2Boot.dto.book;

import java.util.List;

public class BookDtoList {
    public List<BookDTO> bookDtos;

    public BookDtoList(List<BookDTO> bookDtos) {
        this.bookDtos = bookDtos;
    }

    public BookDtoList() {
    }

    public List<BookDTO> getBookDTOs() {
        return bookDtos;
    }

    public void setBookDTOs(List<BookDTO> bookDTOs) {
        this.bookDtos = bookDTOs;
    }
}
