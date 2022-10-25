package kz.bisen.springwebapp1.project2Boot.repositories;

import kz.bisen.springwebapp1.project2Boot.models.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Integer> {
    Optional<Reader> findByFullName(String fullName);

    Optional<Reader> findByUsername(String username);
}