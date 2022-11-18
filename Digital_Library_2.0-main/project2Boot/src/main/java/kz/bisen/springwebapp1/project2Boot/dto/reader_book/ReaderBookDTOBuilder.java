package kz.bisen.springwebapp1.project2Boot.dto.reader_book;

import kz.bisen.springwebapp1.project2Boot.model.ReaderBook;

public interface ReaderBookDTOBuilder {
    ReaderBook fromReaderBookDTO(ReaderBookDTO readerBookDTO);

    ReaderBookDTO fromReaderBook(ReaderBook readerBook);
}