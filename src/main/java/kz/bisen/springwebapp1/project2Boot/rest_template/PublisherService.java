package kz.bisen.springwebapp1.project2Boot.rest_template;

import kz.bisen.springwebapp1.project2Boot.dto.book.BookDTO;
import kz.bisen.springwebapp1.project2Boot.dto.book.BookDtoList;
import kz.bisen.springwebapp1.project2Boot.exception.BookNotFoundException;
import kz.bisen.springwebapp1.project2Boot.model.Book;
import kz.bisen.springwebapp1.project2Boot.model.PublisherBook;
import kz.bisen.springwebapp1.project2Boot.model.PublisherBookStatus;
import kz.bisen.springwebapp1.project2Boot.repository.datajpa.CrudBookRepository;
import kz.bisen.springwebapp1.project2Boot.repository.datajpa.CrudPublisherBookRepository;
import kz.bisen.springwebapp1.project2Boot.util.PublisherBookBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {
    private final RestTemplate restTemplate = new RestTemplate();

    private final String BOOK_RESOURCE_URL = "http://localhost:8080/book";

    private final CrudPublisherBookRepository publisherBookRepository;

    private final PublisherBookBuilder publisherBookBuilder;

    private final CrudBookRepository bookRepository;

    @Autowired
    public PublisherService(CrudPublisherBookRepository publisherBookRepository, PublisherBookBuilder publisherBookBuilder, CrudBookRepository bookRepository) {
        this.publisherBookRepository = publisherBookRepository;
        this.publisherBookBuilder = publisherBookBuilder;
        this.bookRepository = bookRepository;
    }

    public BookDTO[] getAll() {
        return restTemplate.getForObject(BOOK_RESOURCE_URL, BookDTO[].class);
    }

    public List<BookDTO> getAllByIsbns(List<String> isbns) {
        BookDtoList bookDtoList = restTemplate.postForObject(BOOK_RESOURCE_URL + "/isbn", isbns, BookDtoList.class);
        if (bookDtoList == null) throw new BookNotFoundException(HttpStatus.NOT_FOUND, "not found");
        List<BookDTO> bookDTOs = bookDtoList.getBookDTOs();
        System.out.println(bookDTOs);

        List<PublisherBook> publisherBooks = bookDTOs.stream().map(publisherBookBuilder::fromBook).toList();
        publisherBookRepository.saveAll(publisherBooks);

        return bookDTOs;
    }

    public void saveBooks(List<Book> books) {
        List<PublisherBook> foundPublisherBooks = publisherBookRepository.findByStatus(PublisherBookStatus.NEW);
        List<PublisherBook> publisherBooksToUpdate =
                foundPublisherBooks
                        .stream()
                        .peek(p -> {
                            Optional<Book> book = books.stream().filter(b -> b.getIsbn().equals(p.getIsbn())).findFirst().stream().findAny();
                            p.setStatus(book.isPresent() ? PublisherBookStatus.NEW : PublisherBookStatus.REJECTED);
                        })
                        .toList();
        publisherBookRepository.saveAll(publisherBooksToUpdate);
        bookRepository.saveAll(books);
    }

    public List<PublisherBook> getAllNew() {
        return publisherBookRepository.findByStatus(PublisherBookStatus.NEW);
    }
}
