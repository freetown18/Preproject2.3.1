package com.pp231.dao;

import com.pp231.models.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();

    void saveNew(User user);

    void update(User user);

    User getById(int id);

    void delete(int id);
}
