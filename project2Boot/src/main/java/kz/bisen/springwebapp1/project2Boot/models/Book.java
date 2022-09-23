package kz.bisen.springwebapp1.project2Boot.models;



import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "book_name")
    @NotEmpty(message="Название книги не может быть пустым")
    @Size(min = 2, max = 150, message = "Некорректное название")
    private String name;

    @Column(name = "book_author")
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 100, message = "Некорректное имя")
    private String author;

    @Column(name = "book_year_of_production")
    @Min(value = 0, message = "Некорректная дата публикации")
    private String yearOfProduction;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person owner;

    @Transient
    private String startingWord;

    @Column(name = "date_of_taking")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateOfTaking;

    @Column(name = "taken_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date takenAt;

    @Transient
    private boolean isOverdue;

    public Book() {

    }

    public Book(String name, String author, String yearOfProduction, Person owner) {
        this.name = name;
        this.author = author;
        this.yearOfProduction = yearOfProduction;
        this.owner = owner;
    }

    public Date getDateOfTaking() {
        return dateOfTaking;
    }

    public void setDateOfTaking(Date dateOfTaking) {
        this.dateOfTaking = dateOfTaking;
    }

    public Date getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(Date takenAt) {
        this.takenAt = takenAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(String yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public String getStartingWord() {
        return startingWord;
    }

    public void setStartingWord(String startingWord) {
        this.startingWord = startingWord;
    }

    public boolean getOverdue() {
        return isOverdue;
    }

    public void setOverdue(boolean overdue) {
        isOverdue = overdue;
    }

}
