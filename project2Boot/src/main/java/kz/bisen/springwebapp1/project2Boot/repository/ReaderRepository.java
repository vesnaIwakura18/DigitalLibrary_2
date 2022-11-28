package kz.bisen.springwebapp1.project2Boot.repository;

import kz.bisen.springwebapp1.project2Boot.model.Reader;

import java.util.List;
import java.util.Optional;

public interface ReaderRepository {
    Optional<Reader> findByFullName(String fullName);

    Optional<Reader> findByUsername(String username);

    Optional<Reader> findById(Integer id);

    List<Reader> findAll();

    <S extends Reader> S save(S reader);

    void deleteById(Integer id);
}