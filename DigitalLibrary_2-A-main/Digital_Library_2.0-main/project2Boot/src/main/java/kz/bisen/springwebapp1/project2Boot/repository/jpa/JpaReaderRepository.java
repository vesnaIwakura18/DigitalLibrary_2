package kz.bisen.springwebapp1.project2Boot.repository.jpa;

import kz.bisen.springwebapp1.project2Boot.model.Reader;
import kz.bisen.springwebapp1.project2Boot.model.ReaderBook;
import kz.bisen.springwebapp1.project2Boot.repository.ReaderRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaReaderRepository implements ReaderRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public JpaReaderRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Reader> findByFullName(String fullName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Reader> query =
                cb.createQuery(Reader.class);

        Root<Reader> root = query.from(Reader.class);

        query.select(root)
                .having(root.get("fullName"));

        return entityManager.createQuery(query).getResultList().stream().findAny();
    }

    @Override
    public Optional<Reader> findByUsername(String username) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Reader> query =
                cb.createQuery(Reader.class);

        Root<Reader> root = query.from(Reader.class);

        query.select(root)
                .having(root.get("username"));

        return entityManager.createQuery(query).getResultList().stream().findAny();
    }

    @Override
    public Optional<Reader> findById(Integer id) {
        Reader reader = entityManager.find(Reader.class, id);

        return Optional.of(reader);
    }

    @Override
    public List<Reader> findAll() {
        TypedQuery<Reader> query = entityManager.createQuery("SELECT r FROM Reader r", Reader.class);

        return query.getResultList();
    }

    @Override
    public <S extends Reader> S save(S reader) {
        entityManager.persist(reader);

        return reader;
    }

    @Override
    public void deleteById(Integer id) {
        Optional<Reader> reader = findById(id);
        entityManager.remove(reader);
    }
}
