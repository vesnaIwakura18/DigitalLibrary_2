package kz.bisen.springwebapp1.project2Boot.services;

import kz.bisen.springwebapp1.project2Boot.models.Person;
import kz.bisen.springwebapp1.project2Boot.repositories.BookRepository;
import kz.bisen.springwebapp1.project2Boot.repositories.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    private static final int ID = 1;

    private static final String FIO = "Admin Admin Admin";

    private static final String PASSWORD = "Some_password";

    private static final String ROLE = "ROLE_SOME";

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void findAll_shouldCallRepositories() {
        final List<Person> people = List.of(mock(Person.class));
        when(personRepository.findAll()).thenReturn(people);

        final List<Person> actualPeople = personService.findAll();

        assertEquals(people, actualPeople);
        verify(personRepository).findAll();
    }

    @Test
    void findOne_shouldCallRepository() {
        final Person person = mock(Person.class);
        when(personRepository.findById(ID)).thenReturn(Optional.of(person));


        final Person actualPerson = personService.findOne(ID);

        assertEquals(person, actualPerson);
        verify(personRepository).findById(ID);
    }

    @Test
    void findPersonByFio_shouldCallRepository() {
        final Person person = mock(Person.class);
        when(personRepository.findPersonByFio(FIO)).thenReturn(Optional.of(person));

        final Person actualPerson = personService.findPersonByFio(FIO).orElse(null);

        assertEquals(person, actualPerson);
        verify(personRepository).findPersonByFio(FIO);
    }

    @Test
    void save_shouldCallRepository() {
        final Person person = mock(Person.class);

        person.setPassword(passwordEncoder.encode(PASSWORD));
        person.setRole(ROLE);
        personService.save(person);

        verify(personRepository).save(person);
    }

    @Test
    void update_shouldCallRepository() {
        final Person person = mock(Person.class);

        person.setId(ID);
        person.setRole(ROLE);
        person.setPassword(passwordEncoder.encode(PASSWORD));
        personService.save(person);

        verify(personRepository).save(person);
    }

    @Test
    void delete_shouldCallRepository() {
        personService.delete(ID);

        verify(personRepository).deleteById(ID);
    }
}
