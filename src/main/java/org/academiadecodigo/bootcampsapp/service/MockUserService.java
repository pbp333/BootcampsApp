package org.academiadecodigo.bootcampsapp.service;


import org.academiadecodigo.bootcampsapp.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by codecadet on 08/11/17.
 */
public class MockUserService implements UserService {

    private Map<String, User> userMap;

    public MockUserService() {

        userMap = new HashMap<>();
    }

    @Override
    public boolean authenticate(String username, String password) {

        if (userMap.containsKey(username)) {

            if (userMap.get(username).getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addUser(User user) {

        userMap.put(user.getUsername(), user);
    }

    @Override
    public void removeUser(User user) {
        userMap.remove(user.getUsername());
    }

    @Override
    public User findByName(String name) {

        return userMap.get(name);

    }

    @Override
    public int count() {
        return userMap.size();
    }

    public boolean emailAvailability(String email) {

        for (String s : userMap.keySet()) {
            if (userMap.get(s).getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }
}
