package org.academiadecodigo.bootcampsapp.persistence;

import javax.persistence.EntityManager;

/**
 * Created by codecadet on 04/12/17.
 */
public interface SessionManager {

    void startSession();

    void stopSession();

    EntityManager getCurrentSession();
}
