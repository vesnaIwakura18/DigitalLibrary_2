package kz.bisen.springwebapp1.project2Boot.service;

import kz.bisen.springwebapp1.project2Boot.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll(int page, int booksPerPage, Optional<Boolean> isSorted);

    Book findOne(int id);

    List<Book> findBookByTitle(String title);

    List<Book> findBookStartingWith(String word);

    void save(Book book);

    void update(int id, Book updatedBook);

    void delete(int id);
}
