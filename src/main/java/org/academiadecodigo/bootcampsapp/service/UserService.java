package org.academiadecodigo.bootcampsapp.service;


import org.academiadecodigo.bootcampsapp.model.User;

/**
 * Created by codecadet on 08/11/17.
 */
public interface UserService extends Service{

    boolean authenticate(String username, String password);

    void addUser(User user);

    void removeUser(User user);

    User findByName(String name);

    int count();

    boolean emailAvailability(String email);
}
