package kz.bisen.springwebapp1.project2Boot.repository.datajpa;

import kz.bisen.springwebapp1.project2Boot.model.PublisherBook;
import kz.bisen.springwebapp1.project2Boot.model.PublisherBookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CrudPublisherBookRepository extends JpaRepository<PublisherBook, Long> {
    List<PublisherBook> findByStatus(PublisherBookStatus status);

    Optional<PublisherBook> findByIsbn(String isbn);
}
