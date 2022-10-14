package kz.bisen.springwebapp1.project2Boot.repositories;



import kz.bisen.springwebapp1.project2Boot.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findPersonByFio(String fio);

    Optional<Person> findPersonByUsername(String username);
}