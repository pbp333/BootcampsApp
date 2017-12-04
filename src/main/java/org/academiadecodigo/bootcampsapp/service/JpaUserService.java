package org.academiadecodigo.bootcampsapp.service;

import org.academiadecodigo.bootcampsapp.model.User;
import org.academiadecodigo.bootcampsapp.persistence.SessionManager;
import org.academiadecodigo.bootcampsapp.persistence.TransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by codecadet on 30/11/17.
 */
public class JpaUserService implements UserService {

    private TransactionManager transactionManager;
    private SessionManager sessionManager;

    public JpaUserService(TransactionManager transactionManager, SessionManager sessionManager) {
        this.transactionManager = transactionManager;
        this.sessionManager = sessionManager;
    }

    @Override
    public boolean authenticate(String username, String password) {
        System.out.println("I WAS INVOCATED authenticate");
        User user = findByName(username);

        if (user == null) {
            return false;
        }

        return user.getPassword().equals(password);

    }

    @Override
    public void addUser(User user) {
        try {


            if (findByName(user.getUsername()) == null) {

                transactionManager.beginWrite();
                sessionManager.getCurrentSession().persist(user);
                transactionManager.commit();
            }

        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("Adding user went wrong");

        } finally {
            sessionManager.stopSession();
        }
    }

    @Override
    public void removeUser(User user) {

        transactionManager.beginRead();

        if (findByName(user.getUsername()) != null) {

            sessionManager.getCurrentSession().remove(user);
            transactionManager.commit();
        }

    }

    @Override
    public User findByName(String name) {

        try {

            CriteriaBuilder criteriaBuilder = sessionManager.getCurrentSession().getCriteriaBuilder();

            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root);
            criteriaQuery.where(criteriaBuilder.equal(root.get("username"), name));

            List<User> userList = sessionManager.getCurrentSession().createQuery(criteriaQuery).getResultList();

            if (userList.isEmpty()) {

                return null;
            } else {

                return userList.get(0);
            }

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        } finally {

            sessionManager.stopSession();
        }
    }


    @Override
    public int count() {

        CriteriaBuilder criteriaBuilder = sessionManager.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

        criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(User.class)));

        long count = sessionManager.getCurrentSession().createQuery(criteriaQuery).getSingleResult();

        return (int) count;
    }

    @Override
    public boolean emailAvailability(String email) {
        try {
            CriteriaBuilder criteriaBuilder = sessionManager.getCurrentSession().getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root);
            criteriaQuery.where(criteriaBuilder.equal(root.get("email"), email));

            List<User> emailList = sessionManager.getCurrentSession().createQuery(criteriaQuery).getResultList();

            return emailList.isEmpty();

        } catch (Exception e) {

            e.printStackTrace();
            return true;

        } finally {

            sessionManager.stopSession();
        }
    }
}
