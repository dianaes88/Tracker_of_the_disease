package ru.javaweb.tracker.repository;

import ru.javaweb.tracker.model.Patient;
import ru.javaweb.tracker.model.User;
import ru.javaweb.tracker.util.PatientsUtil;
import ru.javaweb.tracker.util.UsersUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryUserRepositoryImpl implements PersonRepository<User>{
    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UsersUtil.USERS.forEach(this::save);
    }

    @Override
    public User save(User user) {
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
        }
        repository.put(user.getId(), user);
        return user;
    }

    @Override
    public void delete(int id) {
        repository.remove(id);
    }

    @Override
    public User get(int id) {
        return repository.get(id);
    }

    @Override
    public Collection<User> getAll() {
        return repository.values();
    }
}
