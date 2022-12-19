package kz.bisen.springwebapp1.project2Boot.soap.builder;


import kz.bisen.springwebapp1.project2Boot.model.Book;
import kz.bisen.springwebapp1.project2Boot.repository.datajpa.CrudAuthorRepository;
import kz.bisen.springwebapp1.project2Boot.soap.generated.SoapBook;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SoapBookBuilder {
    private final CrudAuthorRepository authorRepository;

    public SoapBookBuilder(CrudAuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Book fromSoapBook(SoapBook soapBook) {
        return new Book(
                soapBook.getTitle(),
                LocalDateTime.parse(soapBook.getIssueDateTime()),
                soapBook.getAmount(),
                soapBook.getMinAmount(),
                soapBook.getIsbn(),
                authorRepository.findAuthorByFirstNameAndLastName(soapBook.getAuthorFirstName(), soapBook.getAuthorLastName()).orElse(null)
        );
    }
}
