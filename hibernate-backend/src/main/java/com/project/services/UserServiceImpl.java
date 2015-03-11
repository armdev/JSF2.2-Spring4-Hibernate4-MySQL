package com.project.services;

import com.project.dao.*;
import com.project.entities.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Home
 */
@Service("userService")
@Component
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDao;

    @Override
    public List<User> getUserList(Integer start, Integer max) {
        return userDao.getUserList(start, max);
    }

    @Override
    public Long saveUser(User user) {

        return userDao.saveUser(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        return userDao.deleteUser(id);
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public User loadUser(Long id) {
        return userDao.loadUser(id);
    }

    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    public User getUserByEmail(String email) {
       return userDao.getUserByEmail(email);
    }
}
