package kz.bisen.springwebapp1.project2Boot.repository.datajpa;

import kz.bisen.springwebapp1.project2Boot.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CrudBookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByTitle(String title);

    List<Book> findByTitleStartingWith(String title);


    List<Book> findByIssueDateTimeStartingWith(LocalDateTime time);

    Optional<Book> findByIsbn(String isbn);
}