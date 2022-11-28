package kz.bisen.springwebapp1.project2Boot.rest_template;

import kz.bisen.springwebapp1.project2Boot.dto.book.BookDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class PublisherService {
    private final RestTemplate restTemplate = new RestTemplate();

    private final String bookResourceUrl = "http://localhost:8080/book";

    public ResponseEntity<BookDTO[]> getAll() {
        return restTemplate.getForEntity(bookResourceUrl, BookDTO[].class);
    }

    public BookDTO postBook(BookDTO bookDTO) {
        HttpEntity<BookDTO> request = new HttpEntity<>(bookDTO);
        return restTemplate.postForObject(bookResourceUrl, request, BookDTO.class);
    }
}
