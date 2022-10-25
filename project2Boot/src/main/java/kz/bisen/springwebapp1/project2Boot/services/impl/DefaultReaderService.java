package kz.bisen.springwebapp1.project2Boot.services.impl;

import kz.bisen.springwebapp1.project2Boot.models.Reader;
import kz.bisen.springwebapp1.project2Boot.repositories.BookRepository;
import kz.bisen.springwebapp1.project2Boot.repositories.ReaderRepository;
import kz.bisen.springwebapp1.project2Boot.services.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "readerData")
@Transactional(readOnly = true)
public class DefaultReaderService implements ReaderService
{

    public static final int OVERDUE_DAYS = 10;
    private final ReaderRepository readerRepository;
    private final PasswordEncoder passwordEncoder;

    private final BookRepository bookRepository;

    @Autowired
    public DefaultReaderService(ReaderRepository readerRepository,
                                PasswordEncoder passwordEncoder,
                                BookRepository bookRepository) {
        this.readerRepository = readerRepository;
        this.passwordEncoder = passwordEncoder;
        this.bookRepository = bookRepository;
    }

    @Cacheable
    public List<Reader> findAll() {
        return readerRepository.findAll();
    }

    @Cacheable
    public Reader findOne(int id) {
        Optional<Reader> foundReader = readerRepository.findById(id);
        bookRepository.findByOwnerId(id).stream()
                .filter(a -> a.getDateTimeTaken().plusDays(OVERDUE_DAYS).isBefore(LocalDateTime.now()))
                .forEach(a -> a.setOverdue(true));
        return foundReader.orElse(null);
    }

    @Cacheable
    public Optional<Reader> findReaderByFullName(String fullName) {
        return readerRepository.findByFullName(fullName).stream().findAny();
    }

    @Cacheable
    public Optional<Reader> findReaderByUsername(String username) {
        return readerRepository.findByUsername(username).stream().findAny();
    }

    @Transactional
    public void save(Reader reader) {
        reader.setPassword(passwordEncoder.encode(reader.getPassword()));
        reader.setRole("ROLE_USER");
        readerRepository.save(reader);
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void update(int id, Reader updatedReader) {
        updatedReader.setId(id);
        updatedReader.setRole("ROLE_USER");
        updatedReader.setPassword(passwordEncoder.encode(updatedReader.getPassword()));
        readerRepository.save(updatedReader);
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(int id) {
        readerRepository.deleteById(id);
    }

}