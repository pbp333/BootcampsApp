package org.academiadecodigo.bootcampsapp.service;

import org.academiadecodigo.bootcampsapp.model.User;
import org.academiadecodigo.bootcampsapp.persistence.TransactionException;
import org.academiadecodigo.bootcampsapp.persistence.TransactionManager;
import org.academiadecodigo.bootcampsapp.persistence.dao.UserDao;

/**
 * Created by codecadet on 30/11/17.
 */
public class JpaUserService implements UserService {

    private TransactionManager transactionManager;
    private UserDao userDao;

    public JpaUserService(TransactionManager transactionManager, UserDao userDao) {
        this.transactionManager = transactionManager;
        this.userDao = userDao;
    }

    @Override
    public boolean authenticate(String username, String password) {

        User user = findByName(username);

        if (user == null) {
            return false;

        }
        return user.getPassword().equals(password);
    }


    @Override
    public void addUser(User user) {

        try {

            transactionManager.beginWrite();

            if (userDao.findByUsername(user.getUsername()) == null) {

                userDao.saveOrUpdate(user);
            }
            transactionManager.commit();

        } catch (TransactionException e) {

            transactionManager.rollback();
            e.printStackTrace();
            System.out.println("Adding user went wrong");
        }
    }

    @Override
    public void removeUser(User user) {

        try {

            transactionManager.beginWrite();

            if (userDao.findByUsername(user.getUsername()) != null) {

                userDao.delete(user.getId());
            }
            transactionManager.commit();

        } catch (TransactionException e) {
            transactionManager.rollback();
            e.printStackTrace();
        }

    }

    @Override
    public User findByName(String name) {

        try {

            transactionManager.beginRead();
            return userDao.findByUsername(name);

        } catch (TransactionException e) {

            e.printStackTrace();
            return null;

        } finally {

            transactionManager.commit();
        }
    }

    @Override
    public int count() {

        try {

            transactionManager.beginRead();
            return userDao.count();

        } catch (TransactionException e) {

            e.printStackTrace();
            return 0;

        } finally {
            transactionManager.commit();
        }
    }

    @Override
    public boolean emailAvailability(String email) {

        try {
            transactionManager.beginRead();
            User user = userDao.findByEmail(email);
            transactionManager.commit();

            return user == null;

        } catch (TransactionException e) {

            transactionManager.rollback();
            e.printStackTrace();
            return true;
        }
    }
}
