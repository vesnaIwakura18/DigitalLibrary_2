package kz.bisen.springwebapp1.project2Boot.service.impl;

import kz.bisen.springwebapp1.project2Boot.model.Reader;
import kz.bisen.springwebapp1.project2Boot.repository.datajpa.DataJpaReaderRepository;
import kz.bisen.springwebapp1.project2Boot.security.ReaderDetails;
import kz.bisen.springwebapp1.project2Boot.service.ReaderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultReaderDetailsService implements UserDetailsService, ReaderDetailsService {
    private final DataJpaReaderRepository dataJpaReaderRepository;

    @Autowired
    public DefaultReaderDetailsService(DataJpaReaderRepository dataJpaReaderRepository) {
        this.dataJpaReaderRepository = dataJpaReaderRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Reader> foundPerson = dataJpaReaderRepository.findByUsername(username);
        if (foundPerson.isEmpty())
            throw new UsernameNotFoundException("Упс! Пользователь не найден...");
        return new ReaderDetails(foundPerson.get());
    }
}