package kz.bisen.springwebapp1.project2Boot.service;

import kz.bisen.springwebapp1.project2Boot.model.Reader;
import kz.bisen.springwebapp1.project2Boot.repository.BookRepository;
import kz.bisen.springwebapp1.project2Boot.repository.ReaderRepository;
import kz.bisen.springwebapp1.project2Boot.service.impl.DefaultReaderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DefaultReaderServiceTest {
    private static final int ID = 2;

    private static final String FULLNAME = "Admin Admin Admin";

    private static final String PASSWORD = "Some_password";

    private static final String ROLE = "ROLE_SOME";

    @Mock
    private ReaderRepository readerRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private DefaultReaderService defaultReaderService;

    @Test
    void findAll_shouldCallRepository() {
        final List<Reader> people = List.of(mock(Reader.class));
        when(readerRepository.findAll()).thenReturn(people);

        final List<Reader> actualPeople = defaultReaderService.findAll();

        assertEquals(people, actualPeople);
        verify(readerRepository).findAll();
    }

    @Test
    void findOne_shouldCallRepository() {
        final Reader reader = mock(Reader.class);
        when(readerRepository.findById(ID)).thenReturn(Optional.of(reader));


        final Reader actualReader = defaultReaderService.findOne(ID);

        assertEquals(reader, actualReader);
        verify(readerRepository).findById(ID);
    }

    @Test
    void findReaderByFullName_shouldCallRepository() {
        final Reader reader = mock(Reader.class);
        when(readerRepository.findByFullName(FULLNAME)).thenReturn(Optional.of(reader));

        final Reader actualReader = defaultReaderService.findReaderByFullName(FULLNAME).orElse(null);

        assertEquals(reader, actualReader);
        verify(readerRepository).findByFullName(FULLNAME);
    }

    @Test
    void save_shouldCallRepository() {
        final Reader reader = mock(Reader.class);

        reader.setPassword(passwordEncoder.encode(PASSWORD));
        reader.setRole(ROLE);
        defaultReaderService.save(reader);

        verify(readerRepository).save(reader);
    }

    @Test
    void update_shouldCallRepository() {
        final Reader reader = mock(Reader.class);

        reader.setId(ID);
        reader.setRole(ROLE);
        reader.setPassword(passwordEncoder.encode(PASSWORD));
        defaultReaderService.save(reader);

        verify(readerRepository).save(reader);
    }

    @Test
    void delete_shouldCallRepository() {
        defaultReaderService.delete(ID);

        verify(readerRepository).deleteById(ID);
    }
}
