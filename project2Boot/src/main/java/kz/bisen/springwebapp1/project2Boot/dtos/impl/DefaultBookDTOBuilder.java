package kz.bisen.springwebapp1.project2Boot.dtos.impl;

import kz.bisen.springwebapp1.project2Boot.dtos.BookDTO;
import kz.bisen.springwebapp1.project2Boot.dtos.BookDTOBuilder;
import kz.bisen.springwebapp1.project2Boot.models.Book;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultBookDTOBuilder implements BookDTOBuilder {

    private final ModelMapper modelMapper;

    @Autowired
    public DefaultBookDTOBuilder(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public BookDTO fromBook(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }

    @Override
    public Book fromBookDTO(BookDTO bookDTO) {
        return modelMapper.map(bookDTO, Book.class);
    }
}