package kz.bisen.springwebapp1.project2Boot.controllers;

import kz.bisen.springwebapp1.project2Boot.dtos.Reader.ReaderDTO;
import kz.bisen.springwebapp1.project2Boot.dtos.Reader.impl.DefaultReaderDTOBuilder;
import kz.bisen.springwebapp1.project2Boot.models.Reader;
import kz.bisen.springwebapp1.project2Boot.services.impl.DefaultReaderService;
import kz.bisen.springwebapp1.project2Boot.util.ReaderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final ReaderValidator readerValidator;

    private final DefaultReaderService defaultReaderService;

    @Autowired
    public AuthController(ReaderValidator readerValidator,
                          DefaultReaderService defaultReaderService) {
        this.readerValidator = readerValidator;
        this.defaultReaderService = defaultReaderService;
    }

    @GetMapping("/login")
    public String loginPage() {

        return "auth";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("reader") Reader reader) {

        return "registration";
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> performRegistration(@Valid Reader reader,
                                                          BindingResult bindingResult) {
        readerValidator.validate(reader, bindingResult);
        if (bindingResult.hasErrors())
            return ResponseEntity.ok(HttpStatus.BAD_GATEWAY);

        defaultReaderService.save(reader);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}