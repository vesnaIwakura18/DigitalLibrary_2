//package kz.bisen.springwebapp1.project2Boot.controller;
//
//import kz.bisen.springwebapp1.project2Boot.dto.ReaderBook.ReaderBookDTO;
//import kz.bisen.springwebapp1.project2Boot.dto.ReaderBook.impl.DefaultReaderBookDTOBuilder;
//import kz.bisen.springwebapp1.project2Boot.model.ReaderBook;
//import kz.bisen.springwebapp1.project2Boot.service.datajpa.JpaReaderBookService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.validation.BindingResult;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class ReaderBookControllerTest {
//    private final static int ID = 3;
//
//    @Mock
//    private JpaReaderBookService service;
//
//    @Mock
//    private DefaultReaderBookDTOBuilder dtoBuilder;
//
//    @InjectMocks
//    private ReaderBookController controller;
//
//    @Test
//    void get_shouldCallService() {
//        final List<ReaderBook> readerBooks = mock(ReaderBook.class);
//        when(service.findReaderBooks(ID)).thenReturn(readerBooks);
//        final List<ReaderBookDTO> readerBooksDTO = mock(ReaderBookDTO.class);
//        when(dtoBuilder.fromReaderBook(readerBooks)).thenReturn(readerBooksDTO);
//
//        final ReaderBookDTO actualReaderBookDTO = controller.getReaderBooks(ID);
//
//        assertNotNull(actualReaderBookDTO);
//        assertEquals(readerBooksDTO, actualReaderBookDTO);
//        verify(service).findReaderBooks(ID);
//        verify(dtoBuilder).fromReaderBook(readerBooks);
//    }
//
//    @Test
//    void setBook_shouldCallService() {
//        final ReaderBook readerBooks = mock(ReaderBook.class);
//        final ReaderBookDTO readerBooksDTO = mock(ReaderBookDTO.class);
//        final BindingResult bindingResult = mock(BindingResult.class);
//        when(dtoBuilder.fromReaderBookDTO(readerBooksDTO)).thenReturn(readerBooks);
//
//        controller.setBook(readerBooksDTO, bindingResult);
//
//        verify(dtoBuilder).fromReaderBookDTO(readerBooksDTO);
//        verify(service).setBook(readerBooks);
//    }
//
//    @Test
//    void returnBook_shouldCallService() {
//        controller.returnBook(ID);
//
//        verify(service).returnBook(ID);
//    }
//}