package kz.bisen.springwebapp1.project2Boot.dtos.Reader;

import kz.bisen.springwebapp1.project2Boot.dtos.Reader.ReaderDTO;
import kz.bisen.springwebapp1.project2Boot.models.Reader;

public interface ReaderDTOBuilder {
    ReaderDTO fromReader(Reader reader);

    Reader fromReaderDTO(ReaderDTO readerDTO);
}