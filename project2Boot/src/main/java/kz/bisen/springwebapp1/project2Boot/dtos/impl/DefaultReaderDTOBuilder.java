package kz.bisen.springwebapp1.project2Boot.dtos.impl;

import kz.bisen.springwebapp1.project2Boot.dtos.ReaderDTO;
import kz.bisen.springwebapp1.project2Boot.dtos.ReaderDTOBuilder;
import kz.bisen.springwebapp1.project2Boot.models.Reader;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultReaderDTOBuilder implements ReaderDTOBuilder {
    private final ModelMapper modelMapper;

    @Autowired
    public DefaultReaderDTOBuilder(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ReaderDTO fromReader(Reader reader) {
        return modelMapper.map(reader, ReaderDTO.class);
    }

    @Override
    public Reader fromReaderDTO(ReaderDTO readerDTO) {
        return modelMapper.map(readerDTO, Reader.class);
    }
}