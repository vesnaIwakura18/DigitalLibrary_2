package kz.bisen.springwebapp1.project2Boot.services;

import kz.bisen.springwebapp1.project2Boot.models.Book;
import kz.bisen.springwebapp1.project2Boot.models.Person;
import kz.bisen.springwebapp1.project2Boot.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;
    private final PersonService personService;

    @Autowired
    public BookService(BookRepository bookRepository, PersonService personService) {
        this.bookRepository = bookRepository;
        this.personService = personService;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> findAll(int page, int booksPerPage) { // TODO change
        return bookRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }
    public List<Book> findAll(boolean isSorted) {
        if (isSorted)
            return bookRepository.findAll(Sort.by("yearOfProduction"));
        return bookRepository.findAll();
    }

    public List<Book> findAll(int page, int booksPerPage, boolean isSorted) {
        if (isSorted)
            return bookRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("yearOfProduction"))).getContent();
        return bookRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }

    public Book findOne(int id) {
        Optional<Book> foundPerson = bookRepository.findById(id);
        return foundPerson.orElse(null);
    }

    public Optional<Book> findBookByName(String name) {
        return bookRepository.findBookByName(name).stream().findAny();
    }

    public Optional<Book> findBookByAuthor(String author) {
        return bookRepository.findBookByAuthor(author).stream().findAny();
    }

    public List<Book> findBookByOwner(Person owner) {
        return bookRepository.findBookByOwner(owner);
    }

    public List<Book> findBookByOwnerId(int ownerId) {
        return bookRepository.findBookByOwnerId(ownerId);
    }

    public List<Book> findBookByNameStartingWith(String word) {
        return bookRepository.findBookByNameStartingWith(word);
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
    public void select(int personId, int id) {
        Date date = new Date();
        findOne(id).setTakenAt(date);
        findOne(id).setOwner(personService.findOne(personId));
        personService.findOne(personId).getBooks().add(findOne(id));
        bookRepository.save(findOne(id));
    }
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void reject(int id) {
        findOne(id).getOwner().getBooks().remove(findOne(id));
        findOne(id).setOwner(null);
        bookRepository.save(findOne(id));
    }
}