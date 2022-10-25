package kz.bisen.springwebapp1.project2Boot.services.impl;

import kz.bisen.springwebapp1.project2Boot.models.Reader;
import kz.bisen.springwebapp1.project2Boot.repositories.ReaderRepository;
import kz.bisen.springwebapp1.project2Boot.security.ReaderDetails;
import kz.bisen.springwebapp1.project2Boot.services.ReaderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultReaderDetailsService implements UserDetailsService, ReaderDetailsService
{
    private final ReaderRepository readerRepository;

    @Autowired
    public DefaultReaderDetailsService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Reader> foundPerson = readerRepository.findByUsername(username);
        if(foundPerson.isEmpty())
            throw new UsernameNotFoundException("Упс! Пользователь не найден...");
        return new ReaderDetails(foundPerson.get());
    }
}