package kz.bisen.springwebapp1.project2Boot.util;

import kz.bisen.springwebapp1.project2Boot.dtos.Reader.ReaderDTO;
import kz.bisen.springwebapp1.project2Boot.models.Reader;
import kz.bisen.springwebapp1.project2Boot.services.impl.DefaultReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ReaderValidator implements Validator {

    private final DefaultReaderService defaultReaderService;

    @Autowired
    public ReaderValidator(DefaultReaderService defaultReaderService) {
        this.defaultReaderService = defaultReaderService;
    }

    @Override
    public boolean supports(Class<?> aClass) {

        return Reader.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ReaderDTO reader = (ReaderDTO) o;
        if (defaultReaderService.findReaderByFullName(reader.getFullName()).isPresent())
            errors.rejectValue("fullName", "", "Это ФИО занято.");
        if (defaultReaderService.findReaderByUsername(reader.getUsername()).isPresent())
            errors.rejectValue("username", "", "Это имя пользователя занято.");
    }
}