package ru.javaweb.tracker.model;

import java.util.Objects;

public class Patient extends Person {
    private Integer insuranceId;
    private final int userID;

    public Patient(String firstName, String middleName, String lastName, Integer insuranceId, int userID) {
        this(firstName, middleName, lastName, insuranceId, null, userID);
    }

    public Patient(String firstName, String middleName, String lastName, Integer insuranceId, Integer id, int userID) {
        super(firstName, middleName, lastName, id);
        this.insuranceId = insuranceId;
        this.userID = userID;
    }

    public Patient(int userID) {
        super();
        this.userID = userID;
    }

    public Integer getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Integer insuranceId) {
        this.insuranceId = insuranceId;
    }

    public int getUserID() {
        return userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        if (!super.equals(o)) return false;
        Patient patient = (Patient) o;
        return Objects.equals(insuranceId, patient.insuranceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), insuranceId);
    }

    @Override
    public String toString() {
        return "Patient{" +
                super.toString() +
                "insuranceId=" + insuranceId +
                "userId=" + userID +
                '}';
    }
}
