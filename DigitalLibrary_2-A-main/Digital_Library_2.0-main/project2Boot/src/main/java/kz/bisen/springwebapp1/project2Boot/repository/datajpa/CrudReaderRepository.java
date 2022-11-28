package kz.bisen.springwebapp1.project2Boot.repository.datajpa;

import kz.bisen.springwebapp1.project2Boot.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudReaderRepository extends JpaRepository<Reader, Integer> {
    Optional<Reader> findByFullName(String fullName);

    Optional<Reader> findByUsername(String username);
}