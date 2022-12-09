package kz.bisen.springwebapp1.project2Boot.dto.author.impl;

import kz.bisen.springwebapp1.project2Boot.dto.author.AuthorDto;
import kz.bisen.springwebapp1.project2Boot.model.Author;
import org.springframework.stereotype.Component;

@Component
public class DefaultAuthorDtoBuilder {
    public AuthorDto fromAuthor(Author author) {
        return new AuthorDto(author.getFirstName(), author.getLastName());
    }

    public Author fromAuthorDto(AuthorDto authorDto) {
        return new Author(authorDto.getFirstName(), authorDto.getLastName());
    }
}
