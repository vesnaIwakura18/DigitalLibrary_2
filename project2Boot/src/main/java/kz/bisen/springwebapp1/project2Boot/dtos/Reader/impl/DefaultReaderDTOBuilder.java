package kz.bisen.springwebapp1.project2Boot.dtos.Reader.impl;

import kz.bisen.springwebapp1.project2Boot.dtos.Reader.ReaderDTO;
import kz.bisen.springwebapp1.project2Boot.dtos.Reader.ReaderDTOBuilder;
import kz.bisen.springwebapp1.project2Boot.models.Reader;
import org.springframework.stereotype.Component;

@Component
public class DefaultReaderDTOBuilder implements ReaderDTOBuilder {

    @Override
    public ReaderDTO fromReader(Reader reader) {
        return new ReaderDTO(reader.getFullName(),
                reader.getBirthDate(),
                reader.getPassword(),
                reader.getUsername());
    }

    @Override
    public Reader fromReaderDTO(ReaderDTO readerDTO) {
        return new Reader(readerDTO.getFullName(),
                readerDTO.getBirthDate(),
                readerDTO.getPassword(),
                readerDTO.getUsername(),
                "ROLE_USER");
    }
}