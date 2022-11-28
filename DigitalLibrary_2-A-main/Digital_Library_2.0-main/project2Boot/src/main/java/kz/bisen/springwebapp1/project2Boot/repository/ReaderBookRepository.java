package kz.bisen.springwebapp1.project2Boot.repository;

import kz.bisen.springwebapp1.project2Boot.model.Book;
import kz.bisen.springwebapp1.project2Boot.model.ReaderBook;

import java.util.List;
import java.util.Optional;

public interface ReaderBookRepository {
    List<ReaderBook> findByReaderId(Integer id);

//    Optional<ReaderBook> findByBookId TODO

    Optional<ReaderBook> findById(Integer id);

    <S extends ReaderBook> S save(S entity);

    void delete(ReaderBook readerBook);
}