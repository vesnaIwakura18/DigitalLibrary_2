package kz.bisen.springwebapp1.project2Boot.kafka.producer;

import kz.bisen.springwebapp1.project2Boot.kafka.BookIsbnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookQuantityProducer {
    private final KafkaTemplate<String, BookIsbnMessage> kafkaTemplate;

    @Autowired
    public BookQuantityProducer(KafkaTemplate<String, BookIsbnMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessageToTopic(BookIsbnMessage message) {
        kafkaTemplate.send("producer.topic", message.getPartition(), message);
    }
}
