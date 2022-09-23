package kz.bisen.springwebapp1.project2Boot.util;


import kz.bisen.springwebapp1.project2Boot.models.Person;
import kz.bisen.springwebapp1.project2Boot.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        if (peopleService.findPersonByFio(person.getFio()).isPresent()) {
            errors.rejectValue("fio", "", "Это ФИО уже есть в базе данных");
        }
    }
}