package kz.bisen.springwebapp1.project2Boot.dto.book;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class BookDTO {
    @NotEmpty(message = "Название книги не может быть пустым")
    @Size(min = 2, max = 150, message = "Некорректное название")
    private String title;

    @Min(value = 0, message = "Некорректная дата публикации")
    private LocalDateTime issueDateTime;

    @Min(value = 0, message = "Минимальное значение кол-ва книг: 0")
    private Integer amount;

    @NotEmpty
    private Integer minAmount;

    @NotEmpty(message = "ISBN must not be empty")
    private String isbn;

    private String authorFirstName;

    private String authorLastName;

    public BookDTO(
            String title,
            LocalDateTime issueDateTime,
            Integer amount,
            Integer minAmount,
            String isbn,
            String authorFirstName,
            String authorLastName
    ) {
        this.title = title;
        this.issueDateTime = issueDateTime;
        this.amount = amount;
        this.isbn = isbn;
        this.minAmount = minAmount;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
    }

    public BookDTO() {
    }

    public Integer getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Integer minAmount) {
        this.minAmount = minAmount;
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

    public LocalDateTime getIssueDateTime() {
        return issueDateTime;
    }

    public void setIssueDateTime(LocalDateTime issueDateTime) {
        this.issueDateTime = issueDateTime;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }
}