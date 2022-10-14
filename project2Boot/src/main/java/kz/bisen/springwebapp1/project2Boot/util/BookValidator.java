package kz.bisen.springwebapp1.project2Boot.util;


import kz.bisen.springwebapp1.project2Boot.models.Book;
import kz.bisen.springwebapp1.project2Boot.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {
    private final BookService bookService;

    @Autowired
    public BookValidator(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Book book = (Book) o;

        if (bookService.findBookByName(book.getName()).isPresent()) {
            errors.rejectValue("name", "", "Эта книга уже есть в базе данных");
        } else if (bookService.findBookByAuthor(book.getAuthor()).isPresent()) {
            errors.rejectValue("author", "", "Имя автора уже есть в базе данных");
        }
    }
}