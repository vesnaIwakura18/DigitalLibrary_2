package kz.bisen.springwebapp1.project2Boot.controller;

import kz.bisen.springwebapp1.project2Boot.dto.reader.ReaderDTO;
import kz.bisen.springwebapp1.project2Boot.dto.reader.impl.DefaultReaderDTOBuilder;
import kz.bisen.springwebapp1.project2Boot.model.Reader;
import kz.bisen.springwebapp1.project2Boot.service.impl.DefaultReaderService;
import kz.bisen.springwebapp1.project2Boot.util.ReaderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final ReaderValidator readerValidator;

    private final DefaultReaderService defaultReaderService;

    private final DefaultReaderDTOBuilder defaultReaderDTOBuilder;

    @Autowired
    public AuthController(ReaderValidator readerValidator,
                          DefaultReaderService defaultReaderService,
                          DefaultReaderDTOBuilder defaultReaderDTOBuilder) {
        this.readerValidator = readerValidator;
        this.defaultReaderService = defaultReaderService;
        this.defaultReaderDTOBuilder = defaultReaderDTOBuilder;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @PostMapping("/registration")
    public void performRegistration(@RequestBody @Valid ReaderDTO readerDTO,
                                      BindingResult bindingResult) throws Exception {
//        readerValidator.validate(readerDTO, bindingResult);
        if(bindingResult.hasErrors())
            throw new Exception();
        final Reader reader = defaultReaderDTOBuilder.fromReaderDTO(readerDTO);
        defaultReaderService.save(reader);
    }
}