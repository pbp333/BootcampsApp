package org.academiadecodigo.bootcampsapp.persistence.dao;

import org.academiadecodigo.bootcampsapp.model.Bootcamp;
import org.academiadecodigo.bootcampsapp.model.CodeCadet;
import org.academiadecodigo.bootcampsapp.persistence.TransactionException;

import java.util.Date;
import java.util.List;

/**
 * Created by codecadet on 04/12/17.
 */
public interface BootcampDao {

    void createBootcamp(Bootcamp bootcamp) throws TransactionException;

    void addCodeCadet(int bootcampID, CodeCadet codeCadet) throws TransactionException;

    List<Bootcamp> getBootcamps() throws TransactionException;

    void removeBootcamp(Bootcamp bootcamp) throws TransactionException;
}
