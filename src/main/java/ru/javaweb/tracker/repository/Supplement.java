package ru.javaweb.tracker.repository;

import ru.javaweb.tracker.model.Person;

import java.util.Collection;

public interface Supplement<T extends Person> {
    T save (T element);

    void delete(int id);

    T get(int id);

    Collection<T> getAll();
}
