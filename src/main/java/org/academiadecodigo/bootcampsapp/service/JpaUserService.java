package org.academiadecodigo.bootcampsapp.service;

import org.academiadecodigo.bootcampsapp.model.User;

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

    private EntityManagerFactory entityManagerFactory;

    @Override
    public boolean authenticate(String username, String password) {

        User user = findByName(username);

        if(user == null){
            return false;
        }

        return user.getPassword().equals(password);

    }

    @Override
    public void addUser(User user) {

        if (findByName(user.getUsername()) == null) {

            initiateEntityManagerFactory();

            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }

    @Override
    public void removeUser(User user) {

        if (findByName(user.getUsername()) != null) {

            initiateEntityManagerFactory();

            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(user);
            entityManager.getTransaction().commit();
            entityManager.close();
        }

    }

    @Override
    public User findByName(String name) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

            Root<User> root = criteriaQuery.from(User.class);

            criteriaQuery.select(root);

            criteriaQuery.where(criteriaBuilder.equal(root.get("username"), name));


            List<User> userList = entityManager.createQuery(criteriaQuery).getResultList();
            if (userList.isEmpty()) {

                return null;
            } else {
                return userList.get(0);

            }


        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public boolean emailAvailability(String email) {

        initiateEntityManagerFactory();

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root);
            criteriaQuery.where(criteriaBuilder.equal(root.get("email"), email));

            List<User> emailList = entityManager.createQuery(criteriaQuery).getResultList();

            return emailList.isEmpty();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public void initiateEntityManagerFactory() {

        if (entityManagerFactory == null) {

            entityManagerFactory = Persistence.createEntityManagerFactory("test");
        }
    }

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
}
