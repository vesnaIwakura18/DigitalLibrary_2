package kz.bisen.springwebapp1.project2Boot.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    @NotEmpty(message = "Название книги не может быть пустым")
    @Size(min = 2, max = 150, message = "Некорректное название")
    private String title;

    @Column(name = "author")
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 100, message = "Некорректное имя")
    private String author;

    @Column(name = "issue_year")
    @Min(value = 0, message = "Некорректная дата публикации")
    private String issueYear;

    @ManyToOne
    @JoinColumn(name = "reader_id", referencedColumnName = "reader_id")
    private Reader owner;

    @Column(name = "datetime_taken")
    private LocalDateTime dateTimeTaken;

    @Transient
    private boolean isOverdue;

    public Book() {
    }

    public Book(String title, String author, String issueYear) {
        this.title = title;
        this.author = author;
        this.issueYear = issueYear;
    }

    public LocalDateTime getDateTimeTaken() {
        return dateTimeTaken;
    }

    public void setDateTimeTaken(LocalDateTime dateTimeTaken) {
        this.dateTimeTaken = dateTimeTaken;
    }

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

    public Reader getReader() {
        return owner;
    }

    public void setReader(Reader owner) {
        this.owner = owner;
    }

    public boolean getOverdue() {
        return isOverdue;
    }

    public void setOverdue(boolean overdue) {
        isOverdue = overdue;
    }
}
