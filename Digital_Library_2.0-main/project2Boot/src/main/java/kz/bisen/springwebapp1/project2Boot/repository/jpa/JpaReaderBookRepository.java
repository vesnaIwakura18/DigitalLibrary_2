package kz.bisen.springwebapp1.project2Boot.repository.jpa;

import kz.bisen.springwebapp1.project2Boot.model.Book;
import kz.bisen.springwebapp1.project2Boot.model.ReaderBook;
import kz.bisen.springwebapp1.project2Boot.repository.ReaderBookRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaReaderBookRepository implements ReaderBookRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public JpaReaderBookRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<ReaderBook> findByReaderId(Integer id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<ReaderBook> query =
                cb.createQuery(ReaderBook.class);

        Root<ReaderBook> root = query.from(ReaderBook.class);

        query.select(root)
                .having(root.get("readerId"));

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Optional<ReaderBook> findById(Integer id) {
        ReaderBook readerBook = entityManager.find(ReaderBook.class, id);

        return Optional.of(readerBook);
    }

    @Override
    public <S extends ReaderBook> S save(S entity) {
        entityManager.persist(entity);

        return entity;
    }

    @Override
    public void delete(ReaderBook readerBook) {
        entityManager.remove(readerBook);
    }
}
