package com.project.dao;

import com.project.entities.User;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Home
 */
@Component
@Repository("userDAO")
public class UserDAOImpl extends AbstractDao implements UserDAO {

    public UserDAOImpl() {
        System.out.println("user Service INIT ");
    }

    @Override
    public List<User> getUserList(Integer start, Integer max) {
        List<User> finalList = null;
        try {
            Query query = getSession().createQuery("SELECT c FROM User c WHERE c.id > 0 ORDER BY c.id DESC");
            if (start != null) {
                query.setFirstResult(start);
            }
            if (max != null) {
                query.setMaxResults(max);
            }
            finalList = query.list();

            if (finalList == null) {
                return null;
            }
         //   System.out.println("finalList " + finalList.size());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return finalList;
    }

    @Override
    public Long saveUser(User user) {
        Long id = 0L;
        getSession().save(user);
        getSession().flush();
        id = user.getId();
        return id;
    }

    @Override
    public boolean deleteUser(Long id) {
        if (id != null) {
            User user = this.getUserById(id);
            if(user != null){
            getSession().delete(user);
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        if (user != null) {
            getSession().update(user);
        } else {
            return false;
        }
        return true;
    }

    @Override
    public User getUserById(Long id) {
        User entity = null;
        try {
            Query query = getSession().createQuery("SELECT c FROM User c WHERE c.id=:id").setParameter("id", id);
            entity = (User) query.uniqueResult();

            if (entity == null) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return entity;
    }
    
    @Override
    public User getUserByEmail(String email) {
        User entity = null;
        try {
            Query query = getSession().createQuery("SELECT c FROM User c WHERE c.email=:email").setParameter("email", email);
            entity = (User) query.uniqueResult();

            if (entity == null) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return entity;
    }

    @Override
    public User loadUser(Long id) {
        User entity = null;
        try {
            entity = (User) getSession().load(User.class, id);//return proxy        
            if (entity == null) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return entity;
    }

    @Override
    public User getUser(Long id) {
        User entity = null;
        try {
            entity = (User) getSession().get(User.class, id);//get user from database
            if (entity == null) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return entity;
    }

}
