package kz.bisen.springwebapp1.project2Boot.kafka.listener;

import kz.bisen.springwebapp1.project2Boot.kafka.BookIsbnMessage;
import kz.bisen.springwebapp1.project2Boot.repository.datajpa.CrudBookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class BookQuantityListener {
    private final CrudBookRepository repository;

    @Autowired
    public BookQuantityListener(CrudBookRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "library.topic", groupId = "library-A")
    public void handle(BookIsbnMessage message) {
        List<String> isbns = message.getIsbns();
        isbns
                .stream()
                .map(o -> repository.findByIsbn(o).get())
                .forEach(b -> {
                    b.setAmount(b.getAmount() + message.getAmount());
                    repository.save(b);
                });
    }
}
