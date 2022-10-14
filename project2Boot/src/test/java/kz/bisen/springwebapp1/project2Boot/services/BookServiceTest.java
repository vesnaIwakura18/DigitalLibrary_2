package kz.bisen.springwebapp1.project2Boot.services;

import kz.bisen.springwebapp1.project2Boot.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    private static final long ID = 1;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService service;

    @Test
    void findAll_shouldCallRepositories() {

    }
}
