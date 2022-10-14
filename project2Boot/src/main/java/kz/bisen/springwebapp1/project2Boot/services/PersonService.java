package kz.bisen.springwebapp1.project2Boot.services;

import kz.bisen.springwebapp1.project2Boot.models.Book;
import kz.bisen.springwebapp1.project2Boot.models.Person;
import kz.bisen.springwebapp1.project2Boot.repositories.BookRepository;
import kz.bisen.springwebapp1.project2Boot.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {

    // Максимальное количество секунд которое дается читателу для возврата книги
    public static final int OVERDUE = 864_000_000;
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    private final BookRepository bookRepository;

    @Autowired
    public PersonService(@Lazy PersonRepository personRepository, PasswordEncoder passwordEncoder, BookRepository bookRepository) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
        this.bookRepository = bookRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }


    public Person findOne(int id) {
        Date date = new Date();
        Optional<Person> foundPerson = personRepository.findById(id);
        for (Book book : bookRepository.findBookByOwnerId(id)) {
            book.setOverdue(date.getTime() - book.getTakenAt().getTime() > OVERDUE); // TODO CHANGE
        }
        return foundPerson.orElse(null);
    }

    public Optional<Person> findPersonByFio(String fio) {
        return personRepository.findPersonByFio(fio).stream().findAny();
    }

    @Transactional
    public void save(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER");
        personRepository.save(person);
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        updatedPerson.setRole("ROLE_USER");
        updatedPerson.setPassword(passwordEncoder.encode(updatedPerson.getPassword()));
        personRepository.save(updatedPerson);
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(int id) {
        personRepository.deleteById(id);
    }

    public Optional<Person> findPersonByUsername(String username) {
        return personRepository.findPersonByUsername(username).stream().findAny();
    }
}