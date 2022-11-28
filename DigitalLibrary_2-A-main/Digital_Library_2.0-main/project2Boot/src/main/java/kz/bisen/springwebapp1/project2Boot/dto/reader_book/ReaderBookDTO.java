package kz.bisen.springwebapp1.project2Boot.dto.reader_book;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ReaderBookDTO {
    @NotNull(message = "Reader id must not be empty")
    private int readerId;

    @NotNull(message = "Book id must not be empty")
    private int bookId;

    @Min(value = 1, message = "Instance must be at least 1 book that you can buy")
    @Max(value = 10, message = "Instance must be less than 11")
    private int instance;

    @NotNull(message = "Reading days must not be empty")
    private int readingDays;

    public ReaderBookDTO(int readerId, int bookId, int instance, int readingDays) {
        this.readerId = readerId;
        this.bookId = bookId;
        this.instance = instance;
        this.readingDays = readingDays;
    }

    public ReaderBookDTO() {
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

    public int getReadingDays() {
        return readingDays;
    }

    public void setReadingDays(int readingDays) {
        this.readingDays = readingDays;
    }
}
