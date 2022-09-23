package kz.bisen.springwebapp1.project2Boot.repositories;



import kz.bisen.springwebapp1.project2Boot.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    List<Person> findPersonByFio(String fio);
}

