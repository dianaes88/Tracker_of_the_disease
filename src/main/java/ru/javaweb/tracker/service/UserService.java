package ru.javaweb.tracker.service;

import ru.javaweb.tracker.model.User;
import ru.javaweb.tracker.util.exception.NotFoundException;

import java.util.List;

public interface UserService {
    User create(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    void update(User user);

    List<User> getAll();
}
