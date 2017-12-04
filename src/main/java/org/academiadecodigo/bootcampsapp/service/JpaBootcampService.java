package org.academiadecodigo.bootcampsapp.service;

import org.academiadecodigo.bootcampsapp.model.Bootcamp;
import org.academiadecodigo.bootcampsapp.model.CodeCadet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by codecadet on 30/11/17.
 */
public class JpaBootcampService implements BootcampService {

    private EntityManagerFactory entityManagerFactory;

    @Override
    public void createBootcamp(String location, Date start, Date end) {

        Bootcamp bootcamp = new Bootcamp();
        bootcamp.setupBootcamp(location, start, end);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(bootcamp);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override
    public void addCodeCadet(int bootcampID, CodeCadet codeCadet) {

        List<Bootcamp> bootcamps = getBootcamps();


        if (bootcamps.get(bootcampID) != null) {

            bootcamps.get(bootcampID).addCadet(codeCadet);
        }

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(bootcamps.get(bootcampID));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Bootcamp> getBootcamps() {

        List<Bootcamp> bootcamps;

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Bootcamp> criteriaQuery = criteriaBuilder.createQuery(Bootcamp.class);
            Root<Bootcamp> root = criteriaQuery.from(Bootcamp.class);
            criteriaQuery.select(root);
            bootcamps = entityManager.createQuery(criteriaQuery).getResultList();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return bootcamps;
    }

    @Override
    public void addBootcamp(Bootcamp bootcamp) {

        List<Bootcamp> bootcamps = getBootcamps();

        for (Bootcamp bc : bootcamps) {

            if (bc.equals(bootcamp)) {
                return;
            }
        }

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(bootcamp);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<CodeCadet> getCodeCadets(int bootcampID) {

        List<Bootcamp> bootcamps = getBootcamps();

        return bootcamps.get(bootcampID).getCodeCadets();
    }

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void removeBootcamp(Bootcamp bootcamp){

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(bootcamp);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
