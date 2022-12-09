package kz.bisen.springwebapp1.project2Boot.repository.datajpa;

import kz.bisen.springwebapp1.project2Boot.model.ReaderBook;
import kz.bisen.springwebapp1.project2Boot.repository.ReaderBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DataJpaReaderBookRepository implements ReaderBookRepository {
    private final CrudReaderBookRepository crudReaderBookRepository;

    @Autowired
    public DataJpaReaderBookRepository(CrudReaderBookRepository crudReaderBookRepository) {
        this.crudReaderBookRepository = crudReaderBookRepository;
    }

    @Override
    public List<ReaderBook> findByReaderId(Integer id) {
        return crudReaderBookRepository.findByReaderId(id);
    }

    @Override
    public Optional<ReaderBook> findById(Integer id) {
        return crudReaderBookRepository.findById(id);
    }

    @Override
    public <S extends ReaderBook> S save(S entity) {
        return crudReaderBookRepository.save(entity);
    }

    @Override
    public void delete(ReaderBook readerBook) {
        crudReaderBookRepository.delete(readerBook);
    }
}
