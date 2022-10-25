package kz.bisen.springwebapp1.project2Boot.services.impl;

import kz.bisen.springwebapp1.project2Boot.models.Book;
import kz.bisen.springwebapp1.project2Boot.repositories.BookRepository;
import kz.bisen.springwebapp1.project2Boot.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class DefaultBookService implements BookService
    {
    private final BookRepository bookRepository;
    private final DefaultReaderService defaultReaderService;

    @Autowired
    public DefaultBookService(BookRepository bookRepository,
                              DefaultReaderService defaultReaderService) {
        this.bookRepository = bookRepository;
        this.defaultReaderService = defaultReaderService;
    }

    @Cacheable
    public List<Book> findAll(int page, int booksPerPage, Optional<Boolean> isSorted) {
        Sort sortByAsc = Sort.by(Sort.Order.asc("issueYear"));
        Sort sortByDesc = Sort.by(Sort.Order.desc("issueYear"));
        return isSorted.map(aBoolean -> bookRepository.findAll(PageRequest.of(page, booksPerPage,
                aBoolean ? sortByAsc : sortByDesc)).getContent())
                .orElseGet(() -> bookRepository.findAll(PageRequest.of(page,
                booksPerPage, sortByDesc)).getContent());
    }

    @Cacheable
    public Book findOne(int id) {
        Optional<Book> foundReader = bookRepository.findById(id);
        return foundReader.orElse(null);
    }

    @Cacheable
    public Optional<Book> findBookByTitle(String title) {
        return bookRepository.findByTitle(title).stream().findAny();
    }

    @Cacheable
    public Optional<Book> findBookByAuthor(String author) {
        return bookRepository.findByAuthor(author).stream().findAny();
    }

    @Cacheable
    public List<Book> findBookStartingWith(String word) {
        return Stream.of(bookRepository.findByTitleStartingWith(word),
                         bookRepository.findByAuthorStartingWith(word),
                         bookRepository.findByIssueYearStartingWith(word))
                         .flatMap(Collection::stream).toList();
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        bookRepository.save(updatedBook);
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void select(int readerId, int id) {
        findOne(id).setDateTimeTaken(LocalDateTime.now());
        findOne(id).setReader(defaultReaderService.findOne(readerId));
        defaultReaderService.findOne(readerId).getBooks().add(findOne(id));
        bookRepository.save(findOne(id));
    }
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void reject(int id) {
        findOne(id).getReader().getBooks().remove(findOne(id));
        findOne(id).setReader(null);
        bookRepository.save(findOne(id));
    }
}