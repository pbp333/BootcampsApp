package org.academiadecodigo.bootcampsapp.service;

import org.academiadecodigo.bootcampsapp.model.Bootcamp;
import org.academiadecodigo.bootcampsapp.model.CodeCadet;
import org.academiadecodigo.bootcampsapp.persistence.SessionManager;
import org.academiadecodigo.bootcampsapp.persistence.TransactionException;
import org.academiadecodigo.bootcampsapp.persistence.TransactionManager;
import org.academiadecodigo.bootcampsapp.persistence.dao.jpa.JpaBootcampDao;

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
    private JpaBootcampDao jpaBootcampDao;

    public JpaBootcampService(TransactionManager transactionManager, JpaBootcampDao jpaBootcampDao) {

        this.transactionManager = transactionManager;
        this.jpaBootcampDao = jpaBootcampDao;
    }

    @Override
    public void createBootcamp(String location, Date start, Date end) {

        Bootcamp bootcamp = new Bootcamp();
        bootcamp.setupBootcamp(location, start, end);

        addBootcamp(bootcamp);
    }

    @Override
    public void addCodeCadet(int bootcampID, CodeCadet codeCadet) {

        try {

            transactionManager.beginWrite();
            jpaBootcampDao.addCodeCadet(bootcampID, codeCadet);
            transactionManager.commit();
        } catch (TransactionException e) {
            transactionManager.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<Bootcamp> getBootcamps() {

        try {
            transactionManager.beginRead();
            return jpaBootcampDao.getBootcamps();

        } catch (TransactionException e) {
            transactionManager.rollback();
            throw new IllegalStateException();

        } finally {
            transactionManager.commit();
        }
    }

    @Override
    public void addBootcamp(Bootcamp bootcamp) {

        try {

            transactionManager.beginWrite();
            jpaBootcampDao.createBootcamp(bootcamp);
            transactionManager.commit();
        } catch (TransactionException e) {
            transactionManager.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<CodeCadet> getCodeCadets(int bootcampID) {

        List<Bootcamp> bootcamps = getBootcamps();

        return bootcamps.get(bootcampID).getCodeCadets();
    }

    public void removeBootcamp(Bootcamp bootcamp) {

        try {

            transactionManager.beginWrite();
            jpaBootcampDao.removeBootcamp(bootcamp);
            transactionManager.commit();
        } catch (TransactionException e) {
            transactionManager.rollback();
            e.printStackTrace();
        }
    }
}
