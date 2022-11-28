package kz.bisen.springwebapp1.project2Boot.service;

import kz.bisen.springwebapp1.project2Boot.model.Book;
import kz.bisen.springwebapp1.project2Boot.repository.BookRepository;
import kz.bisen.springwebapp1.project2Boot.service.impl.DefaultBookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DefaultBookServiceTest {
    private static final long ID = 1;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private DefaultBookService defaultBookService;

    @Test
    void findAll_shouldCallRepository() {
        final List<Book> books = List.of(mock(Book.class));
        when(bookRepository.findAll()).thenReturn(books);

        final List<Book> actualBooks = defaultBookService.findAll(0, 5, Optional.of(Boolean.TRUE));

        assertEquals(books, actualBooks);
        verify(bookRepository).findAll();
    }
}