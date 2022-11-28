package kz.bisen.springwebapp1.project2Boot.repository.datajpa;


import kz.bisen.springwebapp1.project2Boot.model.Book;
import kz.bisen.springwebapp1.project2Boot.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class DataJpaBookRepository implements BookRepository {
    private final CrudBookRepository crudBookRepository;

    @Autowired
    public DataJpaBookRepository(CrudBookRepository crudBookRepository) {
        this.crudBookRepository = crudBookRepository;
    }

    @Override
    public List<Book> findByTitle(String title) {
        return crudBookRepository.findByTitle(title);
    }

    @Override
    public List<Book> findByTitleStartingWith(String titleStarting) {
        return crudBookRepository.findByTitleStartingWith(titleStarting);
    }

    @Override
    public List<Book> findByIssueDateTimeStartingWith(LocalDateTime issueDateTimeStarting) {
        return crudBookRepository.findByIssueDateTimeStartingWith(issueDateTimeStarting);
    }

    @Override
    public List<Book> findAll() {
        return crudBookRepository.findAll();
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return crudBookRepository.findAll(pageable);
    }

    @Override
    public Optional<Book> findById(int id) {
        return crudBookRepository.findById(id);
    }

    @Override
    public <S extends Book> S save(S entity) {
        return crudBookRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        crudBookRepository.deleteById(id);
    }
}