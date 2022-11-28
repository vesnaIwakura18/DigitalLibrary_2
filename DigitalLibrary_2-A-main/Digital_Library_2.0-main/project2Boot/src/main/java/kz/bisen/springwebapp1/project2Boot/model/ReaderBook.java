package kz.bisen.springwebapp1.project2Boot.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "reader_book")
public class ReaderBook {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "reader_id")
    @NotNull(message = "int must not be empty")
    private int readerId;
    @Column(name = "book_id")
    @NotNull(message = "int must not be empty")
    private int bookId;
    @Column
    @Min(value = 1, message = "It must be at least 1 book that you can buy")
    @Max(value = 10, message = "Instance must be less than 11")
    private int instance;
    @Column
    @NotNull(message = "Datetime must not be empty")
    private LocalDateTime takenAt;

    @Column
    private int readingDays;

    public ReaderBook(int readerId, int bookId, int instance, LocalDateTime takenAt, int readingDays) {
        this.readerId = readerId;
        this.bookId = bookId;
        this.instance = instance;
        this.takenAt = takenAt;
        this.readingDays = readingDays;
    }

    public ReaderBook() {}

    @Override
    public String toString() {
        return "ReaderBook{" +
                "id=" + id +
                ", readerId=" + readerId +
                ", bookId=" + bookId +
                ", instance=" + instance +
                ", takenAt=" + takenAt +
                ", readingDays=" + readingDays +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getInstance() {
        return instance;
    }

    public void setInstance(int instance) {
        this.instance = instance;
    }

    public LocalDateTime getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(LocalDateTime takenAt) {
        this.takenAt = takenAt;
    }

    public int getReadingDays() {
        return readingDays;
    }

    public void setReadingDays(int readingDays) {
        this.readingDays = readingDays;
    }
}