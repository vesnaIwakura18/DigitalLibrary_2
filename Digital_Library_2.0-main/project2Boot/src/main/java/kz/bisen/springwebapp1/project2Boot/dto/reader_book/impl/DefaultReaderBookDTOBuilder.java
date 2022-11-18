package kz.bisen.springwebapp1.project2Boot.dto.reader_book.impl;

import kz.bisen.springwebapp1.project2Boot.dto.reader_book.ReaderBookDTO;
import kz.bisen.springwebapp1.project2Boot.dto.reader_book.ReaderBookDTOBuilder;
import kz.bisen.springwebapp1.project2Boot.model.ReaderBook;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DefaultReaderBookDTOBuilder implements ReaderBookDTOBuilder {

    public ReaderBook fromReaderBookDTO(ReaderBookDTO readerBookDTO) {
        return new ReaderBook(readerBookDTO.getReaderId(),
                readerBookDTO.getBookId(),
                readerBookDTO.getInstance(),
                LocalDateTime.now(),
                readerBookDTO.getReadingDays());
    }

    public ReaderBookDTO fromReaderBook(ReaderBook readerBook) {
        return new ReaderBookDTO(readerBook.getReaderId(),
                readerBook.getBookId(),
                readerBook.getInstance(),
                readerBook.getReadingDays());
    }
}