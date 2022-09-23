package kz.bisen.springwebapp1.project2Boot.services;

import kz.bisen.springwebapp1.project2Boot.models.Book;
import kz.bisen.springwebapp1.project2Boot.models.Person;
import kz.bisen.springwebapp1.project2Boot.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;
    private final BooksService booksService;

    @Autowired
    public PeopleService(@Lazy PeopleRepository peopleRepository, @Lazy BooksService booksService) {
        this.peopleRepository = peopleRepository;
        this.booksService = booksService;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }


    public Person findOne(int id) {
        Date date = new Date();
        Optional<Person> foundPerson = peopleRepository.findById(id);
        for (Book book : booksService.findBookByOwnerId(id)) {
            book.setOverdue(date.getTime() - book.getTakenAt().getTime() > 864000000);
        }
        return foundPerson.orElse(null);
    }

    public Optional<Person> findPersonByFio(String fio) {
        return peopleRepository.findPersonByFio(fio).stream().findAny();
    }

    @Transactional
    public void save(Person person) {
//        person.setCreatedAt(new Date());

        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }
}