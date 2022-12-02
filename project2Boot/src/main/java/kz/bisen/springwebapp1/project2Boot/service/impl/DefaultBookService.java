package kz.bisen.springwebapp1.project2Boot.service.impl;

import kz.bisen.springwebapp1.project2Boot.model.Book;
import kz.bisen.springwebapp1.project2Boot.repository.datajpa.DataJpaBookRepository;
import kz.bisen.springwebapp1.project2Boot.repository.jpa.JpaBookRepository;
import kz.bisen.springwebapp1.project2Boot.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@CacheConfig(cacheNames = "bookData")
@Transactional(readOnly = true)
public class DefaultBookService implements BookService {
    private final DataJpaBookRepository repository;

    @Autowired
    public DefaultBookService(DataJpaBookRepository repository) {
        this.repository = repository;
    }

    @Cacheable
    public List<Book> findAll(int page, int booksPerPage, Optional<Boolean> isSorted) {
        Sort sortByAsc = Sort.by(Sort.Order.asc("issueYear"));
        Sort sortByDesc = Sort.by(Sort.Order.desc("issueYear"));
        return isSorted.map(aBoolean -> repository.findAll(PageRequest.of(page, booksPerPage, aBoolean ? sortByAsc : sortByDesc)).getContent())
                .orElseGet(() -> repository.findAll(PageRequest.of(page,
                        booksPerPage, sortByDesc)).getContent());
    }

    @Cacheable
    public Book findOne(int id) {
        Optional<Book> foundReader = repository.findById(id);
        return foundReader.orElse(null);
    }

    @Cacheable
    public List<Book> findBookByTitle(String title) {
        return repository.findByTitle(title);
    }

    @Cacheable
    public List<Book> findBookStartingWith(String word) {
        return Stream.of(repository.findByTitleStartingWith(word),
                        repository.findByIssueDateTimeStartingWith(LocalDateTime.parse(word)))
                .flatMap(Collection::stream).toList();
    }

    @Transactional
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void save(Book book) {
        repository.save(book);
    }

    @Transactional
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        repository.save(updatedBook);
    }

    @Transactional
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(int id) {
        repository.deleteById(id);
    }
}