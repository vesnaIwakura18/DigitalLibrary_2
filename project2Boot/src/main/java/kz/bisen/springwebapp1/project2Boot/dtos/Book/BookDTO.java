package kz.bisen.springwebapp1.project2Boot.dtos.Book;

import kz.bisen.springwebapp1.project2Boot.models.Reader;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Optional;

public class BookDTO {
    private int id;

    @NotEmpty(message="Название книги не может быть пустым")
    @Size(min = 2, max = 150, message = "Некорректное название")
    private String title;

    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 100, message = "Некорректное имя")
    private String author;

    @Min(value = 0, message = "Некорректная дата публикации")
    private String issueYear;

    public BookDTO(String title, String author, String issueYear) {
        this.title = title;
        this.author = author;
        this.issueYear = issueYear;
    }

    public BookDTO() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIssueYear() {
        return issueYear;
    }

    public void setIssueYear(String issueYear) {
        this.issueYear = issueYear;
    }

//    public List<Optional<String>> getSearchBy() {
//        return searchBy;
//    }
//
//    public void setSearchBy(List<Optional<String>> searchBy) {
//        this.searchBy = searchBy;
//    }
}