package org.academiadecodigo.bootcampsapp.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Created by codecadet on 04/12/17.
 */
public class JpaSessionManager implements SessionManager {

    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    public JpaSessionManager(EntityManagerFactory entityManagerFactory){

        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void startSession() {

        if(entityManager == null){

            entityManager = entityManagerFactory.createEntityManager();
        }
    }

    @Override
    public void stopSession() {

        if(entityManager != null){

            entityManager.close();
        }

        entityManager = null;

    }

    @Override
    public EntityManager getCurrentSession() {

        startSession();
        return entityManager;
    }
}
