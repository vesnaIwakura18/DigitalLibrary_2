package kz.bisen.springwebapp1.project2Boot.service.impl;

import kz.bisen.springwebapp1.project2Boot.model.Reader;
import kz.bisen.springwebapp1.project2Boot.repository.datajpa.DataJpaReaderRepository;
import kz.bisen.springwebapp1.project2Boot.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "readerData")
@Transactional(readOnly = true)
public class DefaultReaderService implements ReaderService {

    public static final int OVERDUE_DAYS = 10;
    private final DataJpaReaderRepository dataJpaDataJpaReaderRepository;
    private final PasswordEncoder passwordEncoder;



    @Autowired
    public DefaultReaderService(DataJpaReaderRepository dataJpaDataJpaReaderRepository,
                                PasswordEncoder passwordEncoder) {
        this.dataJpaDataJpaReaderRepository = dataJpaDataJpaReaderRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Cacheable
    public List<Reader> findAll() {
        return dataJpaDataJpaReaderRepository.findAll();
    }

    @Cacheable
    public Reader findOne(int id) {
        Optional<Reader> foundReader = dataJpaDataJpaReaderRepository.findById(id);
        return foundReader.orElse(null);
    }

    @Cacheable
    public Optional<Reader> findReaderByFullName(String fullName) {
        return dataJpaDataJpaReaderRepository.findByFullName(fullName).stream().findAny();
    }

    @Cacheable
    public Optional<Reader> findReaderByUsername(String username) {
        return dataJpaDataJpaReaderRepository.findByUsername(username).stream().findAny();
    }

    @Transactional
    public Reader save(Reader reader) {
        final String encodedPassword = passwordEncoder.encode(reader.getPassword());
        reader.setPassword(encodedPassword);
        System.out.println("Пароль: " + encodedPassword);
        reader.setRole("ROLE_USER");
        return dataJpaDataJpaReaderRepository.save(reader);
    }

    @Transactional
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public Reader update(Reader updatedReader) {
        updatedReader.setPassword(passwordEncoder.encode(updatedReader.getPassword()));
        return dataJpaDataJpaReaderRepository.save(updatedReader);
    }

    @Transactional
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(int id) {
        dataJpaDataJpaReaderRepository.deleteById(id);
    }
}