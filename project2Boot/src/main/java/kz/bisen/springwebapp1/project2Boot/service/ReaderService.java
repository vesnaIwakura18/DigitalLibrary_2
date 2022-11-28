package kz.bisen.springwebapp1.project2Boot.service;

import kz.bisen.springwebapp1.project2Boot.model.Reader;

import java.util.List;
import java.util.Optional;

public interface ReaderService {
    List<Reader> findAll();

    Reader findOne(int id);

    Optional<Reader> findReaderByUsername(String username);

    Optional<Reader> findReaderByFullName(String fullName);

    Reader save(Reader reader);

    Reader update(Reader updatedReader);

    void delete(int id);
}
