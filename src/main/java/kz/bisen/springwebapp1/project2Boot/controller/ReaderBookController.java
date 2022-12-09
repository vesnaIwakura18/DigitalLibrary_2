package kz.bisen.springwebapp1.project2Boot.controller;

import kz.bisen.springwebapp1.project2Boot.dto.reader_book.ReaderBookDTO;
import kz.bisen.springwebapp1.project2Boot.dto.reader_book.impl.DefaultReaderBookDTOBuilder;
import kz.bisen.springwebapp1.project2Boot.exception.BookNotCreatedException;
import kz.bisen.springwebapp1.project2Boot.model.ReaderBook;
import kz.bisen.springwebapp1.project2Boot.service.impl.DefaultReaderBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/reader-book")
public class ReaderBookController {
    private final DefaultReaderBookService defaultReaderBookService;

    private final DefaultReaderBookDTOBuilder defaultReaderBookDTOBuilder;

    @Autowired
    public ReaderBookController(DefaultReaderBookService defaultReaderBookService, DefaultReaderBookDTOBuilder defaultReaderBookDTOBuilder) {
        this.defaultReaderBookService = defaultReaderBookService;
        this.defaultReaderBookDTOBuilder = defaultReaderBookDTOBuilder;
    }

    @GetMapping("/{id}")
    public List<ReaderBookDTO> getReaderBooks(@PathVariable("id") int readerId) {
        final List<ReaderBook> readerBooks = defaultReaderBookService.findReaderBooks(readerId);

        return readerBooks
                .stream()
                .map(defaultReaderBookDTOBuilder::fromReaderBook)
                .toList();
    }

    @PostMapping
    public ReaderBook setBook(@RequestBody @Valid ReaderBookDTO readerBookDTO,
                              BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error : errors) {
                errorMsg
                        .append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new BookNotCreatedException(HttpStatus.NOT_ACCEPTABLE, errorMsg.toString());
        }
        final ReaderBook readerBook = defaultReaderBookDTOBuilder.fromReaderBookDTO(readerBookDTO);

        return defaultReaderBookService.setBook(readerBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> returnBook(@PathVariable("id") int id) {
        defaultReaderBookService.returnBook(id);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}