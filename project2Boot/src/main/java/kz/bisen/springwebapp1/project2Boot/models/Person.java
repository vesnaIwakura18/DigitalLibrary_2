package kz.bisen.springwebapp1.project2Boot.models;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private int id;

    @Column(name = "fio")
    @NotEmpty(message = "ФИО не может быть пустым")
    @Size(min = 2, max = 100, message = "Слишком короткое либо слишком длинное ФИО")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+", message ="Некорректное ФИО")
    private String fio;

    @Column(name = "birth_date")
    @Min(value = 1900, message = "Некорректная дата рождения")
    private String birthDate;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;


    public Person() {
    }

    public Person(String fio, String birthDate) {
        this.fio = fio;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
