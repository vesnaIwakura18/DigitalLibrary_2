package kz.bisen.springwebapp1.project2Boot.repositories;


import kz.bisen.springwebapp1.project2Boot.models.Book;
import kz.bisen.springwebapp1.project2Boot.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    List<Book> findBookByName(String name);

    List<Book> findBookByOwner(Person owner);

    List<Book> findBookByOwnerId(int id);

    List<Book> findBookByAuthor(String author);

    List<Book> findBookByNameStartingWith(String name);
}