package kz.bisen.springwebapp1.project2Boot.repository.datajpa;

import kz.bisen.springwebapp1.project2Boot.model.ReaderBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudReaderBookRepository extends JpaRepository<ReaderBook, Integer> {
    List<ReaderBook> findByReaderId(int id);

//    Optional<ReaderBook> findByBookId TODO
}