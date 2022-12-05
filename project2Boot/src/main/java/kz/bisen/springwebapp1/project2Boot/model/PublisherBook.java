package kz.bisen.springwebapp1.project2Boot.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table
@NoArgsConstructor
public class PublisherBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;
    @Column
    @NotEmpty
    private String authorFirstName;

    @Column
    @NotEmpty
    private String authorLastName;

    @Column
    @NotEmpty
    private LocalDateTime createdAt;

    @Column
    @NotEmpty
    private PublisherBookStatus status;

    @Column
    @NotEmpty
    private String isbn;

    @Column
    @NotNull
    private LocalDateTime issueDateTime;

    public PublisherBook(
            String title,
            String authorFirstName,
            String authorLastName,
            LocalDateTime createdAt,
            PublisherBookStatus status,
            String isbn,
            LocalDateTime issueDateTime
    ) {
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.createdAt = createdAt;
        this.status = status;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public PublisherBookStatus getStatus() {
        return status;
    }

    public void setStatus(PublisherBookStatus status) {
        this.status = status;
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
