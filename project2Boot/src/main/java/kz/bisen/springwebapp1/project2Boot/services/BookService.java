package kz.bisen.springwebapp1.project2Boot.services;

import kz.bisen.springwebapp1.project2Boot.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll(int page, int booksPerPage, Optional<Boolean> isSorted);

    Book findOne(int id);

    Optional<Book> findBookByTitle(String title);

    Optional<Book> findBookByAuthor(String author);

    List<Book> findBookStartingWith(String word);

    void save(Book book);

    void update(int id, Book updatedBook);

    void delete(int id);

    void select(int readerId, int id);

    void reject(int id);
}
