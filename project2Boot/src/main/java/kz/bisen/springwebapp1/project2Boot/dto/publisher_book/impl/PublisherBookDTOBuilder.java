package kz.bisen.springwebapp1.project2Boot.dto.publisher_book.impl;

import kz.bisen.springwebapp1.project2Boot.dto.publisher_book.PublisherBookDTO;
import kz.bisen.springwebapp1.project2Boot.model.PublisherBook;
import org.springframework.stereotype.Component;

@Component
public class PublisherBookDTOBuilder {
    public PublisherBookDTO fromPublisherBook(PublisherBook publisherBook) {
        return new PublisherBookDTO(
                publisherBook.getTitle(),
                publisherBook.getAuthorFirstName(),
                publisherBook.getAuthorLastName(),
                publisherBook.getIsbn(),
                publisherBook.getIssueDateTime()
        );
    }

//    public PublisherBook fromPublisherBookDTO(PublisherBookDTO publisherBookDTO) {
//        return new PublisherBook(
//                publisherBookDTO.getTitle(),
//                publisherBookDTO.getAuthorFirstName(),
//                publisherBookDTO.getAuthorLastName(),
//                publisherBookDTO.getIsbn(),
//                publisherBookDTO.getIssueDateTime()
//        );
//    }
}
