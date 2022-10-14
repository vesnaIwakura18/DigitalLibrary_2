package kz.bisen.springwebapp1.project2Boot.services;

import kz.bisen.springwebapp1.project2Boot.models.Person;
import kz.bisen.springwebapp1.project2Boot.repositories.PersonRepository;
import kz.bisen.springwebapp1.project2Boot.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> foundPerson = personRepository.findPersonByUsername(username);
        if(foundPerson.isEmpty())
            throw new UsernameNotFoundException("Упс! Пользователь не найден...");
        return new PersonDetails(foundPerson.get());
    }
}