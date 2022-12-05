package kz.bisen.springwebapp1.project2Boot.dto.author;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import kz.bisen.springwebapp1.project2Boot.dto.book.BookDTO;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class AuthorDto {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    public AuthorDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public AuthorDto() {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
