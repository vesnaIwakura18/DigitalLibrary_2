package kz.bisen.springwebapp1.project2Boot.repository.datajpa;

import kz.bisen.springwebapp1.project2Boot.model.Reader;
import kz.bisen.springwebapp1.project2Boot.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DataJpaReaderRepository implements ReaderRepository {
    private final CrudReaderRepository crudReaderRepository;

    @Autowired
    public DataJpaReaderRepository(CrudReaderRepository crudReaderRepository) {
        this.crudReaderRepository = crudReaderRepository;
    }

    @Override
    public Optional<Reader> findByFullName(String fullName) {
        return crudReaderRepository.findByFullName(fullName);
    }

    @Override
    public Optional<Reader> findByUsername(String username) {
        return crudReaderRepository.findByUsername(username);
    }

    @Override
    public Optional<Reader> findById(Integer id) {
        return crudReaderRepository.findById(id);
    }

    @Override
    public List<Reader> findAll() {
        return crudReaderRepository.findAll();
    }

    @Override
    public <S extends Reader> S save(S reader) {
        return crudReaderRepository.save(reader);
    }

    @Override
    public void deleteById(Integer id) {
        crudReaderRepository.deleteById(id);
    }
}
