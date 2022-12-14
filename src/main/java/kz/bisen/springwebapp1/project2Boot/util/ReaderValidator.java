package kz.bisen.springwebapp1.project2Boot.util;

import kz.bisen.springwebapp1.project2Boot.dto.reader.ReaderDTO;
import kz.bisen.springwebapp1.project2Boot.model.Reader;
import kz.bisen.springwebapp1.project2Boot.service.impl.DefaultReaderService;
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
        if (defaultReaderService.findReaderByFullName(reader.getFullName()).isPresent() /*||  defaultReaderService.findReaderByUsername(reader.getUsername()).isPresent() */)
            errors.rejectValue("fullName", "", "Имя пользователя или ФИО занято");
    }
}