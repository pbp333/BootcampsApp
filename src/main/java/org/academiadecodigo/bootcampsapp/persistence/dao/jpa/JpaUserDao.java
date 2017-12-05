package org.academiadecodigo.bootcampsapp.persistence.dao.jpa;

import org.academiadecodigo.bootcampsapp.model.User;
import org.academiadecodigo.bootcampsapp.persistence.SessionManager;
import org.academiadecodigo.bootcampsapp.persistence.TransactionException;
import org.academiadecodigo.bootcampsapp.persistence.dao.UserDao;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by codecadet on 04/12/17.
 */
public class JpaUserDao implements UserDao {

    private SessionManager sessionManager;

    public JpaUserDao(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public List<User> findAll() throws TransactionException {


        try {
            CriteriaBuilder criteriaBuilder = sessionManager.getCurrentSession().getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root);

            return sessionManager.getCurrentSession().createQuery(criteriaQuery).getResultList();
        } catch (PersistenceException ex) {
            throw new TransactionException(ex);
        }

    }

    @Override
    public User findById(Integer id) throws TransactionException {

        try {

            return sessionManager.getCurrentSession().find(User.class, id);

        } catch (PersistenceException e) {

            throw new TransactionException(e);
        }
    }

    @Override
    public User saveOrUpdate(User user) throws TransactionException {

        try {

            return sessionManager.getCurrentSession().merge(user);

        } catch (PersistenceException e) {
            throw new TransactionException(e);
        }
    }

    @Override
    public void delete(Integer id) throws TransactionException {
        try {

            sessionManager.getCurrentSession().remove(findById(id));
        } catch (PersistenceException e) {
            throw new TransactionException(e);
        }
    }

    @Override
    public User findByUsername(String username) throws TransactionException {

        try {

            CriteriaBuilder criteriaBuilder = sessionManager.getCurrentSession().getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.where(criteriaBuilder.equal(root.get("username"), username));

            return sessionManager.getCurrentSession().createQuery(criteriaQuery).getSingleResult();

        } catch (PersistenceException e) {

            throw new TransactionException(e);
        }
    }

    @Override
    public User findByEmail(String email) throws TransactionException {

        try {

            CriteriaBuilder criteriaBuilder = sessionManager.getCurrentSession().getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.where(criteriaBuilder.equal(root.get("email"), email));

            return sessionManager.getCurrentSession().createQuery(criteriaQuery).getSingleResult();

        } catch (PersistenceException e) {

            throw new TransactionException(e);
        }
    }

    @Override
    public int count() throws TransactionException {

        try {

            CriteriaBuilder criteriaBuilder = sessionManager.getCurrentSession().getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

            criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(User.class)));

            long count = sessionManager.getCurrentSession().createQuery(criteriaQuery).getSingleResult();

            return (int) count;

        } catch (Exception e) {

            throw new TransactionException(e);
        }
    }
}
