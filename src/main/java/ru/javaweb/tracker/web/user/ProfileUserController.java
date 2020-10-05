package ru.javaweb.tracker.web.user;

import org.springframework.stereotype.Controller;
import ru.javaweb.tracker.model.User;
import ru.javaweb.tracker.web.AuthorizedUser;
@Controller
public class ProfileUserController extends AbstractUserController{
    public User get() {
        return super.get(AuthorizedUser.id());
    }

    public void delete() {
        super.delete(AuthorizedUser.id());
    }

    public void update(User user) {
        super.update(user, AuthorizedUser.id());
    }
}
