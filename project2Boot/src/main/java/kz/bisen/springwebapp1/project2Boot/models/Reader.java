package kz.bisen.springwebapp1.project2Boot.models;


import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "Reader")
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reader_id")
    private int id;

    @Column(name = "fullname")
    @NotEmpty(message = "ФИО не может быть пустым")
    @Size(min = 2, max = 100, message = "Слишком короткое либо слишком длинное ФИО")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+", message ="Некорректное ФИО")
    private String fullName;

    @Column(name = "birth_date")
    @Min(value = 1900, message = "Некорректная дата рождения")
    private int birthDate;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    @Length(min = 3, max = 15, message = "Длина имени пользователя должна быть от 3 до 15 включительно")
    @Pattern(regexp = "[a-z]\\w+")
    private String username;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    @Column(name = "role")
    private String role;

    public Reader() {}

    public Reader(String fullName, int birthDate, String password, String username, String role) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.password = password;
        this.username = username;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(int birthDate) {
        this.birthDate = birthDate;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
