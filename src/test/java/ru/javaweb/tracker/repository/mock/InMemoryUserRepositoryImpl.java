package ru.javaweb.tracker.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javaweb.tracker.UserTestData;
import org.springframework.stereotype.Repository;
import ru.javaweb.tracker.model.User;
import ru.javaweb.tracker.repository.UserRepository;
import ru.javaweb.tracker.util.UsersUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static ru.javaweb.tracker.UserTestData.*;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);

    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(100);

    public void init() {
        repository.clear();
        repository.put(USER_ID, USER);
        repository.put(ADMIN_ID, ADMIN);
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
            repository.put(user.getId(), user);
            return user;
        }
        return repository.computeIfPresent(user.getId(), (id, oldUser) -> user);
    }

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        return repository.remove(id, repository.get(id));
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        return UsersUtil.getUsersByName(repository.values());
    }
}
