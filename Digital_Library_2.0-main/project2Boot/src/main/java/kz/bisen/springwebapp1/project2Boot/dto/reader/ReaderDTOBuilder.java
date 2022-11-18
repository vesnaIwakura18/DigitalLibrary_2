package kz.bisen.springwebapp1.project2Boot.dto.reader;

import kz.bisen.springwebapp1.project2Boot.model.Reader;

public interface ReaderDTOBuilder {
    ReaderDTO fromReader(Reader reader);

    Reader fromReaderDTO(ReaderDTO readerDTO);
}