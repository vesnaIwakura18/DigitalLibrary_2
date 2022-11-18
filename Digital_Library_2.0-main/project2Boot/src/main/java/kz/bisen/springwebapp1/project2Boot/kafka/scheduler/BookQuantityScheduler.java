package kz.bisen.springwebapp1.project2Boot.kafka.scheduler;

import kz.bisen.springwebapp1.project2Boot.kafka.BookIsbnMessage;
import kz.bisen.springwebapp1.project2Boot.kafka.producer.BookQuantityProducer;
import kz.bisen.springwebapp1.project2Boot.model.Book;
import kz.bisen.springwebapp1.project2Boot.repository.datajpa.CrudBookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class BookQuantityScheduler {
    private final CrudBookRepository repository;

    private final BookQuantityProducer producer;

    @Autowired
    private BookQuantityScheduler(CrudBookRepository repository,
                                  BookQuantityProducer producer) {
        this.repository = repository;
        this.producer = producer;
    }

    @Scheduled(fixedRateString = "PT03H", initialDelay = 1000)
    private void scheduleCheckQuantity() {
        List<String> isbns = repository.findAll()
                .stream()
                .filter(b -> b.getAmount() < b.getMinAmount())
                .map(Book::getIsbn)
                .toList();
        producer.sendMessageToTopic(new BookIsbnMessage(isbns, 500));
    }

    @Scheduled(fixedRateString = "PT05H", initialDelay = 1000)
    private void scheduleReduceQuantity() {
        List<Book> books = repository.findAll();
        books.forEach(b -> {
            int randomValue = new Random().nextInt(b.getAmount() + 1);
            b.setAmount(b.getAmount() - randomValue);
        });

        repository.saveAll(books);
    }
}
