package ru.javaweb.tracker.model;

import java.util.*;

public class User extends Person {
    private String position;
    private String password;
    private boolean enabled = true;

    private Date dateRegistered = new Date();

    private Set<Role> roles = new HashSet<>();

    public User(String firstName, String middleName, String lastName, String position) {
        this(firstName, middleName, lastName, position, null);
    }

    public User(String firstName, String lastName, String middleName, String position, Integer id) {
        super(firstName, lastName, middleName, id);
        this.position = position;
        this.password = "123";
        this.roles.add(Role.ROLE_USER);
    }

    public User(String firstName, String middleName, String lastName, String position,Integer id, String password, Role role, Role... roles) {
        this(firstName, lastName, middleName, position, id, password, true, EnumSet.of(role, roles));
    }

    public User(String firstName, String lastName, String middleName, String position, Integer id, String password, boolean enabled, Set<Role> roles) {
        super(firstName, lastName, middleName, id);
        this.position = position;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }

    public User() {

    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(position, user.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), position);
    }

    @Override
    public String toString() {
        return "User{" +
                "position='" + position + '\'' +
                ", enabled=" + enabled +
                ", dateRegistered=" + dateRegistered +
                ", roles=" + roles +
                ", id=" + super.getId() +
                '}';
    }
}
