package com.pp231.service;

import com.pp231.models.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    void saveNew(User user);

    User getById(int id);

    void update(User user);

    void delete(int id);
}
