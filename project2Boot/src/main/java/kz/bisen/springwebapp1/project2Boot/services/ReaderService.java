package kz.bisen.springwebapp1.project2Boot.services;

import kz.bisen.springwebapp1.project2Boot.models.Reader;

import java.util.List;
import java.util.Optional;

public interface ReaderService {
    List<Reader> findAll();

    Reader findOne(int id);

    Optional<Reader> findReaderByUsername(String username);

    Optional<Reader> findReaderByFullName(String fullName);

    void save(Reader reader);

    void update(int id, Reader updatedReader);

    void delete(int id);
}
