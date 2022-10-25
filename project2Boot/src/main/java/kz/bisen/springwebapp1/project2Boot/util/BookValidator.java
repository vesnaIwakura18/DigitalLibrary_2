package kz.bisen.springwebapp1.project2Boot.util;


import kz.bisen.springwebapp1.project2Boot.models.Book;
import kz.bisen.springwebapp1.project2Boot.services.impl.DefaultBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {
    private final DefaultBookService defaultBookService;

    @Autowired
    public BookValidator(DefaultBookService defaultBookService) {
        this.defaultBookService = defaultBookService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Book book = (Book) o;

        if (defaultBookService.findBookByTitle(book.getTitle()).isPresent()) {
            errors.rejectValue("title", "", "Эта книга уже есть в базе данных");
        } else if (defaultBookService.findBookByAuthor(book.getAuthor()).isPresent()) {
            errors.rejectValue("author", "", "Имя автора уже есть в базе данных");
        }
    }
}