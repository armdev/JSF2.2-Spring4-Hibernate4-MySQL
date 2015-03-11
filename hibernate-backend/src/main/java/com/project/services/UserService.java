package com.project.services;

import com.project.entities.User;

import java.util.List;

/**
 *
 * @author Home
 */
public interface UserService {

    public List<User> getUserList(Integer start, Integer max);

    public Long saveUser(User user);

    public boolean deleteUser(Long id);

    public boolean updateUser(User user);

    public User getUserById(Long id);

    public User loadUser(Long id);

    public User getUser(Long id);

    public User getUserByEmail(String email);
}
