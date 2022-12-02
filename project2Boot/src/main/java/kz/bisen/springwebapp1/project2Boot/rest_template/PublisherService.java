package kz.bisen.springwebapp1.project2Boot.rest_template;

import kz.bisen.springwebapp1.project2Boot.dto.book.BookDTO;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class PublisherService {
    private final RestTemplate restTemplate = new RestTemplate();

    private final String bookResourceUrl = "http://localhost:8080/book";

    public BookDTO[] getAll() {
        return restTemplate.getForObject(bookResourceUrl, BookDTO[].class);
    }

    public BookDTO[] getAllByIsbn(List<String> isbns) {
        return restTemplate.getForObject(bookResourceUrl + "/isbn", BookDTO[].class);
    }
}
