package kz.bisen.springwebapp1.project2Boot.controller;

import kz.bisen.springwebapp1.project2Boot.dto.book.BookDTO;
import kz.bisen.springwebapp1.project2Boot.dto.book.impl.DefaultBookDTOBuilder;
import kz.bisen.springwebapp1.project2Boot.model.Book;
import kz.bisen.springwebapp1.project2Boot.service.impl.DefaultBookService;
import kz.bisen.springwebapp1.project2Boot.util.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookValidator bookValidator;
    private final DefaultBookService defaultBookService;
    private final DefaultBookDTOBuilder defaultBookDTOBuilder;

    @Autowired
    public BookController(BookValidator bookValidator,
                          DefaultBookService defaultBookService,
                          DefaultBookDTOBuilder defaultBookDTOBuilder) {
        this.bookValidator = bookValidator;
        this.defaultBookService = defaultBookService;
        this.defaultBookDTOBuilder = defaultBookDTOBuilder;
    }


    @GetMapping()
    public List<BookDTO> getAll(@RequestParam(value = "page") int page,
                                @RequestParam(value = "book_per_page") int bookPerPage,
                                @RequestParam(value = "sorted_by_year", required = false) Optional<Boolean> isSorted) {
        final List<Book> books = defaultBookService.findAll(page, bookPerPage, isSorted);
        return books.stream().map(defaultBookDTOBuilder::fromBook).toList();
    }


    @GetMapping("/{id}")
    public BookDTO getOne(@PathVariable("id") int id) {
        final Book book = defaultBookService.findOne(id);
        return defaultBookDTOBuilder.fromBook(book);
    }

    @GetMapping("/title")
    public List<BookDTO> getOneByTitle(@RequestParam(value = "title") String title) {
        return defaultBookService.findBookByTitle(title)
                .stream()
                .map(defaultBookDTOBuilder::fromBook)
                .toList();
    }

    @PostMapping()
    public void createBook(@Valid BookDTO bookDTO,
                             BindingResult bindingResult) throws Exception {
        bookValidator.validate(bookDTO, bindingResult);
        if (bindingResult.hasErrors())
            throw new Exception();

        final Book book = defaultBookDTOBuilder.fromBookDTO(bookDTO);
        defaultBookService.save(book);
    }


    @PatchMapping("/{id}")
    public void updateBook(@Valid BookDTO bookDTO,
                             BindingResult bindingResult,
                             @PathVariable("id") int id) throws Exception {
        bookValidator.validate(bookDTO, bindingResult);
        if (bindingResult.hasErrors())
            throw new Exception();

        final Book book = defaultBookDTOBuilder.fromBookDTO(bookDTO);
        defaultBookService.update(id, book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") int id) {
        defaultBookService.delete(id);
    }

    @GetMapping("/search")
    public List<BookDTO> search(@RequestParam(value = "searchBy", required = false) String searchBy) {
        return defaultBookService.findBookStartingWith(searchBy).stream()
                .map(defaultBookDTOBuilder::fromBook).toList();
    }
}