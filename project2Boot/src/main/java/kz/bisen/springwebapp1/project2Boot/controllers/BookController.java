package kz.bisen.springwebapp1.project2Boot.controllers;


import kz.bisen.springwebapp1.project2Boot.models.Book;
import kz.bisen.springwebapp1.project2Boot.models.Person;
import kz.bisen.springwebapp1.project2Boot.services.BookService;
import kz.bisen.springwebapp1.project2Boot.services.PersonService;
import kz.bisen.springwebapp1.project2Boot.util.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@Controller
@RequestMapping("/books")
public class BookController {

    private final PersonService personService;

    private final BookValidator bookValidator;
    private final BookService bookService;

    @Autowired
    public BookController(BookValidator bookValidator, BookService bookService, PersonService personService) {
        this.bookValidator = bookValidator;
        this.bookService = bookService;
        this.personService = personService;
    }


    @GetMapping()
    public String getAll(Model model, @RequestParam(value = "page", required = false) Optional<Integer> page,
                                     @RequestParam(value = "books per page", required = false) Optional<Integer> booksPerPage,
                                    @RequestParam(value = "sorted_by_year", required = false) Optional<Boolean> isSorted) {
        if (page.isPresent() && booksPerPage.isPresent() && isSorted.isPresent()) {
            model.addAttribute("books", bookService.findAll(page.get(), booksPerPage.get(), isSorted.get()));
            return "books/index";
        } else if (page.isEmpty() && booksPerPage.isEmpty() && isSorted.isEmpty()) {
            model.addAttribute("books", bookService.findAll());
            return "books/index";
        } else if (page.isPresent() && booksPerPage.isPresent()) {
            model.addAttribute("books", bookService.findAll(page.get(), booksPerPage.get()));
            return "books/index";
        } else if (page.isEmpty() && booksPerPage.isEmpty())
            model.addAttribute("books", bookService.findAll(isSorted.get()));
            return "books/index";
    }



    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("people", personService.findAll());
        model.addAttribute("book", bookService.findOne(id));
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return "books/new";

        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookService.findOne(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return "books/edit";

        bookService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/select")
    public String select(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        bookService.select(person.getId(), id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/reject")
    public String reject(@PathVariable("id") int id) {
        bookService.reject(id);
        return "redirect:/books";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "value", required = false) String startingWord, Model model, @ModelAttribute("book") Book book) {
        if (startingWord != null)
            model.addAttribute("books", bookService.findBookByNameStartingWith(startingWord));
        if (book.getStartingWord() != null)
            model.addAttribute("books", bookService.findBookByNameStartingWith(book.getStartingWord()));
        return "books/search";
    }
}