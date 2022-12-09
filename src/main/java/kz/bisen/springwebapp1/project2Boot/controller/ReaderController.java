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
import java.util.List;


@RestController
@RequestMapping("/reader")
public class ReaderController {
    private final ReaderValidator readerValidator;

    private final DefaultReaderService defaultReaderService;

    private final DefaultReaderDTOBuilder defaultReaderDTOBuilder;

    @Autowired
    public ReaderController(ReaderValidator readerValidator,
                            DefaultReaderService defaultReaderService,
                            DefaultReaderDTOBuilder defaultReaderDTOBuilder) {
        this.readerValidator = readerValidator;
        this.defaultReaderService = defaultReaderService;
        this.defaultReaderDTOBuilder = defaultReaderDTOBuilder;
    }

    @GetMapping()
    public List<ReaderDTO> getAll() {
        final List<Reader> readers = defaultReaderService.findAll();
        return readers.stream().map(defaultReaderDTOBuilder::fromReader).toList();
    }

    @GetMapping("/{id}")
    public ReaderDTO getReader(@PathVariable("id") int id) {
        final Reader reader = defaultReaderService.findOne(id);
        return defaultReaderDTOBuilder.fromReader(reader);
    }

    @PostMapping()
    public ReaderDTO createReader(@Valid ReaderDTO readerDTO,
                                    BindingResult bindingResult) throws Exception { // todo
        readerValidator.validate(readerDTO, bindingResult);
       if (bindingResult.hasErrors())
            throw new Exception();
       final Reader reader = defaultReaderDTOBuilder.fromReaderDTO(readerDTO);
       defaultReaderService.save(reader);
       return readerDTO;
    }

    @PutMapping
    public ReaderDTO updateReader(@Valid ReaderDTO readerDTO,
                             BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors())
            throw new Exception();

        final Reader reader = defaultReaderDTOBuilder.fromReaderDTO(readerDTO);
        defaultReaderService.update(reader);
        return readerDTO;
    }

    @DeleteMapping("/{id}")
    public Integer deleteReader(@PathVariable("id") int id) {
        defaultReaderService.delete(id);

        return id;
    }
}