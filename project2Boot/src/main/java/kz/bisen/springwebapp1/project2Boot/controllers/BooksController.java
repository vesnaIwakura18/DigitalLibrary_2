package kz.bisen.springwebapp1.project2Boot.controllers;


import kz.bisen.springwebapp1.project2Boot.models.Book;
import kz.bisen.springwebapp1.project2Boot.models.Person;
import kz.bisen.springwebapp1.project2Boot.services.BooksService;
import kz.bisen.springwebapp1.project2Boot.services.PeopleService;
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
public class BooksController {

    private final PeopleService peopleService;

    private final BookValidator bookValidator;
    private final BooksService booksService;

    @Autowired
    public BooksController(BookValidator bookValidator, BooksService booksService, PeopleService peopleService) {
        this.bookValidator = bookValidator;
        this.booksService = booksService;
        this.peopleService = peopleService;
    }


    @GetMapping()
    public String index(Model model, @RequestParam(value = "page", required = false) Optional<Integer> page,
                                     @RequestParam(value = "books per page", required = false) Optional<Integer> booksPerPage, @RequestParam(value = "sorted_by_year", required = false) Optional<Boolean> isSorted) {
        if (page.isPresent() && booksPerPage.isPresent() && isSorted.isPresent()) {
            model.addAttribute("books", booksService.findAll(page.get(), booksPerPage.get(), isSorted.get()));
            return "books/index";
        } else if (page.isEmpty() && booksPerPage.isEmpty() && isSorted.isEmpty()) {
            model.addAttribute("books", booksService.findAll());
            return "books/index";
        } else if (page.isPresent() && booksPerPage.isPresent()) {
            model.addAttribute("books", booksService.findAll(page.get(), booksPerPage.get()));
            return "books/index";
        } else if (page.isEmpty() && booksPerPage.isEmpty())
            model.addAttribute("books", booksService.findAll(isSorted.get()));
            return "books/index";
    }



    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("people", peopleService.findAll());
        model.addAttribute("book", booksService.findOne(id));
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

        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", booksService.findOne(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return "books/edit";

        booksService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/select")
    public String select(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        booksService.select(person.getId(), id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/reject")
    public String reject(@PathVariable("id") int id) {
        booksService.reject(id);
        return "redirect:/books";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "value", required = false) String startingWord, Model model, @ModelAttribute("book") Book book) {
        if (startingWord != null)
            model.addAttribute("books", booksService.findBookByNameStartingWith(startingWord));
        if (book.getStartingWord() != null)
            model.addAttribute("books", booksService.findBookByNameStartingWith(book.getStartingWord()));
        return "books/search";
    }
}