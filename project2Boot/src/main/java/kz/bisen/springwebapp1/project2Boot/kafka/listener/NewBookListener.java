package kz.bisen.springwebapp1.project2Boot.kafka.listener;

import kz.bisen.springwebapp1.project2Boot.dto.book.BookDTO;
import kz.bisen.springwebapp1.project2Boot.dto.book.impl.DefaultBookDTOBuilder;
import kz.bisen.springwebapp1.project2Boot.model.Book;
import kz.bisen.springwebapp1.project2Boot.model.PublisherBookStatus;
import kz.bisen.springwebapp1.project2Boot.repository.datajpa.CrudBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewBookListener {
    private final CrudBookRepository repository;

    private final DefaultBookDTOBuilder dtoBuilder;

    @Autowired
    public NewBookListener(CrudBookRepository repository, DefaultBookDTOBuilder dtoBuilder) {
        this.repository = repository;
        this.dtoBuilder = dtoBuilder;
    }

    @KafkaListener(topics = "library.topic", groupId = "library-B")
    private void handle(List<BookDTO> bookDTOS) {
        final List<Book> books = bookDTOS
                .stream()
                .map(dtoBuilder::fromBookDTO)
                .toList();
        repository.saveAll(books);
    }
}
