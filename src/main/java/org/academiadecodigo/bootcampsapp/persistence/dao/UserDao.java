package org.academiadecodigo.bootcampsapp.persistence.dao;

import org.academiadecodigo.bootcampsapp.model.User;
import org.academiadecodigo.bootcampsapp.persistence.TransactionException;

import java.util.List;

/**
 * Created by codecadet on 04/12/17.
 */
public interface UserDao {

    List<User> findAll() throws TransactionException;

    User findById(Integer Id) throws TransactionException;

    User saveOrUpdate(User user) throws TransactionException;

    void delete(Integer Id) throws TransactionException;

    User findByUsername(String username) throws TransactionException;

    User findByEmail(String email) throws TransactionException;

    int count() throws TransactionException;
}
