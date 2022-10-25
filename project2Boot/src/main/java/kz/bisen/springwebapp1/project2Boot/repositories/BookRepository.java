package kz.bisen.springwebapp1.project2Boot.repositories;

import kz.bisen.springwebapp1.project2Boot.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByTitle(String title);

    List<Book> findByOwnerId(int id);

    List<Book> findByAuthor(String author);

    List<Book> findByTitleStartingWith(String title);

    List<Book> findByAuthorStartingWith(String author);

    List<Book> findByIssueYearStartingWith(String issueYear);
}