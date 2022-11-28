package kz.bisen.springwebapp1.project2Boot.service;

import kz.bisen.springwebapp1.project2Boot.model.ReaderBook;

import java.util.List;

public interface ReaderBookService {
    List<ReaderBook> findReaderBooks(int readerId);

    ReaderBook setBook(ReaderBook readerBook);

    void returnBook(int id);
}
