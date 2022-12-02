package kz.bisen.springwebapp1.project2Boot.dto.author.impl;

import kz.bisen.springwebapp1.project2Boot.dto.author.AuthorDTO;
import kz.bisen.springwebapp1.project2Boot.model.Author;
import org.springframework.stereotype.Component;

@Component
public class DefaultAuthorDTOBuilder {
    public AuthorDTO fromAuthor(Author author) {
        return new AuthorDTO(author.getId(), author.getFirstName(), author.getLastName());
    }

    public Author fromAuthorDTO(AuthorDTO authorDTO) {
        return new Author(authorDTO.getFirstName(), authorDTO.getLastName());
    }
}
