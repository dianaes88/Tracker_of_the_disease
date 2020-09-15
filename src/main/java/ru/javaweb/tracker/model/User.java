package ru.javaweb.tracker.model;

import java.util.Objects;

public class User extends Person {
    private String position;

    public User(String firstName, String lastName, String middleName, String position) {
        super(firstName, lastName, middleName);
        this.position = position;
    }

    public User() {

    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
                super.toString() +
                ", position='" + position + '\'' +
                '}';
    }
}
