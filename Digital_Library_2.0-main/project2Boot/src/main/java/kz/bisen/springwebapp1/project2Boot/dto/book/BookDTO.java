package kz.bisen.springwebapp1.project2Boot.dto.book;

import kz.bisen.springwebapp1.project2Boot.kafka.BookIsbnMessage;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class BookDTO {
    private Integer id;

    @NotEmpty(message = "Название книги не может быть пустым")
    @Size(min = 2, max = 150, message = "Некорректное название")
    private String title;

    @Min(value = 0, message = "Некорректная дата публикации")
    private int issueYear;

    @Min(value = 1, message = "Минимальное значение кол-ва книг: 1")
    private Integer amount;

    @NotEmpty(message = "ISBN must not be empty")
    private String isbn;

    public BookDTO(Integer id, String title, int issueYear, Integer amount, String isbn) {
        this.id = id;
        this.title = title;
        this.issueYear = issueYear;
        this.amount = amount;
        this.isbn = isbn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIssueYear() {
        return issueYear;
    }

    public void setIssueYear(int issueYear) {
        this.issueYear = issueYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", issueYear=" + issueYear +
                ", amount=" + amount +
                '}';
    }
}