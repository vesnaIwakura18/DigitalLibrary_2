package kz.bisen.springwebapp1.project2Boot.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table
@SecondaryTable(name = "book_amount", pkJoinColumns = @PrimaryKeyJoinColumn(name = "book_id", referencedColumnName = "id"))
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotEmpty(message = "Название книги не может быть пустым")
    @Size(min = 2, max = 150, message = "Некорректное название")
    private String title;

    @Column
    @Min(value = 0, message = "Некорректная дата публикации")
    private LocalDateTime issueDateTime;

    @Column(table = "book_amount")
    private int amount;

    @Column(table = "book_amount")
    private int minAmount;

    @Column
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    public Book() {
    }

    public Book(String title, LocalDateTime issueDateTime, int amount, int minAmount, String isbn, Author author) {
        this.title = title;
        this.issueDateTime = issueDateTime;
        this.amount = amount;
        this.minAmount = minAmount;
        this.isbn = isbn;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(int minAmount) {
        this.minAmount = minAmount;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
