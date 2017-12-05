package org.academiadecodigo.bootcampsapp.persistence.dao.jpa;

import org.academiadecodigo.bootcampsapp.model.Bootcamp;
import org.academiadecodigo.bootcampsapp.model.CodeCadet;
import org.academiadecodigo.bootcampsapp.persistence.SessionManager;
import org.academiadecodigo.bootcampsapp.persistence.TransactionException;
import org.academiadecodigo.bootcampsapp.persistence.dao.BootcampDao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

/**
 * Created by codecadet on 04/12/17.
 */
public class JpaBootcampDao implements BootcampDao {

    private SessionManager sessionManager;

    public JpaBootcampDao(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void createBootcamp(Bootcamp bootcamp) throws TransactionException {

        try {

            sessionManager.getCurrentSession().merge(bootcamp);

        } catch (Exception e) {

            throw new TransactionException(e);
        }
    }

    @Override
    public void addCodeCadet(int bootcampID, CodeCadet codeCadet) throws TransactionException {

        List<Bootcamp> bootcamps = getBootcamps();

        if (bootcamps.get(bootcampID) != null) {

            bootcamps.get(bootcampID).addCadet(codeCadet);
        }

        try {

            sessionManager.getCurrentSession().merge(bootcamps);

        } catch (Exception e) {

            throw new TransactionException(e);
        }
    }

    @Override
    public List<Bootcamp> getBootcamps() throws TransactionException {

        try {

            CriteriaBuilder criteriaBuilder = sessionManager.getCurrentSession().getCriteriaBuilder();
            CriteriaQuery<Bootcamp> criteriaQuery = criteriaBuilder.createQuery(Bootcamp.class);
            Root<Bootcamp> root = criteriaQuery.from(Bootcamp.class);
            criteriaQuery.select(root);

            return sessionManager.getCurrentSession().createQuery(criteriaQuery).getResultList();

        } catch (Exception e) {

            throw new TransactionException(e);
        }
    }

    @Override
    public void removeBootcamp(Bootcamp bootcamp) throws TransactionException {

        try {

            sessionManager.getCurrentSession().remove(bootcamp);

        } catch (Exception e) {

            throw new TransactionException(e);
        }

    }
}
