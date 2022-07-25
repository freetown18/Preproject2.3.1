package com.pp231.dao;

import com.pp231.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> getAll() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getById(int id) {
        return em.createQuery("select u from User u where u.id =: id", User.class)
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public void saveNew(User user) {
        em.persist(user);
    }

    @Override
    public void update(User user) {
        em.merge(user);
    }

    @Override
    public void delete(int id) {
        User user = getById(id);
        em.remove(user);
    }
}
