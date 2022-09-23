package kz.bisen.springwebapp1.project2Boot.services;

import kz.bisen.springwebapp1.project2Boot.models.Book;
import kz.bisen.springwebapp1.project2Boot.models.Person;
import kz.bisen.springwebapp1.project2Boot.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;
    private final PeopleService peopleService;

    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleService peopleService) {
        this.booksRepository = booksRepository;
        this.peopleService = peopleService;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public List<Book> findAll(int page, int booksPerPage) {
        return booksRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }
    public List<Book> findAll(boolean isSorted) {
        if (isSorted)
            return booksRepository.findAll(Sort.by("yearOfProduction"));
        return booksRepository.findAll();
    }

    public List<Book> findAll(int page, int booksPerPage, boolean isSorted) {
        if (isSorted)
            return booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("yearOfProduction"))).getContent();
        return booksRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }

    public Book findOne(int id) {
        Optional<Book> foundPerson = booksRepository.findById(id);
        return foundPerson.orElse(null);
    }

    public Optional<Book> findBookByName(String name) {
        return booksRepository.findBookByName(name).stream().findAny();
    }

    public Optional<Book> findBookByAuthor(String author) {
        return booksRepository.findBookByAuthor(author).stream().findAny();
    }

    public List<Book> findBookByOwner(Person owner) {
        return booksRepository.findBookByOwner(owner);
    }

    public List<Book> findBookByOwnerId(int ownerId) {
        return booksRepository.findBookByOwnerId(ownerId);
    }

    public List<Book> findBookByNameStartingWith(String word) {
        return booksRepository.findBookByNameStartingWith(word);
    }

    @Transactional
    public void save(Book book) {

        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void select(int personId, int id) {
        Date date = new Date();
        findOne(id).setTakenAt(date);
        findOne(id).setOwner(peopleService.findOne(personId));
        peopleService.findOne(personId).getBooks().add(findOne(id));
        booksRepository.save(findOne(id));
    }
    @Transactional
    public void reject(int id) {
        findOne(id).getOwner().getBooks().remove(findOne(id));
        findOne(id).setOwner(null);
        booksRepository.save(findOne(id));
    }
}