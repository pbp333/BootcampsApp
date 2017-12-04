package org.academiadecodigo.bootcampsapp.persistence;

/**
 * Created by codecadet on 04/12/17.
 */
public interface TransactionManager {

    void beginRead();

    void beginWrite();

    void commit();

    void rollback();
}
