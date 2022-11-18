package kz.bisen.springwebapp1.project2Boot.repository;

import kz.bisen.springwebapp1.project2Boot.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<Book> findByTitle(String title);

    List<Book> findByTitleStartingWith(String title);

    List<Book> findByIssueYearStartingWith(int issueYear);

    List<Book> findAll();

    Page<Book> findAll(Pageable pageable);

    Optional<Book> findById(int id);

    <S extends Book> S save(S entity);

    void deleteById(Integer id);
}