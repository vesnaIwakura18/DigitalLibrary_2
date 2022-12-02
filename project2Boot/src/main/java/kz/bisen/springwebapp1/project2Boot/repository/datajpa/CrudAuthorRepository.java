package kz.bisen.springwebapp1.project2Boot.repository.datajpa;

import kz.bisen.springwebapp1.project2Boot.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;
import java.util.Optional;


@Repository
public interface CrudAuthorRepository extends JpaRepository<Author, Integer> {
    Optional<Author> findAuthorByFirstNameAndLastName(String firstName, String lastName);
}
