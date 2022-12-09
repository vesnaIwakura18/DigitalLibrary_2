//package kz.bisen.springwebapp1.project2Boot.service;
//
//import kz.bisen.springwebapp1.project2Boot.model.ReaderBook;
//import kz.bisen.springwebapp1.project2Boot.repository.ReaderBookRepository;
//import kz.bisen.springwebapp1.project2Boot.service.datajpa.JpaReaderBookService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class DataJpaReaderBookServiceTest {
//    private static final int READER_ID = 10;
//
//    @Mock
//    private ReaderBookRepository repository;
//
//    @InjectMocks
//    private JpaReaderBookService service;
//
//    @Test
//    void findOne_shouldCallRepository() {
//        final ReaderBook readerBook = mock(ReaderBook.class);
//        when(repository.findById(READER_ID)).thenReturn(Optional.of(readerBook));
//
//        final ReaderBook actual = service.findReaderBooks(READER_ID);
//
//        assertNotNull(actual);
//        assertEquals(readerBook, actual);
//        verify(repository).findById(READER_ID);
//    }
//
//    @Test
//    void setBook_shouldCallRepository() {
//        final ReaderBook readerBook = mock(ReaderBook.class);
//
//        service.setBook(readerBook);
//
//        verify(repository).save(readerBook);
//    }
//
//    @Test
//    void returnBook_shouldCallRepository() {
//        ReaderBook readerBook = mock(ReaderBook.class);
//        when(repository.findById(READER_ID)).thenReturn(Optional.of(readerBook));
//
//        service.returnBook(READER_ID);
//
//        verify(repository).delete(readerBook);
//    }
//}