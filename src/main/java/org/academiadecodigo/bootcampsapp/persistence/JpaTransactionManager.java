package org.academiadecodigo.bootcampsapp.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Created by codecadet on 04/12/17.
 */
public class JpaTransactionManager implements TransactionManager {

    SessionManager sessionManager;

    public JpaTransactionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void beginRead() {

        sessionManager.startSession();

    }

    @Override
    public void beginWrite() {

        sessionManager.getCurrentSession().getTransaction().begin();
    }

    @Override
    public void commit() {

        if (sessionManager.getCurrentSession().getTransaction().isActive()) {

            sessionManager.getCurrentSession().getTransaction().commit();
        }

        sessionManager.stopSession();
    }

    @Override
    public void rollback() {

        if (sessionManager.getCurrentSession().getTransaction().isActive()) {

            sessionManager.getCurrentSession().getTransaction().rollback();
        }

        sessionManager.stopSession();
    }
}
