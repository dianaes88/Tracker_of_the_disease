package ru.javaweb.tracker;
import ru.javaweb.tracker.model.Role;
import ru.javaweb.tracker.model.User;

public class UserTestData {
    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 2;

    public static final User USER = new User("user", "mn", "ln", "pos",USER_ID,"123", Role.ROLE_USER);
    public static final User ADMIN = new User("admin", "mn", "ln", "pos",ADMIN_ID,"qwe", Role.ROLE_ADMIN);
}
