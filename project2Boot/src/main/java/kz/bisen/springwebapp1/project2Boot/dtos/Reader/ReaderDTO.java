package kz.bisen.springwebapp1.project2Boot.dtos.Reader;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ReaderDTO {
    private int id;

    @NotEmpty(message = "ФИО не может быть пустым")
    @Size(min = 2, max = 100, message = "Слишком короткое либо слишком длинное ФИО")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+", message = "Некорректное ФИО")
    private String fullName;

    @Min(value = 1900, message = "Некорректная дата рождения")
    private int birthDate;

    private String password;

    @Length(min = 3, max = 15, message = "Длина имени пользователя должна быть от 3 до 15 включительно")
    @Pattern(regexp = "[a-z]\\w+")
    private String username;

    public ReaderDTO(String fullName, int birthDate, String password, String username) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.password = password;
        this.username = username;
    }

    public ReaderDTO() {
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
}
