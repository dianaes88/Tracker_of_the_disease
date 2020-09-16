package ru.javaweb.tracker.util;

import ru.javaweb.tracker.model.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UsersUtil {
    public static final List<User> USERS = Arrays.asList(
            new User("Иван", "Иванович", "Иванов", "терапевт"),
            new User("Анна", "Андреевна", "Ахматова", "акушерка"),
            new User("Николай", "Васильевич", "Гоголь", "медбрат"),
            new User("Наталья", "Николаевна", "Гончарова", "медсестра")

    );

    public static List<User> getUsersByName(Collection<User> users) {
        return users.stream().sorted((u1, u2)->u1.getFirstName().compareTo(u2.getFirstName())).collect(Collectors.toList());
    }

    public static List<User> getUsersBySurname(Collection<User> users) {
        return users.stream().sorted((u1, u2)->u1.getLastName().compareTo(u2.getLastName())).collect(Collectors.toList());
    }

    public static List<User> getUsersByPosition(Collection<User> users) {
        return users.stream().sorted((u1, u2)->u1.getPosition().compareTo(u2.getPosition())).collect(Collectors.toList());
    }
}
