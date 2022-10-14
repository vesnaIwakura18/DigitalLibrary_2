package kz.bisen.springwebapp1.project2Boot.util;


import kz.bisen.springwebapp1.project2Boot.models.Person;
import kz.bisen.springwebapp1.project2Boot.services.PersonService;
import kz.bisen.springwebapp1.project2Boot.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonService personService;
    private final PersonDetailsService personDetailsService;

    @Autowired
    public PersonValidator(PersonService personService, PersonDetailsService personDetailsService) {
        this.personService = personService;
        this.personDetailsService = personDetailsService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        if (personService.findPersonByFio(person.getFio()).isPresent() || personService.findPersonByUsername(person.getUsername()).isPresent())
            errors.rejectValue("fio", "", "Имя пользователя или ФИО занято");
    }
}