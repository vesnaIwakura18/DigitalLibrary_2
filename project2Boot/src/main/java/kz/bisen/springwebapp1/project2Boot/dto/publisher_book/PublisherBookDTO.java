package kz.bisen.springwebapp1.project2Boot.dto.publisher_book;

import kz.bisen.springwebapp1.project2Boot.model.PublisherBookStatus;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class PublisherBookDTO {
    @NotNull
    private Long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String authorFirstName;

    @NotEmpty
    private String authorLastName;

    @NotEmpty
    private String isbn;

    @NotNull
    private LocalDateTime issueDateTime;

    public PublisherBookDTO(Long id, String title, String authorFirstName, String authorLastName, String isbn, LocalDateTime issueDateTime) {
        this.id = id;
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.isbn = isbn;
        this.issueDateTime = issueDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDateTime getIssueDateTime() {
        return issueDateTime;
    }

    public void setIssueDateTime(LocalDateTime issueDateTime) {
        this.issueDateTime = issueDateTime;
    }
}
