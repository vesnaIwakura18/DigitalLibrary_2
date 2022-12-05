package kz.bisen.springwebapp1.project2Boot.service.impl;

import kz.bisen.springwebapp1.project2Boot.exception.BookNotFoundException;
import kz.bisen.springwebapp1.project2Boot.model.ReaderBook;
import kz.bisen.springwebapp1.project2Boot.repository.datajpa.DataJpaReaderBookRepository;
import kz.bisen.springwebapp1.project2Boot.service.ReaderBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DefaultReaderBookService implements ReaderBookService {
    private final DataJpaReaderBookRepository dataJpaDataJpaReaderBookRepository;

    private final BookNotFoundException notFoundException = new BookNotFoundException(HttpStatus.NOT_FOUND, "Not found");

    @Autowired
    public DefaultReaderBookService(DataJpaReaderBookRepository dataJpaDataJpaReaderBookRepository) {
        this.dataJpaDataJpaReaderBookRepository = dataJpaDataJpaReaderBookRepository;
    }

    public List<ReaderBook> findReaderBooks(int readerId) {

        return dataJpaDataJpaReaderBookRepository.findByReaderId(readerId);
    }

    @Transactional
    //@PreAuthorize("hasAnyRole({'ROLE_ADMIN', 'ROLE_USER'})")
    public ReaderBook setBook(ReaderBook readerBook) {
        return dataJpaDataJpaReaderBookRepository.save(readerBook);
    }

    @Transactional
    public void returnBook(int id) {
        final ReaderBook readerBook = dataJpaDataJpaReaderBookRepository.findById(id)
                .orElseThrow(() -> notFoundException);
        dataJpaDataJpaReaderBookRepository.delete(readerBook);
    }
}