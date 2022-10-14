package kz.bisen.springwebapp1.project2Boot.controllers;

import kz.bisen.springwebapp1.project2Boot.models.Person;
import kz.bisen.springwebapp1.project2Boot.services.PersonService;
import kz.bisen.springwebapp1.project2Boot.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final PersonValidator personValidator;

    private final PersonService personService;

    @Autowired
    public AuthController(PersonValidator personValidator, PersonService personService) {
        this.personValidator = personValidator;
        this.personService = personService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") @Valid Person person,
                                      BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors())
            return "auth/registration";
        personService.save(person);
        return "/auth/login";
    }

    @GetMapping("/feed")
    public String mainPage() {
        return "auth/main";
    }
}
