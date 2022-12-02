package kz.bisen.springwebapp1.project2Boot.dto.publisher_book.impl;

import kz.bisen.springwebapp1.project2Boot.dto.publisher_book.PublisherBookDTO;
import kz.bisen.springwebapp1.project2Boot.model.PublisherBook;
import org.springframework.stereotype.Component;

@Component
public class PublisherBookDTOBuilder {
    public PublisherBookDTO fromPublisherBook(PublisherBook publisherBook) {
        return new PublisherBookDTO(publisherBook.getId(),
                publisherBook.getTitle(),
                publisherBook.getAuthorFirstName(),
                publisherBook.getAuthorLastName(),
                publisherBook.getIsbn(),
                publisherBook.getIssueDateTime());
    }
}
