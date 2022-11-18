package kz.bisen.springwebapp1.project2Boot.repository.jpa;

import kz.bisen.springwebapp1.project2Boot.model.Book;
import kz.bisen.springwebapp1.project2Boot.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaBookRepository implements BookRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public JpaBookRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Book> findByTitle(String title) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Book> query =
                cb.createQuery(Book.class);

        Root<Book> root = query.from(Book.class);

        query.select(root)
                .where(cb.equal(root.get("title"), title));

        return entityManager
                .createQuery(query)
                    .getResultList();
    }

    @Override
    public List<Book> findByTitleStartingWith(String title) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Book> query =
                cb.createQuery(Book.class);

        Root<Book> root = query.from(Book.class);

        query.select(root)
                .having(root.get("title"));

        return entityManager.createQuery(query).getResultList();
    }


    @Override
    public List<Book> findByIssueYearStartingWith(int issueYear) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Book> query =
                cb.createQuery(Book.class);

        Root<Book> root = query.from(Book.class);

        query.select(root)
                .having(root.get("issueYear"));

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Book> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Book> query =
                cb.createQuery(Book.class);

        Root<Book> root = query.from(Book.class);

        query.select(root);

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
//        TypedQuery<Book> query = entityManager.find(pageable); todo

        return null;
    }

    @Override
    public Optional<Book> findById(int id) {
        Book book = entityManager.find(Book.class, id);
        return Optional.of(book);
    }

    @Override
    public <S extends Book> S save(S entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void deleteById(Integer id) {
        Optional<Book> book = findById(id);
        entityManager.remove(book);
    }
}
