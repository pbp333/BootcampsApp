package org.academiadecodigo.bootcampsapp.service;

import org.academiadecodigo.bootcampsapp.model.Bootcamp;
import org.academiadecodigo.bootcampsapp.model.CodeCadet;
import org.academiadecodigo.bootcampsapp.persistence.SessionManager;
import org.academiadecodigo.bootcampsapp.persistence.TransactionManager;

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

    private TransactionManager transactionManager;
    private SessionManager sessionManager;

    public JpaBootcampService(TransactionManager transactionManager, SessionManager sessionManager) {

        this.transactionManager = transactionManager;
        this.sessionManager = sessionManager;
    }

    @Override
    public void createBootcamp(String location, Date start, Date end) {

        Bootcamp bootcamp = new Bootcamp();
        bootcamp.setupBootcamp(location, start, end);

        transactionManager.beginRead();
        transactionManager.beginWrite();
        sessionManager.getCurrentSession().persist(bootcamp);
        transactionManager.commit();

    }

    @Override
    public void addCodeCadet(int bootcampID, CodeCadet codeCadet) {

        List<Bootcamp> bootcamps = getBootcamps();


        if (bootcamps.get(bootcampID) != null) {

            bootcamps.get(bootcampID).addCadet(codeCadet);
        }

        transactionManager.beginWrite();
        sessionManager.getCurrentSession().merge(bootcamps.get(bootcampID));
        transactionManager.commit();
    }

    @Override
    public List<Bootcamp> getBootcamps() {

        List<Bootcamp> bootcamps;

        try {
            CriteriaBuilder criteriaBuilder = sessionManager.getCurrentSession().getCriteriaBuilder();
            CriteriaQuery<Bootcamp> criteriaQuery = criteriaBuilder.createQuery(Bootcamp.class);
            Root<Bootcamp> root = criteriaQuery.from(Bootcamp.class);
            criteriaQuery.select(root);
            bootcamps = sessionManager.getCurrentSession().createQuery(criteriaQuery).getResultList();

        } finally {

            sessionManager.stopSession();

        }

        return bootcamps;
    }

    @Override
    public void addBootcamp(Bootcamp bootcamp) {

        transactionManager.beginWrite();
        sessionManager.getCurrentSession().merge(bootcamp);
        transactionManager.commit();
    }

    @Override
    public List<CodeCadet> getCodeCadets(int bootcampID) {

        List<Bootcamp> bootcamps = getBootcamps();

        return bootcamps.get(bootcampID).getCodeCadets();
    }

    public void removeBootcamp(Bootcamp bootcamp) {

        transactionManager.beginWrite();
        sessionManager.getCurrentSession().remove(bootcamp);
        transactionManager.commit();
    }
}
